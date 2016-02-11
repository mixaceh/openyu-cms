package org.openyu.cms.site.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.site.dao.SiteDao;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.service.SiteService;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.service.impl.SiteServiceImpl;
import org.openyu.cms.site.vo.SiteCollector;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class SiteServiceImpl extends AppServiceSupporter implements SiteService
{
	private static transient final Logger log = LogManager.getLogger(SiteServiceImpl.class);

	protected transient SiteCollector siteCollector = SiteCollector.getInstance();

	public SiteServiceImpl()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//初始化cache,<id,Site>
		List<Site> sites = findSite();
		for (Site site : sites)
		{
			addSite(site);
		}
	}

	public SiteDao getSiteDao()
	{
		return (SiteDao) getOjDao();
	}

	@Autowired
	@Qualifier("siteDao")
	public void setSiteDao(SiteDao siteDao)
	{
		setOjDao(siteDao);
	}

	public List<Site> findSite()
	{
		List<SitePo> orig = getSiteDao().findSite();
		return ClassHelper.copyProperties(orig);
	}

	public List<Site> findSite(Locale locale)
	{
		List<SitePo> orig = getSiteDao().findSite(locale);
		return ClassHelper.copyProperties(orig);
	}

	public Site findFirstSite()
	{
		SitePo orig = getSiteDao().findFirstSite();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param site
	 * @return
	 */
	public List<Site> findSite(Locale locale, Site searcher)
	{
		List<SitePo> orig = getSiteDao().findSite(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param site
	 * @return
	 */
	public List<Site> findSite(Inquiry inquiry, Locale locale, Site searcher)
	{
		List<SitePo> orig = getSiteDao().findSite(inquiry, locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------
	/**
	 * 加入網站
	 * 
	 * @param site
	 * @return
	 */
	public synchronized Site addSite(Site site)
	{
		Site result = null;
		if (site != null)
		{
			result = (Site) beans.put(site.getId(), site);
		}
		return result;
	}

	/**
	 * 更新網站
	 * 
	 * @param origSite
	 * @param newSite
	 * @return
	 */
	public synchronized Site updateSite(Site origSite, Site newSite)
	{
		Site result = null;
		if (origSite != null)
		{
			String siteId = origSite.getId();
			boolean contains = beans.contains(siteId);
			if (contains)
			{
				result = removeSite(siteId);
			}
			addSite(newSite);
		}
		return result;
	}

	/**
	 * 移除網站
	 * 
	 * @param siteId
	 * @return
	 */
	public synchronized Site removeSite(String siteId)
	{
		Site result = null;
		if (siteId != null)
		{
			result = (Site) beans.remove(siteId);
		}
		return result;
	}

	/**
	 * 取得所有網站
	 * 
	 * @return
	 */
	public synchronized List<Site> getSites()
	{
		List<Site> result = new LinkedList<Site>();
		for (Object entry : beans.getValues())
		{
			if (entry instanceof Site)
			{
				result.add((Site) entry);
			}
		}
		return result;
	}

	// --------------------------------------------------
	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param siteId, DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public Site createSite(String siteId)
	{
		return siteCollector.createSite(siteId);
	}

}

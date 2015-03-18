package org.openyu.cms.adSpace.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.adSpace.dao.AdSpaceDao;
import org.openyu.cms.adSpace.po.AdSpacePo;
import org.openyu.cms.adSpace.service.AdSpaceService;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.vo.AdSpaceCollector;
import org.openyu.cms.site.service.SiteService;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.adSpace.vo.impl.AdSpaceImpl;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class AdSpaceServiceImpl extends AppServiceSupporter implements
		AdSpaceService {
	private static transient final Logger log = LogManager
			.getLogger(AdSpaceServiceImpl.class);

	/**
	 * 網站服務
	 */
	@Autowired
	@Qualifier("siteService")
	protected transient SiteService siteService;

	protected transient AdSpaceCollector adSpaceCollector = AdSpaceCollector
			.getInstance();

	public AdSpaceServiceImpl() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		// 初始化cache,<siteId,List<AdSpace>>
		List<Site> sites = siteService.getSites();
		for (Site site : sites) {
			List<AdSpace> adSpaces = findAdSpace(site.getSeq());
			beans.put(site.getId(), adSpaces);
		}
	}

	public AdSpaceDao getAdSpaceDao() {
		return (AdSpaceDao) getOjDao();
	}

	@Autowired
	@Qualifier("adSpaceDao")
	public void setAdSpaceDao(AdSpaceDao adSpaceDao) {
		setOjDao(adSpaceDao);
	}

	/**
	 * 查詢廣告版位
	 * 
	 * @return
	 */
	public List<AdSpace> findAdSpace() {
		List<AdSpacePo> orig = getAdSpaceDao().findAdSpace();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢廣告版位
	 * 
	 * @param locale
	 * @return
	 */
	public List<AdSpace> findAdSpace(Locale locale) {
		List<AdSpacePo> orig = getAdSpaceDao().findAdSpace(locale);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢廣告版位,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<AdSpace> findAdSpace(long siteSeq) {
		List<AdSpacePo> orig = getAdSpaceDao().findAdSpace(siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢廣告版位,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	public List<AdSpace> findAdSpace(Locale locale, long siteSeq) {
		List<AdSpacePo> orig = getAdSpaceDao().findAdSpace(locale, siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param adSpace
	 * @return
	 */
	public List<AdSpace> findAdSpace(Locale locale, AdSpace searcher) {
		List<AdSpacePo> orig = getAdSpaceDao().findAdSpace(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param adSpace
	 * @return
	 */
	public List<AdSpace> findAdSpace(Inquiry inquiry, Locale locale,
			AdSpace searcher) {
		List<AdSpacePo> orig = getAdSpaceDao().findAdSpace(inquiry, locale,
				searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<AdSpace> findAdSpace(Locale locale, long siteSeq,
			AdSpace searcher) {
		List<AdSpacePo> orig = getAdSpaceDao().findAdSpace(locale, siteSeq,
				searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<AdSpace> findAdSpace(Locale locale, Site siteSearcher,
			AdSpace searcher) {
		List<AdSpacePo> orig = getAdSpaceDao().findAdSpace(locale,
				siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<AdSpace> findAdSpace(Inquiry inquiry, Locale locale,
			long siteSeq, AdSpace searcher) {
		List<AdSpacePo> orig = getAdSpaceDao().findAdSpace(inquiry, locale,
				siteSeq, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<AdSpace> findAdSpace(Inquiry inquiry, Locale locale,
			Site siteSearcher, AdSpace searcher) {
		List<AdSpacePo> orig = getAdSpaceDao().findAdSpace(inquiry, locale,
				siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 加入廣告版位,網站別
	 * 
	 * @param siteId
	 * @param adSpace
	 * @return
	 */
	public synchronized AdSpace addAdSpace(String siteId, AdSpace adSpace) {
		AdSpace result = null;
		if (siteId != null && adSpace != null) {
			List<AdSpace> list = getAdSpaces(siteId);
			int index = list.indexOf(adSpace);
			if (index > -1) {
				list.remove(index);
			}
			list.add(adSpace);
		}
		return result;
	}

	/**
	 * 移除廣告版位,網站別
	 * 
	 * @param siteId
	 * @param adSpaceId
	 * @return
	 */
	public synchronized AdSpace removeAdSpace(String siteId, AdSpace adSpace) {
		AdSpace result = null;
		if (siteId != null && adSpace != null) {
			List<AdSpace> list = getAdSpaces(siteId);
			int index = list.indexOf(adSpace);
			if (index > -1) {
				list.remove(index);
			}
		}
		return result;
	}

	/**
	 * 取得所有廣告版位,網站別
	 * 
	 * @return
	 */
	public synchronized List<AdSpace> getAdSpaces(String siteId) {
		@SuppressWarnings("unchecked")
		List<AdSpace> result = (List<AdSpace>) beans.get(siteId);
		if (result == null) {
			result = new LinkedList<AdSpace>();
			beans.put(siteId, result);
		}
		return result;
	}

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param adSpaceId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public AdSpace createAdSpace(String adSpaceId) {
		return adSpaceCollector.createAdSpace(adSpaceId);
	}

}

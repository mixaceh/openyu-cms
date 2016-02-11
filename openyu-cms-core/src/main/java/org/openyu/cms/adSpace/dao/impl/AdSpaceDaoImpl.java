package org.openyu.cms.adSpace.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.adSpace.dao.AdSpaceDao;
import org.openyu.cms.adSpace.po.AdSpacePo;
import org.openyu.cms.adSpace.po.impl.AdSpacePoImpl;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class AdSpaceDaoImpl extends AppDaoSupporter implements AdSpaceDao
{
	private static transient final Logger log = LogManager.getLogger(AdSpaceDaoImpl.class);

	private static final String AD_SPACE_PO_NAME = AdSpacePoImpl.class.getName();

	/**
	 * 查詢廣告版位
	 * 
	 * @return
	 */
	public List<AdSpacePo> findAdSpace()
	{
		return findAdSpace(null);
	}

	/**
	 * 查詢廣告版位
	 * 
	 * @param locale
	 * @return
	 */
	public List<AdSpacePo> findAdSpace(Locale locale)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(AD_SPACE_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		return findByHql(null, locale, hql, params);
	}

	// --------------------------------------------------

	/**
	 * 查詢廣告版位,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<AdSpacePo> findAdSpace(long siteSeq)
	{
		return findAdSpace(null, siteSeq);
	}

	/**
	 * 查詢廣告版位,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @return
	 */
	public List<AdSpacePo> findAdSpace(Locale locale, long siteSeq)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(AD_SPACE_PO_NAME + " ");
		hql.append("where 1=1 ");
		hql.append("and site.seq=:siteSeq ");
		params.put("siteSeq", siteSeq);
		//
		return findByHql(null, locale, hql, params);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<AdSpacePo> findAdSpace(Locale locale, AdSpace searcher)
	{
		return findAdSpace(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<AdSpacePo> findAdSpace(Inquiry inquiry, Locale locale, AdSpace searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(AD_SPACE_PO_NAME + " ");
		hql.append("where 1=1 ");

		if (searcher != null)
		{
			//seq
			if (searcher.getSeq() > 0)
			{
				hql.append("and seq=:seq ");
				params.put("seq", searcher.getSeq());
			}

			//id
			if (StringHelper.notBlank(searcher.getId()))
			{
				hql.append("and lower(id) like lower(:id) ");
				params.put("id", "%" + searcher.getId() + "%");
			}

			//name
			if (StringHelper.notBlank(searcher.getName()))
			{
				hql.append("and lower(names) like lower(:name)");
				params.put("name", "%" + searcher.getName() + "%");
			}
		}
		//
		return findByHql(inquiry, locale, hql, params);
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
	public List<AdSpacePo> findAdSpace(Locale locale, long siteSeq, AdSpace searcher)
	{
		return findAdSpace(locale, new SiteImpl(siteSeq), searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<AdSpacePo> findAdSpace(Locale locale, Site siteSearcher, AdSpace searcher)
	{
		return findAdSpace(null, locale, siteSearcher, searcher);
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
	public List<AdSpacePo> findAdSpace(Inquiry inquiry, Locale locale, long siteSeq,
										AdSpace searcher)
	{
		return findAdSpace(inquiry, locale, new SiteImpl(siteSeq), searcher);
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
	public List<AdSpacePo> findAdSpace(Inquiry inquiry, Locale locale, Site siteSearcher,
										AdSpace searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(AD_SPACE_PO_NAME + " ");
		hql.append("where 1=1 ");

		//siteSearcher
		if (siteSearcher != null)
		{
			//seq
			if (siteSearcher.getSeq() > 0)
			{
				hql.append("and site.seq=:siteSeq ");
				params.put("siteSeq", siteSearcher.getSeq());
			}

			//id
			if (StringHelper.notBlank(siteSearcher.getId()))
			{
				hql.append("and lower(site.id) like lower(:siteId) ");
				params.put("siteId", "%" + siteSearcher.getId() + "%");
			}

			//name
			if (StringHelper.notBlank(siteSearcher.getName()))
			{
				hql.append("and lower(site.names) like lower(:siteName)");
				params.put("siteName", "%" + siteSearcher.getName() + "%");
			}
		}

		//searcher
		if (searcher != null)
		{
			//seq
			if (searcher.getSeq() > 0)
			{
				hql.append("and seq=:seq ");
				params.put("seq", searcher.getSeq());
			}

			//id
			if (StringHelper.notBlank(searcher.getId()))
			{
				hql.append("and lower(id) like lower(:id) ");
				params.put("id", "%" + searcher.getId() + "%");
			}

			//valid
			WhetherOption validOption = searcher.getValidOption();
			if (validOption != null)
			{
				WhetherType whetherType = validOption.getId();
				switch (whetherType)
				{
					case TRUE:
						hql.append("and valid=true ");
						break;
					case FALSE:
						hql.append("and (valid=false ");
						hql.append("or valid is null) ");
						break;
					default:
						break;
				}
			}
			
			//name
			if (StringHelper.notBlank(searcher.getName()))
			{
				hql.append("and lower(names) like lower(:name)");
				params.put("name", "%" + searcher.getName() + "%");
			}
		}
		//
		return findByHql(inquiry, locale, hql, params);
	}
}

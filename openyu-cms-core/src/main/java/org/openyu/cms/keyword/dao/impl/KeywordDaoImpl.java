package org.openyu.cms.keyword.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.keyword.dao.KeywordDao;
import org.openyu.cms.keyword.po.KeywordPo;
import org.openyu.cms.keyword.po.impl.KeywordPoImpl;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class KeywordDaoImpl extends AppDaoSupporter implements KeywordDao
{

	private static transient final Logger log = LogManager.getLogger(KeywordDaoImpl.class);

	private static final String KEYWORD_PO_NAME = KeywordPoImpl.class.getName();

	public List<KeywordPo> findKeyword()
	{
		return findKeyword(null);
	}

	public List<KeywordPo> findKeyword(Locale locale)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		//
		hql.append("from ");
		hql.append(KEYWORD_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		if (locale != null)
		{
			hql.append("and locale=:locale ");
			params.put("locale", locale);
		}
		else
		{
			hql.append("order by seq ");
		}

		return findByHql(null, hql.toString(), params);
	}

	// --------------------------------------------------

	/**
	 * 查詢關鍵字,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<KeywordPo> findKeyword(long siteSeq)
	{
		return findKeyword(null, siteSeq);
	}

	/**
	 * 查詢關鍵字,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @return
	 */
	public List<KeywordPo> findKeyword(Locale locale, long siteSeq)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(KEYWORD_PO_NAME + " ");
		hql.append("where 1=1 ");
		hql.append("and site.seq=:siteSeq ");
		params.put("siteSeq", siteSeq);
		//
		return findByHql(null, locale, hql, params);
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
	public List<KeywordPo> findKeyword(Locale locale, long siteSeq, Keyword searcher)
	{
		return findKeyword(locale, new SiteImpl(siteSeq), searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<KeywordPo> findKeyword(Locale locale, Site siteSearcher, Keyword searcher)
	{
		return findKeyword(null, locale, siteSearcher, searcher);
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
	public List<KeywordPo> findKeyword(Inquiry inquiry, Locale locale, long siteSeq,
										Keyword searcher)
	{
		return findKeyword(inquiry, locale, new SiteImpl(siteSeq), searcher);
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
	public List<KeywordPo> findKeyword(Inquiry inquiry, Locale locale, Site siteSearcher,
										Keyword searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(KEYWORD_PO_NAME + " ");
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
				hql.append("and site.id=:siteId ");
				params.put("siteId", siteSearcher.getId());
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

			//locale
			if (searcher.getLocale() != null)
			{
				hql.append("and locale=:locale ");
				params.put("locale", locale);
			}
		}

		return findByHql(null, hql.toString(), params);
	}
}

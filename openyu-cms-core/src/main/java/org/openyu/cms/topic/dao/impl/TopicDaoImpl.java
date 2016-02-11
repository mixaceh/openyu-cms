package org.openyu.cms.topic.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.topic.dao.TopicDao;
import org.openyu.cms.topic.po.TopicPo;
import org.openyu.cms.topic.po.impl.TopicPoImpl;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class TopicDaoImpl extends AppDaoSupporter implements TopicDao
{
	private static transient final Logger log = LogManager.getLogger(TopicDaoImpl.class);

	private static final String TOPIC_PO_NAME = TopicPoImpl.class.getName();

	/**
	 * 查詢專題版位
	 * 
	 * @return
	 */
	public List<TopicPo> findTopic()
	{
		return findTopic(null);
	}

	/**
	 * 查詢專題版位
	 * 
	 * @param locale
	 * @return
	 */
	public List<TopicPo> findTopic(Locale locale)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(TOPIC_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		return findByHql(null, locale, hql, params);
	}

	// --------------------------------------------------

	/**
	 * 查詢專題版位,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<TopicPo> findTopic(long siteSeq)
	{
		return findTopic(null, siteSeq);
	}

	/**
	 * 查詢專題版位,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @return
	 */
	public List<TopicPo> findTopic(Locale locale, long siteSeq)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(TOPIC_PO_NAME + " ");
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
	public List<TopicPo> findTopic(Locale locale, Topic searcher)
	{
		return findTopic(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<TopicPo> findTopic(Inquiry inquiry, Locale locale, Topic searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(TOPIC_PO_NAME + " ");
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
	public List<TopicPo> findTopic(Locale locale, long siteSeq, Topic searcher)
	{
		return findTopic(locale, new SiteImpl(siteSeq), searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<TopicPo> findTopic(Locale locale, Site siteSearcher, Topic searcher)
	{
		return findTopic(null, locale, siteSearcher, searcher);
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
	public List<TopicPo> findTopic(Inquiry inquiry, Locale locale, long siteSeq, Topic searcher)
	{
		return findTopic(inquiry, locale, new SiteImpl(siteSeq), searcher);
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
	public List<TopicPo> findTopic(Inquiry inquiry, Locale locale, Site siteSearcher, Topic searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(TOPIC_PO_NAME + " ");
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

			//recommend
			WhetherOption recommendOption = searcher.getRecommendOption();
			if (recommendOption != null)
			{
				WhetherType whetherType = recommendOption.getId();
				switch (whetherType)
				{
					case TRUE:
						hql.append("and recommend=true ");
						break;
					case FALSE:
						hql.append("and (recommend=false ");
						hql.append("or recommend is null) ");
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

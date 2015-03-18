package org.openyu.cms.vote.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.vote.dao.VoteDao;
import org.openyu.cms.vote.po.VotePo;
import org.openyu.cms.vote.po.impl.VotePoImpl;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class VoteDaoImpl extends AppDaoSupporter implements VoteDao
{
	private static transient final Logger log = LogManager.getLogger(VoteDaoImpl.class);

	private static final String MODULE_PO_NAME = VotePoImpl.class.getName();

	/**
	 * 查詢是否有效投票
	 * 
	 * @param valid
	 * @return
	 */
	public List<VotePo> findVote(boolean valid)
	{
		return findVote(null, valid);
	}

	/**
	 * 查詢是否有效投票
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	public List<VotePo> findVote(Locale locale, boolean valid)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(MODULE_PO_NAME + " ");
		hql.append("where 1=1 ");

		//valid
		if (valid)
		{
			hql.append("and valid=:valid ");
		}
		else
		{
			hql.append("and (valid=:valid ");
			hql.append("or valid is null) ");
		}
		params.put("valid", valid);
		//
		return findByHql(null, locale, hql, params);
	}

	/**
	 * 查詢預設投票
	 * 
	 * @return
	 */
	public VotePo findDefaultVote()
	{
		StringBuilder hql = new StringBuilder();
		//
		hql.append("from ");
		hql.append(MODULE_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		hql.append("and defaultz=true ");

		return findUniqueByHql(hql);
	}

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<VotePo> findVote(Locale locale, Vote searcher)
	{
		return findVote(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<VotePo> findVote(Inquiry inquiry, Locale locale, Vote searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(MODULE_PO_NAME + " ");
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

	//--------------------------------------------------------------------
	/**
	 * 查詢條件,網站別
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<VotePo> findVote(Inquiry inquiry, Locale locale, long siteSeq, Vote searcher)
	{
		return findVote(inquiry, locale, new SiteImpl(siteSeq), searcher);
	}

	/**
	 * 查詢條件,網站別
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<VotePo> findVote(Inquiry inquiry, Locale locale, Site siteSearcher, Vote searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(MODULE_PO_NAME + " ");
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

package org.openyu.cms.friendType.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.friendType.dao.FriendTypeDao;
import org.openyu.cms.friendType.po.FriendTypePo;
import org.openyu.cms.friendType.po.impl.FriendTypePoImpl;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class FriendTypeDaoImpl extends AppDaoSupporter implements FriendTypeDao
{
	private static transient final Logger log = LogManager.getLogger(FriendTypeDaoImpl.class);

	private static final String FRIEND_TTYPE_PO_NAME = FriendTypePoImpl.class.getName();

	/**
	 * 查詢友情類型
	 * 
	 * @return
	 */
	public List<FriendTypePo> findFriendType()
	{
		return findFriendType(null);
	}

	/**
	 * 查詢友情類型
	 * 
	 * @param locale
	 * @return
	 */
	public List<FriendTypePo> findFriendType(Locale locale)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FRIEND_TTYPE_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		return findByHql(null, locale, hql, params);
	}

	// --------------------------------------------------

	/**
	 * 查詢友情類型,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<FriendTypePo> findFriendType(long siteSeq)
	{
		return findFriendType(null, siteSeq);
	}

	/**
	 * 查詢友情類型,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @return
	 */
	public List<FriendTypePo> findFriendType(Locale locale, long siteSeq)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FRIEND_TTYPE_PO_NAME + " ");
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
	public List<FriendTypePo> findFriendType(Locale locale, FriendType searcher)
	{
		return findFriendType(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<FriendTypePo> findFriendType(Inquiry inquiry, Locale locale, FriendType searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FRIEND_TTYPE_PO_NAME + " ");
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
				if (searcher.isOnly())//唯一
				{
					hql.append("and id=:id ");
					params.put("id", searcher.getId());
				}
				else
				{
					hql.append("and lower(id) like lower(:id) ");
					params.put("id", "%" + searcher.getId() + "%");
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

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<FriendTypePo> findFriendType(Locale locale, long siteSeq, FriendType searcher)
	{
		return findFriendType(locale, new SiteImpl(siteSeq), searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<FriendTypePo> findFriendType(Locale locale, Site siteSearcher, FriendType searcher)
	{
		return findFriendType(null, locale, siteSearcher, searcher);
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
	public List<FriendTypePo> findFriendType(Inquiry inquiry, Locale locale, long siteSeq,
												FriendType searcher)
	{
		return findFriendType(inquiry, locale, new SiteImpl(siteSeq), searcher);
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
	public List<FriendTypePo> findFriendType(Inquiry inquiry, Locale locale, Site siteSearcher,
												FriendType searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FRIEND_TTYPE_PO_NAME + " ");
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

			//id
			if (StringHelper.notBlank(searcher.getId()))
			{
				if (searcher.isOnly())
				{
					hql.append("and id=:id ");//唯一
					params.put("id", searcher.getId());
				}
				else
				{
					hql.append("and lower(id) like lower(:id) ");
					params.put("id", "%" + searcher.getId() + "%");
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

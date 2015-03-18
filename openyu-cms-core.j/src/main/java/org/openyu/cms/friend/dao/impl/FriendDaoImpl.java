package org.openyu.cms.friend.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.friend.dao.FriendDao;
import org.openyu.cms.friend.po.FriendPo;
import org.openyu.cms.friend.po.impl.FriendPoImpl;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class FriendDaoImpl extends AppDaoSupporter implements FriendDao
{
	private static transient final Logger log = LogManager.getLogger(FriendDaoImpl.class);

	private static final String FRIEND_PO_NAME = FriendPoImpl.class.getName();

	/**
	 * 查詢友情連結
	 * 
	 * @return
	 */
	public List<FriendPo> findFriend()
	{
		return findFriend(null);
	}

	/**
	 * 查詢友情連結
	 * 
	 * @param locale
	 * @return
	 */
	public List<FriendPo> findFriend(Locale locale)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FRIEND_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		return findByHql(null, locale, hql, params);
	}

	// --------------------------------------------------

	/**
	 * 查詢友情連結,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<FriendPo> findFriend(long siteSeq)
	{
		return findFriend(null, siteSeq);
	}

	/**
	 * 查詢友情連結,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @return
	 */
	public List<FriendPo> findFriend(Locale locale, long siteSeq)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FRIEND_PO_NAME + " ");
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
	public List<FriendPo> findFriend(Locale locale, Friend searcher)
	{
		return findFriend(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<FriendPo> findFriend(Inquiry inquiry, Locale locale, Friend searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FRIEND_PO_NAME + " ");
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

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<FriendPo> findFriend(Locale locale, long siteSeq, Friend searcher)
	{
		return findFriend(locale, new SiteImpl(siteSeq), searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<FriendPo> findFriend(Locale locale, Site siteSearcher, Friend searcher)
	{
		return findFriend(null, locale, siteSearcher, searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param friendTypeSearcher
	 * @param searcher
	 * @return
	 */
	public List<FriendPo> findFriend(Locale locale, long siteSeq, FriendType friendTypeSearcher,
										Friend searcher)
	{
		return findFriend(locale, new SiteImpl(siteSeq), friendTypeSearcher, searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param friendTypeSearcher
	 * @param searcher
	 * @return
	 */
	public List<FriendPo> findFriend(Locale locale, Site siteSearcher,
										FriendType friendTypeSearcher, Friend searcher)
	{
		return findFriend(null, locale, siteSearcher, friendTypeSearcher, searcher);
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
	public List<FriendPo> findFriend(Inquiry inquiry, Locale locale, long siteSeq, Friend searcher)
	{
		return findFriend(inquiry, locale, new SiteImpl(siteSeq), searcher);
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
	public List<FriendPo> findFriend(Inquiry inquiry, Locale locale, Site siteSearcher,
										Friend searcher)
	{
		return findFriend(inquiry, locale, siteSearcher, null, searcher);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @param friendTypeSearcher
	 * @return
	 */
	public List<FriendPo> findFriend(Inquiry inquiry, Locale locale, long siteSeq,
										FriendType friendTypeSearcher, Friend searcher)
	{
		return findFriend(inquiry, locale, new SiteImpl(siteSeq), friendTypeSearcher, searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @param friendTypeSearcher
	 * @return
	 */
	public List<FriendPo> findFriend(Inquiry inquiry, Locale locale, Site siteSearcher,
										FriendType friendTypeSearcher, Friend searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FRIEND_PO_NAME + " ");
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

		//friendTypeSearcher
		if (friendTypeSearcher != null)
		{
			//seq
			if (friendTypeSearcher.getSeq() > 0)
			{
				hql.append("and friendType.seq=:friendTypeSeq ");
				params.put("friendTypeSeq", friendTypeSearcher.getSeq());
			}

			//id
			if (StringHelper.notBlank(friendTypeSearcher.getId()))
			{
				hql.append("and friendType.id=:friendTypeId ");
				params.put("friendTypeId", friendTypeSearcher.getId());
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

package org.openyu.cms.guestbook.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.guestbook.dao.GuestbookDao;
import org.openyu.cms.guestbook.po.GuestbookPo;
import org.openyu.cms.guestbook.po.impl.GuestbookPoImpl;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class GuestbookDaoImpl extends AppDaoSupporter implements GuestbookDao
{
	private static transient final Logger log = LogManager.getLogger(GuestbookDaoImpl.class);

	private static final String FRIEND_PO_NAME = GuestbookPoImpl.class.getName();

	/**
	 * 查詢留言
	 * 
	 * @return
	 */
	public List<GuestbookPo> findGuestbook()
	{
		return findGuestbook(null);
	}

	/**
	 * 查詢留言
	 * 
	 * @param locale
	 * @return
	 */
	public List<GuestbookPo> findGuestbook(Locale locale)
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
	 * 查詢留言,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<GuestbookPo> findGuestbook(long siteSeq)
	{
		return findGuestbook(null, siteSeq);
	}

	/**
	 * 查詢留言,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @return
	 */
	public List<GuestbookPo> findGuestbook(Locale locale, long siteSeq)
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
	public List<GuestbookPo> findGuestbook(Locale locale, Guestbook searcher)
	{
		return findGuestbook(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<GuestbookPo> findGuestbook(Inquiry inquiry, Locale locale, Guestbook searcher)
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
//			if (StringHelper.notBlank(searcher.getName()))
//			{
//				hql.append("and lower(names) like lower(:name)");
//				params.put("name", "%" + searcher.getName() + "%");
//			}
			//title
			if (StringHelper.notBlank(searcher.getTitle()))
			{
				hql.append("and lower(titles) like lower(:title)");
				params.put("title", "%" + searcher.getTitle() + "%");
			}
			//content
			if (StringHelper.notBlank(searcher.getContent()))
			{
				hql.append("and lower(contents) like lower(:content)");
				params.put("content", "%" + searcher.getContent() + "%");
			}
			//reply
			if (StringHelper.notBlank(searcher.getReply()))
			{
				hql.append("and lower(replys) like lower(:reply)");
				params.put("reply", "%" + searcher.getReply() + "%");
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
	public List<GuestbookPo> findGuestbook(Locale locale, long siteSeq, Guestbook searcher)
	{
		return findGuestbook(locale, new SiteImpl(siteSeq), searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<GuestbookPo> findGuestbook(Locale locale, Site siteSearcher, Guestbook searcher)
	{
		return findGuestbook(null, locale, siteSearcher, searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param guestbookTypeSearcher
	 * @param searcher
	 * @return
	 */
	public List<GuestbookPo> findGuestbook(Locale locale, long siteSeq, GuestbookType guestbookTypeSearcher,
										Guestbook searcher)
	{
		return findGuestbook(locale, new SiteImpl(siteSeq), guestbookTypeSearcher, searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param guestbookTypeSearcher
	 * @param searcher
	 * @return
	 */
	public List<GuestbookPo> findGuestbook(Locale locale, Site siteSearcher,
										GuestbookType guestbookTypeSearcher, Guestbook searcher)
	{
		return findGuestbook(null, locale, siteSearcher, guestbookTypeSearcher, searcher);
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
	public List<GuestbookPo> findGuestbook(Inquiry inquiry, Locale locale, long siteSeq, Guestbook searcher)
	{
		return findGuestbook(inquiry, locale, new SiteImpl(siteSeq), searcher);
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
	public List<GuestbookPo> findGuestbook(Inquiry inquiry, Locale locale, Site siteSearcher,
										Guestbook searcher)
	{
		return findGuestbook(inquiry, locale, siteSearcher, null, searcher);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @param guestbookTypeSearcher
	 * @return
	 */
	public List<GuestbookPo> findGuestbook(Inquiry inquiry, Locale locale, long siteSeq,
										GuestbookType guestbookTypeSearcher, Guestbook searcher)
	{
		return findGuestbook(inquiry, locale, new SiteImpl(siteSeq), guestbookTypeSearcher, searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @param guestbookTypeSearcher
	 * @return
	 */
	public List<GuestbookPo> findGuestbook(Inquiry inquiry, Locale locale, Site siteSearcher,
										GuestbookType guestbookTypeSearcher, Guestbook searcher)
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

		//guestbookTypeSearcher
		if (guestbookTypeSearcher != null)
		{
			//seq
			if (guestbookTypeSearcher.getSeq() > 0)
			{
				hql.append("and guestbookType.seq=:guestbookTypeSeq ");
				params.put("guestbookTypeSeq", guestbookTypeSearcher.getSeq());
			}

			//id
			if (StringHelper.notBlank(guestbookTypeSearcher.getId()))
			{
				hql.append("and guestbookType.id=:guestbookTypeId ");
				params.put("guestbookTypeId", guestbookTypeSearcher.getId());
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
//			WhetherOption validOption = searcher.getValidOption();
//			if (validOption != null)
//			{
//				WhetherType whetherType = validOption.getId();
//				switch (whetherType)
//				{
//					case TRUE:
//						hql.append("and valid=true ");
//						break;
//					case FALSE:
//						hql.append("and (valid=false ");
//						hql.append("or valid is null) ");
//						break;
//					default:
//						break;
//				}
//			}

			//title
			if (StringHelper.notBlank(searcher.getTitle()))
			{
				hql.append("and lower(titles) like lower(:title)");
				params.put("title", "%" + searcher.getTitle() + "%");
			}
			//content
			if (StringHelper.notBlank(searcher.getContent()))
			{
				hql.append("and lower(contents) like lower(:content)");
				params.put("content", "%" + searcher.getContent() + "%");
			}
			//reply
			if (StringHelper.notBlank(searcher.getReply()))
			{
				hql.append("and lower(replys) like lower(:reply)");
				params.put("reply", "%" + searcher.getReply() + "%");
			}
			
		}

		//
		return findByHql(inquiry, locale, hql, params);
	}
}

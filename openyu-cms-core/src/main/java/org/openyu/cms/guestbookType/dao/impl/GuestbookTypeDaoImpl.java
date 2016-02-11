package org.openyu.cms.guestbookType.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.guestbookType.dao.GuestbookTypeDao;
import org.openyu.cms.guestbookType.po.GuestbookTypePo;
import org.openyu.cms.guestbookType.po.impl.GuestbookTypePoImpl;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class GuestbookTypeDaoImpl extends AppDaoSupporter implements GuestbookTypeDao
{
	private static transient final Logger log = LogManager.getLogger(GuestbookTypeDaoImpl.class);

	private static final String GUEST_BOOK_TYPE_PO_NAME = GuestbookTypePoImpl.class.getName();

	/**
	 * 查詢留言類型
	 * 
	 * @return
	 */
	public List<GuestbookTypePo> findGuestbookType()
	{
		return findGuestbookType(null);
	}

	/**
	 * 查詢留言類型
	 * 
	 * @param locale
	 * @return
	 */
	public List<GuestbookTypePo> findGuestbookType(Locale locale)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(GUEST_BOOK_TYPE_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		return findByHql(null, locale, hql, params);
	}

	// --------------------------------------------------

	/**
	 * 查詢留言類型,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<GuestbookTypePo> findGuestbookType(long siteSeq)
	{
		return findGuestbookType(null, siteSeq);
	}

	/**
	 * 查詢留言類型,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @return
	 */
	public List<GuestbookTypePo> findGuestbookType(Locale locale, long siteSeq)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(GUEST_BOOK_TYPE_PO_NAME + " ");
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
	public List<GuestbookTypePo> findGuestbookType(Locale locale, GuestbookType searcher)
	{
		return findGuestbookType(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<GuestbookTypePo> findGuestbookType(Inquiry inquiry, Locale locale,
													GuestbookType searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(GUEST_BOOK_TYPE_PO_NAME + " ");
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
	public List<GuestbookTypePo> findGuestbookType(Locale locale, long siteSeq,
													GuestbookType searcher)
	{
		return findGuestbookType(locale, new SiteImpl(siteSeq), searcher);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<GuestbookTypePo> findGuestbookType(Locale locale, Site siteSearcher,
													GuestbookType searcher)
	{
		return findGuestbookType(null, locale, siteSearcher, searcher);
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
	public List<GuestbookTypePo> findGuestbookType(Inquiry inquiry, Locale locale, long siteSeq,
													GuestbookType searcher)
	{
		return findGuestbookType(inquiry, locale, new SiteImpl(siteSeq), searcher);
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
	public List<GuestbookTypePo> findGuestbookType(Inquiry inquiry, Locale locale,
													Site siteSearcher, GuestbookType searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(GUEST_BOOK_TYPE_PO_NAME + " ");
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

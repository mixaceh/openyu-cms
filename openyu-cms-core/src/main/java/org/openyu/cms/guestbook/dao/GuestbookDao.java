package org.openyu.cms.guestbook.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.guestbook.po.GuestbookPo;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface GuestbookDao extends AppDao
{

	/**
	 * 查詢留言
	 * 
	 * @return
	 */
	List<GuestbookPo> findGuestbook();

	/**
	 * 查詢留言
	 * 
	 * @param locale
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢留言,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<GuestbookPo> findGuestbook(long siteSeq);

	/**
	 * 查詢留言,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Locale locale, Guestbook searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Inquiry inquiry, Locale locale, Guestbook searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Locale locale, long siteSeq, Guestbook searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Locale locale, Site siteSearcher, Guestbook searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param guestbookTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Locale locale, long siteSeq, GuestbookType guestbookTypeSearcher,
								Guestbook searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param guestbookTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Locale locale, Site siteSearcher, GuestbookType guestbookTypeSearcher,
								Guestbook searcher);

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
	List<GuestbookPo> findGuestbook(Inquiry inquiry, Locale locale, long siteSeq, Guestbook searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Inquiry inquiry, Locale locale, Site siteSearcher, Guestbook searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param guestbookTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Inquiry inquiry, Locale locale, long siteSeq,
								GuestbookType guestbookTypeSearcher, Guestbook searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param guestbookTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<GuestbookPo> findGuestbook(Inquiry inquiry, Locale locale, Site siteSearcher,
								GuestbookType guestbookTypeSearcher, Guestbook searcher);
}
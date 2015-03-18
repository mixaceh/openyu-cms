package org.openyu.cms.guestbook.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.service.AppService;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 留言服務
 */
public interface GuestbookService extends AppService {

	/**
	 * 查詢留言
	 * 
	 * @param valid
	 * @return
	 */
	List<Guestbook> findGuestbook();

	/**
	 * 查詢留言
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<Guestbook> findGuestbook(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢留言,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<Guestbook> findGuestbook(long siteSeq);

	/**
	 * 查詢留言,,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<Guestbook> findGuestbook(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Guestbook> findGuestbook(Locale locale, Guestbook searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Guestbook> findGuestbook(Inquiry inquiry, Locale locale,
			Guestbook searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<Guestbook> findGuestbook(Locale locale, long siteSeq,
			Guestbook searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<Guestbook> findGuestbook(Locale locale, Site siteSearcher,
			Guestbook searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param guestbookTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<Guestbook> findGuestbook(Locale locale, long siteSeq,
			GuestbookType guestbookTypeSearcher, Guestbook searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param guestbookTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<Guestbook> findGuestbook(Locale locale, Site siteSearcher,
			GuestbookType guestbookTypeSearcher, Guestbook searcher);

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
	List<Guestbook> findGuestbook(Inquiry inquiry, Locale locale, long siteSeq,
			Guestbook searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<Guestbook> findGuestbook(Inquiry inquiry, Locale locale,
			Site siteSearcher, Guestbook searcher);

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
	List<Guestbook> findGuestbook(Inquiry inquiry, Locale locale, long siteSeq,
			GuestbookType guestbookTypeSearcher, Guestbook searcher);

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
	List<Guestbook> findGuestbook(Inquiry inquiry, Locale locale,
			Site siteSearcher, GuestbookType guestbookTypeSearcher,
			Guestbook searcher);

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param guestbookId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	Guestbook createGuestbook(String guestbookId);

}
package org.openyu.cms.guestbookType.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.guestbookType.po.GuestbookTypePo;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface GuestbookTypeDao extends AppDao
{

	/**
	 * 查詢留言類型
	 * 
	 * @return
	 */
	List<GuestbookTypePo> findGuestbookType();

	/**
	 * 查詢留言類型
	 * 
	 * @param locale
	 * @return
	 */
	List<GuestbookTypePo> findGuestbookType(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢留言類型,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<GuestbookTypePo> findGuestbookType(long siteSeq);

	/**
	 * 查詢留言類型,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<GuestbookTypePo> findGuestbookType(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<GuestbookTypePo> findGuestbookType(Locale locale, GuestbookType searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<GuestbookTypePo> findGuestbookType(Inquiry inquiry, Locale locale, GuestbookType searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<GuestbookTypePo> findGuestbookType(Locale locale, long siteSeq, GuestbookType searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<GuestbookTypePo> findGuestbookType(Locale locale, Site siteSearcher, GuestbookType searcher);

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
	List<GuestbookTypePo> findGuestbookType(Inquiry inquiry, Locale locale, long siteSeq,
										GuestbookType searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<GuestbookTypePo> findGuestbookType(Inquiry inquiry, Locale locale, Site siteSearchers,
										GuestbookType searcher);

}
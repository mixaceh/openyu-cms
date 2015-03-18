package org.openyu.cms.guestbookType.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.openyu.cms.app.service.AppService;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 留言類型服務
 */
public interface GuestbookTypeService extends AppService {

	/**
	 * 查詢留言類型
	 * 
	 * @param valid
	 * @return
	 */
	List<GuestbookType> findGuestbookType();

	/**
	 * 查詢留言類型
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<GuestbookType> findGuestbookType(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢留言類型,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<GuestbookType> findGuestbookType(long siteSeq);

	/**
	 * 查詢留言類型,,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<GuestbookType> findGuestbookType(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<GuestbookType> findGuestbookType(Locale locale, GuestbookType searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<GuestbookType> findGuestbookType(Inquiry inquiry, Locale locale,
			GuestbookType searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<GuestbookType> findGuestbookType(Locale locale, long siteSeq,
			GuestbookType searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<GuestbookType> findGuestbookType(Locale locale, Site siteSearcher,
			GuestbookType searcher);

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
	List<GuestbookType> findGuestbookType(Inquiry inquiry, Locale locale,
			long siteSeq, GuestbookType searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<GuestbookType> findGuestbookType(Inquiry inquiry, Locale locale,
			Site siteSearcher, GuestbookType searcher);

	// --------------------------------------------------
	/**
	 * 加入留言類型,網站別
	 * 
	 * @param guestbookType
	 * @return
	 */
	GuestbookType addGuestbookType(String siteId, GuestbookType guestbookType);

	/**
	 * 移除留言類型,網站別
	 * 
	 * @param guestbookTypeId
	 * @return
	 */
	GuestbookType removeGuestbookType(String siteId, GuestbookType guestbookType);

	/**
	 * 取得所有留言類型,網站別
	 * 
	 * @return
	 */
	List<GuestbookType> getGuestbookTypes(String siteId);

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param guestbookTypeId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	GuestbookType createGuestbookType(String guestbookTypeId);

}
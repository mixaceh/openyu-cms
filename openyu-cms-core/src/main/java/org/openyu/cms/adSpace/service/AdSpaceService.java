package org.openyu.cms.adSpace.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.openyu.cms.app.service.AppService;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 廣告版位服務
 */
public interface AdSpaceService extends AppService {

	/**
	 * 查詢廣告版位
	 * 
	 * @param valid
	 * @return
	 */
	List<AdSpace> findAdSpace();

	/**
	 * 查詢廣告版位
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<AdSpace> findAdSpace(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢廣告版位,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<AdSpace> findAdSpace(long siteSeq);

	/**
	 * 查詢廣告版位,,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<AdSpace> findAdSpace(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<AdSpace> findAdSpace(Locale locale, AdSpace searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<AdSpace> findAdSpace(Inquiry inquiry, Locale locale, AdSpace searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<AdSpace> findAdSpace(Locale locale, long siteSeq, AdSpace searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<AdSpace> findAdSpace(Locale locale, Site siteSearcher, AdSpace searcher);

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
	List<AdSpace> findAdSpace(Inquiry inquiry, Locale locale, long siteSeq,
			AdSpace searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<AdSpace> findAdSpace(Inquiry inquiry, Locale locale,
			Site siteSearcher, AdSpace searcher);

	// --------------------------------------------------
	/**
	 * 加入廣告版位,網站別
	 * 
	 * @param adSpace
	 * @return
	 */
	public AdSpace addAdSpace(String siteId, AdSpace adSpace);

	/**
	 * 移除廣告版位,網站別
	 * 
	 * @param adSpaceId
	 * @return
	 */
	AdSpace removeAdSpace(String siteId, AdSpace adSpace);

	/**
	 * 取得所有廣告版位,網站別
	 * 
	 * @return
	 */
	List<AdSpace> getAdSpaces(String siteId);

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param adSpaceId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	AdSpace createAdSpace(String adSpaceId);

	// --------------------------------------------------

}
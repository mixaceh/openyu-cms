package org.openyu.cms.ad.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.service.AppService;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 廣告服務
 */
public interface AdService extends AppService {

	/**
	 * 查詢廣告
	 * 
	 * @param valid
	 * @return
	 */
	List<Ad> findAd();

	/**
	 * 查詢廣告
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<Ad> findAd(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢廣告,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<Ad> findAd(long siteSeq);

	/**
	 * 查詢廣告,,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<Ad> findAd(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Ad> findAd(Locale locale, Ad searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Ad> findAd(Inquiry inquiry, Locale locale, Ad searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<Ad> findAd(Locale locale, long siteSeq, Ad searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<Ad> findAd(Locale locale, Site siteSearcher, Ad searcher);

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
	List<Ad> findAd(Inquiry inquiry, Locale locale, long siteSeq, Ad searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<Ad> findAd(Inquiry inquiry, Locale locale, Site siteSearcher,
			Ad searcher);

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param adId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	Ad createAd(String adId);

}
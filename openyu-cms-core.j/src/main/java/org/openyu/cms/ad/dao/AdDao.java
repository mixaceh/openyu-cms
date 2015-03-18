package org.openyu.cms.ad.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.ad.po.AdPo;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface AdDao extends AppDao
{

	/**
	 * 查詢廣告
	 * 
	 * @return
	 */
	List<AdPo> findAd();

	/**
	 * 查詢廣告
	 * 
	 * @param locale
	 * @return
	 */
	List<AdPo> findAd(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢廣告,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<AdPo> findAd(long siteSeq);

	/**
	 * 查詢廣告,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<AdPo> findAd(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<AdPo> findAd(Locale locale, Ad searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<AdPo> findAd(Inquiry inquiry, Locale locale, Ad searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<AdPo> findAd(Locale locale, long siteSeq, Ad searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<AdPo> findAd(Locale locale, Site siteSearcher, Ad searcher);

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
	List<AdPo> findAd(Inquiry inquiry, Locale locale, long siteSeq, Ad searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<AdPo> findAd(Inquiry inquiry, Locale locale, Site siteSearchers, Ad searcher);

}
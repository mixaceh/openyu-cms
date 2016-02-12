package org.openyu.cms.ad.service.impl;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.ad.dao.AdDao;
import org.openyu.cms.ad.po.AdPo;
import org.openyu.cms.ad.service.AdService;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.ad.vo.AdCollector;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class AdServiceImpl extends AppServiceSupporter implements AdService {

	private static final long serialVersionUID = 1L;

	private static transient final Logger log = LogManager.getLogger(AdServiceImpl.class);

	protected transient AdCollector adCollector = AdCollector.getInstance();

	public AdServiceImpl() {
	}

	public AdDao getAdDao() {
		return (AdDao) getCommonDao();
	}

	@Autowired
	@Qualifier("adDao")
	public void setAdDao(AdDao adDao) {
		setCommonDao(adDao);
	}

	/**
	 * 查詢廣告
	 * 
	 * @return
	 */
	public List<Ad> findAd() {
		List<AdPo> orig = getAdDao().findAd();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢廣告
	 * 
	 * @param locale
	 * @return
	 */
	public List<Ad> findAd(Locale locale) {
		List<AdPo> orig = getAdDao().findAd(locale);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢廣告,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<Ad> findAd(long siteSeq) {
		List<AdPo> orig = getAdDao().findAd(siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢廣告,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	public List<Ad> findAd(Locale locale, long siteSeq) {
		List<AdPo> orig = getAdDao().findAd(locale, siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param ad
	 * @return
	 */
	public List<Ad> findAd(Locale locale, Ad searcher) {
		List<AdPo> orig = getAdDao().findAd(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param ad
	 * @return
	 */
	public List<Ad> findAd(Inquiry inquiry, Locale locale, Ad searcher) {
		List<AdPo> orig = getAdDao().findAd(inquiry, locale, searcher);
		return ClassHelper.copyProperties(orig);
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
	public List<Ad> findAd(Locale locale, long siteSeq, Ad searcher) {
		List<AdPo> orig = getAdDao().findAd(locale, siteSeq, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<Ad> findAd(Locale locale, Site siteSearcher, Ad searcher) {
		List<AdPo> orig = getAdDao().findAd(locale, siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
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
	public List<Ad> findAd(Inquiry inquiry, Locale locale, long siteSeq, Ad searcher) {
		List<AdPo> orig = getAdDao().findAd(inquiry, locale, siteSeq, searcher);
		return ClassHelper.copyProperties(orig);
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
	public List<Ad> findAd(Inquiry inquiry, Locale locale, Site siteSearcher, Ad searcher) {
		List<AdPo> orig = getAdDao().findAd(inquiry, locale, siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

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
	public Ad createAd(String adId) {
		return adCollector.createAd(adId);
	}

}

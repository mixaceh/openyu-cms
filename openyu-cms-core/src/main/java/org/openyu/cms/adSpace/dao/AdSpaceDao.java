package org.openyu.cms.adSpace.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.adSpace.po.AdSpacePo;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface AdSpaceDao extends AppDao
{

	/**
	 * 查詢廣告版位
	 * 
	 * @return
	 */
	List<AdSpacePo> findAdSpace();

	/**
	 * 查詢廣告版位
	 * 
	 * @param locale
	 * @return
	 */
	List<AdSpacePo> findAdSpace(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢廣告版位,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<AdSpacePo> findAdSpace(long siteSeq);

	/**
	 * 查詢廣告版位,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<AdSpacePo> findAdSpace(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<AdSpacePo> findAdSpace(Locale locale, AdSpace searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<AdSpacePo> findAdSpace(Inquiry inquiry, Locale locale, AdSpace searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<AdSpacePo> findAdSpace(Locale locale, long siteSeq, AdSpace searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<AdSpacePo> findAdSpace(Locale locale, Site siteSearcher, AdSpace searcher);

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
	List<AdSpacePo> findAdSpace(Inquiry inquiry, Locale locale, long siteSeq,
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
	List<AdSpacePo> findAdSpace(Inquiry inquiry, Locale locale, Site siteSearchers,
										AdSpace searcher);

}
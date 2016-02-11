package org.openyu.cms.site.service;

import java.util.List;
import java.util.Locale;
import org.openyu.cms.app.service.AppService;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface SiteService extends AppService
{

	/**
	 * 查詢網站
	 * 
	 * @return
	 */
	List<Site> findSite();

	/**
	 * 查詢網站
	 * 
	 * @param locale
	 * @return
	 */
	List<Site> findSite(Locale locale);

	/**
	 * 查詢第一筆網站
	 * 
	 * @return
	 */
	Site findFirstSite();

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Site> findSite(Locale locale, Site searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Site> findSite(Inquiry inquiry, Locale locale, Site searcher);

	// --------------------------------------------------
	/**
	 * 加入網站
	 * 
	 * @param site
	 * @return
	 */
	Site addSite(Site site);

	/**
	 * 更新網站
	 * 
	 * @param origSite
	 * @param newSite
	 * @return
	 */
	Site updateSite(Site origSite, Site newSite);

	/**
	 * 移除網站
	 * 
	 * @param siteId
	 * @return
	 */
	Site removeSite(String siteId);

	/**
	 * 取得所有網站
	 * 
	 * @return
	 */
	List<Site> getSites();

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param siteId, DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	Site createSite(String siteId);
}
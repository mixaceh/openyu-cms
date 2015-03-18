package org.openyu.cms.site.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface SiteDao extends AppDao
{
	/**
	 * 查詢網站
	 * 
	 * @return
	 */
	List<SitePo> findSite();

	/**
	 * 查詢網站
	 * 
	 * @param locale
	 * @return
	 */
	List<SitePo> findSite(Locale locale);

	/**
	 * 查詢第一筆網站
	 * 
	 * @return
	 */
	SitePo findFirstSite();

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<SitePo> findSite(Locale locale, Site searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<SitePo> findSite(Inquiry inquiry, Locale locale, Site searcher);

}
package org.openyu.cms.guestbookType.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.guestbookType.dao.GuestbookTypeDao;
import org.openyu.cms.guestbookType.po.GuestbookTypePo;
import org.openyu.cms.guestbookType.service.GuestbookTypeService;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.GuestbookTypeCollector;
import org.openyu.cms.site.service.SiteService;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class GuestbookTypeServiceImpl extends AppServiceSupporter implements
		GuestbookTypeService {
	private static transient final Logger log = LogManager
			.getLogger(GuestbookTypeServiceImpl.class);

	/**
	 * 網站服務
	 */
	@Autowired
	@Qualifier("siteService")
	protected transient SiteService siteService;

	protected transient GuestbookTypeCollector guestbookTypeCollector = GuestbookTypeCollector
			.getInstance();

	public GuestbookTypeServiceImpl() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		// 初始化cache,<siteId,List<GuestbookType>>
		List<Site> sites = siteService.getSites();
		for (Site site : sites) {
			List<GuestbookType> guestbookTypes = findGuestbookType(site
					.getSeq());
			beans.put(site.getId(), guestbookTypes);
		}
	}

	public GuestbookTypeDao getGuestbookTypeDao() {
		return (GuestbookTypeDao) getOjDao();
	}

	@Autowired
	@Qualifier("guestbookTypeDao")
	public void setGuestbookTypeDao(GuestbookTypeDao guestbookTypeDao) {
		setOjDao(guestbookTypeDao);
	}

	/**
	 * 查詢留言類型
	 * 
	 * @return
	 */
	public List<GuestbookType> findGuestbookType() {
		List<GuestbookTypePo> orig = getGuestbookTypeDao().findGuestbookType();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢留言類型
	 * 
	 * @param locale
	 * @return
	 */
	public List<GuestbookType> findGuestbookType(Locale locale) {
		List<GuestbookTypePo> orig = getGuestbookTypeDao().findGuestbookType(
				locale);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢留言類型,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<GuestbookType> findGuestbookType(long siteSeq) {
		List<GuestbookTypePo> orig = getGuestbookTypeDao().findGuestbookType(
				siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢留言類型,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	public List<GuestbookType> findGuestbookType(Locale locale, long siteSeq) {
		List<GuestbookTypePo> orig = getGuestbookTypeDao().findGuestbookType(
				locale, siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param guestbookType
	 * @return
	 */
	public List<GuestbookType> findGuestbookType(Locale locale,
			GuestbookType searcher) {
		List<GuestbookTypePo> orig = getGuestbookTypeDao().findGuestbookType(
				locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param guestbookType
	 * @return
	 */
	public List<GuestbookType> findGuestbookType(Inquiry inquiry,
			Locale locale, GuestbookType searcher) {
		List<GuestbookTypePo> orig = getGuestbookTypeDao().findGuestbookType(
				inquiry, locale, searcher);
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
	public List<GuestbookType> findGuestbookType(Locale locale, long siteSeq,
			GuestbookType searcher) {
		List<GuestbookTypePo> orig = getGuestbookTypeDao().findGuestbookType(
				locale, siteSeq, searcher);
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
	public List<GuestbookType> findGuestbookType(Locale locale,
			Site siteSearcher, GuestbookType searcher) {
		List<GuestbookTypePo> orig = getGuestbookTypeDao().findGuestbookType(
				locale, siteSearcher, searcher);
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
	public List<GuestbookType> findGuestbookType(Inquiry inquiry,
			Locale locale, long siteSeq, GuestbookType searcher) {
		List<GuestbookTypePo> orig = getGuestbookTypeDao().findGuestbookType(
				inquiry, locale, siteSeq, searcher);
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
	public List<GuestbookType> findGuestbookType(Inquiry inquiry,
			Locale locale, Site siteSearcher, GuestbookType searcher) {
		List<GuestbookTypePo> orig = getGuestbookTypeDao().findGuestbookType(
				inquiry, locale, siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 加入留言類型,網站別
	 * 
	 * @param siteId
	 * @param guestbookType
	 * @return
	 */
	public synchronized GuestbookType addGuestbookType(String siteId,
			GuestbookType guestbookType) {
		GuestbookType result = null;
		if (siteId != null && guestbookType != null) {
			List<GuestbookType> list = getGuestbookTypes(siteId);
			int index = list.indexOf(guestbookType);
			if (index > -1) {
				list.remove(index);
			}
			list.add(guestbookType);
		}
		return result;
	}

	/**
	 * 移除留言類型,網站別
	 * 
	 * @param siteId
	 * @param guestbookTypeId
	 * @return
	 */
	public synchronized GuestbookType removeGuestbookType(String siteId,
			GuestbookType guestbookType) {
		GuestbookType result = null;
		if (siteId != null && guestbookType != null) {
			List<GuestbookType> list = getGuestbookTypes(siteId);
			int index = list.indexOf(guestbookType);
			if (index > -1) {
				list.remove(index);
			}
		}
		return result;
	}

	/**
	 * 取得所有留言類型,網站別
	 * 
	 * @return
	 */
	public synchronized List<GuestbookType> getGuestbookTypes(String siteId) {
		@SuppressWarnings("unchecked")
		List<GuestbookType> result = (List<GuestbookType>) beans.get(siteId);
		if (result == null) {
			result = new LinkedList<GuestbookType>();
			beans.put(siteId, result);
		}
		return result;
	}

	// --------------------------------------------------
	/**
	 * 加入GuestbookType
	 * 
	 * @param guestbookType
	 * @return
	 */
	public GuestbookType addGuestbookType(GuestbookType guestbookType) {
		GuestbookType result = null;
		if (guestbookType != null) {
			result = (GuestbookType) beans.put(guestbookType.getId(),
					guestbookType);
		}
		return result;
	}

	/**
	 * 移除GuestbookType
	 * 
	 * @param guestbookTypeId
	 * @return
	 */
	public GuestbookType removeGuestbookType(String guestbookTypeId) {
		GuestbookType result = null;
		if (guestbookTypeId != null) {
			result = (GuestbookType) beans.remove(guestbookTypeId);
		}
		return result;
	}

	/**
	 * 取得所有GuestbookType
	 * 
	 * @return
	 */
	public List<GuestbookType> getGuestbookTypes() {
		List<GuestbookType> result = new LinkedList<GuestbookType>();
		for (Object entry : beans.getValues()) {
			if (entry instanceof GuestbookType) {
				result.add((GuestbookType) entry);
			}
		}
		return result;
	}

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
	public GuestbookType createGuestbookType(String guestbookTypeId) {
		return guestbookTypeCollector.createGuestbookType(guestbookTypeId);
	}

}
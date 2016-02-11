package org.openyu.cms.guestbook.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.guestbook.dao.GuestbookDao;
import org.openyu.cms.guestbook.po.GuestbookPo;
import org.openyu.cms.guestbook.service.GuestbookService;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbook.vo.GuestbookCollector;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.guestbook.vo.impl.GuestbookImpl;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class GuestbookServiceImpl extends AppServiceSupporter implements
		GuestbookService {
	private static transient final Logger log = LogManager
			.getLogger(GuestbookServiceImpl.class);

	protected transient GuestbookCollector guestbookCollector = GuestbookCollector
			.getInstance();

	public GuestbookServiceImpl() {
	}

	public GuestbookDao getGuestbookDao() {
		return (GuestbookDao) getOjDao();
	}

	@Autowired
	@Qualifier("guestbookDao")
	public void setGuestbookDao(GuestbookDao guestbookDao) {
		setOjDao(guestbookDao);
	}

	/**
	 * 查詢留言
	 * 
	 * @return
	 */
	public List<Guestbook> findGuestbook() {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢留言
	 * 
	 * @param locale
	 * @return
	 */
	public List<Guestbook> findGuestbook(Locale locale) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(locale);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢留言,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<Guestbook> findGuestbook(long siteSeq) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢留言,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	public List<Guestbook> findGuestbook(Locale locale, long siteSeq) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(locale,
				siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param guestbook
	 * @return
	 */
	public List<Guestbook> findGuestbook(Locale locale, Guestbook searcher) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(locale,
				searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param guestbook
	 * @return
	 */
	public List<Guestbook> findGuestbook(Inquiry inquiry, Locale locale,
			Guestbook searcher) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(inquiry,
				locale, searcher);
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
	public List<Guestbook> findGuestbook(Locale locale, long siteSeq,
			Guestbook searcher) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(locale,
				siteSeq, searcher);
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
	public List<Guestbook> findGuestbook(Locale locale, Site siteSearcher,
			Guestbook searcher) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(locale,
				siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param guestbookTypeSearcher
	 * @param searcher
	 * @return
	 */
	public List<Guestbook> findGuestbook(Locale locale, long siteSeq,
			GuestbookType guestbookTypeSearcher, Guestbook searcher) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(locale,
				siteSeq, guestbookTypeSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param guestbookTypeSearcher
	 * @param searcher
	 * @return
	 */
	public List<Guestbook> findGuestbook(Locale locale, Site siteSearcher,
			GuestbookType guestbookTypeSearcher, Guestbook searcher) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(locale,
				siteSearcher, guestbookTypeSearcher, searcher);
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
	public List<Guestbook> findGuestbook(Inquiry inquiry, Locale locale,
			long siteSeq, Guestbook searcher) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(inquiry,
				locale, siteSeq, searcher);
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
	public List<Guestbook> findGuestbook(Inquiry inquiry, Locale locale,
			Site siteSearcher, Guestbook searcher) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(inquiry,
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
	 * @param guestbookTypeSearcher
	 * @return
	 */
	public List<Guestbook> findGuestbook(Inquiry inquiry, Locale locale,
			long siteSeq, GuestbookType guestbookTypeSearcher,
			Guestbook searcher) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(inquiry,
				locale, siteSeq, guestbookTypeSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @param guestbookTypeSearcher
	 * @return
	 */
	public List<Guestbook> findGuestbook(Inquiry inquiry, Locale locale,
			Site siteSearcher, GuestbookType guestbookTypeSearcher,
			Guestbook searcher) {
		List<GuestbookPo> orig = getGuestbookDao().findGuestbook(inquiry,
				locale, siteSearcher, guestbookTypeSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param guestbookId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public Guestbook createGuestbook(String guestbookId) {
		return guestbookCollector.createGuestbook(guestbookId);
	}

}

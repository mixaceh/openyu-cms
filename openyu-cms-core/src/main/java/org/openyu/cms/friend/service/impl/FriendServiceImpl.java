package org.openyu.cms.friend.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.friend.dao.FriendDao;
import org.openyu.cms.friend.po.FriendPo;
import org.openyu.cms.friend.service.FriendService;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friend.vo.FriendCollector;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.friend.vo.impl.FriendImpl;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class FriendServiceImpl extends AppServiceSupporter implements
		FriendService {
	private static transient final Logger log = LogManager
			.getLogger(FriendServiceImpl.class);

	protected transient FriendCollector friendCollector = FriendCollector
			.getInstance();

	public FriendServiceImpl() {
	}

	public FriendDao getFriendDao() {
		return (FriendDao) getOjDao();
	}

	@Autowired
	@Qualifier("friendDao")
	public void setFriendDao(FriendDao friendDao) {
		setOjDao(friendDao);
	}

	/**
	 * 查詢友情連結
	 * 
	 * @return
	 */
	public List<Friend> findFriend() {
		List<FriendPo> orig = getFriendDao().findFriend();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢友情連結
	 * 
	 * @param locale
	 * @return
	 */
	public List<Friend> findFriend(Locale locale) {
		List<FriendPo> orig = getFriendDao().findFriend(locale);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢友情連結,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<Friend> findFriend(long siteSeq) {
		List<FriendPo> orig = getFriendDao().findFriend(siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢友情連結,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	public List<Friend> findFriend(Locale locale, long siteSeq) {
		List<FriendPo> orig = getFriendDao().findFriend(locale, siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param friend
	 * @return
	 */
	public List<Friend> findFriend(Locale locale, Friend searcher) {
		List<FriendPo> orig = getFriendDao().findFriend(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param friend
	 * @return
	 */
	public List<Friend> findFriend(Inquiry inquiry, Locale locale,
			Friend searcher) {
		List<FriendPo> orig = getFriendDao().findFriend(inquiry, locale,
				searcher);
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
	public List<Friend> findFriend(Locale locale, long siteSeq, Friend searcher) {
		List<FriendPo> orig = getFriendDao().findFriend(locale, siteSeq,
				searcher);
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
	public List<Friend> findFriend(Locale locale, Site siteSearcher,
			Friend searcher) {
		List<FriendPo> orig = getFriendDao().findFriend(locale, siteSearcher,
				searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param friendTypeSearcher
	 * @param searcher
	 * @return
	 */
	public List<Friend> findFriend(Locale locale, long siteSeq,
			FriendType friendTypeSearcher, Friend searcher) {
		List<FriendPo> orig = getFriendDao().findFriend(locale, siteSeq,
				friendTypeSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param friendTypeSearcher
	 * @param searcher
	 * @return
	 */
	public List<Friend> findFriend(Locale locale, Site siteSearcher,
			FriendType friendTypeSearcher, Friend searcher) {
		List<FriendPo> orig = getFriendDao().findFriend(locale, siteSearcher,
				friendTypeSearcher, searcher);
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
	public List<Friend> findFriend(Inquiry inquiry, Locale locale,
			long siteSeq, Friend searcher) {
		List<FriendPo> orig = getFriendDao().findFriend(inquiry, locale,
				siteSeq, searcher);
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
	public List<Friend> findFriend(Inquiry inquiry, Locale locale,
			Site siteSearcher, Friend searcher) {
		List<FriendPo> orig = getFriendDao().findFriend(inquiry, locale,
				siteSearcher, searcher);
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
	 * @param friendTypeSearcher
	 * @return
	 */
	public List<Friend> findFriend(Inquiry inquiry, Locale locale,
			long siteSeq, FriendType friendTypeSearcher, Friend searcher) {
		List<FriendPo> orig = getFriendDao().findFriend(inquiry, locale,
				siteSeq, friendTypeSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @param friendTypeSearcher
	 * @return
	 */
	public List<Friend> findFriend(Inquiry inquiry, Locale locale,
			Site siteSearcher, FriendType friendTypeSearcher, Friend searcher) {
		List<FriendPo> orig = getFriendDao().findFriend(inquiry, locale,
				siteSearcher, friendTypeSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param friendId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public Friend createFriend(String friendId) {
		return friendCollector.createFriend(friendId);
	}

}

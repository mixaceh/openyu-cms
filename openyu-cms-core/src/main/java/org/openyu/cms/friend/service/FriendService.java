package org.openyu.cms.friend.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.service.AppService;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 友情連結服務
 */
public interface FriendService extends AppService {

	/**
	 * 查詢友情連結
	 * 
	 * @param valid
	 * @return
	 */
	List<Friend> findFriend();

	/**
	 * 查詢友情連結
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<Friend> findFriend(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢友情連結,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<Friend> findFriend(long siteSeq);

	/**
	 * 查詢友情連結,,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<Friend> findFriend(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Friend> findFriend(Locale locale, Friend searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Friend> findFriend(Inquiry inquiry, Locale locale, Friend searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<Friend> findFriend(Locale locale, long siteSeq, Friend searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<Friend> findFriend(Locale locale, Site siteSearcher, Friend searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param friendTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<Friend> findFriend(Locale locale, long siteSeq,
			FriendType friendTypeSearcher, Friend searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param friendTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<Friend> findFriend(Locale locale, Site siteSearcher,
			FriendType friendTypeSearcher, Friend searcher);

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
	List<Friend> findFriend(Inquiry inquiry, Locale locale, long siteSeq,
			Friend searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<Friend> findFriend(Inquiry inquiry, Locale locale, Site siteSearcher,
			Friend searcher);

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
	List<Friend> findFriend(Inquiry inquiry, Locale locale, long siteSeq,
			FriendType friendTypeSearcher, Friend searcher);

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
	List<Friend> findFriend(Inquiry inquiry, Locale locale, Site siteSearcher,
			FriendType friendTypeSearcher, Friend searcher);

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
	Friend createFriend(String friendId);

}
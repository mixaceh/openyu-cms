package org.openyu.cms.friend.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.friend.po.FriendPo;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface FriendDao extends AppDao
{

	/**
	 * 查詢友情連結
	 * 
	 * @return
	 */
	List<FriendPo> findFriend();

	/**
	 * 查詢友情連結
	 * 
	 * @param locale
	 * @return
	 */
	List<FriendPo> findFriend(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢友情連結,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<FriendPo> findFriend(long siteSeq);

	/**
	 * 查詢友情連結,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<FriendPo> findFriend(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<FriendPo> findFriend(Locale locale, Friend searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<FriendPo> findFriend(Inquiry inquiry, Locale locale, Friend searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<FriendPo> findFriend(Locale locale, long siteSeq, Friend searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<FriendPo> findFriend(Locale locale, Site siteSearcher, Friend searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param friendTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<FriendPo> findFriend(Locale locale, long siteSeq, FriendType friendTypeSearcher,
								Friend searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param friendTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<FriendPo> findFriend(Locale locale, Site siteSearcher, FriendType friendTypeSearcher,
								Friend searcher);

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
	List<FriendPo> findFriend(Inquiry inquiry, Locale locale, long siteSeq, Friend searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<FriendPo> findFriend(Inquiry inquiry, Locale locale, Site siteSearcher, Friend searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param friendTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<FriendPo> findFriend(Inquiry inquiry, Locale locale, long siteSeq,
								FriendType friendTypeSearcher, Friend searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param friendTypeSearcher
	 * @param searcher
	 * @return
	 */
	List<FriendPo> findFriend(Inquiry inquiry, Locale locale, Site siteSearcher,
								FriendType friendTypeSearcher, Friend searcher);
}
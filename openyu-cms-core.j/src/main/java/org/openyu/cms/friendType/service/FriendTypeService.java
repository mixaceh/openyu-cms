package org.openyu.cms.friendType.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.app.service.AppService;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 友情類型服務
 */
public interface FriendTypeService extends AppService {

	/**
	 * 查詢友情類型
	 * 
	 * @param valid
	 * @return
	 */
	List<FriendType> findFriendType();

	/**
	 * 查詢友情類型
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<FriendType> findFriendType(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢友情類型,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<FriendType> findFriendType(long siteSeq);

	/**
	 * 查詢友情類型,,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<FriendType> findFriendType(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<FriendType> findFriendType(Locale locale, FriendType searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<FriendType> findFriendType(Inquiry inquiry, Locale locale,
			FriendType searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<FriendType> findFriendType(Locale locale, long siteSeq,
			FriendType searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<FriendType> findFriendType(Locale locale, Site siteSearcher,
			FriendType searcher);

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
	List<FriendType> findFriendType(Inquiry inquiry, Locale locale,
			long siteSeq, FriendType searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<FriendType> findFriendType(Inquiry inquiry, Locale locale,
			Site siteSearcher, FriendType searcher);

	// --------------------------------------------------
	/**
	 * 加入友情類型,網站別
	 * 
	 * @param friendType
	 * @return
	 */
	public FriendType addFriendType(String siteId, FriendType friendType);

	/**
	 * 移除友情類型,網站別
	 * 
	 * @param friendTypeId
	 * @return
	 */
	FriendType removeFriendType(String siteId, FriendType friendType);

	/**
	 * 取得所有友情類型,網站別
	 * 
	 * @return
	 */
	List<FriendType> getFriendTypes(String siteId);

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param friendTypeId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	FriendType createFriendType(String friendTypeId);

}
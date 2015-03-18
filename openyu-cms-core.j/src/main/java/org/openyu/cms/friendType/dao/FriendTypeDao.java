package org.openyu.cms.friendType.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.friendType.po.FriendTypePo;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface FriendTypeDao extends AppDao
{

	/**
	 * 查詢友情類型
	 * 
	 * @return
	 */
	List<FriendTypePo> findFriendType();

	/**
	 * 查詢友情類型
	 * 
	 * @param locale
	 * @return
	 */
	List<FriendTypePo> findFriendType(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢友情類型,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<FriendTypePo> findFriendType(long siteSeq);

	/**
	 * 查詢友情類型,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<FriendTypePo> findFriendType(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<FriendTypePo> findFriendType(Locale locale, FriendType searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<FriendTypePo> findFriendType(Inquiry inquiry, Locale locale, FriendType searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<FriendTypePo> findFriendType(Locale locale, long siteSeq, FriendType searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<FriendTypePo> findFriendType(Locale locale, Site siteSearcher, FriendType searcher);

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
	List<FriendTypePo> findFriendType(Inquiry inquiry, Locale locale, long siteSeq,
										FriendType searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<FriendTypePo> findFriendType(Inquiry inquiry, Locale locale, Site siteSearchers,
										FriendType searcher);

}
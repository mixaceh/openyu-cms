package org.openyu.cms.friend.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.friend.log.FriendLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface FriendLogDao extends AppLogDao
{
	// --------------------------------------------------
	// FriendLog
	// --------------------------------------------------
	/**
	 * 查詢友情連結改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<FriendLog> findFriendLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢友情連結改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<FriendLog> findFriendLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢友情連結改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<FriendLog> findFriendLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除友情連結改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteFriendLog(long siteSeq, String userId);

}

package org.openyu.cms.friend.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.friend.vo.ActionType;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friend.log.FriendLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface FriendLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

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

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// RoleLevelLog
	// --------------------------------------------------
	/**
	 * 紀錄友情連結改變
	 * 
	 * @param user
	 * @param actionType
	 * @param friend
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	void recordChangeFriend(User user, ActionType actionType, Friend friend);
}

package org.openyu.cms.friendType.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.friendType.vo.ActionType;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.log.FriendTypeLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface FriendTypeLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// FriendTypeLog
	// --------------------------------------------------
	/**
	 * 查詢友情類型改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<FriendTypeLog> findFriendTypeLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢友情類型改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<FriendTypeLog> findFriendTypeLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢友情類型改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<FriendTypeLog> findFriendTypeLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除友情類型改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteFriendTypeLog(long siteSeq, String userId);

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// RoleLevelLog
	// --------------------------------------------------
	/**
	 * 紀錄友情類型改變
	 * 
	 * @param user
	 * @param actionType
	 * @param friendType
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	void recordChangeFriendType(User user, ActionType actionType, FriendType friendType);
}

package org.openyu.cms.friendType.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.friendType.log.FriendTypeLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface FriendTypeLogDao extends AppLogDao
{
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

}

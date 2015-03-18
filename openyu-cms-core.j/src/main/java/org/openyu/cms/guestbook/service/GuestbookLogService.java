package org.openyu.cms.guestbook.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.guestbook.vo.ActionType;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbook.log.GuestbookLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface GuestbookLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// GuestbookLog
	// --------------------------------------------------
	/**
	 * 查詢留言改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<GuestbookLog> findGuestbookLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢留言改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<GuestbookLog> findGuestbookLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢留言改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<GuestbookLog> findGuestbookLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除留言改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteGuestbookLog(long siteSeq, String userId);

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// RoleLevelLog
	// --------------------------------------------------
	/**
	 * 紀錄留言改變
	 * 
	 * @param user
	 * @param actionType
	 * @param guestbook
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	void recordChangeGuestbook(User user, ActionType actionType, Guestbook guestbook);
}

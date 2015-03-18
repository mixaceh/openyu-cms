package org.openyu.cms.guestbookType.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.guestbookType.vo.ActionType;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.log.GuestbookTypeLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface GuestbookTypeLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// GuestbookTypeLog
	// --------------------------------------------------
	/**
	 * 查詢留言類型改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<GuestbookTypeLog> findGuestbookTypeLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢留言類型改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<GuestbookTypeLog> findGuestbookTypeLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢留言類型改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<GuestbookTypeLog> findGuestbookTypeLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除留言類型改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteGuestbookTypeLog(long siteSeq, String userId);

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// RoleLevelLog
	// --------------------------------------------------
	/**
	 * 紀錄留言類型改變
	 * 
	 * @param user
	 * @param actionType
	 * @param guestbookType
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	void recordChangeGuestbookType(User user, ActionType actionType, GuestbookType guestbookType);
}

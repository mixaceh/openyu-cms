package org.openyu.cms.adSpace.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.adSpace.vo.ActionType;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.log.AdSpaceLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface AdSpaceLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// AdSpaceLog
	// --------------------------------------------------
	/**
	 * 查詢廣告版位改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<AdSpaceLog> findAdSpaceLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢廣告版位改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<AdSpaceLog> findAdSpaceLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢廣告版位改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<AdSpaceLog> findAdSpaceLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除廣告版位改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteAdSpaceLog(long siteSeq, String userId);

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// RoleLevelLog
	// --------------------------------------------------
	/**
	 * 紀錄廣告版位改變
	 * 
	 * @param user
	 * @param actionType
	 * @param adSpace
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	void recordChangeAdSpace(User user, ActionType actionType, AdSpace adSpace);
}

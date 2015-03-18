package org.openyu.cms.resource.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.resource.log.ResourceLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface ResourceLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// ResourceLog
	// --------------------------------------------------
	/**
	 * 查詢資源改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<ResourceLog> findResourceLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢資源改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<ResourceLog> findResourceLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢資源改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<ResourceLog> findResourceLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除資源改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteResourceLog(long siteSeq, String userId);

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// ResourceLog
	// --------------------------------------------------

	/**
	 * 紀錄資源改變
	 * 
	 * @param user
	 * @param actionType
	 * @param beforePath
	 * @param afterPath
	 */
	void recordChangeResource(User user, ActionType actionType, String beforePath, String afterPath);

}

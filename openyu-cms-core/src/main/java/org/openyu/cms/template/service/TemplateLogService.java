package org.openyu.cms.template.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.template.log.TemplateLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface TemplateLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// TemplateLog
	// --------------------------------------------------
	/**
	 * 查詢資源改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<TemplateLog> findTemplateLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢資源改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<TemplateLog> findTemplateLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢資源改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<TemplateLog> findTemplateLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除資源改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteTemplateLog(long siteSeq, String userId);

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// TemplateLog
	// --------------------------------------------------

	/**
	 * 紀錄資源改變
	 * 
	 * @param user
	 * @param actionType
	 * @param beforePath
	 * @param afterPath
	 */
	void recordChangeTemplate(User user, ActionType actionType, String beforePath, String afterPath);

}

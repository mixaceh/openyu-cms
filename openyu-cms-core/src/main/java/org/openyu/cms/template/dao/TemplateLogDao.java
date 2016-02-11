package org.openyu.cms.template.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.template.log.TemplateLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface TemplateLogDao extends AppLogDao
{
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

}

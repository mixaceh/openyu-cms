package org.openyu.cms.resource.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.resource.log.ResourceLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface ResourceLogDao extends AppLogDao
{
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

}

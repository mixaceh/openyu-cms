package org.openyu.cms.adSpace.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.adSpace.log.AdSpaceLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface AdSpaceLogDao extends AppLogDao
{
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

}

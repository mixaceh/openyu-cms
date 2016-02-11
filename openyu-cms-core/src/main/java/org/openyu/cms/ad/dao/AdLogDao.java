package org.openyu.cms.ad.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.ad.log.AdLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface AdLogDao extends AppLogDao
{
	// --------------------------------------------------
	// AdLog
	// --------------------------------------------------
	/**
	 * 查詢廣告改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<AdLog> findAdLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢廣告改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<AdLog> findAdLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢廣告改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<AdLog> findAdLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除廣告改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteAdLog(long siteSeq, String userId);

}

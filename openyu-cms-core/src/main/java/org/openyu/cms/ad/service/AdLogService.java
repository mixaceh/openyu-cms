package org.openyu.cms.ad.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.ad.vo.ActionType;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.ad.log.AdLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface AdLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

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

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// RoleLevelLog
	// --------------------------------------------------
	/**
	 * 紀錄廣告改變
	 * 
	 * @param user
	 * @param actionType
	 * @param ad
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	void recordChangeAd(User user, ActionType actionType, Ad ad);
}

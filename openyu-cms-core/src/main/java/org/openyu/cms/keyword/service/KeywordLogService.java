package org.openyu.cms.keyword.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.keyword.vo.ActionType;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.keyword.log.KeywordLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface KeywordLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// KeywordLog
	// --------------------------------------------------
	/**
	 * 查詢關鍵字改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<KeywordLog> findKeywordLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢關鍵字改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<KeywordLog> findKeywordLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢關鍵字改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<KeywordLog> findKeywordLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除關鍵字改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteKeywordLog(long siteSeq, String userId);

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// RoleLevelLog
	// --------------------------------------------------
	/**
	 * 紀錄關鍵字改變
	 * 
	 * @param user
	 * @param actionType
	 * @param keyword
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	void recordChangeDictionary(User user, ActionType actionType, Keyword keyword,
								Dictionary beforeDictionary, Dictionary afterDictionary);
}

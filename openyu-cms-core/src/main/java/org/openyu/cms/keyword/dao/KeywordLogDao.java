package org.openyu.cms.keyword.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.keyword.log.KeywordLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface KeywordLogDao extends AppLogDao
{
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

}

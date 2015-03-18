package org.openyu.cms.topic.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.topic.log.TopicLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface TopicLogDao extends AppLogDao
{
	// --------------------------------------------------
	// TopicLog
	// --------------------------------------------------
	/**
	 * 查詢專題改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<TopicLog> findTopicLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢專題改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<TopicLog> findTopicLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢專題改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<TopicLog> findTopicLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除專題改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteTopicLog(long siteSeq, String userId);

}

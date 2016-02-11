package org.openyu.cms.topic.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.topic.vo.ActionType;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.topic.log.TopicLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface TopicLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

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

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// RoleLevelLog
	// --------------------------------------------------
	/**
	 * 紀錄專題改變
	 * 
	 * @param user
	 * @param actionType
	 * @param topic
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	void recordChangeTopic(User user, ActionType actionType, Topic topic);
}

package org.openyu.cms.topic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.topic.dao.TopicLogDao;
import org.openyu.cms.topic.log.TopicLog;
import org.openyu.cms.topic.log.impl.TopicLogImpl;
import org.openyu.cms.topic.service.TopicLogService;
import org.openyu.cms.topic.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.entity.EntityHelper;

public class TopicLogServiceImpl extends AppLogServiceSupporter implements TopicLogService
{

	@Autowired
	@Qualifier("topicLogDao")
	protected transient TopicLogDao topicLogDao;

	public TopicLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// TopicLog
	// --------------------------------------------------
	public List<TopicLog> findTopicLog(long siteSeq, String userId, String clientIp)
	{
		return topicLogDao.findTopicLog(siteSeq, userId, clientIp);
	}

	public List<TopicLog> findTopicLog(Inquiry inquiry, long siteSeq)
	{
		return topicLogDao.findTopicLog(inquiry, siteSeq);
	}

	public List<TopicLog> findTopicLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		return topicLogDao.findTopicLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteTopicLog(long siteSeq, String userId)
	{
		return topicLogDao.deleteTopicLog(siteSeq, userId);
	}

	// --------------------------------------------------
	// biz
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
	public void recordChangeTopic(User user, ActionType actionType, Topic topic)
	{
		TopicLog log = new TopicLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站seq
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setTopicSeq(topic.getSeq());
		log.setTopicId(topic.getId());
		log.setActionType(actionType);
		//名稱
		log.setNames(EntityHelper.toNames(topic.getNames()));
		//
		offerInsert(log);
	}

}

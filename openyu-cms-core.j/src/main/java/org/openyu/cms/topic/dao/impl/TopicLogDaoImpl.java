package org.openyu.cms.topic.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.topic.dao.TopicLogDao;
import org.openyu.cms.topic.log.TopicLog;
import org.openyu.cms.topic.log.impl.TopicLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class TopicLogDaoImpl extends AppLogDaoSupporter implements TopicLogDao
{

	private static transient final Logger log = LogManager.getLogger(TopicLogDaoImpl.class);

	/**
	 * 專題改變log
	 */
	private static final String TOPIC_LOG_PO_NAME = TopicLogImpl.class.getName();

	public TopicLogDaoImpl()
	{}

	// --------------------------------------------------
	// TopicLevelLog
	// --------------------------------------------------
	/**
	 * 查詢專題改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<TopicLog> findTopicLog(long siteSeq, String userId, String clientIp)
	{

		return findTopicLog(null, siteSeq, userId, clientIp);
	}

	/**
	 * 分頁查詢專題改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	public List<TopicLog> findTopicLog(Inquiry inquiry, long siteSeq)
	{
		return findTopicLog(inquiry, siteSeq, null, null);
	}

	/**
	 * 分頁查詢專題改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<TopicLog> findTopicLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(TOPIC_LOG_PO_NAME + " ");
		hql.append("where 1=1 ");

		//siteSeq
		if (siteSeq > 0)
		{
			hql.append("and siteSeq = :siteSeq ");
			params.put("siteSeq", siteSeq);
		}

		//userId
		if (StringHelper.notBlank(userId))
		{
			hql.append("and userId = :userId ");
			params.put("userId", userId);
		}

		//clientIp
		if (StringHelper.notBlank(clientIp))
		{
			hql.append("and clientIp = :clientIp ");
			params.put("clientIp", clientIp);
		}
		//
		return findByHql(inquiry, null, hql, params);
	}

	/**
	 * 刪除專題改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	public int deleteTopicLog(long siteSeq, String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(TOPIC_LOG_PO_NAME + " ");
		hql.append("where 1=1 ");

		//siteSeq
		hql.append("and siteSeq = :siteSeq ");
		params.put("siteSeq", siteSeq);

		//userId
		hql.append("and userId = :userId ");
		params.put("userId", userId);
		//
		return executeByHql(hql, params);
	}

}

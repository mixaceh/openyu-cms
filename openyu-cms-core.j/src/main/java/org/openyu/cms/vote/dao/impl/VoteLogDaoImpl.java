package org.openyu.cms.vote.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.vote.dao.VoteLogDao;
import org.openyu.cms.vote.log.VoteLog;
import org.openyu.cms.vote.log.impl.VoteLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class VoteLogDaoImpl extends AppLogDaoSupporter implements VoteLogDao
{

	private static transient final Logger log = LogManager.getLogger(VoteLogDaoImpl.class);

	/**
	 * 投票改變log
	 */
	private static final String VOTE_LOG_PO_NAME = VoteLogImpl.class.getName();

	public VoteLogDaoImpl()
	{}

	// --------------------------------------------------
	// VoteLevelLog
	// --------------------------------------------------
	/**
	 * 查詢投票改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<VoteLog> findVoteLog(long siteSeq, String userId, String clientIp)
	{

		return findVoteLog(null, siteSeq, userId, clientIp);
	}

	/**
	 * 分頁查詢投票改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	public List<VoteLog> findVoteLog(Inquiry inquiry, long siteSeq)
	{
		return findVoteLog(inquiry, siteSeq, null, null);
	}

	/**
	 * 分頁查詢投票改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<VoteLog> findVoteLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(VOTE_LOG_PO_NAME + " ");
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
	 * 刪除投票改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	public int deleteVoteLog(long siteSeq, String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(VOTE_LOG_PO_NAME + " ");
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

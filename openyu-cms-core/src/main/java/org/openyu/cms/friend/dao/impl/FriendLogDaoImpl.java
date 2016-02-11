package org.openyu.cms.friend.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.friend.dao.FriendLogDao;
import org.openyu.cms.friend.log.FriendLog;
import org.openyu.cms.friend.log.impl.FriendLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class FriendLogDaoImpl extends AppLogDaoSupporter implements FriendLogDao
{

	private static transient final Logger log = LogManager.getLogger(FriendLogDaoImpl.class);

	/**
	 * 友情連結改變log
	 */
	private static final String FRIEND_LOG_PO_NAME = FriendLogImpl.class.getName();

	public FriendLogDaoImpl()
	{}

	// --------------------------------------------------
	// FriendLevelLog
	// --------------------------------------------------
	/**
	 * 查詢友情連結改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<FriendLog> findFriendLog(long siteSeq, String userId, String clientIp)
	{

		return findFriendLog(null, siteSeq, userId, clientIp);
	}

	/**
	 * 分頁查詢友情連結改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	public List<FriendLog> findFriendLog(Inquiry inquiry, long siteSeq)
	{
		return findFriendLog(inquiry, siteSeq, null, null);
	}

	/**
	 * 分頁查詢友情連結改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<FriendLog> findFriendLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FRIEND_LOG_PO_NAME + " ");
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
	 * 刪除友情連結改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	public int deleteFriendLog(long siteSeq, String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(FRIEND_LOG_PO_NAME + " ");
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

package org.openyu.cms.friendType.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.friendType.dao.FriendTypeLogDao;
import org.openyu.cms.friendType.log.FriendTypeLog;
import org.openyu.cms.friendType.log.impl.FriendTypeLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class FriendTypeLogDaoImpl extends AppLogDaoSupporter implements FriendTypeLogDao
{

	private static transient final Logger log = LogManager.getLogger(FriendTypeLogDaoImpl.class);

	/**
	 * 友情類型改變log
	 */
	private static final String FRIENDTYPE_LOG_PO_NAME = FriendTypeLogImpl.class.getName();

	public FriendTypeLogDaoImpl()
	{}

	// --------------------------------------------------
	// FriendTypeLevelLog
	// --------------------------------------------------
	/**
	 * 查詢友情類型改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<FriendTypeLog> findFriendTypeLog(long siteSeq, String userId, String clientIp)
	{

		return findFriendTypeLog(null, siteSeq, userId, clientIp);
	}

	/**
	 * 分頁查詢友情類型改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	public List<FriendTypeLog> findFriendTypeLog(Inquiry inquiry, long siteSeq)
	{
		return findFriendTypeLog(inquiry, siteSeq, null, null);
	}

	/**
	 * 分頁查詢友情類型改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<FriendTypeLog> findFriendTypeLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FRIENDTYPE_LOG_PO_NAME + " ");
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
	 * 刪除友情類型改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	public int deleteFriendTypeLog(long siteSeq, String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(FRIENDTYPE_LOG_PO_NAME + " ");
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

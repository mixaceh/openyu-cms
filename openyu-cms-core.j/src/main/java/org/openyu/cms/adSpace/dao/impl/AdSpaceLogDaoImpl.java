package org.openyu.cms.adSpace.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.adSpace.dao.AdSpaceLogDao;
import org.openyu.cms.adSpace.log.AdSpaceLog;
import org.openyu.cms.adSpace.log.impl.AdSpaceLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class AdSpaceLogDaoImpl extends AppLogDaoSupporter implements AdSpaceLogDao
{

	private static transient final Logger log = LogManager.getLogger(AdSpaceLogDaoImpl.class);

	/**
	 * 廣告版位改變log
	 */
	private static final String ADSPACE_LOG_PO_NAME = AdSpaceLogImpl.class.getName();

	public AdSpaceLogDaoImpl()
	{}

	// --------------------------------------------------
	// AdSpaceLevelLog
	// --------------------------------------------------
	/**
	 * 查詢廣告版位改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<AdSpaceLog> findAdSpaceLog(long siteSeq, String userId, String clientIp)
	{

		return findAdSpaceLog(null, siteSeq, userId, clientIp);
	}

	/**
	 * 分頁查詢廣告版位改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	public List<AdSpaceLog> findAdSpaceLog(Inquiry inquiry, long siteSeq)
	{
		return findAdSpaceLog(inquiry, siteSeq, null, null);
	}

	/**
	 * 分頁查詢廣告版位改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<AdSpaceLog> findAdSpaceLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(ADSPACE_LOG_PO_NAME + " ");
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
	 * 刪除廣告版位改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	public int deleteAdSpaceLog(long siteSeq, String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(ADSPACE_LOG_PO_NAME + " ");
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

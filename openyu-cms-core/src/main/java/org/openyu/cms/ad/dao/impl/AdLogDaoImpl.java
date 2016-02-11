package org.openyu.cms.ad.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.ad.dao.AdLogDao;
import org.openyu.cms.ad.log.AdLog;
import org.openyu.cms.ad.log.impl.AdLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class AdLogDaoImpl extends AppLogDaoSupporter implements AdLogDao
{

	private static transient final Logger log = LogManager.getLogger(AdLogDaoImpl.class);

	/**
	 * 廣告改變log
	 */
	private static final String AD_LOG_PO_NAME = AdLogImpl.class.getName();

	public AdLogDaoImpl()
	{}

	// --------------------------------------------------
	// AdLevelLog
	// --------------------------------------------------
	/**
	 * 查詢廣告改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<AdLog> findAdLog(long siteSeq, String userId, String clientIp)
	{

		return findAdLog(null, siteSeq, userId, clientIp);
	}

	/**
	 * 分頁查詢廣告改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	public List<AdLog> findAdLog(Inquiry inquiry, long siteSeq)
	{
		return findAdLog(inquiry, siteSeq, null, null);
	}

	/**
	 * 分頁查詢廣告改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<AdLog> findAdLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(AD_LOG_PO_NAME + " ");
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
	 * 刪除廣告改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	public int deleteAdLog(long siteSeq, String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(AD_LOG_PO_NAME + " ");
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

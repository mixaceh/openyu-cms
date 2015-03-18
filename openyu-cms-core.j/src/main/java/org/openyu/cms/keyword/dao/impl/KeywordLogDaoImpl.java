package org.openyu.cms.keyword.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.keyword.dao.KeywordLogDao;
import org.openyu.cms.keyword.log.KeywordLog;
import org.openyu.cms.keyword.log.impl.KeywordLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class KeywordLogDaoImpl extends AppLogDaoSupporter implements KeywordLogDao
{

	private static transient final Logger log = LogManager.getLogger(KeywordLogDaoImpl.class);

	/**
	 * 關鍵字改變log
	 */
	private static final String KEYWORD_LOG_PO_NAME = KeywordLogImpl.class.getName();

	public KeywordLogDaoImpl()
	{}

	// --------------------------------------------------
	// KeywordLevelLog
	// --------------------------------------------------
	/**
	 * 查詢關鍵字改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<KeywordLog> findKeywordLog(long siteSeq, String userId, String clientIp)
	{

		return findKeywordLog(null, siteSeq, userId, clientIp);
	}

	/**
	 * 分頁查詢關鍵字改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	public List<KeywordLog> findKeywordLog(Inquiry inquiry, long siteSeq)
	{
		return findKeywordLog(inquiry, siteSeq, null, null);
	}

	/**
	 * 分頁查詢關鍵字改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<KeywordLog> findKeywordLog(Inquiry inquiry, long siteSeq, String userId,
											String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(KEYWORD_LOG_PO_NAME + " ");
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
	 * 刪除關鍵字改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	public int deleteKeywordLog(long siteSeq, String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(KEYWORD_LOG_PO_NAME + " ");
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

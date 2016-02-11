package org.openyu.cms.tag.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.tag.dao.TagLogDao;
import org.openyu.cms.tag.log.TagLog;
import org.openyu.cms.tag.log.impl.TagLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class TagLogDaoImpl extends AppLogDaoSupporter implements TagLogDao
{

	private static transient final Logger log = LogManager.getLogger(TagLogDaoImpl.class);

	/**
	 * 標籤改變log
	 */
	private static final String KEYWORD_LOG_PO_NAME = TagLogImpl.class.getName();

	public TagLogDaoImpl()
	{}

	// --------------------------------------------------
	// TagLevelLog
	// --------------------------------------------------
	/**
	 * 查詢標籤改變log
	 * 
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<TagLog> findTagLog(String userId, String clientIp)
	{

		return findTagLog(null, userId, clientIp);
	}

	/**
	 * 分頁查詢關鍵字改變log
	 * 
	 * @param inquiry
	 * @return
	 */
	public List<TagLog> findTagLog(Inquiry inquiry)
	{
		return findTagLog(inquiry, null, null);
	}

	/**
	 * 分頁查詢關鍵字改變log
	 * 
	 * @param inquiry
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<TagLog> findTagLog(Inquiry inquiry, String userId, String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(KEYWORD_LOG_PO_NAME + " ");
		hql.append("where 1=1 ");

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
	 * @param userId
	 * @return
	 */
	public int deleteTagLog(String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(KEYWORD_LOG_PO_NAME + " ");
		hql.append("where 1=1 ");

		//userId
		hql.append("and userId = :userId ");
		params.put("userId", userId);
		//
		return executeByHql(hql, params);
	}

}

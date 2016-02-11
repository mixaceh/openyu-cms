package org.openyu.cms.template.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.template.dao.TemplateLogDao;
import org.openyu.cms.template.log.TemplateLog;
import org.openyu.cms.template.log.impl.TemplateLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class TemplateLogDaoImpl extends AppLogDaoSupporter implements TemplateLogDao
{

	private static transient final Logger log = LogManager.getLogger(TemplateLogDaoImpl.class);

	/**
	 * 資源改變log
	 */
	private static final String TEMPLATE_LOG_PO_NAME = TemplateLogImpl.class.getName();

	public TemplateLogDaoImpl()
	{}

	// --------------------------------------------------
	// TemplateLog
	// --------------------------------------------------
	/**
	 * 查詢資源改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<TemplateLog> findTemplateLog(long siteSeq, String userId, String clientIp)
	{

		return findTemplateLog(null, siteSeq, userId, clientIp);
	}

	/**
	 * 分頁查詢資源改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	public List<TemplateLog> findTemplateLog(Inquiry inquiry, long siteSeq)
	{
		return findTemplateLog(inquiry, siteSeq, null, null);
	}

	/**
	 * 分頁查詢資源改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<TemplateLog> findTemplateLog(Inquiry inquiry, long siteSeq, String userId,
												String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(TEMPLATE_LOG_PO_NAME + " ");
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
		return findByHql(inquiry, null, hql.toString(), params);
	}

	/**
	 * 刪除資源改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	public int deleteTemplateLog(long siteSeq, String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(TEMPLATE_LOG_PO_NAME + " ");
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

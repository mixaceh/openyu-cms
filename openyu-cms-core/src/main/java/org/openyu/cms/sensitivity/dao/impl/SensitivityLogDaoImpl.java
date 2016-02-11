package org.openyu.cms.sensitivity.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.sensitivity.dao.SensitivityLogDao;
import org.openyu.cms.sensitivity.log.SensitivityLog;
import org.openyu.cms.sensitivity.log.impl.SensitivityLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class SensitivityLogDaoImpl extends AppLogDaoSupporter implements SensitivityLogDao
{

	private static transient final Logger log = LogManager.getLogger(SensitivityLogDaoImpl.class);

	/**
	 * 敏感詞改變log
	 */
	private static final String SENSITIVITY_LOG_PO_NAME = SensitivityLogImpl.class.getName();

	public SensitivityLogDaoImpl()
	{}

	// --------------------------------------------------
	// SensitivityLog
	// --------------------------------------------------
	/**
	 * 查詢敏感詞改變log
	 * 
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<SensitivityLog> findSensitivityLog(String userId, String clientIp)
	{

		return findSensitivityLog(null, userId, clientIp);
	}

	/**
	 * 分頁查詢敏感詞改變log
	 * 
	 * @param inquiry
	 * @return
	 */
	public List<SensitivityLog> findSensitivityLog(Inquiry inquiry)
	{
		return findSensitivityLog(inquiry, null, null);
	}

	/**
	 * 分頁查詢敏感詞改變log
	 * 
	 * @param inquiry
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<SensitivityLog> findSensitivityLog(Inquiry inquiry, String userId, String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(SENSITIVITY_LOG_PO_NAME + " ");
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
		return findByHql(inquiry, null, hql.toString(), params);
	}

	/**
	 * 刪除敏感詞改變log
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteSensitivityLog(String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(SENSITIVITY_LOG_PO_NAME + " ");
		hql.append("where 1=1 ");

		//userId
		hql.append("and userId = :userId ");
		params.put("userId", userId);
		//
		return executeByHql(hql, params);
	}

}

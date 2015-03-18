package org.openyu.cms.sensitivity.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.sensitivity.log.SensitivityLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface SensitivityLogDao extends AppLogDao
{
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
	List<SensitivityLog> findSensitivityLog(String userId, String clientIp);

	/**
	 * 分頁查詢敏感詞改變log
	 * 
	 * @param inquiry
	 * @return
	 */
	List<SensitivityLog> findSensitivityLog(Inquiry inquiry);

	/**
	 * 分頁查詢敏感詞改變log
	 * 
	 * @param inquiry
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<SensitivityLog> findSensitivityLog(Inquiry inquiry, String userId, String clientIp);

	/**
	 * 刪除敏感詞改變log
	 * 
	 * @param userId
	 * @return
	 */
	int deleteSensitivityLog(String userId);

}

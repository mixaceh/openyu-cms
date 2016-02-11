package org.openyu.cms.sensitivity.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.sensitivity.log.SensitivityLog;
import org.openyu.cms.sensitivity.vo.ActionType;
import org.openyu.cms.sensitivity.vo.Sensitivity;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface SensitivityLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

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

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// SensitivityLog
	// --------------------------------------------------
	/**
	 * 紀錄敏感詞改變
	 * 
	 * @param actionType
	 * @param id
	 * @param locale
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	void recordChangeDictionary(User user, ActionType actionType, Sensitivity sensitivity,
								Dictionary beforeDictionary, Dictionary afterDictionary);
}

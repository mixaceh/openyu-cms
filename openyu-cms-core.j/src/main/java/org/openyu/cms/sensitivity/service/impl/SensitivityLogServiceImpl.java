package org.openyu.cms.sensitivity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.sensitivity.dao.SensitivityLogDao;
import org.openyu.cms.sensitivity.log.SensitivityLog;
import org.openyu.cms.sensitivity.log.impl.SensitivityLogImpl;
import org.openyu.cms.sensitivity.service.SensitivityLogService;
import org.openyu.cms.sensitivity.vo.ActionType;
import org.openyu.cms.sensitivity.vo.Sensitivity;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public class SensitivityLogServiceImpl extends AppLogServiceSupporter implements
		SensitivityLogService
{
	@Autowired
	@Qualifier("sensitivityLogDao")
	protected transient SensitivityLogDao sensitivityLogDao;

	public SensitivityLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// SensitivityLog
	// --------------------------------------------------
	public List<SensitivityLog> findSensitivityLog(String userId, String clientIp)
	{
		return sensitivityLogDao.findSensitivityLog(userId, clientIp);
	}

	public List<SensitivityLog> findSensitivityLog(Inquiry inquiry)
	{
		return sensitivityLogDao.findSensitivityLog(inquiry);
	}

	public List<SensitivityLog> findSensitivityLog(Inquiry inquiry, String userId, String clientIp)
	{
		return sensitivityLogDao.findSensitivityLog(inquiry, userId, clientIp);
	}

	public int deleteSensitivityLog(String userId)
	{
		return sensitivityLogDao.deleteSensitivityLog(userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------
	/**
	 * 紀錄增減敏感詞
	 * 
	 * @param roleId
	 * @param level
	 * @param beforeLevel
	 */
	public void recordChangeDictionary(User user, ActionType actionType, Sensitivity sensitivity,
										Dictionary beforeDictionary, Dictionary afterDictionary)
	{
		SensitivityLog log = new SensitivityLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);
		//
		log.setLocale(user.getSessionLocale());
		log.setSensitivitySeq(sensitivity.getSeq());
		log.setSensitivityId(sensitivity.getId());
		log.setActionType(actionType);
		log.setAfterDictionary(afterDictionary);
		log.setBeforeDictionary(beforeDictionary);
		//
		offerInsert(log);
	}

}

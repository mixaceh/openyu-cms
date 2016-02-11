package org.openyu.cms.template.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.template.dao.TemplateLogDao;
import org.openyu.cms.template.log.TemplateLog;
import org.openyu.cms.template.log.impl.TemplateLogImpl;
import org.openyu.cms.template.service.TemplateLogService;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public class TemplateLogServiceImpl extends AppLogServiceSupporter implements TemplateLogService
{

	@Autowired
	@Qualifier("templateLogDao")
	protected transient TemplateLogDao templateLogDao;

	public TemplateLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// TemplateLog
	// --------------------------------------------------
	public List<TemplateLog> findTemplateLog(long siteSeq, String userId, String clientIp)
	{
		return templateLogDao.findTemplateLog(siteSeq, userId, clientIp);
	}

	public List<TemplateLog> findTemplateLog(Inquiry inquiry, long siteSeq)
	{
		return templateLogDao.findTemplateLog(inquiry, siteSeq);
	}

	public List<TemplateLog> findTemplateLog(Inquiry inquiry, long siteSeq, String userId,
												String clientIp)
	{
		return templateLogDao.findTemplateLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteTemplateLog(long siteSeq, String userId)
	{
		return templateLogDao.deleteTemplateLog(siteSeq, userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	/**
	 * 紀錄資源改變
	 * 
	 * @param user
	 * @param actionType
	 * @param beforePath
	 * @param afterPath
	 */
	public void recordChangeTemplate(User user, ActionType actionType, String beforePath,
										String afterPath)
	{
		TemplateLog log = new TemplateLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站id
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setActionType(actionType);
		log.setBeforePath(beforePath);
		log.setAfterPath(afterPath);
		//
		offerInsert(log);
	}
}

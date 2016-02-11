package org.openyu.cms.resource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.resource.dao.ResourceLogDao;
import org.openyu.cms.resource.log.ResourceLog;
import org.openyu.cms.resource.log.impl.ResourceLogImpl;
import org.openyu.cms.resource.service.ResourceLogService;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public class ResourceLogServiceImpl extends AppLogServiceSupporter implements ResourceLogService
{

	@Autowired
	@Qualifier("resourceLogDao")
	protected transient ResourceLogDao resourceLogDao;

	public ResourceLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// ResourceLog
	// --------------------------------------------------
	public List<ResourceLog> findResourceLog(long siteSeq, String userId, String clientIp)
	{
		return resourceLogDao.findResourceLog(siteSeq, userId, clientIp);
	}

	public List<ResourceLog> findResourceLog(Inquiry inquiry, long siteSeq)
	{
		return resourceLogDao.findResourceLog(inquiry, siteSeq);
	}

	public List<ResourceLog> findResourceLog(Inquiry inquiry, long siteSeq, String userId,
												String clientIp)
	{
		return resourceLogDao.findResourceLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteResourceLog(long siteSeq, String userId)
	{
		return resourceLogDao.deleteResourceLog(siteSeq, userId);
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
	public void recordChangeResource(User user, ActionType actionType, String beforePath,
										String afterPath)
	{
		ResourceLog log = new ResourceLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站seq
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setActionType(actionType);
		log.setBeforePath(beforePath);
		log.setAfterPath(afterPath);
		//
		offerInsert(log);
	}
}

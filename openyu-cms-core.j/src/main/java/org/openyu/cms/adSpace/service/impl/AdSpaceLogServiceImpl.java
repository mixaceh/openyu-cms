package org.openyu.cms.adSpace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.dao.AdSpaceLogDao;
import org.openyu.cms.adSpace.log.AdSpaceLog;
import org.openyu.cms.adSpace.log.impl.AdSpaceLogImpl;
import org.openyu.cms.adSpace.service.AdSpaceLogService;
import org.openyu.cms.adSpace.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.entity.EntityHelper;

public class AdSpaceLogServiceImpl extends AppLogServiceSupporter implements AdSpaceLogService
{

	@Autowired
	@Qualifier("adSpaceLogDao")
	protected transient AdSpaceLogDao adSpaceLogDao;

	public AdSpaceLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// AdSpaceLog
	// --------------------------------------------------
	public List<AdSpaceLog> findAdSpaceLog(long siteSeq, String userId, String clientIp)
	{
		return adSpaceLogDao.findAdSpaceLog(siteSeq, userId, clientIp);
	}

	public List<AdSpaceLog> findAdSpaceLog(Inquiry inquiry, long siteSeq)
	{
		return adSpaceLogDao.findAdSpaceLog(inquiry, siteSeq);
	}

	public List<AdSpaceLog> findAdSpaceLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		return adSpaceLogDao.findAdSpaceLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteAdSpaceLog(long siteSeq, String userId)
	{
		return adSpaceLogDao.deleteAdSpaceLog(siteSeq, userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------
	/**
	 * 紀錄廣告版位改變
	 * 
	 * @param user
	 * @param actionType
	 * @param adSpace
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	public void recordChangeAdSpace(User user, ActionType actionType, AdSpace adSpace)
	{
		AdSpaceLog log = new AdSpaceLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站seq
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setAdSpaceSeq(adSpace.getSeq());
		log.setAdSpaceId(adSpace.getId());
		log.setActionType(actionType);
		//名稱
		log.setNames(EntityHelper.toNames(adSpace.getNames()));
		//
		offerInsert(log);
	}

}

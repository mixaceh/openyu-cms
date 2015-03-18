package org.openyu.cms.guestbookType.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.dao.GuestbookTypeLogDao;
import org.openyu.cms.guestbookType.log.GuestbookTypeLog;
import org.openyu.cms.guestbookType.log.impl.GuestbookTypeLogImpl;
import org.openyu.cms.guestbookType.service.GuestbookTypeLogService;
import org.openyu.cms.guestbookType.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.entity.EntityHelper;

public class GuestbookTypeLogServiceImpl extends AppLogServiceSupporter implements GuestbookTypeLogService
{

	@Autowired
	@Qualifier("guestbookTypeLogDao")
	protected transient GuestbookTypeLogDao guestbookTypeLogDao;

	public GuestbookTypeLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// GuestbookTypeLog
	// --------------------------------------------------
	public List<GuestbookTypeLog> findGuestbookTypeLog(long siteSeq, String userId, String clientIp)
	{
		return guestbookTypeLogDao.findGuestbookTypeLog(siteSeq, userId, clientIp);
	}

	public List<GuestbookTypeLog> findGuestbookTypeLog(Inquiry inquiry, long siteSeq)
	{
		return guestbookTypeLogDao.findGuestbookTypeLog(inquiry, siteSeq);
	}

	public List<GuestbookTypeLog> findGuestbookTypeLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		return guestbookTypeLogDao.findGuestbookTypeLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteGuestbookTypeLog(long siteSeq, String userId)
	{
		return guestbookTypeLogDao.deleteGuestbookTypeLog(siteSeq, userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------
	/**
	 * 紀錄留言類型改變
	 * 
	 * @param user
	 * @param actionType
	 * @param guestbookType
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	public void recordChangeGuestbookType(User user, ActionType actionType, GuestbookType guestbookType)
	{
		GuestbookTypeLog log = new GuestbookTypeLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站seq
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setGuestbookTypeSeq(guestbookType.getSeq());
		log.setGuestbookTypeId(guestbookType.getId());
		log.setActionType(actionType);
		//名稱
		log.setNames(EntityHelper.toNames(guestbookType.getNames()));
		//
		offerInsert(log);
	}

}

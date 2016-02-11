package org.openyu.cms.guestbook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbook.dao.GuestbookLogDao;
import org.openyu.cms.guestbook.log.GuestbookLog;
import org.openyu.cms.guestbook.log.impl.GuestbookLogImpl;
import org.openyu.cms.guestbook.service.GuestbookLogService;
import org.openyu.cms.guestbook.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.entity.EntityHelper;

public class GuestbookLogServiceImpl extends AppLogServiceSupporter implements GuestbookLogService
{

	@Autowired
	@Qualifier("guestbookLogDao")
	protected transient GuestbookLogDao guestbookLogDao;

	public GuestbookLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// GuestbookLog
	// --------------------------------------------------
	public List<GuestbookLog> findGuestbookLog(long siteSeq, String userId, String clientIp)
	{
		return guestbookLogDao.findGuestbookLog(siteSeq, userId, clientIp);
	}

	public List<GuestbookLog> findGuestbookLog(Inquiry inquiry, long siteSeq)
	{
		return guestbookLogDao.findGuestbookLog(inquiry, siteSeq);
	}

	public List<GuestbookLog> findGuestbookLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		return guestbookLogDao.findGuestbookLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteGuestbookLog(long siteSeq, String userId)
	{
		return guestbookLogDao.deleteGuestbookLog(siteSeq, userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------
	/**
	 * 紀錄留言改變
	 * 
	 * @param user
	 * @param actionType
	 * @param guestbook
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	public void recordChangeGuestbook(User user, ActionType actionType, Guestbook guestbook)
	{
		GuestbookLog log = new GuestbookLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站seq
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setGuestbookSeq(guestbook.getSeq());
		log.setGuestbookId(guestbook.getId());
		log.setActionType(actionType);
		//名稱
		log.setNames(EntityHelper.toNames(guestbook.getTitles()));
		//
		offerInsert(log);
	}

}

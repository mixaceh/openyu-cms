package org.openyu.cms.friendType.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.dao.FriendTypeLogDao;
import org.openyu.cms.friendType.log.FriendTypeLog;
import org.openyu.cms.friendType.log.impl.FriendTypeLogImpl;
import org.openyu.cms.friendType.service.FriendTypeLogService;
import org.openyu.cms.friendType.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.entity.EntityHelper;

public class FriendTypeLogServiceImpl extends AppLogServiceSupporter implements FriendTypeLogService
{

	@Autowired
	@Qualifier("friendTypeLogDao")
	protected transient FriendTypeLogDao friendTypeLogDao;

	public FriendTypeLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// FriendTypeLog
	// --------------------------------------------------
	public List<FriendTypeLog> findFriendTypeLog(long siteSeq, String userId, String clientIp)
	{
		return friendTypeLogDao.findFriendTypeLog(siteSeq, userId, clientIp);
	}

	public List<FriendTypeLog> findFriendTypeLog(Inquiry inquiry, long siteSeq)
	{
		return friendTypeLogDao.findFriendTypeLog(inquiry, siteSeq);
	}

	public List<FriendTypeLog> findFriendTypeLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		return friendTypeLogDao.findFriendTypeLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteFriendTypeLog(long siteSeq, String userId)
	{
		return friendTypeLogDao.deleteFriendTypeLog(siteSeq, userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------
	/**
	 * 紀錄友情類型改變
	 * 
	 * @param user
	 * @param actionType
	 * @param friendType
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	public void recordChangeFriendType(User user, ActionType actionType, FriendType friendType)
	{
		FriendTypeLog log = new FriendTypeLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站seq
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setFriendTypeSeq(friendType.getSeq());
		log.setFriendTypeId(friendType.getId());
		log.setActionType(actionType);
		//名稱
		log.setNames(EntityHelper.toNames(friendType.getNames()));
		//
		offerInsert(log);
	}

}

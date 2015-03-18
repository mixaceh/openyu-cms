package org.openyu.cms.friend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friend.dao.FriendLogDao;
import org.openyu.cms.friend.log.FriendLog;
import org.openyu.cms.friend.log.impl.FriendLogImpl;
import org.openyu.cms.friend.service.FriendLogService;
import org.openyu.cms.friend.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.entity.EntityHelper;

public class FriendLogServiceImpl extends AppLogServiceSupporter implements FriendLogService
{

	@Autowired
	@Qualifier("friendLogDao")
	protected transient FriendLogDao friendLogDao;

	public FriendLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// FriendLog
	// --------------------------------------------------
	public List<FriendLog> findFriendLog(long siteSeq, String userId, String clientIp)
	{
		return friendLogDao.findFriendLog(siteSeq, userId, clientIp);
	}

	public List<FriendLog> findFriendLog(Inquiry inquiry, long siteSeq)
	{
		return friendLogDao.findFriendLog(inquiry, siteSeq);
	}

	public List<FriendLog> findFriendLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		return friendLogDao.findFriendLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteFriendLog(long siteSeq, String userId)
	{
		return friendLogDao.deleteFriendLog(siteSeq, userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------
	/**
	 * 紀錄友情連結改變
	 * 
	 * @param user
	 * @param actionType
	 * @param friend
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	public void recordChangeFriend(User user, ActionType actionType, Friend friend)
	{
		FriendLog log = new FriendLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站seq
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setFriendSeq(friend.getSeq());
		log.setFriendId(friend.getId());
		log.setActionType(actionType);
		//名稱
		log.setNames(EntityHelper.toNames(friend.getNames()));
		//
		offerInsert(log);
	}

}

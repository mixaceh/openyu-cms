package org.openyu.cms.vote.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.dao.VoteLogDao;
import org.openyu.cms.vote.log.VoteLog;
import org.openyu.cms.vote.log.impl.VoteLogImpl;
import org.openyu.cms.vote.service.VoteLogService;
import org.openyu.cms.vote.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.entity.EntityHelper;

public class VoteLogServiceImpl extends AppLogServiceSupporter implements VoteLogService
{

	@Autowired
	@Qualifier("voteLogDao")
	protected transient VoteLogDao voteLogDao;

	public VoteLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// VoteLog
	// --------------------------------------------------
	public List<VoteLog> findVoteLog(long siteSeq, String userId, String clientIp)
	{
		return voteLogDao.findVoteLog(siteSeq, userId, clientIp);
	}

	public List<VoteLog> findVoteLog(Inquiry inquiry, long siteSeq)
	{
		return voteLogDao.findVoteLog(inquiry, siteSeq);
	}

	public List<VoteLog> findVoteLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		return voteLogDao.findVoteLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteVoteLog(long siteSeq, String userId)
	{
		return voteLogDao.deleteVoteLog(siteSeq, userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------
	/**
	 * 紀錄投票改變
	 * 
	 * @param user
	 * @param actionType
	 * @param vote
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	public void recordChangeVote(User user, ActionType actionType, Vote vote)
	{
		VoteLog log = new VoteLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站seq
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setVoteSeq(vote.getSeq());
		log.setVoteId(vote.getId());
		log.setActionType(actionType);
		//名稱
		log.setNames(EntityHelper.toNames(vote.getNames()));
		//
		offerInsert(log);
	}

}

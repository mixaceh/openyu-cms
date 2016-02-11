package org.openyu.cms.vote.service;

import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.vote.vo.ActionType;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.log.VoteLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface VoteLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// VoteLog
	// --------------------------------------------------
	/**
	 * 查詢投票改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<VoteLog> findVoteLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢投票改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<VoteLog> findVoteLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢投票改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<VoteLog> findVoteLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除投票改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteVoteLog(long siteSeq, String userId);

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// RoleLevelLog
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
	void recordChangeVote(User user, ActionType actionType, Vote vote);
}

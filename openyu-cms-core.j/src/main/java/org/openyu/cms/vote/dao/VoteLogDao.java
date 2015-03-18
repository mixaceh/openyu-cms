package org.openyu.cms.vote.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.vote.log.VoteLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface VoteLogDao extends AppLogDao
{
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

}

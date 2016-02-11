package org.openyu.cms.vote.log;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.vote.vo.ActionType;
import org.openyu.commons.entity.NamesEntity;

/**
 * 投票改變log,不做bean,直接用entity處理掉
 */
public interface VoteLog extends AppLogEntity, NamesEntity
{
	String KEY = VoteLog.class.getName();

	/**
	 * 網站seq
	 * 
	 * @return
	 */
	Long getSiteSeq();

	void setSiteSeq(Long siteSeq);

	/**
	 * 目錄seq
	 * 
	 * @return
	 */
	Long getCatalogSeq();

	void setCatalogSeq(Long catalogSeq);

	/**
	 * 投票seq
	 * 
	 * @return
	 */
	Long getVoteSeq();

	void setVoteSeq(Long voteSeq);

	/**
	 * 投票id
	 * 
	 * @return
	 */
	String getVoteId();

	void setVoteId(String voteId);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);
}

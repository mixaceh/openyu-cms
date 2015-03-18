package org.openyu.cms.friend.log;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.friend.vo.ActionType;
import org.openyu.commons.entity.NamesEntity;

/**
 * 友情連結改變log,不做bean,直接用entity處理掉
 */
public interface FriendLog extends AppLogEntity, NamesEntity
{
	String KEY = FriendLog.class.getName();

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
	 * 友情連結seq
	 * 
	 * @return
	 */
	Long getFriendSeq();

	void setFriendSeq(Long friendSeq);

	/**
	 * 友情連結id
	 * 
	 * @return
	 */
	String getFriendId();

	void setFriendId(String friendId);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);
}

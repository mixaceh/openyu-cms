package org.openyu.cms.friendType.log;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.friendType.vo.ActionType;
import org.openyu.commons.entity.NamesEntity;

/**
 * 友情類型改變log,不做bean,直接用entity處理掉
 */
public interface FriendTypeLog extends AppLogEntity, NamesEntity
{
	String KEY = FriendTypeLog.class.getName();

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
	 * 友情類型seq
	 * 
	 * @return
	 */
	Long getFriendTypeSeq();

	void setFriendTypeSeq(Long friendTypeSeq);

	/**
	 * 友情類型id
	 * 
	 * @return
	 */
	String getFriendTypeId();

	void setFriendTypeId(String friendTypeId);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);
}

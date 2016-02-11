package org.openyu.cms.guestbookType.log;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.guestbookType.vo.ActionType;
import org.openyu.commons.entity.NamesEntity;

/**
 * 留言類型改變log,不做bean,直接用entity處理掉
 */
public interface GuestbookTypeLog extends AppLogEntity, NamesEntity
{
	String KEY = GuestbookTypeLog.class.getName();

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
	 * 留言類型seq
	 * 
	 * @return
	 */
	Long getGuestbookTypeSeq();

	void setGuestbookTypeSeq(Long guestbookTypeSeq);

	/**
	 * 留言類型id
	 * 
	 * @return
	 */
	String getGuestbookTypeId();

	void setGuestbookTypeId(String guestbookTypeId);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);
}

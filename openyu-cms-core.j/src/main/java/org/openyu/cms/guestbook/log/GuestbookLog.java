package org.openyu.cms.guestbook.log;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.guestbook.vo.ActionType;
import org.openyu.commons.entity.NamesEntity;

/**
 * 留言改變log,不做bean,直接用entity處理掉
 */
public interface GuestbookLog extends AppLogEntity, NamesEntity
{
	String KEY = GuestbookLog.class.getName();

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
	 * 留言seq
	 * 
	 * @return
	 */
	Long getGuestbookSeq();

	void setGuestbookSeq(Long guestbookSeq);

	/**
	 * 留言id
	 * 
	 * @return
	 */
	String getGuestbookId();

	void setGuestbookId(String guestbookId);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);
}

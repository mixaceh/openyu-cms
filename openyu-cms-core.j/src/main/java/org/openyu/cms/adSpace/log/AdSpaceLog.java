package org.openyu.cms.adSpace.log;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.adSpace.vo.ActionType;
import org.openyu.commons.entity.NamesEntity;

/**
 * 廣告版位改變log,不做bean,直接用entity處理掉
 */
public interface AdSpaceLog extends AppLogEntity, NamesEntity
{
	String KEY = AdSpaceLog.class.getName();

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
	 * 廣告版位seq
	 * 
	 * @return
	 */
	Long getAdSpaceSeq();

	void setAdSpaceSeq(Long adSpaceSeq);

	/**
	 * 廣告版位id
	 * 
	 * @return
	 */
	String getAdSpaceId();

	void setAdSpaceId(String adSpaceId);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);
}

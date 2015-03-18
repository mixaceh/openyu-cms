package org.openyu.cms.ad.log;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.ad.vo.ActionType;
import org.openyu.commons.entity.NamesEntity;

/**
 * 廣告改變log,不做bean,直接用entity處理掉
 */
public interface AdLog extends AppLogEntity, NamesEntity
{
	String KEY = AdLog.class.getName();

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
	 * 廣告seq
	 * 
	 * @return
	 */
	Long getAdSeq();

	void setAdSeq(Long adSeq);

	/**
	 * 廣告id
	 * 
	 * @return
	 */
	String getAdId();

	void setAdId(String adId);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);
}

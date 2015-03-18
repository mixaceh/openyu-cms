package org.openyu.cms.resource.log;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.archive.vo.ActionType;

/**
 * 資源改變log,不做bean,直接用entity處理掉
 */
public interface ResourceLog extends AppLogEntity
{
	String KEY = ResourceLog.class.getName();

	/**
	 * 網站seq
	 * 
	 * @return
	 */
	Long getSiteSeq();

	void setSiteSeq(Long siteSeq);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);

	/**
	 * 改變前完整檔案名稱
	 * 
	 * /custom/resource/s1/default/css/style.css
	 * 
	 * @return
	 */
	String getBeforePath();

	void setBeforePath(String beforePath);

	/**
	 * 改變後完整檔案名稱
	 * 
	 * /custom/resource/s1/default/css/style.css
	 * 
	 * @return
	 */
	String getAfterPath();

	void setAfterPath(String afterPath);

}

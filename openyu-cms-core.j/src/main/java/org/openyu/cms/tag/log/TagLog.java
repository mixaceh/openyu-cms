package org.openyu.cms.tag.log;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.tag.vo.ActionType;
import org.openyu.commons.entity.NamesEntity;

/**
 * 標籤改變log,不做bean,直接用entity處理掉
 */
public interface TagLog extends AppLogEntity, NamesEntity
{
	String KEY = TagLog.class.getName();

	/**
	 * 標籤seq
	 * 
	 * @return
	 */
	Long getTagSeq();

	void setTagSeq(Long tagSeq);

	/**
	 * 標籤id
	 * 
	 * @return
	 */
	String getTagId();

	void setTagId(String tagId);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);

}

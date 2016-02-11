package org.openyu.cms.topic.log;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.topic.vo.ActionType;
import org.openyu.commons.entity.NamesEntity;

/**
 * 專題改變log,不做bean,直接用entity處理掉
 */
public interface TopicLog extends AppLogEntity, NamesEntity
{
	String KEY = TopicLog.class.getName();

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
	 * 專題seq
	 * 
	 * @return
	 */
	Long getTopicSeq();

	void setTopicSeq(Long topicSeq);

	/**
	 * 專題id
	 * 
	 * @return
	 */
	String getTopicId();

	void setTopicId(String topicId);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);
}

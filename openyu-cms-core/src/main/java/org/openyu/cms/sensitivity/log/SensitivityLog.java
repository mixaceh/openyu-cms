package org.openyu.cms.sensitivity.log;

import java.util.Locale;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.sensitivity.vo.ActionType;

/**
 * 敏感詞字典改變log,不做bean,直接用entity處理掉
 */
public interface SensitivityLog extends AppLogEntity
{
	String KEY = SensitivityLog.class.getName();

	/**
	 * 區域
	 * 
	 * @return
	 */
	Locale getLocale();

	void setLocale(Locale locale);

	/**
	 * 敏感詞seq
	 * 
	 * @return
	 */
	Long getSensitivitySeq();

	void setSensitivitySeq(Long sensitivitySeq);
	
	/**
	 * 敏感詞id
	 * 
	 * @return
	 */
	public String getSensitivityId();

	public void setSensitivityId(String sensitivityId);

	/**
	 * 操作類別
	 * 
	 * @return
	 */
	ActionType getActionType();

	void setActionType(ActionType actionType);

	/**
	 * 改變前字典
	 * 
	 * @return
	 */
	Dictionary getBeforeDictionary();

	void setBeforeDictionary(Dictionary beforeDictionary);

	/**
	 * 改變後字典
	 * 
	 * @return
	 */
	Dictionary getAfterDictionary();

	void setAfterDictionary(Dictionary afterDictionary);

}

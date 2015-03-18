package org.openyu.cms.keyword.log;

import java.util.Locale;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.keyword.vo.ActionType;
import org.openyu.cms.keyword.vo.Dictionary;

/**
 * 關鍵字字典改變log,不做bean,直接用entity處理掉
 */
public interface KeywordLog extends AppLogEntity
{
	String KEY = KeywordLog.class.getName();

	/**
	 * 網站seq
	 * 
	 * @return
	 */
	Long getSiteSeq();

	void setSiteSeq(Long siteSeq);

	/**
	 * 區域
	 * 
	 * @return
	 */
	Locale getLocale();

	void setLocale(Locale locale);

	/**
	 * 關鍵字seq
	 * 
	 * @return
	 */
	Long getKeywordSeq();

	void setKeywordSeq(Long keywordSeq);

	/**
	 * 關鍵字id
	 * 
	 * @return
	 */
	String getKeywordId();

	void setKeywordId(String keywordId);

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

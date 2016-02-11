package org.openyu.cms.sensitivity.po;

import java.util.Locale;
import java.util.Map;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.commons.entity.SeqIdAuditEntity;

/**
 * 敏感詞
 */
public interface SensitivityPo extends SeqIdAuditEntity
{
	String KEY = SensitivityPo.class.getName();

	/**
	 * 字典<敏感詞, 替換詞>
	 * 
	 * @return
	 */
	Map<String, Dictionary> getDictionarys();

	void setDictionarys(Map<String, Dictionary> dictionarys);

	/**
	 * 語系 Locale
	 * 
	 * @return
	 */
	Locale getLocale();

	void setLocale(Locale locale);
}
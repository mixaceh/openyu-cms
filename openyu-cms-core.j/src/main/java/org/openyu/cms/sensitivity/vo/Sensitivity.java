package org.openyu.cms.sensitivity.vo;

import java.util.Locale;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.commons.bean.SeqIdAuditBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 敏感詞
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Sensitivity extends SeqIdAuditBean
{
	String KEY = Sensitivity.class.getName();

	//original,replace
	/**
	 * 字典<敏感詞, 替換詞>
	 * 
	 * @return
	 */
	Map<String, Dictionary> getDictionarys();

	void setDictionarys(Map<String, Dictionary> dictionarys);

	/**
	 * Locale
	 * 
	 * @return
	 */
	Locale getLocale();

	void setLocale(Locale locale);
}
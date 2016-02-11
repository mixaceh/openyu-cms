package org.openyu.cms.keyword.vo;

import java.util.Locale;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.SeqIdAuditBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 關鍵字
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Keyword extends SeqIdAuditBean
{
	String KEY = Keyword.class.getName();

	/**
	 * 網站
	 * 
	 * @return
	 */
	Site getSite();

	void setSite(Site site);

	/**
	 * 區域
	 * 
	 * @return
	 */
	Locale getLocale();

	void setLocale(Locale locale);

	/**
	 * 字典<關鍵字, 替換內容>
	 * 
	 * @return
	 */
	Map<String, Dictionary> getDictionarys();

	void setDictionarys(Map<String, Dictionary> dictionarys);

}
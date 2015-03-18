package org.openyu.cms.keyword.po;

import java.util.Locale;
import java.util.Map;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.site.po.SitePo;
import org.openyu.commons.entity.SeqIdAuditEntity;

/**
 * 關鍵字
 */
public interface KeywordPo extends SeqIdAuditEntity
{
	String KEY = KeywordPo.class.getName();

	/**
	 * 網站
	 * 
	 * @return
	 */
	SitePo getSite();

	void setSite(SitePo site);

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
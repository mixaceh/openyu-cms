package org.openyu.cms.adSpace.vo;

import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import org.openyu.commons.bean.WhetherOption;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 廣告版位
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface AdSpace extends SeqIdAuditNamesBean
{
	String KEY = AdSpace.class.getName();

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	boolean getValid();

	void setValid(boolean valid);

	/**
	 * 描述
	 * 
	 * @return
	 */
	String getDescription();

	void setDescription(String description);

	String getDescription(Locale locale);

	void setDescription(Locale locale, String description);

	Set<LocaleNameBean> getDescriptions();

	void setDescriptions(Set<LocaleNameBean> descriptions);

	/**
	 * 網站
	 * 
	 * @return
	 */
	Site getSite();

	void setSite(Site site);

	/**
	 * 搜尋用,是否啟用選項
	 * 
	 * @return
	 */
	WhetherOption getValidOption();

	void setValidOption(WhetherOption validOption);

}
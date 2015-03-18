package org.openyu.cms.guestbookType.vo;

import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 留言類型
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface GuestbookType extends SeqIdAuditNamesBean
{
	String KEY = GuestbookType.class.getName();

	/**
	 * 排序
	 * @return
	 */
	int getSort();

	void setSort(int sort);
	
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

}
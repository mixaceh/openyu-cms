package org.openyu.cms.guestbookType.po;

import java.util.Locale;
import java.util.Set;

import org.openyu.cms.site.po.SitePo;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 留言類型
 */
public interface GuestbookTypePo extends SeqIdAuditNamesEntity
{
	String KEY = GuestbookTypePo.class.getName();

	/**
	 * 排序
	 * @return
	 */
	Integer getSort();

	void setSort(Integer sort);
	
	/**
	 * 描述
	 * 
	 * @return
	 */
	String getDescription();

	void setDescription(String description);

	String getDescription(Locale locale);

	void setDescription(Locale locale, String description);

	Set<LocaleNameEntity> getDescriptions();

	void setDescriptions(Set<LocaleNameEntity> descriptions);

	/**
	 * 網站
	 * 
	 * @return
	 */
	SitePo getSite();

	void setSite(SitePo site);

}
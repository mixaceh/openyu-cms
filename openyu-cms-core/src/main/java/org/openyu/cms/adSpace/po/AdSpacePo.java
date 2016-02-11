package org.openyu.cms.adSpace.po;

import java.util.Locale;
import java.util.Set;

import org.openyu.cms.site.po.SitePo;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 廣告版位
 */
public interface AdSpacePo extends SeqIdAuditNamesEntity
{
	String KEY = AdSpacePo.class.getName();

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	Boolean getValid();

	void setValid(Boolean valid);

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
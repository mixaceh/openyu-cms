package org.openyu.cms.catalog.po;

import org.openyu.cms.site.po.SitePo;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 目錄
 */
public interface CatalogPo extends SeqIdAuditNamesEntity
{
	String KEY = CatalogPo.class.getName();

	/**
	 * 網站
	 * 
	 * @return
	 */
	SitePo getSite();

	void setSite(SitePo site);
}
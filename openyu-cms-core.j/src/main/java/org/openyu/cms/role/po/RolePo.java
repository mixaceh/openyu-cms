package org.openyu.cms.role.po;

import org.openyu.cms.site.po.SitePo;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 角色
 */
public interface RolePo extends SeqIdAuditNamesEntity
{
	String KEY = RolePo.class.getName();

	/**
	 * 網站
	 * 
	 * @return
	 */
	SitePo getSite();

	void setSite(SitePo site);
}

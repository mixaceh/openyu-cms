package org.openyu.cms.role.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 角色
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Role extends SeqIdAuditNamesBean
{
	String KEY = Role.class.getName();

	/**
	 * 網站
	 * 
	 * @return
	 */
	Site getSite();

	void setSite(Site site);
}

package org.openyu.cms.catalog.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 目錄
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Catalog extends SeqIdAuditNamesBean
{
	String KEY = Catalog.class.getName();

	/**
	 * 網站
	 * 
	 * @return
	 */
	Site getSite();

	void setSite(Site site);
}

package org.openyu.cms.friendType.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 友情類型
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface FriendType extends SeqIdAuditNamesBean
{
	String KEY = FriendType.class.getName();

	/**
	 * 排列順序
	 * 
	 * @return
	 */
	int getSort();

	void setSort(int sort);

	/**
	 * 網站
	 * 
	 * @return
	 */
	Site getSite();

	void setSite(Site site);
}

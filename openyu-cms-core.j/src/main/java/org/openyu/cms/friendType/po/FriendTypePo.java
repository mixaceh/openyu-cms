package org.openyu.cms.friendType.po;

import org.openyu.cms.site.po.SitePo;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 友情類型
 */
public interface FriendTypePo extends SeqIdAuditNamesEntity
{
	String KEY = FriendTypePo.class.getName();

	/**
	 * 排列順序
	 * 
	 * @return
	 */
	Integer getSort();

	void setSort(Integer sort);

	/**
	 * 網站
	 * 
	 * @return
	 */
	SitePo getSite();

	void setSite(SitePo site);
}
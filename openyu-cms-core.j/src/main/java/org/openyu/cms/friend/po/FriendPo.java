package org.openyu.cms.friend.po;

import java.util.Locale;
import java.util.Set;

import org.openyu.cms.friendType.po.FriendTypePo;
import org.openyu.cms.site.po.SitePo;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 友情連結
 */
public interface FriendPo extends SeqIdAuditNamesEntity
{
	String KEY = FriendPo.class.getName();

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	Boolean getValid();

	void setValid(Boolean valid);

	/**
	 * 網址
	 * 
	 * @return
	 */
	String getUrl();

	void setUrl(String url);

	/**
	 * 圖示
	 * 
	 * @return
	 */
	String getLogo();

	void setLogo(String logo);

	/**
	 * 信箱
	 * 
	 * @return
	 */
	String getEmail();

	void setEmail(String email);

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
	 * 點擊次數
	 * 
	 * @return
	 */
	Integer getClick();

	void setClick(Integer click);

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

	/**
	 * 友情類型
	 * 
	 * @return
	 */
	FriendTypePo getFriendType();

	void setFriendType(FriendTypePo friendType);

}
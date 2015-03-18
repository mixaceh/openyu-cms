package org.openyu.cms.friend.vo;

import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import org.openyu.commons.bean.WhetherOption;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 友情連結
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Friend extends SeqIdAuditNamesBean
{
	String KEY = Friend.class.getName();

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	boolean getValid();

	void setValid(boolean valid);

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

	Set<LocaleNameBean> getDescriptions();

	void setDescriptions(Set<LocaleNameBean> descriptions);

	/**
	 * 點擊次數
	 * 
	 * @return
	 */
	int getClick();

	void setClick(int click);

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

	/**
	 * 友情類型
	 * 
	 * @return
	 */
	FriendType getFriendType();

	void setFriendType(FriendType friendType);

	/**
	 * 搜尋用,是否啟用選項
	 * 
	 * @return
	 */
	WhetherOption getValidOption();

	void setValidOption(WhetherOption validOption);

}
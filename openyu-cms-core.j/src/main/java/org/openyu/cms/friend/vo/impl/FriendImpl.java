package org.openyu.cms.friend.vo.impl;

import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.adapter.FriendTypeXmlAdapter;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.NamesBean;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.bean.adapter.NamesBeanXmlAdapter;
import org.openyu.commons.bean.impl.WhetherOptionImpl;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "friend")
@XmlAccessorType(XmlAccessType.FIELD)
public class FriendImpl extends SeqIdAuditNamesBeanSupporter implements Friend
{
	private static final long serialVersionUID = -7691405783030646263L;

	/**
	 * 是否有效
	 */
	private boolean valid;

	/**
	 * 網址
	 */
	private String url;

	/**
	 * 圖示
	 */
	private String logo;

	/**
	 * 信箱
	 */
	private String email;

	/**
	 * 描述
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean descriptions = new NamesBeanSupporter();

	/**
	 * 點擊次數
	 */
	private int click;

	/**
	 * 排列順序
	 */
	private int sort;

	/**
	 * 網站
	 */
	@XmlJavaTypeAdapter(SiteXmlAdapter.class)
	private Site site;

	/**
	 * 友情類型
	 */
	@XmlJavaTypeAdapter(FriendTypeXmlAdapter.class)
	private FriendType friendType;

	/**
	 * 搜尋用,是否啟用選項
	 */
	@XmlTransient
	private WhetherOption validOption = new WhetherOptionImpl(WhetherType.ALL);

	public FriendImpl(String id)
	{
		setId(id);
	}

	public FriendImpl()
	{
		this(null);
	}

	public FriendImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	public boolean getValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getLogo()
	{
		return logo;
	}

	public void setLogo(String logo)
	{
		this.logo = logo;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Set<LocaleNameBean> getDescriptions()
	{
		return descriptions.getNames();
	}

	public void setDescriptions(Set<LocaleNameBean> descriptions)
	{
		this.descriptions.setNames(descriptions);
	}

	public String getDescription()
	{
		return descriptions.getName();
	}

	public void setDescription(String description)
	{
		descriptions.setName(description);
	}

	public String getDescription(Locale locale)
	{
		return descriptions.getName(locale);
	}

	public void setDescription(Locale locale, String description)
	{
		descriptions.setName(locale, description);
	}

	public int getClick()
	{
		return click;
	}

	public void setClick(int click)
	{
		this.click = click;
	}

	public int getSort()
	{
		return sort;
	}

	public void setSort(int sort)
	{
		this.sort = sort;
	}

	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public FriendType getFriendType()
	{
		return friendType;
	}

	public void setFriendType(FriendType friendType)
	{
		this.friendType = friendType;
	}

	public WhetherOption getValidOption()
	{
		return validOption;
	}

	public void setValidOption(WhetherOption validOption)
	{
		this.validOption = validOption;
	}


	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("valid", valid);
		builder.append("url", url);
		builder.append("logo", logo);
		builder.append("email", email);
		append(builder, "descriptions", descriptions);
		builder.append("click", click);
		builder.append("site", (site != null ? site.getSeq() + ", " + site.getId() : null));
		builder.append("friendType",
			(friendType != null ? friendType.getSeq() + ", " + friendType.getId() : null));
		builder.append("validOption", validOption);
		return builder.toString();
	}

	public Object clone()
	{
		FriendImpl copy = null;
		copy = (FriendImpl) super.clone();
		//
		copy.descriptions = clone(descriptions);
		copy.site = clone(site);
		copy.friendType = clone(friendType);
		//
		copy.validOption = clone(validOption);
		return copy;
	}
}

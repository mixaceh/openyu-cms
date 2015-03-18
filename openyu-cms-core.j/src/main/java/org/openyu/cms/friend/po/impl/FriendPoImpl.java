package org.openyu.cms.friend.po.impl;

import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import org.openyu.cms.friend.po.FriendPo;
import org.openyu.cms.friendType.po.FriendTypePo;
import org.openyu.cms.friendType.po.impl.FriendTypePoImpl;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.po.impl.SitePoImpl;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.NamesEntity;
import org.openyu.commons.entity.bridge.NamesEntityBridge;
import org.openyu.commons.entity.supporter.NamesEntitySupporter;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_friend")
@SequenceGenerator(name = "cms_friend_g", sequenceName = "cms_friend_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.friend.po.impl.FriendPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_friend", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_friend_1", columnNames = {
		"valid", "sort", "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class FriendPoImpl extends SeqIdAuditNamesEntitySupporter implements FriendPo
{

	private static final long serialVersionUID = -3398334103133708064L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 是否有效
	 */
	private Boolean valid;

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
	private NamesEntity descriptions = new NamesEntitySupporter();

	/**
	 * 點擊次數
	 */
	private Integer click;

	/**
	 * 排列順序
	 */
	private Integer sort;

	/**
	 * 網站
	 */
	private SitePo site;

	/**
	 * 友情類型
	 */
	private FriendTypePo friendType;

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_friend_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "valid")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getValid()
	{
		return valid;
	}

	public void setValid(Boolean valid)
	{
		this.valid = valid;
	}

	@Column(name = "url", length = 255)
	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	@Column(name = "logo", length = 100)
	public String getLogo()
	{
		return logo;
	}

	public void setLogo(String logo)
	{
		this.logo = logo;
	}

	@Column(name = "email", length = 100)
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "descriptions", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getDescriptions()
	{
		return descriptions.getNames();
	}

	public void setDescriptions(Set<LocaleNameEntity> descriptions)
	{
		this.descriptions.setNames(descriptions);
	}

	@Transient
	public String getDescription()
	{
		return descriptions.getName();
	}

	public void setDescription(String description)
	{
		descriptions.setName(description);
	}

	@Transient
	public String getDescription(Locale locale)
	{
		return descriptions.getName(locale);
	}

	public void setDescription(Locale locale, String description)
	{
		descriptions.setName(locale, description);
	}

	@Column(name = "click")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getClick()
	{
		return click;
	}

	public void setClick(Integer click)
	{
		this.click = click;
	}

	@Column(name = "sort")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getSort()
	{
		return sort;
	}

	public void setSort(Integer sort)
	{
		this.sort = sort;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(targetEntity = SitePoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "site_seq")
	@IndexedEmbedded(targetElement = SitePoImpl.class, depth = 1)
	public SitePo getSite()
	{
		return site;
	}

	public void setSite(SitePo site)
	{
		this.site = site;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(targetEntity = FriendTypePoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "friend_type_seq")
	@IndexedEmbedded(targetElement = FriendTypePoImpl.class, depth = 1)
	public FriendTypePo getFriendType()
	{
		return friendType;
	}

	public void setFriendType(FriendTypePo friendType)
	{
		this.friendType = friendType;
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
		return builder.toString();
	}

	public Object clone()
	{
		FriendPoImpl copy = null;
		copy = (FriendPoImpl) super.clone();
		//
		copy.descriptions = clone(descriptions);
		copy.site = clone(site);
		copy.friendType = clone(friendType);
		return copy;
	}
}

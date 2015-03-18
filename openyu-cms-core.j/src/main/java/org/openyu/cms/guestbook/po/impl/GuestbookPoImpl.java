package org.openyu.cms.guestbook.po.impl;

import java.util.Date;
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

import org.openyu.cms.guestbook.po.GuestbookPo;
import org.openyu.cms.guestbookType.po.GuestbookTypePo;
import org.openyu.cms.guestbookType.po.impl.GuestbookTypePoImpl;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.po.impl.SitePoImpl;
import org.openyu.cms.user.po.UserPo;
import org.openyu.cms.user.po.impl.UserPoImpl;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.NamesEntity;
import org.openyu.commons.entity.bridge.NamesEntityBridge;
import org.openyu.commons.entity.supporter.NamesEntitySupporter;
import org.openyu.commons.entity.supporter.SeqIdAuditEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_guestbook")
@SequenceGenerator(name = "cms_guestbook_g", sequenceName = "cms_guestbook_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.guestbook.po.impl.GuestbookPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_guestbook", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_guestbook_1", columnNames = { "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class GuestbookPoImpl extends SeqIdAuditEntitySupporter implements GuestbookPo
{

	private static final long serialVersionUID = -4292780611828937598L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 留言標題
	 */
	private NamesEntity titles = new NamesEntitySupporter();

	/**
	 * 留言內容
	 */
	private NamesEntity contents = new NamesEntitySupporter();

	/**
	 * 回復內容
	 */
	private NamesEntity replys = new NamesEntitySupporter();

	/**
	 * 電子郵件
	 */
	private String email;

	/**
	 * 電話
	 */
	private String phone;

	/**
	 * QQ
	 */
	private String qq;

	/**
	 * 留言IP
	 */
	private String ip;

	/**
	 * 留言時間
	 */
	private Date guestbookDate;

	/**
	 * 回復時間
	 */
	private Date replayDate;

	/**
	 * 是否審核
	 */
	private Boolean checked;

	/**
	 * 是否推薦
	 */
	private Boolean recommend;

	/**
	 * 留言會員
	 */
	private UserPo member;

	/**
	 * 回復管理員
	 */
	private UserPo admin;

	/**
	 * 網站
	 */
	private SitePo site;

	/**
	 * 留言類別
	 */
	private GuestbookTypePo guestbookType;

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_guestbook_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "titles", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getTitles()
	{
		return titles.getNames();
	}

	public void setTitles(Set<LocaleNameEntity> titles)
	{
		this.titles.setNames(titles);
	}

	@Transient
	public String getTitle()
	{
		return titles.getName();
	}

	public void setTitle(String title)
	{
		titles.setName(title);
	}

	@Transient
	public String getTitle(Locale locale)
	{
		return titles.getName(locale);
	}

	public void setTitle(Locale locale, String title)
	{
		titles.setName(locale, title);
	}

	//

	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "contents", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getContents()
	{
		return contents.getNames();
	}

	public void setContents(Set<LocaleNameEntity> contents)
	{
		this.contents.setNames(contents);
	}

	@Transient
	public String getContent()
	{
		return contents.getName();
	}

	public void setContent(String title)
	{
		contents.setName(title);
	}

	@Transient
	public String getContent(Locale locale)
	{
		return contents.getName(locale);
	}

	public void setContent(Locale locale, String title)
	{
		contents.setName(locale, title);
	}

	//
	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "replys", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getReplys()
	{
		return replys.getNames();
	}

	public void setReplys(Set<LocaleNameEntity> replys)
	{
		this.replys.setNames(replys);
	}

	@Transient
	public String getReply()
	{
		return replys.getName();
	}

	public void setReply(String reply)
	{
		replys.setName(reply);
	}

	@Transient
	public String getReply(Locale locale)
	{
		return replys.getName(locale);
	}

	public void setReply(Locale locale, String reply)
	{
		replys.setName(locale, reply);
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

	@Column(name = "phone", length = 100)
	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	@Column(name = "qq", length = 50)
	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	@Column(name = "ip", length = 50)
	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	@Column(name = "guestbook_date")
	public Date getGuestbookDate()
	{
		return guestbookDate;
	}

	public void setGuestbookDate(Date guestbookDate)
	{
		this.guestbookDate = guestbookDate;
	}

	@Column(name = "replay_date")
	public Date getReplayDate()
	{
		return replayDate;
	}

	public void setReplayDate(Date replayDate)
	{
		this.replayDate = replayDate;
	}

	@Column(name = "checked")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getChecked()
	{
		return checked;
	}

	public void setChecked(Boolean checked)
	{
		this.checked = checked;
	}

	@Column(name = "recommend")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getRecommend()
	{
		return recommend;
	}

	public void setRecommend(Boolean recommend)
	{
		this.recommend = recommend;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(targetEntity = UserPoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "member_seq")
	@IndexedEmbedded(targetElement = UserPoImpl.class, depth = 1)
	public UserPo getMember()
	{
		return member;
	}

	public void setMember(UserPo member)
	{
		this.member = member;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(targetEntity = UserPoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "admin_seq")
	@IndexedEmbedded(targetElement = UserPoImpl.class, depth = 1)
	public UserPo getAdmin()
	{
		return admin;
	}

	public void setAdmin(UserPo admin)
	{
		this.admin = admin;
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
	@ManyToOne(targetEntity = GuestbookTypePoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "guestbook_type_seq")
	@IndexedEmbedded(targetElement = GuestbookTypePoImpl.class, depth = 1)
	public GuestbookTypePo getGuestbookType()
	{
		return guestbookType;
	}

	public void setGuestbookType(GuestbookTypePo guestbookType)
	{
		this.guestbookType = guestbookType;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		//
		builder.append("titles", titles);
		builder.append("contents", contents);
		builder.append("replys", replys);
		//
		builder.append("email", email);
		builder.append("phone", phone);
		builder.append("qq", qq);
		builder.append("ip", ip);
		builder.append("createTime", guestbookDate);
		builder.append("replayTime", replayDate);
		builder.append("checked", checked);
		builder.append("recommend", recommend);
		//
		builder.append("member", (member != null ? member.getSeq() + ", " + member.getId() : null));
		builder.append("admin", (admin != null ? admin.getSeq() + ", " + admin.getId() : null));
		builder.append("site", (site != null ? site.getSeq() + ", " + site.getId() : null));
		builder.append("guestbookType", (guestbookType != null ? guestbookType.getSeq() + ", "
				+ guestbookType.getId() : null));
		return builder.toString();
	}

	public Object clone()
	{
		GuestbookPoImpl copy = null;
		copy = (GuestbookPoImpl) super.clone();
		//
		copy.titles = clone(titles);
		copy.contents = clone(contents);
		copy.replys = clone(replys);
		copy.member = clone(member);
		copy.admin = clone(admin);
		copy.site = clone(site);
		copy.guestbookType = clone(guestbookType);
		return copy;
	}
}

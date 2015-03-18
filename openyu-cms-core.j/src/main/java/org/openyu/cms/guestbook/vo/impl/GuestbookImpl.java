package org.openyu.cms.guestbook.vo.impl;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.NamesBean;
import org.openyu.commons.bean.adapter.NamesBeanXmlAdapter;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;
import org.openyu.commons.bean.supporter.SeqIdAuditBeanSupporter;
import org.openyu.cms.user.vo.adapter.UserXmlAdapter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "guestbook")
@XmlAccessorType(XmlAccessType.FIELD)
public class GuestbookImpl extends SeqIdAuditBeanSupporter implements Guestbook
{
	private static final long serialVersionUID = -7566722248444854031L;

	/**
	 * 留言標題
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean titles = new NamesBeanSupporter();

	/**
	 * 留言內容
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean contents = new NamesBeanSupporter();

	/**
	 * 回復內容
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean replys = new NamesBeanSupporter();

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
	private boolean checked;

	/**
	 * 是否推薦
	 */
	private boolean recommend;

	/**
	 * 留言會員
	 */
	@XmlJavaTypeAdapter(UserXmlAdapter.class)
	private User member;

	/**
	 * 回復管理員
	 */
	@XmlJavaTypeAdapter(UserXmlAdapter.class)
	private User admin;

	/**
	 * 網站
	 */
	@XmlJavaTypeAdapter(SiteXmlAdapter.class)
	private Site site;

	/**
	 * 留言類別
	 */
	private GuestbookType guestbookType;

	public GuestbookImpl(String id)
	{
		setId(id);
	}

	public GuestbookImpl()
	{
		this(null);
	}

	public GuestbookImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	public String getTitle()
	{
		return titles.getName();
	}

	public void setTitle(String title)
	{
		titles.setName(title);
	}

	public String getTitle(Locale locale)
	{
		return titles.getName(locale);
	}

	public void setTitle(Locale locale, String title)
	{
		titles.setName(locale, title);
	}

	public Set<LocaleNameBean> getTitles()
	{
		return titles.getNames();
	}

	public void setTitles(Set<LocaleNameBean> titles)
	{
		this.titles.setNames(titles);
	}

	//
	public String getContent()
	{
		return contents.getName();
	}

	public void setContent(String description)
	{
		contents.setName(description);
	}

	public String getContent(Locale locale)
	{
		return contents.getName(locale);
	}

	public void setContent(Locale locale, String description)
	{
		contents.setName(locale, description);
	}

	public Set<LocaleNameBean> getContents()
	{
		return contents.getNames();
	}

	public void setContents(Set<LocaleNameBean> contents)
	{
		this.contents.setNames(contents);
	}

	public String getReply()
	{
		return replys.getName();
	}

	public void setReply(String description)
	{
		replys.setName(description);
	}

	public String getReply(Locale locale)
	{
		return replys.getName(locale);
	}

	public void setReply(Locale locale, String description)
	{
		replys.setName(locale, description);
	}

	public Set<LocaleNameBean> getReplys()
	{
		return replys.getNames();
	}

	public void setReplys(Set<LocaleNameBean> replys)
	{
		this.replys.setNames(replys);
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Date getGuestbookDate()
	{
		return guestbookDate;
	}

	public void setGuestbookDate(Date guestbookDate)
	{
		this.guestbookDate = guestbookDate;
	}

	public Date getReplayDate()
	{
		return replayDate;
	}

	public void setReplayDate(Date replayDate)
	{
		this.replayDate = replayDate;
	}

	public boolean getChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public boolean getRecommend()
	{
		return recommend;
	}

	public void setRecommend(boolean recommend)
	{
		this.recommend = recommend;
	}

	public User getMember()
	{
		return member;
	}

	public void setMember(User member)
	{
		this.member = member;
	}

	public User getAdmin()
	{
		return admin;
	}

	public void setAdmin(User admin)
	{
		this.admin = admin;
	}

	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public GuestbookType getGuestbookType()
	{
		return guestbookType;
	}

	public void setGuestbookType(GuestbookType guestbookType)
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
		GuestbookImpl copy = null;
		copy = (GuestbookImpl) super.clone();
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

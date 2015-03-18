package org.openyu.cms.user.po.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import org.openyu.cms.user.po.UserPo;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_user")
@SequenceGenerator(name = "cms_user_g", sequenceName = "cms_user_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.user.po.impl.UserPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_user", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_user_1", columnNames = { "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class UserPoImpl extends SeqIdAuditNamesEntitySupporter implements UserPo
{

	private static final long serialVersionUID = 7941927632926990662L;

	/**
	 * seq
	 */
	private Long seq;

	//	/**
	//	 * 用戶名
	//	 */
	//	private String username;
	//
	//	/**
	//	 * Email
	//	 */
	//	private String email;
	//
	//	/**
	//	 * 註冊時間
	//	 */
	//	private Date registerTime;
	//
	//	/**
	//	 * 註冊IP
	//	 */
	//	private String registerIp;
	//
	//	/**
	//	 * 最後登錄時間
	//	 */
	//	private Date lastLoginTime;
	//
	//	/**
	//	 * 最後登錄IP
	//	 */
	//	private String lastLoginIp;
	//
	//	/**
	//	 * 登錄次數
	//	 */
	//	private Integer loginCount;
	//
	//	/**
	//	 * 管理員級別
	//	 */
	//	private Integer rank;
	//
	//	/**
	//	 * 上傳總大小
	//	 */
	//	private Long uploadTotal;
	//
	//	/**
	//	 * 上傳大小
	//	 */
	//	private Integer uploadSize;
	//
	//	/**
	//	 * 上傳日期
	//	 */
	//	private Date uploadDate;
	//
	//	/**
	//	 * 是否管理員
	//	 */
	//	private Boolean admin;
	//
	//	/**
	//	 * 是否唯讀管理員
	//	 */
	//	private Boolean viewonlyAdmin;
	//
	//	/**
	//	 * 是否只管理自己的資料
	//	 */
	//	private Boolean selfAdmin;
	//
	//	/**
	//	 * 是否禁用
	//	 */
	//	private Boolean valid;
	//
	//	/**
	//	 * 真實姓名
	//	 */
	//	private String realname;
	//
	//	/**
	//	 * 性別
	//	 */
	//	private Boolean gender;
	//
	//	/**
	//	 * 出生日期
	//	 */
	//	private Date birthday;
	//
	//	/**
	//	 * 個人介紹
	//	 */
	//	private String intro;
	//
	//	/**
	//	 * 來自
	//	 */
	//	private String comefrom;
	//
	//	/**
	//	 * QQ
	//	 */
	//	private String qq;
	//
	//	/**
	//	 * MSN
	//	 */
	//	private String msn;
	//
	//	/**
	//	 * 電話
	//	 */
	//	private String phone;
	//
	//	/**
	//	 * 手機
	//	 */
	//	private String mobile;
	//
	//	/**
	//	 * 用戶頭像
	//	 */
	//	private String userImg;
	//
	//	/**
	//	 * 用戶個性簽名
	//	 */
	//	private String userSignature;
	//
	//	/**
	//	 * 群組
	//	 */
	//	private Group group;
	//
	//
	//	//
	//	/**
	//	 * 多個發送訊息項目形成"發送訊息模組"
	//	 */
	//	private Set<Message> sendMessages = new LinkedHashSet<Message>();
	//
	//	/**
	//	 * 多個接收訊息項目形成"接收訊息模組"
	//	 */
	//	private Set<Message> receivMessages = new LinkedHashSet<Message>();
	//
	//	/**
	//	 * 多個站內信收信表項目形成"站內信收信表模組"
	//	 */
	//	private Set<ReceiverMessage> sendReceiverMessages = new LinkedHashSet<ReceiverMessage>();
	//
	//	/**
	//	 * 多個接收站內信收信表項目形成"接收站內信收信表模組"
	//	 */
	//	private Set<ReceiverMessage> receivReceiverMessages = new LinkedHashSet<ReceiverMessage>();

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_user_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	//	@Column(name = "username", length = 100)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getUsername()
	//	{
	//		return username;
	//	}
	//
	//	public void setUsername(String username)
	//	{
	//		this.username = username;
	//	}
	//
	//	@Column(name = "email", length = 100)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getEmail()
	//	{
	//		return email;
	//	}
	//
	//	public void setEmail(String email)
	//	{
	//		this.email = email;
	//	}
	//
	//	@Column(name = "register_time")
	//	public Date getRegisterTime()
	//	{
	//		return registerTime;
	//	}
	//
	//	public void setRegisterTime(Date registerTime)
	//	{
	//		this.registerTime = registerTime;
	//	}
	//
	//	@Column(name = "register_ip", length = 50)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getRegisterIp()
	//	{
	//		return registerIp;
	//	}
	//
	//	public void setRegisterIp(String registerIp)
	//	{
	//		this.registerIp = registerIp;
	//	}
	//
	//	@Column(name = "last_login_time")
	//	public Date getLastLoginTime()
	//	{
	//		return lastLoginTime;
	//	}
	//
	//	public void setLastLoginTime(Date lastLoginTime)
	//	{
	//		this.lastLoginTime = lastLoginTime;
	//	}
	//
	//	@Column(name = "last_login_ip", length = 50)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getLastLoginIp()
	//	{
	//		return lastLoginIp;
	//	}
	//
	//	public void setLastLoginIp(String lastLoginIp)
	//	{
	//		this.lastLoginIp = lastLoginIp;
	//	}
	//
	//	@Column(name = "login_count")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public Integer getLoginCount()
	//	{
	//		return loginCount;
	//	}
	//
	//	public void setLoginCount(Integer loginCount)
	//	{
	//		this.loginCount = loginCount;
	//	}
	//
	//	@Column(name = "rank")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public Integer getRank()
	//	{
	//		return rank;
	//	}
	//
	//	public void setRank(Integer rank)
	//	{
	//		this.rank = rank;
	//	}
	//
	//	@Column(name = "upload_total")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public Long getUploadTotal()
	//	{
	//		return uploadTotal;
	//	}
	//
	//	public void setUploadTotal(Long uploadTotal)
	//	{
	//		this.uploadTotal = uploadTotal;
	//	}
	//
	//	@Column(name = "upload_size")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public Integer getUploadSize()
	//	{
	//		return uploadSize;
	//	}
	//
	//	public void setUploadSize(Integer uploadSize)
	//	{
	//		this.uploadSize = uploadSize;
	//	}
	//
	//	@Column(name = "upload_date")
	//	public Date getUploadDate()
	//	{
	//		return uploadDate;
	//	}
	//
	//	public void setUploadDate(Date uploadDate)
	//	{
	//		this.uploadDate = uploadDate;
	//	}
	//
	//	@Column(name = "admin")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public Boolean getAdmin()
	//	{
	//		return admin;
	//	}
	//
	//	public void setAdmin(Boolean admin)
	//	{
	//		this.admin = admin;
	//	}
	//
	//	@Column(name = "viewonly_admin")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public Boolean getViewonlyAdmin()
	//	{
	//		return viewonlyAdmin;
	//	}
	//
	//	public void setViewonlyAdmin(Boolean viewonlyAdmin)
	//	{
	//		this.viewonlyAdmin = viewonlyAdmin;
	//	}
	//
	//	@Column(name = "self_admin")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public Boolean getSelfAdmin()
	//	{
	//		return selfAdmin;
	//	}
	//
	//	public void setSelfAdmin(Boolean selfAdmin)
	//	{
	//		this.selfAdmin = selfAdmin;
	//	}
	//
	//	@Column(name = "valid")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public Boolean getValid()
	//	{
	//		return valid;
	//	}
	//
	//	public void setValid(Boolean valid)
	//	{
	//		this.valid = valid;
	//	}
	//
	//	@Column(name = "realname", length = 100)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getRealname()
	//	{
	//		return realname;
	//	}
	//
	//	public void setRealname(String realname)
	//	{
	//		this.realname = realname;
	//	}
	//
	//	@Column(name = "gender")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public Boolean getGender()
	//	{
	//		return gender;
	//	}
	//
	//	public void setGender(Boolean gender)
	//	{
	//		this.gender = gender;
	//	}
	//
	//	@Column(name = "birthday")
	//	public Date getBirthday()
	//	{
	//		return birthday;
	//	}
	//
	//	public void setBirthday(Date birthday)
	//	{
	//		this.birthday = birthday;
	//	}
	//
	//	@Column(name = "intro", length = 255)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getIntro()
	//	{
	//		return intro;
	//	}
	//
	//	public void setIntro(String intro)
	//	{
	//		this.intro = intro;
	//	}
	//
	//	@Column(name = "comefrom", length = 150)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getComefrom()
	//	{
	//		return comefrom;
	//	}
	//
	//	public void setComefrom(String comefrom)
	//	{
	//		this.comefrom = comefrom;
	//	}
	//
	//	@Column(name = "qq", length = 100)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getQq()
	//	{
	//		return qq;
	//	}
	//
	//	public void setQq(String qq)
	//	{
	//		this.qq = qq;
	//	}
	//
	//	@Column(name = "msn", length = 100)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getMsn()
	//	{
	//		return msn;
	//	}
	//
	//	public void setMsn(String msn)
	//	{
	//		this.msn = msn;
	//	}
	//
	//	@Column(name = "phone", length = 50)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getPhone()
	//	{
	//		return phone;
	//	}
	//
	//	public void setPhone(String phone)
	//	{
	//		this.phone = phone;
	//	}
	//
	//	@Column(name = "mobile", length = 50)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getMobile()
	//	{
	//		return mobile;
	//	}
	//
	//	public void setMobile(String mobile)
	//	{
	//		this.mobile = mobile;
	//	}
	//
	//	@Column(name = "user_img", length = 255)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getUserImg()
	//	{
	//		return userImg;
	//	}
	//
	//	public void setUserImg(String userImg)
	//	{
	//		this.userImg = userImg;
	//	}
	//
	//	@Column(name = "user_signature", length = 255)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getUserSignature()
	//	{
	//		return userSignature;
	//	}
	//
	//	public void setUserSignature(String userSignature)
	//	{
	//		this.userSignature = userSignature;
	//	}
	//
	//	@LazyCollection(LazyCollectionOption.FALSE)
	//	@ManyToOne(targetEntity = GroupPoImpl.class, cascade = CascadeType.REFRESH)
	//	@NotFound(action = NotFoundAction.IGNORE)
	//	@JoinColumn(name = "group_seq")
	//	@IndexedEmbedded(targetElement = GroupPoImpl.class, depth = 1)
	//	public Group getGroup()
	//	{
	//		return group;
	//	}
	//
	//	public void setGroup(Group group)
	//	{
	//		this.group = group;
	//	}

	//
	//	public Set<Message> getSendMessages()
	//	{
	//		return sendMessages;
	//	}
	//
	//	public void setSendMessages(Set<Message> sendMessages)
	//	{
	//		this.sendMessages = sendMessages;
	//	}
	//
	//	public Set<Message> getReceivMessages()
	//	{
	//		return receivMessages;
	//	}
	//
	//	public void setReceivMessages(Set<Message> receivMessages)
	//	{
	//		this.receivMessages = receivMessages;
	//	}
	//
	//	public Set<ReceiverMessage> getSendReceiverMessages()
	//	{
	//		return sendReceiverMessages;
	//	}
	//
	//	public void setSendReceiverMessages(Set<ReceiverMessage> sendReceiverMessages)
	//	{
	//		this.sendReceiverMessages = sendReceiverMessages;
	//	}
	//
	//	public Set<ReceiverMessage> getReceivReceiverMessages()
	//	{
	//		return receivReceiverMessages;
	//	}
	//
	//	public void setReceivReceiverMessages(Set<ReceiverMessage> receivReceiverMessages)
	//	{
	//		this.receivReceiverMessages = receivReceiverMessages;
	//	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		//		builder.append("username", username);
		//		builder.append("email", email);
		//		builder.append("registerTime", registerTime);
		//		builder.append("registerIp", registerIp);
		//		builder.append("lastLoginTime", lastLoginTime);
		//		builder.append("lastLoginIp", lastLoginIp);
		//		builder.append("loginCount", loginCount);
		//		builder.append("rank", rank);
		//		builder.append("uploadTotal", uploadTotal);
		//		builder.append("uploadSize", uploadSize);
		//		builder.append("uploadDate", uploadDate);
		//		builder.append("admin", admin);
		//		builder.append("viewonlyAdmin", viewonlyAdmin);
		//		builder.append("selfAdmin", selfAdmin);
		//		builder.append("valid", valid);
		//		builder.append("realname", realname);
		//		builder.append("gender", gender);
		//		builder.append("birthday", birthday);
		//		builder.append("intro", intro);
		//		builder.append("comefrom", comefrom);
		//		builder.append("qq", qq);
		//		builder.append("msn", msn);
		//		builder.append("phone", phone);
		//		builder.append("mobile", mobile);
		//		builder.append("userImg", userImg);
		//		builder.append("userSignature", userSignature);
		//		builder.append("group", (group != null ? group.getSeq() + ", " + group.getId() : null));
		//		builder.append("userSites", userSites);
		//		builder.append("roles", roles);
		//		builder.append("channels", channels);
		//		builder.append("contents", contents);
		//		builder.append("sendMessages", sendMessages);
		//		builder.append("receivMessages", receivMessages);
		//		builder.append("sendReceiverMessages", sendReceiverMessages);
		//		builder.append("receivReceiverMessages", receivReceiverMessages);
		return builder.toString();
	}

	public Object clone()
	{
		UserPoImpl copy = null;
		copy = (UserPoImpl) super.clone();
		//		copy.group = clone(group);
		//		copy.userSites = clone(userSites);
		//		copy.roles = clone(roles);
		//		copy.channels = clone(channels);
		//		copy.contents = clone(contents);
		//		copy.sendMessages = clone(sendMessages);
		//		copy.receivMessages = clone(receivMessages);
		//		copy.sendReceiverMessages = clone(sendReceiverMessages);
		//		copy.receivReceiverMessages = clone(receivReceiverMessages);
		return copy;
	}

}

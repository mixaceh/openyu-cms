package org.openyu.cms.config.po.impl;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.openyu.cms.config.po.ConfigPo;
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
@Table(name = "cms_config")
@SequenceGenerator(name = "cms_config_g", sequenceName = "cms_config_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.config.po.impl.ConfigPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_config", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_config_1", columnNames = { "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class ConfigPoImpl extends SeqIdAuditEntitySupporter implements ConfigPo
{

	private static final long serialVersionUID = -6069463521942265701L;

	/**
	 * seq
	 */
	private Long seq;

	//system
	/**
	 * 部署路徑
	 */
	private String sysDeployeePath;

	/**
	 * Port
	 */
	private Integer sysPort;

	/**
	 * 圖片不存在時預設圖片
	 */
	private String sysDefaultImg;

	/**
	 * 開啟郵箱驗證
	 */
	private Boolean sysEmailValidate;

	/**
	 * 資料庫附件訪問位址
	 */
	private String sysDbFileUri;

	/**
	 * 上傳附件至資料庫
	 */
	private Boolean sysUploadToDb;

	//other
	/**
	 * Servlet掛載點
	 */
	private String servletPoint;

	/**
	 * 登入地址
	 */
	private String loginUrl;

	/**
	 * 登入後處理位址
	 */
	private String processUrl;

	/**
	 * 計數器清除時間
	 */
	private Date countClearTime;

	/**
	 * 計數器拷貝時間
	 */
	private Date countCopyTime;

	/**
	 * 下載防盜鏈md5混淆碼
	 */
	private String downloadCode;

	/**
	 * 下載有效時間（小時）
	 */
	private Integer downloadTime;

	//login
	/**
	 * 登入錯誤次數
	 */
	private Integer loginErrorTimes;

	/**
	 * 登入錯誤時間
	 */
	private Integer loginErrorInterval;

	/**
	 * 郵件伺服器
	 */
	private String emailHost;

	/**
	 * 郵件埠
	 */
	private Integer emailPort;

	/**
	 * 郵件用戶名
	 */
	private String emailUsername;

	/**
	 * 郵件密碼
	 */
	private String emailPassword;

	/**
	 * 郵件編碼
	 */
	private String emailEncoding;

	/**
	 * 寄件者
	 */
	private String emailPersonal;

	/**
	 * 找回密碼標題
	 */
	private NamesEntity forgotPasswordSubjects = new NamesEntitySupporter();

	/**
	 * 找回密碼內容
	 */
	private NamesEntity forgotPasswordTexts = new NamesEntitySupporter();

	/**
	 * 會員註冊標題
	 */
	private NamesEntity registerSubjects = new NamesEntitySupporter();

	/**
	 * 會員註冊內容
	 */
	private NamesEntity registerTexts = new NamesEntitySupporter();

	//water mark
	/**
	 * 開啟圖片浮水印
	 */
	private Boolean mark;

	/**
	 * mark_width 圖片寬度
	 */
	private Integer markWidth;

	/**
	 * mark_height 圖片高度
	 */
	private Integer markHeight;

	/**
	 * mark_image 圖片浮水印
	 */
	private String markImagePath;

	/**
	 * mark_content 文字浮水印內容
	 */
	private String markContent;

	/**
	 * mark_size 文字浮水印大小
	 */
	private Integer markSize;

	/**
	 * mark_color 文字浮水印顏色
	 */
	private String markColor;

	/**
	 * mark_alpha 浮水印透明度（0-100）
	 */
	private Integer markAlpha;

	/**
	 * mark_position 浮水印位置(0-5)
	 */
	private Integer markPos;

	/**
	 * mark_offset_x x座標偏移量
	 */
	private Integer markOffsetX;

	/**
	 * mark_offset_y y座標偏移量
	 */
	private Integer markOffsetY;

	//member
	/**
	 * 開啟會員功能
	 */
	private Boolean member;

	/**
	 * 開啟會員註冊
	 */
	private Boolean register;

	/**
	 * 使用者名最小長度
	 */
	private Integer usernameMinLen;

	/**
	 * 密碼最小長度
	 */
	private Integer passwordMinLen;

	/**
	 * 使用者頭像寬度
	 */
	private Integer userImgWidth;

	/**
	 * 使用者頭像高度
	 */
	private Integer userImgHeight;

	/**
	 * 使用者名保留字
	 */
	private String usernameReserved;

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_config_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "sys_deployee_path", length = 20)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getSysDeployeePath()
	{
		return sysDeployeePath;
	}

	public void setSysDeployeePath(String sysDeployeePath)
	{
		this.sysDeployeePath = sysDeployeePath;
	}

	@Column(name = "servlet_point", length = 20)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getServletPoint()
	{
		return servletPoint;
	}

	public void setServletPoint(String servletPoint)
	{
		this.servletPoint = servletPoint;
	}

	@Column(name = "sys_port")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getSysPort()
	{
		return sysPort;
	}

	public void setSysPort(Integer sysPort)
	{
		this.sysPort = sysPort;
	}

	@Column(name = "sys_db_file_uri", length = 50)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getSysDbFileUri()
	{
		return sysDbFileUri;
	}

	public void setSysDbFileUri(String sysDbFileUri)
	{
		this.sysDbFileUri = sysDbFileUri;
	}

	@Column(name = "sys_upload_to_db")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getSysUploadToDb()
	{
		return sysUploadToDb;
	}

	public void setSysUploadToDb(Boolean sysUploadToDb)
	{
		this.sysUploadToDb = sysUploadToDb;
	}

	@Column(name = "sys_default_img", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getSysDefaultImg()
	{
		return sysDefaultImg;
	}

	public void setSysDefaultImg(String sysDefaultImg)
	{
		this.sysDefaultImg = sysDefaultImg;
	}

	@Column(name = "login_url", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getLoginUrl()
	{
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl)
	{
		this.loginUrl = loginUrl;
	}

	@Column(name = "process_url", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getProcessUrl()
	{
		return processUrl;
	}

	public void setProcessUrl(String processUrl)
	{
		this.processUrl = processUrl;
	}

	@Column(name = "count_clear_time")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Date getCountClearTime()
	{
		return countClearTime;
	}

	public void setCountClearTime(Date countClearTime)
	{
		this.countClearTime = countClearTime;
	}

	@Column(name = "count_copy_time")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Date getCountCopyTime()
	{
		return countCopyTime;
	}

	public void setCountCopyTime(Date countCopyTime)
	{
		this.countCopyTime = countCopyTime;
	}

	@Column(name = "download_code", length = 32)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getDownloadCode()
	{
		return downloadCode;
	}

	public void setDownloadCode(String downloadCode)
	{
		this.downloadCode = downloadCode;
	}

	@Column(name = "download_time")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getDownloadTime()
	{
		return downloadTime;
	}

	public void setDownloadTime(Integer downloadTime)
	{
		this.downloadTime = downloadTime;
	}

	@Column(name = "sys_email_validate")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getSysEmailValidate()
	{
		return sysEmailValidate;
	}

	public void setSysEmailValidate(Boolean sysEmailValidate)
	{
		this.sysEmailValidate = sysEmailValidate;
	}

	@Column(name = "mark")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getMark()
	{
		return mark;
	}

	public void setMark(Boolean mark)
	{
		this.mark = mark;
	}

	@Column(name = "mark_width")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getMarkWidth()
	{
		return markWidth;
	}

	public void setMarkWidth(Integer markWidth)
	{
		this.markWidth = markWidth;
	}

	@Column(name = "mark_height")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getMarkHeight()
	{
		return markHeight;
	}

	public void setMarkHeight(Integer markHeight)
	{
		this.markHeight = markHeight;
	}

	@Column(name = "mark_image_path", length = 100)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getMarkImagePath()
	{
		return markImagePath;
	}

	public void setMarkImagePath(String markImagePath)
	{
		this.markImagePath = markImagePath;
	}

	@Column(name = "mark_content")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getMarkContent()
	{
		return markContent;
	}

	public void setMarkContent(String markContent)
	{
		this.markContent = markContent;
	}

	@Column(name = "mark_size")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getMarkSize()
	{
		return markSize;
	}

	public void setMarkSize(Integer markSize)
	{
		this.markSize = markSize;
	}

	@Column(name = "mark_color")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getMarkColor()
	{
		return markColor;
	}

	public void setMarkColor(String markColor)
	{
		this.markColor = markColor;
	}

	@Column(name = "mark_alpha")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getMarkAlpha()
	{
		return markAlpha;
	}

	public void setMarkAlpha(Integer markAlpha)
	{
		this.markAlpha = markAlpha;
	}

	@Column(name = "mark_pos")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getMarkPos()
	{
		return markPos;
	}

	public void setMarkPos(Integer markPos)
	{
		this.markPos = markPos;
	}

	@Column(name = "mark_offset_x")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getMarkOffsetX()
	{
		return markOffsetX;
	}

	public void setMarkOffsetX(Integer markOffsetX)
	{
		this.markOffsetX = markOffsetX;
	}

	@Column(name = "mark_offset_y")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getMarkOffsetY()
	{
		return markOffsetY;
	}

	public void setMarkOffsetY(Integer markOffsetY)
	{
		this.markOffsetY = markOffsetY;
	}

	//	@Column(name = "host")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getHost()
	//	{
	//		return emailSmtp;
	//	}
	//
	//	public void setHost(String host)
	//	{
	//		this.emailSmtp = host;
	//	}
	//
	//	@Column(name = "encoding")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getEncoding()
	//	{
	//		return encoding;
	//	}
	//
	//	public void setEncoding(String encoding)
	//	{
	//		this.encoding = encoding;
	//	}
	//
	//	@Column(name = "username")
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
	//	@Column(name = "password")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getPassword()
	//	{
	//		return password;
	//	}
	//
	//	public void setPassword(String password)
	//	{
	//		this.password = password;
	//	}
	//
	//	@Column(name = "personal")
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	//	public String getPersonal()
	//	{
	//		return personal;
	//	}
	//
	//	public void setPersonal(String personal)
	//	{
	//		this.personal = personal;
	//	}

	//member
	@Column(name = "register")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getRegister()
	{
		return register;
	}

	public void setRegister(Boolean register)
	{
		this.register = register;
	}

	@Column(name = "member")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getMember()
	{
		return member;
	}

	public void setMember(Boolean member)
	{
		this.member = member;
	}

	@Column(name = "username_min_len")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getUsernameMinLen()
	{
		return usernameMinLen;
	}

	public void setUsernameMinLen(Integer usernameMinLen)
	{
		this.usernameMinLen = usernameMinLen;
	}

	@Column(name = "password_min_len")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getPasswordMinLen()
	{
		return passwordMinLen;
	}

	public void setPasswordMinLen(Integer passwordMinLen)
	{
		this.passwordMinLen = passwordMinLen;
	}

	@Column(name = "user_img_width")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getUserImgWidth()
	{
		return userImgWidth;
	}

	public void setUserImgWidth(Integer userImgWidth)
	{
		this.userImgWidth = userImgWidth;
	}

	@Column(name = "user_img_height")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getUserImgHeight()
	{
		return userImgHeight;
	}

	public void setUserImgHeight(Integer userImgHeight)
	{
		this.userImgHeight = userImgHeight;
	}

	@Column(name = "username_reserved")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getUsernameReserved()
	{
		return usernameReserved;
	}

	public void setUsernameReserved(String usernameReserved)
	{
		this.usernameReserved = usernameReserved;
	}

	//login
	@Column(name = "login_error_times")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getLoginErrorTimes()
	{
		return loginErrorTimes;
	}

	public void setLoginErrorTimes(Integer loginErrorTimes)
	{
		this.loginErrorTimes = loginErrorTimes;
	}

	@Column(name = "login_error_interval")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getLoginErrorInterval()
	{
		return loginErrorInterval;
	}

	public void setLoginErrorInterval(Integer loginErrorInterval)
	{
		this.loginErrorInterval = loginErrorInterval;
	}

	@Column(name = "email_host", length = 100)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getEmailHost()
	{
		return emailHost;
	}

	public void setEmailHost(String emailHost)
	{
		this.emailHost = emailHost;
	}

	@Column(name = "email_port")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getEmailPort()
	{
		return emailPort;
	}

	public void setEmailPort(Integer emailPort)
	{
		this.emailPort = emailPort;
	}

	@Column(name = "email_username", length = 100)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getEmailUsername()
	{
		return emailUsername;
	}

	public void setEmailUsername(String emailUsername)
	{
		this.emailUsername = emailUsername;
	}

	@Column(name = "email_password", length = 100)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getEmailPassword()
	{
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword)
	{
		this.emailPassword = emailPassword;
	}

	@Column(name = "email_encoding", length = 20)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getEmailEncoding()
	{
		return emailEncoding;
	}

	public void setEmailEncoding(String emailEncoding)
	{
		this.emailEncoding = emailEncoding;
	}

	@Column(name = "email_personal", length = 100)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getEmailPersonal()
	{
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal)
	{
		this.emailPersonal = emailPersonal;
	}

	//	@Column(name = "forgot_password_subject", length = 255)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)

	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "forgot_password_subjects", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getForgotPasswordSubjects()
	{
		return forgotPasswordSubjects.getNames();
	}

	public void setForgotPasswordSubjects(Set<LocaleNameEntity> forgotPasswordSubjects)
	{
		this.forgotPasswordSubjects.setNames(forgotPasswordSubjects);
	}

	@Transient
	public String getForgotPasswordSubject()
	{
		return forgotPasswordSubjects.getName();
	}

	public void setForgotPasswordSubject(String forgotPasswordSubject)
	{
		forgotPasswordSubjects.setName(forgotPasswordSubject);
	}

	public String getForgotPasswordSubject(Locale locale)
	{
		return forgotPasswordSubjects.getName(locale);
	}

	public void setForgotPasswordSubject(Locale locale, String forgotPasswordSubject)
	{
		forgotPasswordSubjects.setName(locale, forgotPasswordSubject);
	}

	//		@Transient
	//	public String getForgotPasswordSubject()
	//	{
	//		return searchs.getName();
	////		return forgotPasswordSubject;
	//	}
	//
	//	public void setForgotPasswordSubject(String forgotPasswordSubject)
	//	{
	//		this.forgotPasswordSubject = forgotPasswordSubject;
	//	}

	//	@Column(name = "forgot_password_text", length = 255)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "forgot_password_texts", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getForgotPasswordTexts()
	{
		return forgotPasswordTexts.getNames();
	}

	public void setForgotPasswordTexts(Set<LocaleNameEntity> forgotPasswordTexts)
	{
		this.forgotPasswordTexts.setNames(forgotPasswordTexts);
	}

	@Transient
	public String getForgotPasswordText()
	{
		return forgotPasswordTexts.getName();
	}

	public void setForgotPasswordText(String forgotPasswordText)
	{
		forgotPasswordTexts.setName(forgotPasswordText);
	}

	public String getForgotPasswordText(Locale locale)
	{
		return forgotPasswordTexts.getName(locale);
	}

	public void setForgotPasswordText(Locale locale, String forgotPasswordText)
	{
		forgotPasswordTexts.setName(locale, forgotPasswordText);
	}

	//	public String getForgotPasswordText()
	//	{
	//		return forgotPasswordText;
	//	}
	//
	//	public void setForgotPasswordText(String forgotPasswordText)
	//	{
	//		this.forgotPasswordText = forgotPasswordText;
	//	}

	//	@Column(name = "register_subject", length = 255)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "register_subjects", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getRegisterSubjects()
	{
		return registerSubjects.getNames();
	}

	public void setRegisterSubjects(Set<LocaleNameEntity> registerSubjects)
	{
		this.registerSubjects.setNames(registerSubjects);
	}

	@Transient
	public String getRegisterSubject()
	{
		return registerSubjects.getName();
	}

	public void setRegisterSubject(String registerSubject)
	{
		registerSubjects.setName(registerSubject);
	}

	public String getRegisterSubject(Locale locale)
	{
		return registerSubjects.getName(locale);
	}

	public void setRegisterSubject(Locale locale, String registerSubject)
	{
		registerSubjects.setName(locale, registerSubject);
	}

	//	public String getRegisterSubject()
	//	{
	//		return registerSubject;
	//	}
	//
	//	public void setRegisterSubject(String registerSubject)
	//	{
	//		this.registerSubject = registerSubject;
	//	}

	//	@Column(name = "register_text", length = 255)
	//	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "register_texts", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getRegisterTexts()
	{
		return registerTexts.getNames();
	}

	public void setRegisterTexts(Set<LocaleNameEntity> registerSubjects)
	{
		this.registerTexts.setNames(registerSubjects);
	}

	@Transient
	public String getRegisterText()
	{
		return registerTexts.getName();
	}

	public void setRegisterText(String registerSubject)
	{
		registerTexts.setName(registerSubject);
	}

	public String getRegisterText(Locale locale)
	{
		return registerTexts.getName(locale);
	}

	public void setRegisterText(Locale locale, String registerSubject)
	{
		registerTexts.setName(locale, registerSubject);
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("seq", seq);
		builder.append("sysDeployeePath", sysDeployeePath);
		builder.append("sysPort", sysPort);
		builder.append("sysDefaultImg", sysDefaultImg);
		builder.append("sysEmailValidate", sysEmailValidate);
		builder.append("sysDbFileUri", sysDbFileUri);
		builder.append("sysUploadToDb", sysUploadToDb);

		builder.append("servletPoint", servletPoint);
		builder.append("loginUrl", loginUrl);
		builder.append("processUrl", processUrl);
		builder.append("countClearTime", countClearTime);
		builder.append("countCopyTime", countCopyTime);
		builder.append("downloadCode", downloadCode);
		builder.append("downloadTime", downloadTime);

		//water mark
		builder.append("mark", mark);
		builder.append("markWidth", markWidth);
		builder.append("markHeight", markHeight);
		builder.append("markImagePath", markImagePath);
		builder.append("markContent", markContent);
		builder.append("markSize", markSize);
		builder.append("markColor", markColor);
		builder.append("markAlpha", markAlpha);
		builder.append("markPos", markPos);
		builder.append("markOffsetX", markOffsetX);
		builder.append("markOffsetY", markOffsetY);
		//		builder.append("host", emailSmtp);
		//		builder.append("encoding", encoding);
		//		builder.append("username", username);
		//		builder.append("password", password);
		//		builder.append("personal", personal);
		//member
		builder.append("register", register);
		builder.append("member", member);
		builder.append("usernameMinLen", usernameMinLen);
		builder.append("passwordMinLen", passwordMinLen);
		builder.append("userImgWidth", userImgWidth);
		builder.append("userImgHeight", userImgHeight);
		builder.append("usernameReserved", usernameReserved);
		//login
		builder.append("loginErrorTimes", loginErrorTimes);
		builder.append("loginErrorTime", loginErrorInterval);
		builder.append("emailHost", emailHost);
		builder.append("emailPort", emailPort);
		builder.append("emailUsername", emailUsername);
		builder.append("emailPassword", emailPassword);
		builder.append("emailEncoding", emailEncoding);
		builder.append("emailPersonal", emailPersonal);
		//		builder.append("forgotPasswordSubject", forgotPasswordSubject);
		//		builder.append("forgotPasswordText", forgotPasswordText);
		//		builder.append("registerSubject", registerSubject);
		//		builder.append("registerText", registerText);
		//		builder.append("forgetPasswordSubject", forgetPasswordSubject);
		//		builder.append("forgetPasswordContext", forgetPasswordContext);
		builder.append("forgotPasswordSubjects", forgotPasswordSubjects);
		builder.append("forgotPasswordTexts", forgotPasswordTexts);
		builder.append("registerSubjects", registerSubjects);
		builder.append("registerTexts", registerTexts);
		return builder.toString();
	}

	public Object clone()
	{
		ConfigPoImpl copy = null;
		copy = (ConfigPoImpl) super.clone();
		//
		copy.countClearTime = clone(countClearTime);
		copy.countCopyTime = clone(countCopyTime);
		copy.forgotPasswordSubjects = clone(forgotPasswordSubjects);
		copy.forgotPasswordTexts = clone(forgotPasswordTexts);
		copy.registerSubjects = clone(registerSubjects);
		copy.registerTexts = clone(registerTexts);
		return copy;
	}

}

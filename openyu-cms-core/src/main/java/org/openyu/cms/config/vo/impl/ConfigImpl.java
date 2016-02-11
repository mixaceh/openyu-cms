package org.openyu.cms.config.vo.impl;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.config.vo.Config;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.NamesBean;
import org.openyu.commons.bean.adapter.NamesBeanXmlAdapter;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;
import org.openyu.commons.bean.supporter.SeqIdAuditBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "config")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigImpl extends SeqIdAuditBeanSupporter implements Config
{

	private static final long serialVersionUID = 6667192159087405216L;

	//system
	/**
	 * 部署路徑
	 */
	private String sysDeployeePath;

	/**
	 * Port
	 */
	private int sysPort;

	/**
	 * 圖片不存在時預設圖片
	 */
	private String sysDefaultImg;

	/**
	 * 開啟郵箱驗證
	 */
	private boolean sysEmailValidate;

	/**
	 * 資料庫附件訪問位址
	 */
	private String sysDbFileUri;

	/**
	 * 上傳附件至資料庫
	 */
	private boolean sysUploadToDb;

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
	private int downloadTime;

	//login
	/**
	 * 登入錯誤次數
	 */
	private int loginErrorTimes;

	/**
	 * 登入錯誤時間
	 */
	private int loginErrorInterval;

	/**
	 * 郵件伺服器
	 */
	private String emailHost;

	/**
	 * 郵件埠
	 */
	private int emailPort;

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
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean forgotPasswordSubjects = new NamesBeanSupporter();

	//	private String forgotPasswordSubject;

	/**
	 * 找回密碼內容
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean forgotPasswordTexts = new NamesBeanSupporter();

	//	private String forgotPasswordText;

	/**
	 * 會員註冊標題
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean registerSubjects = new NamesBeanSupporter();

	//	private String registerSubject;

	/**
	 * 會員註冊內容
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean registerTexts = new NamesBeanSupporter();

	//	private String registerText;

	//water mark
	/**
	 * 開啟圖片浮水印
	 */
	private boolean mark;

	/**
	 * mark_width 圖片寬度
	 */
	private int markWidth;

	/**
	 * mark_height 圖片高度
	 */
	private int markHeight;

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
	private int markSize;

	/**
	 * mark_color 文字浮水印顏色
	 */
	private String markColor;

	/**
	 * mark_alpha 浮水印透明度（0-100）
	 */
	private int markAlpha;

	/**
	 * mark_position 浮水印位置(0-5)
	 */
	private int markPos;

	/**
	 * mark_offset_x x座標偏移量
	 */
	private int markOffsetX;

	/**
	 * mark_offset_y y座標偏移量
	 */
	private int markOffsetY;

	//member
	/**
	 * 開啟會員功能
	 */
	private boolean member;

	/**
	 * 開啟會員註冊
	 */
	private boolean register;

	/**
	 * 使用者名最小長度
	 */
	private int usernameMinLen;

	/**
	 * 密碼最小長度
	 */
	private int passwordMinLen;

	/**
	 * 使用者頭像寬度
	 */
	private int userImgWidth;

	/**
	 * 使用者頭像高度
	 */
	private int userImgHeight;

	/**
	 * 使用者名保留字
	 */
	private String usernameReserved;

	/**
	 * 會員密碼找回資訊(CSM會員密碼找回資訊)
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean forgetPasswordSubjects = new NamesBeanSupporter();

	//	private String forgetPasswordSubject;

	/**
	 * 會員密碼找回內容( 感謝您使用JEECMS系統會員密碼找回功能，請記住以下找回資訊： 用戶ID：${uid} 用戶名：${username}
	 * 您的新密碼為：${resetPwd} 請訪問如下連結新密碼才能生效：
	 * http://localhost/member/password_reset.jspx?uid=${uid}&key=${resetKey} )
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean forgetPasswordContexts = new NamesBeanSupporter();

	//	private String forgetPasswordContext;

	public ConfigImpl(String id)
	{
		setId(id);
	}

	public ConfigImpl()
	{
		this(null);
	}

	public String getSysDeployeePath()
	{
		return sysDeployeePath;
	}

	public void setSysDeployeePath(String sysDeployeePath)
	{
		this.sysDeployeePath = sysDeployeePath;
	}

	public int getSysPort()
	{
		return sysPort;
	}

	public void setSysPort(int sysPort)
	{
		this.sysPort = sysPort;
	}

	public String getSysDefaultImg()
	{
		return sysDefaultImg;
	}

	public void setSysDefaultImg(String sysDefaultImg)
	{
		this.sysDefaultImg = sysDefaultImg;
	}

	public boolean getSysEmailValidate()
	{
		return sysEmailValidate;
	}

	public void setSysEmailValidate(boolean sysEmailValidate)
	{
		this.sysEmailValidate = sysEmailValidate;
	}

	public String getSysDbFileUri()
	{
		return sysDbFileUri;
	}

	public void setSysDbFileUri(String sysDbFileUri)
	{
		this.sysDbFileUri = sysDbFileUri;
	}

	public boolean getSysUploadToDb()
	{
		return sysUploadToDb;
	}

	public void setSysUploadToDb(boolean sysUploadToDb)
	{
		this.sysUploadToDb = sysUploadToDb;
	}

	public String getServletPoint()
	{
		return servletPoint;
	}

	public void setServletPoint(String servletPoint)
	{
		this.servletPoint = servletPoint;
	}

	public String getLoginUrl()
	{
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl)
	{
		this.loginUrl = loginUrl;
	}

	public String getProcessUrl()
	{
		return processUrl;
	}

	public void setProcessUrl(String processUrl)
	{
		this.processUrl = processUrl;
	}

	public Date getCountClearTime()
	{
		return countClearTime;
	}

	public void setCountClearTime(Date countClearTime)
	{
		this.countClearTime = countClearTime;
	}

	public Date getCountCopyTime()
	{
		return countCopyTime;
	}

	public void setCountCopyTime(Date countCopyTime)
	{
		this.countCopyTime = countCopyTime;
	}

	public String getDownloadCode()
	{
		return downloadCode;
	}

	public void setDownloadCode(String downloadCode)
	{
		this.downloadCode = downloadCode;
	}

	public int getDownloadTime()
	{
		return downloadTime;
	}

	public void setDownloadTime(int downloadTime)
	{
		this.downloadTime = downloadTime;
	}

	public int getLoginErrorTimes()
	{
		return loginErrorTimes;
	}

	public void setLoginErrorTimes(int loginErrorTimes)
	{
		this.loginErrorTimes = loginErrorTimes;
	}

	public int getLoginErrorInterval()
	{
		return loginErrorInterval;
	}

	public void setLoginErrorInterval(int loginErrorInterval)
	{
		this.loginErrorInterval = loginErrorInterval;
	}

	public String getEmailHost()
	{
		return emailHost;
	}

	public void setEmailHost(String emailHost)
	{
		this.emailHost = emailHost;
	}

	public int getEmailPort()
	{
		return emailPort;
	}

	public void setEmailPort(int emailPort)
	{
		this.emailPort = emailPort;
	}

	public String getEmailUsername()
	{
		return emailUsername;
	}

	public void setEmailUsername(String emailUsername)
	{
		this.emailUsername = emailUsername;
	}

	public String getEmailPassword()
	{
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword)
	{
		this.emailPassword = emailPassword;
	}

	public String getEmailEncoding()
	{
		return emailEncoding;
	}

	public void setEmailEncoding(String emailEncoding)
	{
		this.emailEncoding = emailEncoding;
	}

	public String getEmailPersonal()
	{
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal)
	{
		this.emailPersonal = emailPersonal;
	}

	//	public String getForgotPasswordSubject()
	//	{
	//		return forgotPasswordSubject;
	//	}
	//
	//	public void setForgotPasswordSubject(String forgotPasswordSubject)
	//	{
	//		this.forgotPasswordSubject = forgotPasswordSubject;
	//	}

	public Set<LocaleNameBean> getForgotPasswordSubjects()
	{
		return forgotPasswordSubjects.getNames();
	}

	public void setForgotPasswordSubjects(Set<LocaleNameBean> forgotPasswordSubjects)
	{
		this.forgotPasswordSubjects.setNames(forgotPasswordSubjects);
	}

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

	//	public String getForgotPasswordText()
	//	{
	//		return forgotPasswordText;
	//	}
	//
	//	public void setForgotPasswordText(String forgotPasswordText)
	//	{
	//		this.forgotPasswordText = forgotPasswordText;
	//	}

	public Set<LocaleNameBean> getForgotPasswordTexts()
	{
		return forgotPasswordTexts.getNames();
	}

	public void setForgotPasswordTexts(Set<LocaleNameBean> forgotPasswordTexts)
	{
		this.forgotPasswordTexts.setNames(forgotPasswordTexts);
	}

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

	//	public String getRegisterSubject()
	//	{
	//		return registerSubject;
	//	}
	//
	//	public void setRegisterSubject(String registerSubject)
	//	{
	//		this.registerSubject = registerSubject;
	//	}

	public Set<LocaleNameBean> getRegisterSubjects()
	{
		return registerSubjects.getNames();
	}

	public void setRegisterSubjects(Set<LocaleNameBean> registerSubjects)
	{
		this.registerSubjects.setNames(registerSubjects);
	}

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

	//	public String getRegisterText()
	//	{
	//		return registerText;
	//	}
	//
	//	public void setRegisterText(String registerText)
	//	{
	//		this.registerText = registerText;
	//	}

	public Set<LocaleNameBean> getRegisterTexts()
	{
		return registerTexts.getNames();
	}

	public void setRegisterTexts(Set<LocaleNameBean> registerTexts)
	{
		this.registerTexts.setNames(registerTexts);
	}

	public String getRegisterText()
	{
		return registerTexts.getName();
	}

	public void setRegisterText(String registerText)
	{
		registerTexts.setName(registerText);
	}

	public String getRegisterText(Locale locale)
	{
		return registerTexts.getName(locale);
	}

	public void setRegisterText(Locale locale, String registerText)
	{
		registerTexts.setName(locale, registerText);
	}

	public boolean getMark()
	{
		return mark;
	}

	public void setMark(boolean mark)
	{
		this.mark = mark;
	}

	public int getMarkWidth()
	{
		return markWidth;
	}

	public void setMarkWidth(int markWidth)
	{
		this.markWidth = markWidth;
	}

	public int getMarkHeight()
	{
		return markHeight;
	}

	public void setMarkHeight(int markHeight)
	{
		this.markHeight = markHeight;
	}

	public String getMarkImagePath()
	{
		return markImagePath;
	}

	public void setMarkImagePath(String markImagePath)
	{
		this.markImagePath = markImagePath;
	}

	public String getMarkContent()
	{
		return markContent;
	}

	public void setMarkContent(String markContent)
	{
		this.markContent = markContent;
	}

	public int getMarkSize()
	{
		return markSize;
	}

	public void setMarkSize(int markSize)
	{
		this.markSize = markSize;
	}

	public String getMarkColor()
	{
		return markColor;
	}

	public void setMarkColor(String markColor)
	{
		this.markColor = markColor;
	}

	public int getMarkAlpha()
	{
		return markAlpha;
	}

	public void setMarkAlpha(int markAlpha)
	{
		this.markAlpha = markAlpha;
	}

	public int getMarkPos()
	{
		return markPos;
	}

	public void setMarkPos(int markPos)
	{
		this.markPos = markPos;
	}

	public int getMarkOffsetX()
	{
		return markOffsetX;
	}

	public void setMarkOffsetX(int markOffsetX)
	{
		this.markOffsetX = markOffsetX;
	}

	public int getMarkOffsetY()
	{
		return markOffsetY;
	}

	public void setMarkOffsetY(int markOffsetY)
	{
		this.markOffsetY = markOffsetY;
	}

	public boolean getMember()
	{
		return member;
	}

	public void setMember(boolean member)
	{
		this.member = member;
	}

	public boolean getRegister()
	{
		return register;
	}

	public void setRegister(boolean register)
	{
		this.register = register;
	}

	public int getUsernameMinLen()
	{
		return usernameMinLen;
	}

	public void setUsernameMinLen(int usernameMinLen)
	{
		this.usernameMinLen = usernameMinLen;
	}

	public int getPasswordMinLen()
	{
		return passwordMinLen;
	}

	public void setPasswordMinLen(int passwordMinLen)
	{
		this.passwordMinLen = passwordMinLen;
	}

	public int getUserImgWidth()
	{
		return userImgWidth;
	}

	public void setUserImgWidth(int userImgWidth)
	{
		this.userImgWidth = userImgWidth;
	}

	public int getUserImgHeight()
	{
		return userImgHeight;
	}

	public void setUserImgHeight(int userImgHeight)
	{
		this.userImgHeight = userImgHeight;
	}

	public String getUsernameReserved()
	{
		return usernameReserved;
	}

	public void setUsernameReserved(String usernameReserved)
	{
		this.usernameReserved = usernameReserved;
	}

	//	public String getForgetPasswordSubject()
	//	{
	//		return forgetPasswordSubject;
	//	}
	//
	//	public void setForgetPasswordSubject(String forgetPasswordSubject)
	//	{
	//		this.forgetPasswordSubject = forgetPasswordSubject;
	//	}

	public Set<LocaleNameBean> getForgetPasswordSubjects()
	{
		return forgetPasswordSubjects.getNames();
	}

	public void setForgetPasswordSubjects(Set<LocaleNameBean> forgetPasswordSubjects)
	{
		this.forgetPasswordSubjects.setNames(forgetPasswordSubjects);
	}

	public String getForgetPasswordSubject()
	{
		return forgetPasswordSubjects.getName();
	}

	public void setForgetPasswordSubject(String forgetPasswordSubject)
	{
		forgetPasswordSubjects.setName(forgetPasswordSubject);
	}

	public String getForgetPasswordSubject(Locale locale)
	{
		return forgetPasswordSubjects.getName(locale);
	}

	public void setForgetPasswordSubject(Locale locale, String forgetPasswordSubject)
	{
		forgetPasswordSubjects.setName(locale, forgetPasswordSubject);
	}

	//	public String getForgetPasswordContext()
	//	{
	//		return forgetPasswordContext;
	//	}
	//
	//	public void setForgetPasswordContext(String forgetPasswordContext)
	//	{
	//		this.forgetPasswordContext = forgetPasswordContext;
	//	}

	public Set<LocaleNameBean> getForgetPasswordContexts()
	{
		return forgetPasswordContexts.getNames();
	}

	public void setForgetPasswordContexts(Set<LocaleNameBean> forgetPasswordContexts)
	{
		this.forgetPasswordContexts.setNames(forgetPasswordContexts);
	}

	public String getForgetPasswordContext()
	{
		return forgetPasswordContexts.getName();
	}

	public void setForgetPasswordContext(String forgetPasswordContext)
	{
		forgetPasswordContexts.setName(forgetPasswordContext);
	}

	public String getForgetPasswordContext(Locale locale)
	{
		return forgetPasswordContexts.getName(locale);
	}

	public void setForgetPasswordContext(Locale locale, String forgetPasswordContext)
	{
		forgetPasswordContexts.setName(locale, forgetPasswordContext);
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
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
		builder.append("forgotPasswordSubjects", forgotPasswordSubjects);
		builder.append("forgotPasswordTexts", forgotPasswordTexts);
		builder.append("registerSubjects", registerSubjects);
		builder.append("registerTexts", registerTexts);
		return builder.toString();
	}

	public Object clone()
	{
		ConfigImpl copy = null;
		copy = (ConfigImpl) super.clone();
		//
		copy.forgotPasswordSubjects = clone(forgotPasswordSubjects);
		copy.forgotPasswordTexts = clone(forgotPasswordTexts);
		copy.registerSubjects = clone(registerSubjects);
		copy.registerTexts = clone(registerTexts);
		return copy;
	}

}

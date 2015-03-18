package org.openyu.cms.config.vo;

import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.SeqIdAuditBean;
import com.sun.xml.bind.AnyTypeAdapter;

@XmlJavaTypeAdapter(AnyTypeAdapter.class)
/**
 * 環境設定
 */
public interface Config extends SeqIdAuditBean
{

	String KEY = Config.class.getName();

	/**
	 * 部署路徑
	 */
	String getSysDeployeePath();

	void setSysDeployeePath(String contextPath);

	/**
	 * Servlet掛載點
	 */
	String getServletPoint();

	void setServletPoint(String servletPoint);

	/**
	 * Port
	 */
	int getSysPort();

	void setSysPort(int sysPort);

	/**
	 * 資料庫附件訪問位址
	 */
	String getSysDbFileUri();

	void setSysDbFileUri(String sysDbFileUri);

	/**
	 * 上傳附件至資料庫
	 */
	boolean getSysUploadToDb();

	void setSysUploadToDb(boolean sysUploadToDb);

	/**
	 * 圖片不存在時預設圖片
	 */
	String getSysDefaultImg();

	void setSysDefaultImg(String sysDefaultImg);

	/**
	 * 登入地址
	 */
	String getLoginUrl();

	void setLoginUrl(String loginUrl);

	/**
	 * 登錄後處理位址
	 */
	String getProcessUrl();

	void setProcessUrl(String processUrl);

	/**
	 * 計數器清除時間
	 */
	Date getCountClearTime();

	void setCountClearTime(Date countClearTime);

	/**
	 * 計數器拷貝時間
	 */
	Date getCountCopyTime();

	void setCountCopyTime(Date countCopyTime);

	/**
	 * 下載防盜鏈md5混淆碼
	 */
	String getDownloadCode();

	void setDownloadCode(String downloadCode);

	/**
	 * 下載有效時間（小時）
	 */
	int getDownloadTime();

	void setDownloadTime(int downloadTime);

	/**
	 * 開啟郵箱驗證
	 * 
	 * @return
	 */
	boolean getSysEmailValidate();

	void setSysEmailValidate(boolean sysEmailValidate);

	//	com.jeecms.cms.entity.main.MarkConfig getMarkConfig();
	//
	//	/**
	//	 * Set the value related to the column: ${prop.Column}
	//	 * @param m_markConfig the ${prop.Column} value
	//	 */
	//	void setMarkConfig(com.jeecms.cms.entity.main.MarkConfig m_markConfig);
	//--------------------------------------------------------------------------------MarkConfig-start
	/**
	 * 開啟圖片浮水印
	 */
	boolean getMark();

	void setMark(boolean mark);

	/**
	 * mark_width 圖片最小寬度
	 */
	int getMarkWidth();

	void setMarkWidth(int markWidth);

	/**
	 * mark_height 圖片最小高度
	 */
	int getMarkHeight();

	void setMarkHeight(int minHeight);

	/**
	 * mark_image 圖片浮水印
	 */
	String getMarkImagePath();

	void setMarkImagePath(String imagePath);

	/**
	 * mark_content 文字浮水印內容
	 */
	String getMarkContent();

	void setMarkContent(String content);

	/**
	 * mark_size 文字浮水印大小
	 */
	int getMarkSize();

	void setMarkSize(int size);

	/**
	 * mark_color 文字浮水印顏色
	 */
	String getMarkColor();

	void setMarkColor(String color);

	/**
	 * mark_alpha 浮水印透明度（0-100）
	 */
	int getMarkAlpha();

	void setMarkAlpha(int alpha);

	/**
	 * mark_position 浮水印位置(0-5)
	 */
	int getMarkPos();

	void setMarkPos(int pos);

	/**
	 * mark_offset_x x座標偏移量
	 */
	int getMarkOffsetX();

	void setMarkOffsetX(int offsetX);

	/**
	 * mark_offset_y y座標偏移量
	 */
	int getMarkOffsetY();

	void setMarkOffsetY(int offsetY);

	//--------------------------------------------------------------------------------MarkConfig-end
	//--------------------------------------------------------------------------------MemberConfig
	/**
	 * 開啟會員功能
	 */
	boolean getMember();

	void setMember(boolean member);

	/**
	 * 開啟會員註冊
	 */
	boolean getRegister();

	void setRegister(boolean register);

	/**
	 * 使用者名最小長度
	 */
	int getUsernameMinLen();

	void setUsernameMinLen(int usernameMinLen);

	/**
	 * 密碼最小長度
	 */
	int getPasswordMinLen();

	void setPasswordMinLen(int passwordMinLen);

	/**
	 * 使用者頭像寬度
	 */
	int getUserImgWidth();

	void setUserImgWidth(int userImgWidth);

	/**
	 * 使用者頭像高度
	 */
	int getUserImgHeight();

	void setUserImgHeight(int userImgHeight);

	/**
	 * 使用者名保留字
	 */
	String getUsernameReserved();

	void setUsernameReserved(String usernameReserved);

	//--------------------------------------------------------------------------------MemberConfig
	//--------------------------------------------------------------------------------loginConfig
	/**
	 * 登錄錯誤次數
	 */
	int getLoginErrorTimes();

	void setLoginErrorTimes(int loginErrorTimes);

	/**
	 * 登錄錯誤時間
	 */
	int getLoginErrorInterval();

	void setLoginErrorInterval(int loginErrorInterval);

	/**
	 * 郵件伺服器
	 */
	String getEmailHost();

	void setEmailHost(String emailHost);

	/**
	 * 郵件埠
	 */
	int getEmailPort();

	void setEmailPort(int emailPort);

	/**
	 * 郵件用戶名
	 */
	String getEmailUsername();

	void setEmailUsername(String emailUsername);

	/**
	 * 郵件密碼
	 */
	String getEmailPassword();

	void setEmailPassword(String emailPassword);

	/**
	 * 郵件編碼
	 */
	String getEmailEncoding();

	void setEmailEncoding(String emailEncoding);

	/**
	 * 寄件者
	 */
	String getEmailPersonal();

	void setEmailPersonal(String emailPersonal);

	/**
	 * 找回密碼標題
	 */
	String getForgotPasswordSubject();

	void setForgotPasswordSubject(String forgotPasswordSubject);

	String getForgotPasswordSubject(Locale locale);

	void setForgotPasswordSubject(Locale locale, String forgotPasswordSubject);

	/**
	 * 找回密碼內容
	 */
	String getForgotPasswordText();

	void setForgotPasswordText(String forgotPasswordText);

	String getForgotPasswordText(Locale locale);

	void setForgotPasswordText(Locale locale, String forgotPasswordText);

	/**
	 * 會員註冊標題
	 */
	String getRegisterSubject();

	void setRegisterSubject(String registerSubject);

	String getRegisterSubject(Locale locale);

	void setRegisterSubject(Locale locale, String registerSubject);

	/**
	 * 會員註冊內容
	 */
	String getRegisterText();

	void setRegisterText(String registerText);

	String getRegisterText(Locale locale);

	void setRegisterText(Locale locale, String registerText);

}
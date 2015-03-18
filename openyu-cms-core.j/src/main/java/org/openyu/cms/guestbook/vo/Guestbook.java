package org.openyu.cms.guestbook.vo;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.SeqIdAuditBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 留言
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Guestbook extends SeqIdAuditBean
{
	String KEY = Guestbook.class.getName();

	/**
	 * 留言標題
	 * 
	 * @return
	 */
	Set<LocaleNameBean> getTitles();

	void setTitles(Set<LocaleNameBean> titles);

	String getTitle();

	void setTitle(String title);

	String getTitle(Locale locale);

	void setTitle(Locale locale, String title);

	//
	/**
	 * 留言內容
	 * 
	 * @return
	 */
	Set<LocaleNameBean> getContents();

	void setContents(Set<LocaleNameBean> contents);

	String getContent();

	void setContent(String title);

	String getContent(Locale locale);

	void setContent(Locale locale, String title);

	/**
	 * 回復內容
	 * 
	 * @return
	 */
	Set<LocaleNameBean> getReplys();

	void setReplys(Set<LocaleNameBean> replys);

	String getReply();

	void setReply(String reply);

	String getReply(Locale locale);

	void setReply(Locale locale, String reply);

	/**
	 * 電子郵件
	 * 
	 * @return
	 */
	String getEmail();

	void setEmail(String email);

	/**
	 * 電話
	 * 
	 * @return
	 */
	String getPhone();

	void setPhone(String phone);

	/**
	 * QQ
	 * 
	 * @return
	 */
	String getQq();

	void setQq(String qq);

	/**
	 * 留言IP
	 * 
	 * @return
	 */
	String getIp();

	void setIp(String ip);

	/**
	 * 留言時間
	 * 
	 * @return
	 */
	Date getGuestbookDate();

	void setGuestbookDate(Date guestbookDate);

	/**
	 * 回復時間
	 * 
	 * @return
	 */
	Date getReplayDate();

	void setReplayDate(Date replayDate);

	/**
	 * 是否審核
	 * 
	 * @return
	 */
	boolean getChecked();

	void setChecked(boolean checked);

	/**
	 * 是否推薦
	 * 
	 * @return
	 */
	boolean getRecommend();

	void setRecommend(boolean recommend);

	/**
	 * 留言會員
	 * 
	 * @return
	 */
	User getMember();

	void setMember(User member);

	/**
	 * 回復管理員
	 * 
	 * @return
	 */
	User getAdmin();

	void setAdmin(User admin);

	/**
	 * 網站
	 * 
	 * @return
	 */
	Site getSite();

	void setSite(Site site);

	/**
	 * 留言類別
	 * 
	 * @return
	 */
	GuestbookType getGuestbookType();

	void setGuestbookType(GuestbookType guestbookType);
}
package org.openyu.cms.guestbook.po;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.openyu.cms.guestbookType.po.GuestbookTypePo;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.user.po.UserPo;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.SeqIdAuditEntity;

/**
 * 留言
 */
public interface GuestbookPo extends SeqIdAuditEntity
{
	String KEY = GuestbookPo.class.getName();

	/**
	 * 留言標題
	 * 
	 * @return
	 */
	Set<LocaleNameEntity> getTitles();

	void setTitles(Set<LocaleNameEntity> titles);

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
	Set<LocaleNameEntity> getContents();

	void setContents(Set<LocaleNameEntity> contents);

	String getContent();

	void setContent(String title);

	String getContent(Locale locale);

	void setContent(Locale locale, String title);

	/**
	 * 回復內容
	 * 
	 * @return
	 */
	Set<LocaleNameEntity> getReplys();

	void setReplys(Set<LocaleNameEntity> replys);

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
	Boolean getChecked();

	void setChecked(Boolean checked);

	/**
	 * 是否推薦
	 * 
	 * @return
	 */
	Boolean getRecommend();

	void setRecommend(Boolean recommend);

	/**
	 * 留言會員
	 * 
	 * @return
	 */
	UserPo getMember();

	void setMember(UserPo member);

	/**
	 * 回復管理員
	 * 
	 * @return
	 */
	UserPo getAdmin();

	void setAdmin(UserPo admin);

	/**
	 * 網站
	 * 
	 * @return
	 */
	SitePo getSite();

	void setSite(SitePo site);

	/**
	 * 留言類別
	 * 
	 * @return
	 */
	GuestbookTypePo getGuestbookType();

	void setGuestbookType(GuestbookTypePo guestbookType);

}
package org.openyu.cms.user.po;

import org.openyu.commons.entity.SeqIdAuditNamesEntity;

public interface UserPo extends SeqIdAuditNamesEntity
{
	String KEY = UserPo.class.getName();

	//	/**
	//	 * 用戶名
	//	 * @return
	//	 */
	//	String getUsername();
	//
	//	void setUsername(String username);
	//
	//	/**
	//	 * Email
	//	 * @return
	//	 */
	//	String getEmail();
	//
	//	void setEmail(String email);
	//
	//	/**
	//	 * 註冊時間
	//	 * @return
	//	 */
	//	Date getRegisterTime();
	//
	//	void setRegisterTime(Date registerTime);
	//
	//	/**
	//	 * 註冊IP
	//	 * @return
	//	 */
	//	String getRegisterIp();
	//
	//	void setRegisterIp(String registerIp);
	//
	//	/**
	//	 * 最後登錄時間
	//	 * @return
	//	 */
	//	Date getLastLoginTime();
	//
	//	void setLastLoginTime(Date lastLoginTime);
	//
	//	/**
	//	 * 最後登錄IP
	//	 * @return
	//	 */
	//	String getLastLoginIp();
	//
	//	void setLastLoginIp(String lastLoginIp);
	//
	//	/**
	//	 * 登錄次數
	//	 * @return
	//	 */
	//	Integer getLoginCount();
	//
	//	void setLoginCount(Integer loginCount);
	//
	//	/**
	//	 * 管理員級別
	//	 * @return
	//	 */
	//	Integer getRank();
	//
	//	void setRank(Integer rank);
	//
	//	/**
	//	 * 上傳總大小
	//	 * @return
	//	 */
	//	Long getUploadTotal();
	//
	//	void setUploadTotal(Long uploadTotal);
	//
	//	/**
	//	 * 上傳大小
	//	 * @return
	//	 */
	//	Integer getUploadSize();
	//
	//	void setUploadSize(Integer uploadSize);
	//
	//	/**
	//	 * 上傳日期
	//	 * @return
	//	 */
	//	Date getUploadDate();
	//
	//	void setUploadDate(Date uploadDate);
	//
	//	/**
	//	 * 是否管理員
	//	 * @return
	//	 */
	//	Boolean getAdmin();
	//
	//	void setAdmin(Boolean admin);
	//
	//	/**
	//	 * 是否唯讀管理員
	//	 * @return
	//	 */
	//	Boolean getViewonlyAdmin();
	//
	//	void setViewonlyAdmin(Boolean viewonlyAdmin);
	//
	//	/**
	//	 * 是否只管理自己的資料
	//	 * @return
	//	 */
	//	Boolean getSelfAdmin();
	//
	//	void setSelfAdmin(Boolean selfAdmin);
	//
	//	/**
	//	 * 是否禁用
	//	 * @return
	//	 */
	//	Boolean getValid();
	//
	//	void setValid(Boolean valid);
	//
	//	/**
	//	 * 群組
	//	 * @return
	//	 */
	//	Group getGroup();
	//
	//	void setGroup(Group group);
	//
	//	/**
	//	 * 
	//	 */
	//	//	Set<UserExt> getUserExtSet();
	//	//
	//	//	void setUserExtSet(Set<CmsUserExt> userExtSet);
	//
	//	/**
	//	 * 真實姓名
	//	 * @return
	//	 */
	//	String getRealname();
	//
	//	void setRealname(String realname);
	//
	//	/**
	//	 * 性別
	//	 * @return
	//	 */
	//	Boolean getGender();
	//
	//	void setGender(Boolean gender);
	//
	//	/**
	//	 * 出生日期
	//	 * @return
	//	 */
	//	Date getBirthday();
	//
	//	void setBirthday(Date birthday);
	//
	//	/**
	//	 * 個人介紹
	//	 * @return
	//	 */
	//	String getIntro();
	//
	//	void setIntro(String intro);
	//
	//	/**
	//	 * 來自
	//	 * @return
	//	 */
	//	String getComefrom();
	//
	//	void setComefrom(String comefrom);
	//
	//	/**
	//	 * QQ
	//	 * @return
	//	 */
	//	String getQq();
	//
	//	void setQq(String qq);
	//
	//	/**
	//	 * MSN
	//	 * @return
	//	 */
	//	String getMsn();
	//
	//	void setMsn(String msn);
	//
	//	/**
	//	 * 電話
	//	 * @return
	//	 */
	//	String getPhone();
	//
	//	void setPhone(String phone);
	//
	//	/**
	//	 * 手機
	//	 * @return
	//	 */
	//	String getMobile();
	//
	//	void setMobile(String mobile);
	//
	//	/**
	//	 * 用戶頭像
	//	 * @return
	//	 */
	//	String getUserImg();
	//
	//	void setUserImg(String userImg);
	//
	//	/**
	//	 * 用戶個性簽名
	//	 * @return
	//	 */
	//	String getUserSignature();
	//
	//	void setUserSignature(String userSignature);
	//
	//	/**
	//	 * 多個管理員網站表項目形成"管理員網站表模組"
	//	 * @return
	//	 */
	//	Set<UserSite> getUserSites();
	//
	//	void setUserSites(Set<UserSite> userSites);
	//
	//	/**
	//	 * 多個角色表項目形成"角色表模組"
	//	 * @return
	//	 */
	//	Set<Role> getRoles();
	//
	//	void setRoles(Set<Role> roles);
	//
	//	/**
	//	 * 多個CMS欄目表項目形成"CMS欄目表模組"
	//	 * 
	//	 * @return
	//	 */
	//	Set<Channel> getChannels();
	//
	//	void setChannels(Set<Channel> channels);
	//
	//	/**
	//	 * 多個CSM內容表項目形成"CSM內容表模組"
	//	 * 
	//	 * @return
	//	 */
	//	Set<Content> getCollectContents();
	//
	//	void setCollectContents(Set<Content> collectContents);
	//
	//	/**
	//	 * 多個發送訊息項目形成"發送訊息模組"
	//	 * 
	//	 * @return
	//	 */
	//	Set<Message> getSendMessages();
	//
	//	void setSendMessages(Set<Message> sendMessages);
	//
	//	/**
	//	 * 多個接收訊息項目形成"接收訊息模組"
	//	 * 
	//	 * @return
	//	 */
	//	Set<Message> getReceivMessages();
	//
	//	void setReceivMessages(Set<Message> receivMessages);
	//
	//	/**
	//	 * 多個站內信收信表項目形成"站內信收信表模組"
	//	 * 
	//	 * @return
	//	 */
	//	Set<ReceiverMessage> getSendReceiverMessages();
	//
	//	void setSendReceiverMessages(Set<ReceiverMessage> sendReceiverMessages);
	//
	//	/**
	//	 * 多個接收站內信收信表項目形成"接收站內信收信表模組"
	//	 * 
	//	 * @return
	//	 */
	//	Set<ReceiverMessage> getReceivReceiverMessages();
	//
	//	void setReceivReceiverMessages(Set<ReceiverMessage> receivReceiverMessages);

}
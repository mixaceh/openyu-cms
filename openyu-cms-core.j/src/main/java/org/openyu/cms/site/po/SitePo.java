package org.openyu.cms.site.po;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.openyu.cms.ftp.po.FtpPo;
import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 網站
 */
public interface SitePo extends SeqIdAuditNamesEntity
{
	String KEY = SitePo.class.getName();

	/**
	 * 資源路徑
	 * 
	 * @return
	 */
	String getResourcePath();

	void setResourcePath(String resourcePath);

	/**
	 * 網站簡稱
	 * 
	 * @return
	 */
	String getShortName();

	void setShortName(String shortName);

	String getShortName(Locale locale);

	void setShortName(Locale locale, String shortName);

	Set<LocaleNameEntity> getShortNames();

	void setShortNames(Set<LocaleNameEntity> shortNames);

	/**
	 * 訪問協議
	 * 
	 * @return
	 */
	String getProtocol();

	void setProtocol(String protocol);

	/**
	 * 動態頁尾碼,建議使用.yhtml為尾碼，以避免衝突
	 * 
	 * @return
	 */
	String getDynamicSuffix();

	void setDynamicSuffix(String dynamicSuffix);

	/**
	 * 靜態頁尾碼
	 * 
	 * @return
	 */
	String getStaticSuffix();

	void setStaticSuffix(String staticSuffix);

	/**
	 * 靜態頁路徑
	 * 
	 * @return
	 */
	String getStaticPath();

	void setStaticPath(String staticPath);

	/**
	 * 是否首頁放在根目錄下
	 * 
	 * @return
	 */
	Boolean getIndexRoot();

	void setIndexRoot(Boolean indexRoot);

	/**
	 * 是否靜態化首頁
	 * 
	 * @return
	 */
	Boolean getStaticIndex();

	void setStaticIndex(Boolean staticIndex);

	/**
	 * 樣版路徑
	 * 
	 * @return
	 */
	String getTemplatePath();

	void setTemplatePath(String templatePath);

	/**
	 * 終審類別
	 * 
	 * @return
	 */
	VerifyType getVerifyType();

	void setVerifyType(VerifyType verifyType);

	/**
	 * 審核後修改類別(1:不能修改刪除;2:修改後退回;3:修改後不變)
	 * 
	 * @return
	 */
	ModifyType getModifyType();

	void setModifyType(ModifyType modifyType);

	/**
	 * 是否使用相對路徑
	 * 
	 * @return
	 */
	Boolean getRelativePath();

	void setRelativePath(Boolean relativePath);

	/**
	 * 是否開啟回收站
	 * 
	 * @return
	 */
	Boolean getRecover();

	void setRecover(Boolean recover);

	/**
	 * 網域別名
	 * 
	 * @return
	 */
	String getAlias();

	void setAlias(String alias);

	/**
	 * 網域重導
	 * 
	 * @return
	 */
	String getRedirect();

	void setRedirect(String redirect);

	/**
	 * 附件FTP
	 * 
	 * @return
	 */
	FtpPo getFtp();

	void setFtp(FtpPo ftp);

	/**
	 * 屬性
	 * 
	 * @return
	 */
	Map<String, String> getAttributes();

	void setAttributes(Map<String, String> attributes);

	/**
	 * 文字
	 * 
	 * @return
	 */
	Map<String, String> getTexts();

	void setTexts(Map<String, String> texts);

	/**
	 * 設定
	 * 
	 * @return
	 */
	Map<String, String> getConfigs();

	void setConfigs(Map<String, String> configs);

}
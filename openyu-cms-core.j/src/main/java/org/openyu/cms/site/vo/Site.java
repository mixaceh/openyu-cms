package org.openyu.cms.site.vo;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import org.openyu.commons.enumz.IntEnum;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 網站
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Site extends SeqIdAuditNamesBean
{
	String KEY = Site.class.getName();

	/**
	 * 終審類型
	 */
	public enum VerifyType implements IntEnum
	{
		/**
		 * 預設0
		 */
		_0(0),

		/**
		 * 
		 */
		_1(1),

		/**
		 * 
		 */
		_2(2),

		//
		;
		private final int intValue;

		private VerifyType(int intValue)
		{
			this.intValue = intValue;
		}

		public int getValue()
		{
			return intValue;
		}
	}

	/**
	 * 審核後修改類型(1:不能修改刪除;2:修改後退回;3:修改後不變)
	 */
	public enum ModifyType implements IntEnum
	{

		/**
		 * 不能修改刪除
		 */
		CAN_NOT_MODIFY(1),

		/**
		 * 修改後退回
		 */
		WITHDRAW(2),

		/**
		 * 修改後不變
		 */
		UNCHANGING(3),

		//
		;
		private final int intValue;

		private ModifyType(int intValue)
		{
			this.intValue = intValue;
		}

		public int getValue()
		{
			return intValue;
		}
	}

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

	Set<LocaleNameBean> getShortNames();

	void setShortNames(Set<LocaleNameBean> shortNames);

	/**
	 * 訪問協定
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
	 * 靜態頁放路徑
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
	boolean getIndexRoot();

	void setIndexRoot(boolean indexRoot);

	/**
	 * 是否靜態化首頁
	 * 
	 * @return
	 */
	boolean getStaticIndex();

	void setStaticIndex(boolean staticIndex);

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
	boolean getRelativePath();

	void setRelativePath(boolean relativePath);

	/**
	 * 是否開啟回收站
	 * 
	 * @return
	 */
	boolean getRecover();

	void setRecover(boolean recover);

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
	Ftp getFtp();

	void setFtp(Ftp ftp);

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

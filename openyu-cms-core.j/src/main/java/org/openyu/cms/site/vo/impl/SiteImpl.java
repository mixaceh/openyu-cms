package org.openyu.cms.site.vo.impl;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.cms.ftp.vo.adapter.FtpXmlAdapter;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.NamesBean;
import org.openyu.commons.bean.adapter.NamesBeanXmlAdapter;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "site")
@XmlAccessorType(XmlAccessType.FIELD)
public class SiteImpl extends SeqIdAuditNamesBeanSupporter implements Site
{

	private static final long serialVersionUID = 3382679203623255559L;

	//網域domain -> id
	//private String domain; -> id

	/**
	 * 資源路徑
	 */
	private String resourcePath;

	/**
	 * 網站簡稱
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean shortNames = new NamesBeanSupporter();

	/**
	 * 訪問協定
	 */
	private String protocol;

	/**
	 * 動態頁尾碼,建議使用.yhtml為尾碼，以避免衝突
	 */
	private String dynamicSuffix;

	/**
	 * 靜態頁尾碼
	 */
	private String staticSuffix;

	/**
	 * 靜態頁路徑
	 */
	private String staticPath;

	/**
	 * 是否首頁放在根目錄下
	 */
	private boolean indexRoot;

	/**
	 * 是否靜態化首頁
	 */
	private boolean staticIndex;

	/**
	 * 樣版路徑
	 */
	private String templatePath;

	/**
	 * 終審類別
	 */
	private VerifyType verifyType;

	/**
	 * 審核後修改類別(1:不能修改刪除;2:修改後退回;3:修改後不變)
	 */
	private ModifyType modifyType;

	/**
	 * 是否使用相對路徑
	 */
	private boolean relativePath;

	/**
	 * 是否開啟回收站
	 */
	private boolean recover;

	/**
	 * 網域別名
	 */
	private String alias;

	/**
	 * 網域重導
	 */
	private String redirect;

	/**
	 * 附件FTP
	 */
	@XmlJavaTypeAdapter(FtpXmlAdapter.class)
	private Ftp ftp;

	/**
	 * 屬性
	 */
	private Map<String, String> attributes = new LinkedHashMap<String, String>();

	/**
	 * 文字
	 */
	private Map<String, String> texts = new LinkedHashMap<String, String>();

	/**
	 * 設定
	 */
	private Map<String, String> configs = new LinkedHashMap<String, String>();

	public SiteImpl(String id)
	{
		setId(id);
	}

	public SiteImpl()
	{
		this(null);
	}

	public SiteImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	public String getResourcePath()
	{
		return resourcePath;
	}

	public void setResourcePath(String resourcePath)
	{
		this.resourcePath = resourcePath;
	}

	public String getShortName()
	{
		return shortNames.getName();
	}

	public void setShortName(String shortName)
	{
		shortNames.setName(shortName);
	}

	public String getShortName(Locale locale)
	{
		return shortNames.getName(locale);
	}

	public void setShortName(Locale locale, String shortName)
	{
		shortNames.setName(locale, shortName);
	}

	public Set<LocaleNameBean> getShortNames()
	{
		return shortNames.getNames();
	}

	public void setShortNames(Set<LocaleNameBean> shortNames)
	{
		this.shortNames.setNames(shortNames);
	}

	public String getProtocol()
	{
		return protocol;
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	public String getDynamicSuffix()
	{
		return dynamicSuffix;
	}

	public void setDynamicSuffix(String dynamicSuffix)
	{
		this.dynamicSuffix = dynamicSuffix;
	}

	public String getStaticSuffix()
	{
		return staticSuffix;
	}

	public void setStaticSuffix(String staticSuffix)
	{
		this.staticSuffix = staticSuffix;
	}

	public String getStaticPath()
	{
		return staticPath;
	}

	public void setStaticPath(String staticPath)
	{
		this.staticPath = staticPath;
	}

	public boolean getIndexRoot()
	{
		return indexRoot;
	}

	public void setIndexRoot(boolean indexRoot)
	{
		this.indexRoot = indexRoot;
	}

	public boolean getStaticIndex()
	{
		return staticIndex;
	}

	public void setStaticIndex(boolean staticIndex)
	{
		this.staticIndex = staticIndex;
	}

	public String getTemplatePath()
	{
		return templatePath;
	}

	public void setTemplatePath(String templatePath)
	{
		this.templatePath = templatePath;
	}

	public VerifyType getVerifyType()
	{
		return verifyType;
	}

	public void setVerifyType(VerifyType verifyType)
	{
		this.verifyType = verifyType;
	}

	public ModifyType getModifyType()
	{
		return modifyType;
	}

	public void setModifyType(ModifyType modifyType)
	{
		this.modifyType = modifyType;
	}

	public boolean getRelativePath()
	{
		return relativePath;
	}

	public void setRelativePath(boolean relativePath)
	{
		this.relativePath = relativePath;
	}

	public boolean getRecover()
	{
		return recover;
	}

	public void setRecover(boolean recover)
	{
		this.recover = recover;
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public String getRedirect()
	{
		return redirect;
	}

	public void setRedirect(String redirect)
	{
		this.redirect = redirect;
	}

	public Ftp getFtp()
	{
		return ftp;
	}

	public void setFtp(Ftp ftp)
	{
		this.ftp = ftp;
	}

	public Map<String, String> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes)
	{
		this.attributes = attributes;
	}

	public Map<String, String> getTexts()
	{
		return texts;
	}

	public void setTexts(Map<String, String> texts)
	{
		this.texts = texts;
	}

	public Map<String, String> getConfigs()
	{
		return configs;
	}

	public void setConfigs(Map<String, String> configs)
	{
		this.configs = configs;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("resourcePath", resourcePath);
		append(builder, "shortNames", shortNames);
		builder.append("protocol", protocol);
		builder.append("dynamicSuffix", dynamicSuffix);
		builder.append("staticSuffix", staticSuffix);
		builder.append("staticPath", staticPath);
		builder.append("indexRoot", indexRoot);
		builder.append("staticIndex", staticIndex);
		builder.append("templatePath", templatePath);
		builder.append("verifyType", verifyType);
		builder.append("modifyType", modifyType);
		builder.append("relativePath", relativePath);
		builder.append("recover", recover);
		builder.append("alias", alias);
		builder.append("redirect", redirect);
		builder.append("ftp", (ftp != null ? ftp.getSeq() + ", " + ftp.getId() : null));
		builder.append("attributes", attributes);
		builder.append("texts", texts);
		builder.append("configs", configs);
		return builder.toString();
	}

	public Object clone()
	{
		SiteImpl copy = null;
		copy = (SiteImpl) super.clone();
		copy.shortNames = clone(shortNames);
		copy.ftp = clone(ftp);
		copy.attributes = clone(attributes);
		copy.texts = clone(texts);
		copy.configs = clone(configs);
		return copy;
	}
}

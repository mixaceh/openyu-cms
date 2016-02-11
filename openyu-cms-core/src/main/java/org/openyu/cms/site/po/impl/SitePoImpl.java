package org.openyu.cms.site.po.impl;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import org.openyu.cms.ftp.po.FtpPo;
import org.openyu.cms.ftp.po.impl.FtpPoImpl;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.po.bridge.ModifyTypeBridge;
import org.openyu.cms.site.po.bridge.VerifyTypeBridge;
import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.NamesEntity;
import org.openyu.commons.entity.bridge.NamesEntityBridge;
import org.openyu.commons.entity.bridge.StringStringBridge;
import org.openyu.commons.entity.supporter.NamesEntitySupporter;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;
//--------------------------------------------------
//hibernate
//--------------------------------------------------

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_site")
@SequenceGenerator(name = "cms_site_g", sequenceName = "cms_site_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.site.po.impl.SitePoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_site", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_site_1", columnNames = { "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class SitePoImpl extends SeqIdAuditNamesEntitySupporter implements SitePo
{

	private static final long serialVersionUID = -7770042241224108180L;

	/**
	 * seq
	 */
	private Long seq;

	//網域domain -> id
	//private String domain; -> id

	/**
	 * 資源路徑
	 */
	private String resourcePath;

	/**
	 * 網站簡稱
	 */
	private NamesEntity shortNames = new NamesEntitySupporter();

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
	private Boolean indexRoot;

	/**
	 * 是否靜態化首頁
	 */
	private Boolean staticIndex;

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
	private Boolean relativePath;

	/**
	 * 是否開啟回收站
	 */
	private Boolean recover;

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
	private FtpPo ftp;

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

	public SitePoImpl()
	{}

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_site_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "resource_path", length = 30)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getResourcePath()
	{
		return resourcePath;
	}

	public void setResourcePath(String resourcePath)
	{
		this.resourcePath = resourcePath;
	}

	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "short_names", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getShortNames()
	{
		return shortNames.getNames();
	}

	public void setShortNames(Set<LocaleNameEntity> shortNames)
	{
		this.shortNames.setNames(shortNames);
	}

	@Transient
	public String getShortName()
	{
		return shortNames.getName();
	}

	public void setShortName(String shortName)
	{
		shortNames.setName(shortName);
	}

	@Transient
	public String getShortName(Locale locale)
	{
		return shortNames.getName(locale);
	}

	public void setShortName(Locale locale, String shortName)
	{
		shortNames.setName(locale, shortName);
	}

	@Column(name = "protocol", length = 30)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getProtocol()
	{
		return protocol;
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	@Column(name = "dynamic_suffix", length = 10)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getDynamicSuffix()
	{
		return dynamicSuffix;
	}

	public void setDynamicSuffix(String dynamicSuffix)
	{
		this.dynamicSuffix = dynamicSuffix;
	}

	@Column(name = "static_suffix", length = 10)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getStaticSuffix()
	{
		return staticSuffix;
	}

	public void setStaticSuffix(String staticSuffix)
	{
		this.staticSuffix = staticSuffix;
	}

	@Column(name = "static_path", length = 50)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getStaticPath()
	{
		return staticPath;
	}

	public void setStaticPath(String staticPath)
	{
		this.staticPath = staticPath;
	}

	@Column(name = "index_root")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getIndexRoot()
	{
		return indexRoot;
	}

	public void setIndexRoot(Boolean indexRoot)
	{
		this.indexRoot = indexRoot;
	}

	@Column(name = "static_index")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getStaticIndex()
	{
		return staticIndex;
	}

	public void setStaticIndex(Boolean staticIndex)
	{
		this.staticIndex = staticIndex;
	}

	@Column(name = "template_path", length = 30)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getTemplatePath()
	{
		return templatePath;
	}

	public void setTemplatePath(String templatePath)
	{
		this.templatePath = templatePath;
	}

	@Column(name = "verify_type", length = 13)
	@Type(type = "org.openyu.cms.site.po.userType.VerifyTypeUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = VerifyTypeBridge.class)
	public VerifyType getVerifyType()
	{
		return verifyType;
	}

	public void setVerifyType(VerifyType verifyType)
	{
		this.verifyType = verifyType;
	}

	@Column(name = "modify_type", length = 13)
	@Type(type = "org.openyu.cms.site.po.userType.ModifyTypeUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = ModifyTypeBridge.class)
	public ModifyType getModifyType()
	{
		return modifyType;
	}

	public void setModifyType(ModifyType modifyType)
	{
		this.modifyType = modifyType;
	}

	@Column(name = "relative_path")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getRelativePath()
	{
		return relativePath;
	}

	public void setRelativePath(Boolean relativePath)
	{
		this.relativePath = relativePath;
	}

	@Column(name = "recover")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getRecover()
	{
		return recover;
	}

	public void setRecover(Boolean recover)
	{
		this.recover = recover;
	}

	@Column(name = "alias", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	@Column(name = "redirect", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getRedirect()
	{
		return redirect;
	}

	public void setRedirect(String redirect)
	{
		this.redirect = redirect;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(targetEntity = FtpPoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "ftp_seq")
	@IndexedEmbedded(targetElement = FtpPoImpl.class, depth = 1)
	public FtpPo getFtp()
	{
		return ftp;
	}

	public void setFtp(FtpPo ftp)
	{
		this.ftp = ftp;
	}

	@Column(name = "attributes", length = 1024)
	@Type(type = "org.openyu.commons.entity.userType.StringStringUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = StringStringBridge.class)
	public Map<String, String> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes)
	{
		this.attributes = attributes;
	}

	@Column(name = "texts", length = 1024)
	@Type(type = "org.openyu.commons.entity.userType.StringStringUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = StringStringBridge.class)
	public Map<String, String> getTexts()
	{
		return texts;
	}

	public void setTexts(Map<String, String> texts)
	{
		this.texts = texts;
	}

	@Column(name = "configs", length = 1024)
	@Type(type = "org.openyu.commons.entity.userType.StringStringUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = StringStringBridge.class)
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
		SitePoImpl copy = null;
		copy = (SitePoImpl) super.clone();
		copy.shortNames = clone(shortNames);
		copy.ftp = clone(ftp);
		copy.attributes = clone(attributes);
		copy.texts = clone(texts);
		copy.configs = clone(configs);
		return copy;
	}

}

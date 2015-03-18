package org.openyu.cms.module.po.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

import org.openyu.cms.module.po.ModulePo;
import org.openyu.cms.module.po.bridge.CatalogItemsBridge;
import org.openyu.cms.module.po.bridge.ContextItemsBridge;
import org.openyu.cms.module.vo.CatalogItem;
import org.openyu.cms.module.vo.ContextItem;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_module")
@SequenceGenerator(name = "cms_module_g", sequenceName = "cms_module_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.module.po.impl.ModulePoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_module", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_module_1", columnNames = {
		"valid", "sort", "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class ModulePoImpl extends SeqIdAuditNamesEntitySupporter implements ModulePo
{

	private static final long serialVersionUID = 7193261243378684799L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 是否有效
	 */
	private Boolean valid;

	/**
	 * 路徑
	 */
	private String path;

	/**
	 * 目錄範本首碼
	 */
	private String catalogPrefix;

	/**
	 * 本文範本首碼
	 */
	private String contextPrefix;

	/**
	 * 目錄標題圖寬度
	 */
	private Integer catalogImgWidth;

	/**
	 * 目錄標題圖高度
	 */
	private Integer catalogImgHeight;

	/**
	 * 本文圖寬度
	 */
	private Integer contextImgWidth;

	/**
	 * 本文圖高度
	 */
	private Integer contextImgHeight;

	/**
	 * 排列順序
	 */
	private Integer sort;

	/**
	 * 是否有本文
	 */
	private Boolean context;

	/**
	 * 是否預設
	 */
	private Boolean defaultz;

	/**
	 * 多個目錄項目形成"目錄模組"
	 */
	private Set<CatalogItem> catalogItems = new LinkedHashSet<CatalogItem>();

	/**
	 * 多個本文項目形成"本文模組"
	 */
	private Set<ContextItem> contextItems = new LinkedHashSet<ContextItem>();

	public ModulePoImpl()
	{}

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_module_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "valid")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getValid()
	{
		return valid;
	}

	public void setValid(Boolean valid)
	{
		this.valid = valid;
	}

	@Column(name = "path", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	@Column(name = "catalog_prefix", length = 30)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getCatalogPrefix()
	{
		return catalogPrefix;
	}

	public void setCatalogPrefix(String catalogPrefix)
	{
		this.catalogPrefix = catalogPrefix;
	}

	@Column(name = "context_prefix", length = 30)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getContextPrefix()
	{
		return contextPrefix;
	}

	public void setContextPrefix(String contextPrefix)
	{
		this.contextPrefix = contextPrefix;
	}

	@Column(name = "catalog_img_width")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getCatalogImgWidth()
	{
		return catalogImgWidth;
	}

	public void setCatalogImgWidth(Integer catalogImgWidth)
	{
		this.catalogImgWidth = catalogImgWidth;
	}

	@Column(name = "catalog_img_height")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getCatalogImgHeight()
	{
		return catalogImgHeight;
	}

	public void setCatalogImgHeight(Integer catalogImgHeight)
	{
		this.catalogImgHeight = catalogImgHeight;
	}

	@Column(name = "context_img_width")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getContextImgWidth()
	{
		return contextImgWidth;
	}

	public void setContextImgWidth(Integer contextImgWidth)
	{
		this.contextImgWidth = contextImgWidth;
	}

	@Column(name = "context_img_height")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getContextImgHeight()
	{
		return contextImgHeight;
	}

	public void setContextImgHeight(Integer contextImgHeight)
	{
		this.contextImgHeight = contextImgHeight;
	}

	@Column(name = "sort")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getSort()
	{
		return sort;
	}

	public void setSort(Integer sort)
	{
		this.sort = sort;
	}

	@Column(name = "context")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getContext()
	{
		return context;
	}

	public void setContext(Boolean context)
	{
		this.context = context;
	}

	@Column(name = "defaultz")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getDefault()
	{
		return defaultz;
	}

	public void setDefault(Boolean defaultz)
	{
		this.defaultz = defaultz;
	}

	@Column(name = "catalog_items", length = 8192)
	@Type(type = "org.openyu.cms.module.po.userType.CatalogItemsUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = CatalogItemsBridge.class)
	public Set<CatalogItem> getCatalogItems()
	{
		return catalogItems;
	}

	public void setCatalogItems(Set<CatalogItem> catalogItems)
	{
		this.catalogItems = catalogItems;
	}

	@Column(name = "context_items", length = 8192)
	@Type(type = "org.openyu.cms.module.po.userType.ContextItemsUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = ContextItemsBridge.class)
	public Set<ContextItem> getContextItems()
	{
		return contextItems;
	}

	public void setContextItems(Set<ContextItem> contextItems)
	{
		this.contextItems = contextItems;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("valid", valid);
		builder.append("path", path);
		builder.append("catalogPrefix", catalogPrefix);
		builder.append("contextPrefix", contextPrefix);
		builder.append("catalogImgWidth", catalogImgWidth);
		builder.append("catalogImgHeight", catalogImgHeight);
		builder.append("contextImgWidth", contextImgWidth);
		builder.append("contextImgHeight", contextImgHeight);
		builder.append("sort", sort);
		builder.append("context", context);
		builder.append("default", defaultz);
		builder.append("catalogItems", catalogItems);
		builder.append("contextItems", contextItems);
		return builder.toString();
	}

	public Object clone()
	{
		ModulePoImpl copy = null;
		copy = (ModulePoImpl) super.clone();
		copy.catalogItems = clone(catalogItems);
		copy.contextItems = clone(contextItems);
		return copy;
	}
}

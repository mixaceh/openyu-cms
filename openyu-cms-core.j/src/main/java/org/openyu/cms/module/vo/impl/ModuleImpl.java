package org.openyu.cms.module.vo.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.module.vo.CatalogItem;
import org.openyu.cms.module.vo.ContextItem;
import org.openyu.cms.module.vo.Module;
import org.openyu.cms.module.vo.adapter.CatalogItemSetXmlAdapter;
import org.openyu.cms.module.vo.adapter.ContextItemSetXmlAdapter;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.bean.impl.WhetherOptionImpl;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "module")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModuleImpl extends SeqIdAuditNamesBeanSupporter implements Module
{
	private static final long serialVersionUID = -1598050492851804695L;

	/**
	 * 是否有效
	 */
	private boolean valid;

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
	private int catalogImgWidth;

	/**
	 * 目錄標題圖高度
	 */
	private int catalogImgHeight;

	/**
	 * 本文圖寬度
	 */
	private int contextImgWidth;

	/**
	 * 本文圖高度
	 */
	private int contextImgHeight;

	/**
	 * 排列順序
	 */
	private int sort;

	/**
	 * 是否有本文
	 */
	private boolean context;

	/**
	 * 是否預設
	 */
	private boolean dft;

	/**
	 * 多個目錄項目形成"目錄模組"
	 */
	@XmlJavaTypeAdapter(CatalogItemSetXmlAdapter.class)
	private Set<CatalogItem> catalogItems = new LinkedHashSet<CatalogItem>();

	/**
	 * 多個本文項目形成"本文模組"
	 */
	@XmlJavaTypeAdapter(ContextItemSetXmlAdapter.class)
	private Set<ContextItem> contextItems = new LinkedHashSet<ContextItem>();

	/**
	 * 搜尋用,是否啟用選項
	 */
	@XmlTransient
	private WhetherOption validOption = new WhetherOptionImpl(WhetherType.ALL);

	public ModuleImpl(String id)
	{
		setId(id);
	}

	public ModuleImpl()
	{
		this(null);
	}

	public boolean getValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getCatalogPrefix()
	{
		return catalogPrefix;
	}

	public void setCatalogPrefix(String catalogPrefix)
	{
		this.catalogPrefix = catalogPrefix;
	}

	public String getContextPrefix()
	{
		return contextPrefix;
	}

	public void setContextPrefix(String contextPrefix)
	{
		this.contextPrefix = contextPrefix;
	}

	public int getCatalogImgWidth()
	{
		return catalogImgWidth;
	}

	public void setCatalogImgWidth(int catalogImgWidth)
	{
		this.catalogImgWidth = catalogImgWidth;
	}

	public int getCatalogImgHeight()
	{
		return catalogImgHeight;
	}

	public void setCatalogImgHeight(int catalogImgHeight)
	{
		this.catalogImgHeight = catalogImgHeight;
	}

	public int getContextImgWidth()
	{
		return contextImgWidth;
	}

	public void setContextImgWidth(int contextImgWidth)
	{
		this.contextImgWidth = contextImgWidth;
	}

	public int getContextImgHeight()
	{
		return contextImgHeight;
	}

	public void setContextImgHeight(int contextImgHeight)
	{
		this.contextImgHeight = contextImgHeight;
	}

	public int getSort()
	{
		return sort;
	}

	public void setSort(int sort)
	{
		this.sort = sort;
	}

	public boolean getContext()
	{
		return context;
	}

	public void setContext(boolean context)
	{
		this.context = context;
	}

	public boolean getDft()
	{
		return dft;
	}

	public void setDft(boolean dft)
	{
		this.dft = dft;
	}

	public Set<CatalogItem> getCatalogItems()
	{
		return catalogItems;
	}

	public void setCatalogItems(Set<CatalogItem> catalogItems)
	{
		this.catalogItems = catalogItems;
	}

	public Set<ContextItem> getContextItems()
	{
		return contextItems;
	}

	public void setContextItems(Set<ContextItem> contextItems)
	{
		this.contextItems = contextItems;
	}

	public WhetherOption getValidOption()
	{
		return validOption;
	}

	public void setValidOption(WhetherOption validOption)
	{
		this.validOption = validOption;
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
		builder.append("dft", dft);
		builder.append("catalogItems", catalogItems);
		builder.append("contextItems", contextItems);
		builder.append("validOption", validOption);
		return builder.toString();
	}

	public Object clone()
	{
		ModuleImpl copy = null;
		copy = (ModuleImpl) super.clone();
		copy.catalogItems = clone(catalogItems);
		copy.contextItems = clone(contextItems);
		//
		copy.validOption = clone(validOption);
		return copy;
	}
}

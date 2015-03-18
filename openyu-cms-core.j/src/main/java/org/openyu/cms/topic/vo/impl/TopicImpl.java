package org.openyu.cms.topic.vo.impl;

import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.catalog.vo.Catalog;
import org.openyu.cms.catalog.vo.adapter.CatalogXmlAdapter;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.NamesBean;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.bean.adapter.NamesBeanXmlAdapter;
import org.openyu.commons.bean.impl.WhetherOptionImpl;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "topic")
@XmlAccessorType(XmlAccessType.FIELD)
public class TopicImpl extends SeqIdAuditNamesBeanSupporter implements Topic
{

	private static final long serialVersionUID = -6083279373308865496L;

	/**
	 * 簡稱
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean shortNames = new NamesBeanSupporter();

	/**
	 * 描述
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean descriptions = new NamesBeanSupporter();

	/**
	 * 關鍵字
	 */
	private String keyword;

	/**
	 * 標題圖
	 */
	private String titleImg;

	/**
	 * 標題圖寬度
	 */
	private int titleImgWidth;

	/**
	 * 標題圖高度
	 */
	private int titleImgHeight;

	/**
	 * 本文圖
	 */
	private String contextImg;

	/**
	 * 本文圖寬度
	 */
	private int contextImgWidth;

	/**
	 * 本文圖高度
	 */
	private int contextImgHeight;

	/**
	 * 樣版
	 */
	private String template;

	/**
	 * 排序
	 */
	private int sort;

	/**
	 * 是否推薦
	 */
	private boolean recommend;

	/**
	 * 網站
	 */
	@XmlJavaTypeAdapter(SiteXmlAdapter.class)
	private Site site;

	/**
	 * 目錄
	 */
	@XmlJavaTypeAdapter(CatalogXmlAdapter.class)
	private Catalog catalog;

	/**
	 * 搜尋用,是否推薦選項
	 */
	@XmlTransient
	private WhetherOption recommendOption = new WhetherOptionImpl(WhetherType.ALL);

	public TopicImpl(String id)
	{
		setId(id);
	}

	public TopicImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	public TopicImpl()
	{
		this(null);
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

	public String getDescription()
	{
		return descriptions.getName();
	}

	public void setDescription(String description)
	{
		descriptions.setName(description);
	}

	public String getDescription(Locale locale)
	{
		return descriptions.getName(locale);
	}

	public void setDescription(Locale locale, String description)
	{
		descriptions.setName(locale, description);
	}

	public Set<LocaleNameBean> getDescriptions()
	{
		return descriptions.getNames();
	}

	public void setDescriptions(Set<LocaleNameBean> descriptions)
	{
		this.descriptions.setNames(descriptions);
	}

	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}

	public String getTitleImg()
	{
		return titleImg;
	}

	public void setTitleImg(String titleImg)
	{
		this.titleImg = titleImg;
	}

	public int getTitleImgWidth()
	{
		return titleImgWidth;
	}

	public void setTitleImgWidth(int titleImgWidth)
	{
		this.titleImgWidth = titleImgWidth;
	}

	public int getTitleImgHeight()
	{
		return titleImgHeight;
	}

	public void setTitleImgHeight(int titleImgHeight)
	{
		this.titleImgHeight = titleImgHeight;
	}

	public String getContextImg()
	{
		return contextImg;
	}

	public void setContextImg(String contextImg)
	{
		this.contextImg = contextImg;
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

	public String getTemplate()
	{
		return template;
	}

	public void setTemplate(String template)
	{
		this.template = template;
	}

	public int getSort()
	{
		return sort;
	}

	public void setSort(int sort)
	{
		this.sort = sort;
	}

	public boolean getRecommend()
	{
		return recommend;
	}

	public void setRecommend(boolean recommend)
	{
		this.recommend = recommend;
	}

	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public Catalog getCatalog()
	{
		return catalog;
	}

	public void setCatalog(Catalog catalog)
	{
		this.catalog = catalog;
	}

	public WhetherOption getRecommendOption()
	{
		return recommendOption;
	}

	public void setRecommendOption(WhetherOption recommendOption)
	{
		this.recommendOption = recommendOption;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("shortNames", shortNames);
		builder.append("descriptions", descriptions);
		builder.append("keyword", keyword);
		builder.append("titleImg", titleImg);
		builder.append("titleImgWidth", titleImgWidth);
		builder.append("titleImgHeight", titleImgHeight);
		builder.append("contextImg", contextImg);
		builder.append("contextImgWidth", contextImgWidth);
		builder.append("contextImgHeight", contextImgHeight);
		builder.append("template", template);
		builder.append("sort", sort);
		builder.append("recommend", recommend);
		builder.append("site", (site != null ? site.getSeq() + ", " + site.getId() : null));
		builder.append("catalog", (catalog != null ? catalog.getSeq() + ", " + catalog.getId()
				: null));
		builder.append("recommendOption", recommendOption);
		return builder.toString();
	}

	public Object clone()
	{
		TopicImpl copy = null;
		copy = (TopicImpl) super.clone();
		copy.shortNames = clone(shortNames);
		copy.descriptions = clone(descriptions);
		//
		copy.site = clone(site);
		copy.catalog = clone(catalog);
		copy.recommendOption = clone(recommendOption);
		return copy;
	}

}

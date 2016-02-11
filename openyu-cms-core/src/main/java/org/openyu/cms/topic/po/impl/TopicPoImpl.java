package org.openyu.cms.topic.po.impl;

import java.util.Locale;
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

import org.openyu.cms.topic.po.TopicPo;
import org.openyu.cms.catalog.po.CatalogPo;
import org.openyu.cms.catalog.po.impl.CatalogPoImpl;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.po.impl.SitePoImpl;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.NamesEntity;
import org.openyu.commons.entity.bridge.NamesEntityBridge;
import org.openyu.commons.entity.supporter.NamesEntitySupporter;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_topic")
@SequenceGenerator(name = "cms_topic_g", sequenceName = "cms_topic_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.topic.po.impl.TopicPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_topic", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_topic_1", columnNames = {
		"recommend", "sort", "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class TopicPoImpl extends SeqIdAuditNamesEntitySupporter implements TopicPo
{

	private static final long serialVersionUID = 4445837075980937366L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 簡稱
	 */
	private NamesEntity shortNames = new NamesEntitySupporter();

	/**
	 * 描述
	 */
	private NamesEntity descriptions = new NamesEntitySupporter();

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
	 * 範本
	 */
	private String template;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 是否推薦
	 */
	private Boolean recommend;

	/**
	 * 網站
	 */
	private SitePo site;

	/**
	 * 目錄
	 */
	private CatalogPo catalog;

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_topic_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "shortNames", length = 2048)
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

	public void setShortName(String description)
	{
		shortNames.setName(description);
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

	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "descriptions", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getDescriptions()
	{
		return descriptions.getNames();
	}

	public void setDescriptions(Set<LocaleNameEntity> descriptions)
	{
		this.descriptions.setNames(descriptions);
	}

	@Transient
	public String getDescription()
	{
		return descriptions.getName();
	}

	public void setDescription(String description)
	{
		descriptions.setName(description);
	}

	@Transient
	public String getDescription(Locale locale)
	{
		return descriptions.getName(locale);
	}

	public void setDescription(Locale locale, String description)
	{
		descriptions.setName(locale, description);
	}

	@Column(name = "keyword", length = 50)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}

	@Column(name = "title_img", length = 100)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getTitleImg()
	{
		return titleImg;
	}

	public void setTitleImg(String titleImg)
	{
		this.titleImg = titleImg;
	}

	@Column(name = "title_img_width")
	public Integer getTitleImgWidth()
	{
		return titleImgWidth;
	}

	public void setTitleImgWidth(Integer titleImgWidth)
	{
		this.titleImgWidth = titleImgWidth;
	}

	@Column(name = "title_img_height")
	public Integer getTitleImgHeight()
	{
		return titleImgHeight;
	}

	public void setTitleImgHeight(Integer titleImgHeight)
	{
		this.titleImgHeight = titleImgHeight;
	}

	@Column(name = "context_img", length = 100)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getContextImg()
	{
		return contextImg;
	}

	public void setContextImg(String contextImg)
	{
		this.contextImg = contextImg;
	}

	@Column(name = "context_img_width")
	public Integer getContextImgWidth()
	{
		return contextImgWidth;
	}

	public void setContextImgWidth(Integer contextImgWidth)
	{
		this.contextImgWidth = contextImgWidth;
	}

	@Column(name = "context_img_height")
	public Integer getContextImgHeight()
	{
		return contextImgHeight;
	}

	public void setContextImgHeight(Integer contextImgHeight)
	{
		this.contextImgHeight = contextImgHeight;
	}

	@Column(name = "template", length = 100)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getTemplate()
	{
		return template;
	}

	public void setTemplate(String template)
	{
		this.template = template;
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

	@Column(name = "recommend")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getRecommend()
	{
		return recommend;
	}

	public void setRecommend(Boolean recommend)
	{
		this.recommend = recommend;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(targetEntity = SitePoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "site_seq")
	@IndexedEmbedded(targetElement = SitePoImpl.class, depth = 1)
	public SitePo getSite()
	{
		return site;
	}

	public void setSite(SitePo site)
	{
		this.site = site;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(targetEntity = CatalogPoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "catalog_seq")
	@IndexedEmbedded(targetElement = CatalogPoImpl.class, depth = 1)
	public CatalogPo getCatalog()
	{
		return catalog;
	}

	public void setCatalog(CatalogPo catalog)
	{
		this.catalog = catalog;
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
		return builder.toString();
	}

	public Object clone()
	{
		TopicPoImpl copy = null;
		copy = (TopicPoImpl) super.clone();
		copy.shortNames = clone(shortNames);
		copy.descriptions = clone(descriptions);
		//
		copy.site = clone(site);
		copy.catalog = clone(catalog);
		return copy;
	}
}

package org.openyu.cms.ad.po.impl;

import java.util.Date;

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
import org.openyu.cms.ad.po.AdPo;
import org.openyu.cms.ad.po.bridge.AdTypeBridge;
import org.openyu.cms.ad.vo.Ad.AdType;
import org.openyu.cms.adSpace.po.AdSpacePo;
import org.openyu.cms.adSpace.po.impl.AdSpacePoImpl;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.po.impl.SitePoImpl;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@Table(name = "cms_ad")
@SequenceGenerator(name = "cms_ad_g", sequenceName = "cms_ad_s", allocationSize = 1)
// when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.ad.po.impl.AdPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_ad", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_ad_1", columnNames = {
		"valid", "weight", "id" }) })
// --------------------------------------------------
// search
// --------------------------------------------------
// @Indexed
public class AdPoImpl extends SeqIdAuditNamesEntitySupporter implements AdPo {

	private static final long serialVersionUID = -3398334103133708064L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 是否有效
	 */
	private Boolean valid;

	/**
	 * 廣告類型
	 */
	private AdType adType;

	/**
	 * 標題
	 */
	private String title;

	/**
	 * 網址
	 */
	private String url;

	/**
	 * 圖示
	 */
	private String logo;

	/**
	 * 連結目標,_blank,_self
	 */
	private String target;

	/**
	 * 圖片寬度
	 */
	private Integer imgWidth;

	/**
	 * 圖片高度
	 */
	private Integer imgHeight;

	/**
	 * 點擊次數
	 */
	private Integer click;

	/**
	 * 顯示次數
	 */
	private Integer display;

	/**
	 * 權重
	 */
	private Integer weight;

	/**
	 * 開始時間
	 */
	private Date begDate;

	/**
	 * 結束時間
	 */
	private Date endDate;

	/**
	 * 網站
	 */
	private SitePo site;

	/**
	 * 廣告版位
	 */
	private AdSpacePo adSpace;

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_ad_g")
	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	@Column(name = "valid")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	@Column(name = "ad_type", length = 13)
	@Type(type = "org.openyu.cms.ad.po.userType.AdTypeUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = AdTypeBridge.class)
	public AdType getAdType() {
		return adType;
	}

	public void setAdType(AdType adType) {
		this.adType = adType;
	}

	@Column(name = "title", length = 255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "url", length = 255)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "logo", length = 100)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "target", length = 20)
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Column(name = "img_width")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}

	@Column(name = "img_height")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(Integer imgHeight) {
		this.imgHeight = imgHeight;
	}

	@Column(name = "click")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	@Column(name = "display")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	@Column(name = "weight")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Column(name = "beg_date")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Date getBegDate() {
		return begDate;
	}

	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}

	@Column(name = "end_date")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(targetEntity = SitePoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "site_seq")
	@IndexedEmbedded(targetElement = SitePoImpl.class, depth = 1)
	public SitePo getSite() {
		return site;
	}

	public void setSite(SitePo site) {
		this.site = site;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(targetEntity = AdSpacePoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "ad_space_seq")
	@IndexedEmbedded(targetElement = AdSpacePoImpl.class, depth = 1)
	public AdSpacePo getAdSpace() {
		return adSpace;
	}

	public void setAdSpace(AdSpacePo adSpace) {
		this.adSpace = adSpace;
	}

	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("valid", valid);
		builder.append("adType", adType);
		builder.append("title", title);
		builder.append("url", url);
		builder.append("logo", logo);
		builder.append("target", target);
		builder.append("imgWidth", imgWidth);
		builder.append("imgHeight", imgHeight);
		builder.append("click", click);
		builder.append("display", display);
		builder.append("weight", weight);
		builder.append("begDate", begDate);
		builder.append("endDate", endDate);
		builder.append("site",
				(site != null ? site.getSeq() + ", " + site.getId() : null));
		builder.append("adSpace", (adSpace != null ? adSpace.getSeq() + ", "
				+ adSpace.getId() : null));
		return builder.toString();
	}

	public Object clone() {
		AdPoImpl copy = null;
		copy = (AdPoImpl) super.clone();
		//
		copy.begDate = clone(begDate);
		copy.endDate = clone(endDate);
		copy.site = clone(site);
		copy.adSpace = clone(adSpace);
		return copy;
	}
}

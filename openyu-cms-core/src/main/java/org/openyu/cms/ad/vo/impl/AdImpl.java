package org.openyu.cms.ad.vo.impl;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.vo.adapter.AdSpaceXmlAdapter;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.bean.impl.WhetherOptionImpl;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;
import org.openyu.commons.jaxb.adapter.DateXmlAdapter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "ad")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdImpl extends SeqIdAuditNamesBeanSupporter implements Ad
{

	private static final long serialVersionUID = -1470959868628623435L;

	/**
	 * 是否有效
	 */
	private boolean valid;

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
	private int imgWidth;

	/**
	 * 圖片高度
	 */
	private int imgHeight;

	/**
	 * 點擊次數
	 */
	private int click;

	/**
	 * 顯示次數
	 */
	private int display;

	/**
	 * 權重
	 */
	private int weight;

	/**
	 * 開始時間
	 */
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	private Date begDate;

	/**
	 * 結束時間
	 */
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	private Date endDate;

	/**
	 * 網站
	 */
	@XmlJavaTypeAdapter(SiteXmlAdapter.class)
	private Site site;

	/**
	 * 廣告版位
	 */
	@XmlJavaTypeAdapter(AdSpaceXmlAdapter.class)
	private AdSpace adSpace;

	/**
	 * 搜尋用,是否啟用選項
	 */
	@XmlTransient
	private WhetherOption validOption = new WhetherOptionImpl(WhetherType.ALL);

	public AdImpl(String id)
	{
		setId(id);
	}

	public AdImpl()
	{
		this(null);
	}

	public AdImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	public boolean getValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public AdType getAdType()
	{
		return adType;
	}

	public void setAdType(AdType adType)
	{
		this.adType = adType;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getLogo()
	{
		return logo;
	}

	public void setLogo(String logo)
	{
		this.logo = logo;
	}

	public String getTarget()
	{
		return target;
	}

	public void setTarget(String target)
	{
		this.target = target;
	}

	public int getImgWidth()
	{
		return imgWidth;
	}

	public void setImgWidth(int imgWidth)
	{
		this.imgWidth = imgWidth;
	}

	public int getImgHeight()
	{
		return imgHeight;
	}

	public void setImgHeight(int imgHeight)
	{
		this.imgHeight = imgHeight;
	}

	public int getClick()
	{
		return click;
	}

	public void setClick(int click)
	{
		this.click = click;
	}

	public int getDisplay()
	{
		return display;
	}

	public void setDisplay(int display)
	{
		this.display = display;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public Date getBegDate()
	{
		return begDate;
	}

	public void setBegDate(Date begDate)
	{
		this.begDate = begDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public AdSpace getAdSpace()
	{
		return adSpace;
	}

	public void setAdSpace(AdSpace adSpace)
	{
		this.adSpace = adSpace;
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
		builder.append("site", (site != null ? site.getSeq() + ", " + site.getId() : null));
		builder.append("adSpace", (adSpace != null ? adSpace.getSeq() + ", " + adSpace.getId()
				: null));
		builder.append("validOption", validOption);
		return builder.toString();
	}

	public Object clone()
	{
		AdImpl copy = null;
		copy = (AdImpl) super.clone();
		//
		copy.begDate = clone(begDate);
		copy.endDate = clone(endDate);
		copy.site = clone(site);
		copy.adSpace = clone(adSpace);
		//
		copy.validOption = clone(validOption);
		return copy;
	}
}

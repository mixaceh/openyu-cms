package org.openyu.cms.ad.po;

import java.util.Date;

import org.openyu.cms.ad.vo.Ad.AdType;
import org.openyu.cms.adSpace.po.AdSpacePo;
import org.openyu.cms.site.po.SitePo;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 廣告
 */
public interface AdPo extends SeqIdAuditNamesEntity
{
	String KEY = AdPo.class.getName();
	
	/**
	 * 是否有效
	 * 
	 * @return
	 */
	Boolean getValid();

	void setValid(Boolean valid);

	/**
	 * 廣告類型
	 * 
	 * @return
	 */
	AdType getAdType();

	void setAdType(AdType adType);

	/**
	 * 標題
	 * 
	 * @return
	 */
	String getTitle();

	void setTitle(String title);

	/**
	 * 網址
	 * 
	 * @return
	 */
	String getUrl();

	void setUrl(String url);

	/**
	 * 圖示
	 * 
	 * @return
	 */
	String getLogo();

	void setLogo(String logo);

	/**
	 * 連結目標,_blank,_self
	 * 
	 * @return
	 */
	String getTarget();

	void setTarget(String target);

	/**
	 * 圖片寬度
	 */
	Integer getImgWidth();

	void setImgWidth(Integer imgWidth);

	/**
	 * 圖片高度
	 */
	Integer getImgHeight();

	void setImgHeight(Integer imgHeight);

	/**
	 * 點擊次數
	 * 
	 * @return
	 */
	Integer getClick();

	void setClick(Integer click);

	/**
	 * 顯示次數
	 * 
	 * @return
	 */
	Integer getDisplay();

	void setDisplay(Integer display);

	/**
	 * 權重
	 * 
	 * @return
	 */
	Integer getWeight();

	void setWeight(Integer weight);

	/**
	 * 開始時間
	 * 
	 * @return
	 */
	Date getBegDate();

	void setBegDate(Date begDate);

	/**
	 * 結束時間
	 * 
	 * @return
	 */
	Date getEndDate();

	void setEndDate(Date endDate);

	/**
	 * 網站
	 * 
	 * @return
	 */
	SitePo getSite();

	void setSite(SitePo site);

	/**
	 * 廣告版位
	 * 
	 * @return
	 */
	AdSpacePo getAdSpace();

	void setAdSpace(AdSpacePo adSpace);

}
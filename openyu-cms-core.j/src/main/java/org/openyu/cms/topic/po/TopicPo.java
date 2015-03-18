package org.openyu.cms.topic.po;

import java.util.Locale;
import java.util.Set;

import org.openyu.cms.catalog.po.CatalogPo;
import org.openyu.cms.site.po.SitePo;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 專題
 */
public interface TopicPo extends SeqIdAuditNamesEntity
{
	String KEY = TopicPo.class.getName();

	/**
	 * 簡稱
	 * 
	 * @return
	 */
	Set<LocaleNameEntity> getShortNames();

	void setShortNames(Set<LocaleNameEntity> shortNames);

	String getShortName();

	void setShortName(String description);

	String getShortName(Locale locale);

	void setShortName(Locale locale, String shortName);

	/**
	 * 描述
	 * 
	 * @return
	 */
	Set<LocaleNameEntity> getDescriptions();

	void setDescriptions(Set<LocaleNameEntity> descriptions);

	String getDescription();

	void setDescription(String description);

	String getDescription(Locale locale);

	void setDescription(Locale locale, String description);

	/**
	 * 關鍵字
	 * 
	 * @return
	 */
	String getKeyword();

	void setKeyword(String keyword);

	/**
	 * 標題圖
	 * 
	 * @return
	 */
	String getTitleImg();

	void setTitleImg(String titleImg);

	/**
	 * 標題圖寬度
	 * 
	 * @return
	 */
	Integer getTitleImgWidth();

	void setTitleImgWidth(Integer titleImgWidth);

	/**
	 * 標題圖高度
	 * 
	 * @return
	 */
	Integer getTitleImgHeight();

	void setTitleImgHeight(Integer titleImgHeight);

	/**
	 * 本文圖
	 * 
	 * @return
	 */
	String getContextImg();

	void setContextImg(String contextImg);

	/**
	 * 本文圖寬度
	 * 
	 * @return
	 */
	Integer getContextImgWidth();

	void setContextImgWidth(Integer contextImgWidth);

	/**
	 * 本文圖高度
	 * 
	 * @return
	 */
	Integer getContextImgHeight();

	void setContextImgHeight(Integer contextImgHeight);

	/**
	 * 樣版
	 * 
	 * @return
	 */
	String getTemplate();

	void setTemplate(String template);

	/**
	 * 排序
	 * 
	 * @return
	 */
	Integer getSort();

	void setSort(Integer sort);

	/**
	 * 是否推薦
	 * 
	 * @return
	 */
	Boolean getRecommend();

	void setRecommend(Boolean recommend);

	/**
	 * 網站
	 * 
	 * @return
	 */
	SitePo getSite();

	void setSite(SitePo site);

	/**
	 * 目錄
	 * 
	 * @return
	 */
	CatalogPo getCatalog();

	void setCatalog(CatalogPo catalog);

}
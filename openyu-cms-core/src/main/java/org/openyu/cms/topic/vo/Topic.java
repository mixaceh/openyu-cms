package org.openyu.cms.topic.vo;

import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.catalog.vo.Catalog;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import org.openyu.commons.bean.WhetherOption;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 專題
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Topic extends SeqIdAuditNamesBean
{
	String KEY = Topic.class.getName();

	/**
	 * 簡稱
	 * 
	 * @return
	 */
	String getShortName();

	void setShortName(String shortName);

	String getShortName(Locale locale);

	void setShortName(Locale locale, String shortName);

	Set<LocaleNameBean> getShortNames();

	void setShortNames(Set<LocaleNameBean> shortNames);

	/**
	 * 描述
	 * 
	 * @return
	 */
	String getDescription();

	void setDescription(String description);

	String getDescription(Locale locale);

	void setDescription(Locale locale, String description);

	Set<LocaleNameBean> getDescriptions();

	void setDescriptions(Set<LocaleNameBean> descriptions);

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
	int getTitleImgWidth();

	void setTitleImgWidth(int titleImgWidth);

	/**
	 * 標題圖高度
	 * 
	 * @return
	 */
	int getTitleImgHeight();

	void setTitleImgHeight(int titleImgHeight);

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
	int getContextImgWidth();

	void setContextImgWidth(int contextImgWidth);

	/**
	 * 本文圖高度
	 * 
	 * @return
	 */
	int getContextImgHeight();

	void setContextImgHeight(int contextImgHeight);

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
	int getSort();

	void setSort(int sort);

	/**
	 * 是否推薦
	 * 
	 * @return
	 */
	boolean getRecommend();

	void setRecommend(boolean recommend);

	/**
	 * 網站
	 * 
	 * @return
	 */
	Site getSite();

	void setSite(Site site);

	/**
	 * 目錄
	 * 
	 * @return
	 */
	Catalog getCatalog();

	void setCatalog(Catalog catalog);

	/**
	 * 搜尋用,是否啟用選項
	 * 
	 * @return
	 */
	WhetherOption getRecommendOption();

	void setRecommendOption(WhetherOption recommendOption);

}
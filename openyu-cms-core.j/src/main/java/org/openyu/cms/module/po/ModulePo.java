package org.openyu.cms.module.po;

import java.util.Set;

import org.openyu.cms.module.vo.CatalogItem;
import org.openyu.cms.module.vo.ContextItem;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 模組
 */
public interface ModulePo extends SeqIdAuditNamesEntity
{
	String KEY = ModulePo.class.getName();

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	Boolean getValid();

	void setValid(Boolean valid);

	/**
	 * 路徑
	 * 
	 * @return
	 */
	String getPath();

	void setPath(String path);

	/**
	 * 目錄範本首碼
	 * 
	 * @return
	 */
	String getCatalogPrefix();

	void setCatalogPrefix(String catalogPrefix);

	/**
	 * 本文範本首碼
	 * 
	 * @return
	 */
	String getContextPrefix();

	void setContextPrefix(String contextPrefix);

	/**
	 * 目錄標題圖寬度
	 * 
	 * @return
	 */
	Integer getCatalogImgWidth();

	void setCatalogImgWidth(Integer catalogImgWidth);

	/**
	 * 目錄標題圖高度
	 * 
	 * @return
	 */
	Integer getCatalogImgHeight();

	void setCatalogImgHeight(Integer catalogImgHeight);

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
	 * 排列順序
	 * 
	 * @return
	 */
	Integer getSort();

	void setSort(Integer sort);

	/**
	 * 是否有本文
	 * 
	 * @return
	 */
	Boolean getContext();

	void setContext(Boolean context);

	/**
	 * 是否預設
	 * 
	 * @return
	 */
	Boolean getDft();

	void setDft(Boolean dft);

	/**
	 * 多個目錄項目形成"目錄模組"
	 * 
	 * @return
	 */
	Set<CatalogItem> getCatalogItems();

	void setCatalogItems(Set<CatalogItem> catalogItems);

	/**
	 * 多個本文項目形成"本文模組"
	 * 
	 * @return
	 */
	Set<ContextItem> getContextItems();

	void setContextItems(Set<ContextItem> contextItems);

}
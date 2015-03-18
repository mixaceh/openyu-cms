package org.openyu.cms.module.vo;

import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import org.openyu.commons.bean.WhetherOption;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 模組
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Module extends SeqIdAuditNamesBean
{
	String KEY = Module.class.getName();

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	boolean getValid();

	void setValid(boolean valid);

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
	int getCatalogImgWidth();

	void setCatalogImgWidth(int catalogImgWidth);

	/**
	 * 目錄標題圖高度
	 * 
	 * @return
	 */
	int getCatalogImgHeight();

	void setCatalogImgHeight(int catalogImgHeight);

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
	 * 排列順序
	 * 
	 * @return
	 */
	int getSort();

	void setSort(int sort);

	/**
	 * 是否有本文
	 * 
	 * @return
	 */
	boolean getContext();

	void setContext(boolean context);

	/**
	 * 是否預設
	 * 
	 * @return
	 */
	boolean getDefault();

	void setDefault(boolean defaultz);

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

	/**
	 * 搜尋用,是否啟用選項
	 * 
	 * @return
	 */
	WhetherOption getValidOption();

	void setValidOption(WhetherOption validOption);
}
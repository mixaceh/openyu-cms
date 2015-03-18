package org.openyu.cms.contextType.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.SeqIdAuditNamesBean;
import org.openyu.commons.bean.WhetherOption;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 本文類型
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface ContextType extends SeqIdAuditNamesBean
{
	String KEY = ContextType.class.getName();

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	boolean getValid();

	void setValid(boolean valid);

	/**
	 * 圖片寬度
	 */
	int getImgWidth();

	void setImgWidth(int imgWidth);

	/**
	 * 圖片高度
	 */
	int getImgHeight();

	void setImgHeight(int imgHeight);

	/**
	 * 是否有圖片
	 */
	boolean getImage();

	void setImage(boolean image);

	/**
	 * 搜尋用,是否啟用選項
	 * 
	 * @return
	 */
	WhetherOption getValidOption();

	void setValidOption(WhetherOption validOption);
}

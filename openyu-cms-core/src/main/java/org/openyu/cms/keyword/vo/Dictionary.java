package org.openyu.cms.keyword.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.BaseBean;
import com.sun.xml.bind.AnyTypeAdapter;

@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Dictionary extends BaseBean
{
	String KEY = Dictionary.class.getName();

	/**
	 * 關鍵字
	 * 
	 * @return
	 */
	String getKey();

	void setKey(String key);

	/**
	 * 替換字
	 * 
	 * @return
	 */
	String getValue();

	void setValue(String value);

	/**
	 * 啟用
	 * 
	 * @return
	 */
	boolean getValid();

	void setValid(boolean valid);

}
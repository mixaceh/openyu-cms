package org.openyu.cms.archive.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.BaseBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 麵包屑
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Breadcrumb extends BaseBean
{
	String KEY = Breadcrumb.class.getName();

	/**
	 * 父層路徑
	 * 
	 * /custom/resource
	 * 
	 * @return
	 */
	String getParent();

	void setParent(String parent);

	/**
	 * 完整路徑
	 * 
	 * /custom/resource/s1/default
	 * 
	 * @return
	 */
	String getPath();

	void setPath(String path);

	/**
	 * 路徑名稱
	 * 
	 * s1, default
	 * 
	 * @return
	 */
	String getName();

	void setName(String name);
}
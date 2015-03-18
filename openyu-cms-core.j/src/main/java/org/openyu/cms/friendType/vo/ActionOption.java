package org.openyu.cms.friendType.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.BaseBean;
import org.openyu.commons.bean.NamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 操作(-1/1/2/3)選項
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface ActionOption extends BaseBean, NamesBean
{

	/**
	 * 操作類別,key
	 * 
	 * @return
	 */
	ActionType getId();

	void setId(ActionType id);

}

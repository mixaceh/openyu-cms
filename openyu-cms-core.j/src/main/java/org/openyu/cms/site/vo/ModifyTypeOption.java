package org.openyu.cms.site.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.commons.bean.BaseBean;
import org.openyu.commons.bean.NamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 審核後修改類型選項
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface ModifyTypeOption extends BaseBean, NamesBean
{
	String KEY = ModifyTypeOption.class.getName();

	/**
	 * 審核後修改類型,key
	 * 
	 * @return
	 */
	ModifyType getId();

	void setId(ModifyType id);

}

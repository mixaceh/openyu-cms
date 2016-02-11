package org.openyu.cms.site.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.commons.bean.BaseBean;
import org.openyu.commons.bean.NamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 終審類型選項
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface VerifyTypeOption extends BaseBean, NamesBean
{
	String KEY = VerifyTypeOption.class.getName();

	/**
	 * 終審類型,key
	 * 
	 * @return
	 */
	VerifyType getId();

	void setId(VerifyType id);

}

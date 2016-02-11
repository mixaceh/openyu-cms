package org.openyu.cms.ad.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.ad.vo.Ad.AdType;
import org.openyu.commons.bean.BaseBean;
import org.openyu.commons.bean.NamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 廣告類型選項
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface AdTypeOption extends BaseBean, NamesBean
{
	String KEY = AdTypeOption.class.getName();

	/**
	 * 廣告類型,key
	 * 
	 * @return
	 */
	AdType getId();

	void setId(AdType id);

}

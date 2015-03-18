package org.openyu.cms.user.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 使用者
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface UserSite extends SeqIdAuditNamesBean
{
	String KEY = UserSite.class.getName();
}

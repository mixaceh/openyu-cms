package org.openyu.cms.user.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 前台使用者
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface FrontUser extends SeqIdAuditNamesBean
{
	String KEY = FrontUser.class.getName();
}

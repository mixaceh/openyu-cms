package org.openyu.cms.message.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 站內信
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Message extends SeqIdAuditNamesBean
{
	String KEY = Message.class.getName();
}

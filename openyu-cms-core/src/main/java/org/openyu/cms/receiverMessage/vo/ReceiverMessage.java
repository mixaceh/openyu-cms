package org.openyu.cms.receiverMessage.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 站內信收信表
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface ReceiverMessage extends SeqIdAuditNamesBean
{
	String KEY = ReceiverMessage.class.getName();
}

package org.openyu.cms.tag.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 標籤
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Tag extends SeqIdAuditNamesBean
{
	String KEY = Tag.class.getName();

}

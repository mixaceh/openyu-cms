package org.openyu.cms.backup.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.SeqIdAuditBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Field extends SeqIdAuditBean
{
	String KEY = Field.class.getName();

	/**
	 * 欄位名稱
	 */
	String getName();

	void setName(String name);

	/**
	 * 欄位類型
	 */
	String getFieldType();

	void setFieldType(String fieldType);

	/**
	 * 預設值
	 */
	String getFieldDefault();

	void setFieldDefault(String fieldDefault);

	/**
	 * 欄位屬性
	 */
	String getFieldProperty();

	void setFieldProperty(String fieldProperty);

	/**
	 * 註解
	 */
	String getComment();

	void setComment(String comment);

	/**
	 * 可否null
	 */
	String getNullable();

	void setNullable(String nullable);

	/**
	 * 附加屬性
	 */
	String getExtra();

	void setExtra(String extra);
}

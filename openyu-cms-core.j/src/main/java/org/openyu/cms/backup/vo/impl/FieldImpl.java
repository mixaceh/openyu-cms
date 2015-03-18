package org.openyu.cms.backup.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.backup.vo.Field;
import org.openyu.commons.bean.supporter.SeqIdAuditBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "field")
@XmlAccessorType(XmlAccessType.FIELD)
public class FieldImpl extends SeqIdAuditBeanSupporter implements Field
{

	private static final long serialVersionUID = -350527943115154224L;

	/**
	 * 欄位名稱
	 */
	private String name;

	/**
	 * 欄位類型
	 */
	private String fieldType;

	/**
	 * 預設值
	 */
	private String fieldDefault;

	/**
	 * 欄位屬性
	 */
	private String fieldProperty;

	/**
	 * 註解
	 */
	private String comment;

	/**
	 * 可否null
	 */
	private String nullable;

	/**
	 * 附加屬性
	 */
	private String extra;

	public FieldImpl(String id)
	{
		setId(id);
	}

	public FieldImpl()
	{
		this(null);
	}

	public FieldImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType;
	}

	public String getFieldDefault()
	{
		return fieldDefault;
	}

	public void setFieldDefault(String fieldDefault)
	{
		this.fieldDefault = fieldDefault;
	}

	public String getFieldProperty()
	{
		return fieldProperty;
	}

	public void setFieldProperty(String fieldProperty)
	{
		this.fieldProperty = fieldProperty;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getNullable()
	{
		return nullable;
	}

	public void setNullable(String nullable)
	{
		this.nullable = nullable;
	}

	public String getExtra()
	{
		return extra;
	}

	public void setExtra(String extra)
	{
		this.extra = extra;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("name", name);
		builder.append("fieldType", fieldType);
		builder.append("fieldDefault", fieldDefault);
		builder.append("fieldProperty", fieldProperty);
		builder.append("comment", comment);
		builder.append("nullable", nullable);
		builder.append("extra", extra);
		return builder.toString();
	}

	public Object clone()
	{
		FieldImpl copy = null;
		copy = (FieldImpl) super.clone();
		return copy;
	}
}

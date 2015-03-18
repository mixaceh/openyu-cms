package org.openyu.cms.config.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.config.vo.MarkPosOption;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;

/**
 * 排序方向
 */
//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "markPos")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarkPosOptionImpl extends NamesBeanSupporter implements MarkPosOption
{

	private static final long serialVersionUID = 866763787839145985L;
	/**
	 * 排序方向類別,key
	 */
	private MarkPosType id;

	public MarkPosOptionImpl(MarkPosType id)
	{
		this.id = id;
	}

	public MarkPosOptionImpl()
	{
		this(null);
	}

	public MarkPosType getId()
	{
		return id;
	}

	public void setId(MarkPosType id)
	{
		this.id = id;
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(id).toHashCode();
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("id", id);
		builder.appendSuper(super.toString());
		return builder.toString();
	}

	public Object clone()
	{
		MarkPosOptionImpl copy = null;
		copy = (MarkPosOptionImpl) super.clone();
		return copy;
	}

}

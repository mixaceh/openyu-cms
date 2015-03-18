package org.openyu.cms.site.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.cms.site.vo.VerifyTypeOption;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;

/**
 * 終審類型選項
 */
//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "verifyOption")
@XmlAccessorType(XmlAccessType.FIELD)
public class VerifyTypeOptionImpl extends NamesBeanSupporter implements VerifyTypeOption
{

	private static final long serialVersionUID = 8304265723935416859L;

	/**
	 * 終審類型,key
	 */
	private VerifyType id;

	public VerifyTypeOptionImpl(VerifyType id)
	{
		this.id = id;
	}

	public VerifyTypeOptionImpl()
	{
		this(null);
	}

	public VerifyType getId()
	{
		return id;
	}

	public void setId(VerifyType id)
	{
		this.id = id;
	}

	public boolean equals(Object object)
	{
		if (!(object instanceof VerifyTypeOptionImpl))
		{
			return false;
		}
		if (this == object)
		{
			return true;
		}
		VerifyTypeOptionImpl other = (VerifyTypeOptionImpl) object;
		if (id == null || other.id == null)
		{
			return false;
		}
		return new EqualsBuilder().append(id, other.id).isEquals();
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
		VerifyTypeOptionImpl copy = null;
		copy = (VerifyTypeOptionImpl) super.clone();
		return copy;
	}

}

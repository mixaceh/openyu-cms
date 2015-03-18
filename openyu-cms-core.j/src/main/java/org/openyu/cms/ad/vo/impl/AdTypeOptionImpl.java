package org.openyu.cms.ad.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.ad.vo.Ad.AdType;
import org.openyu.cms.ad.vo.AdTypeOption;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;

/**
 * 廣告類型選項
 */
//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "adOption")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdTypeOptionImpl extends NamesBeanSupporter implements AdTypeOption
{

	private static final long serialVersionUID = 8304265723935416859L;

	/**
	 * 廣告類型,key
	 */
	private AdType id;

	public AdTypeOptionImpl(AdType id)
	{
		this.id = id;
	}

	public AdTypeOptionImpl()
	{
		this(null);
	}

	public AdType getId()
	{
		return id;
	}

	public void setId(AdType id)
	{
		this.id = id;
	}

	public boolean equals(Object object)
	{
		if (!(object instanceof AdTypeOptionImpl))
		{
			return false;
		}
		if (this == object)
		{
			return true;
		}
		AdTypeOptionImpl other = (AdTypeOptionImpl) object;
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
		AdTypeOptionImpl copy = null;
		copy = (AdTypeOptionImpl) super.clone();
		return copy;
	}

}

package org.openyu.cms.site.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.site.vo.ModifyTypeOption;
import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;

/**
 * 審核後修改類型選項
 */
//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "modifyOption")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModifyTypeOptionImpl extends NamesBeanSupporter implements ModifyTypeOption
{

	private static final long serialVersionUID = 7171821047492367150L;

	/**
	 * 審核後修改類型,key
	 */
	private ModifyType id;

	public ModifyTypeOptionImpl(ModifyType id)
	{
		this.id = id;
	}

	public ModifyTypeOptionImpl()
	{
		this(null);
	}

	public ModifyType getId()
	{
		return id;
	}

	public void setId(ModifyType id)
	{
		this.id = id;
	}

	public boolean equals(Object object)
	{
		if (!(object instanceof ModifyTypeOptionImpl))
		{
			return false;
		}
		if (this == object)
		{
			return true;
		}
		ModifyTypeOptionImpl other = (ModifyTypeOptionImpl) object;
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
		ModifyTypeOptionImpl copy = null;
		copy = (ModifyTypeOptionImpl) super.clone();
		return copy;
	}

}

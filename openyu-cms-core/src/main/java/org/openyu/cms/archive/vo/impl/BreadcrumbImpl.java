package org.openyu.cms.archive.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.archive.vo.Breadcrumb;
import org.openyu.commons.bean.supporter.BaseBeanSupporter;
//--------------------------------------------------
//jaxb
//--------------------------------------------------

@XmlRootElement(name = "breadcrumb")
@XmlAccessorType(XmlAccessType.FIELD)
public class BreadcrumbImpl extends BaseBeanSupporter implements Breadcrumb
{

	private static final long serialVersionUID = -1957154526611264124L;

	private String parent;

	private String path;

	private String name;

	public BreadcrumbImpl()
	{}

	public String getParent()
	{
		return parent;
	}

	public void setParent(String parent)
	{
		this.parent = parent;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean equals(Object object)
	{
		if (!(object instanceof BreadcrumbImpl))
		{
			return false;
		}
		if (this == object)
		{
			return true;
		}
		BreadcrumbImpl other = (BreadcrumbImpl) object;
		return new EqualsBuilder().append(path, other.path).isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(path).toHashCode();
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("parent", parent);
		builder.append("path", path);
		builder.append("name", name);

		return builder.toString();
	}

	public Object clone()
	{
		BreadcrumbImpl copy = null;
		copy = (BreadcrumbImpl) super.clone();
		return copy;
	}
}

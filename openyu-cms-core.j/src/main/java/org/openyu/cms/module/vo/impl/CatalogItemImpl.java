package org.openyu.cms.module.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.module.vo.CatalogItem;
import org.openyu.cms.module.vo.supporter.ModuleItemSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "catalogItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogItemImpl extends ModuleItemSupporter implements CatalogItem
{

	private static final long serialVersionUID = 1300036391180497454L;

	public CatalogItemImpl()
	{}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		return builder.toString();
	}

	public Object clone()
	{
		CatalogItemImpl copy = null;
		copy = (CatalogItemImpl) super.clone();
		return copy;
	}

}

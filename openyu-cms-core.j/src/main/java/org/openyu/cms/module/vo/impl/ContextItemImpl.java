package org.openyu.cms.module.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.module.vo.ContextItem;
import org.openyu.cms.module.vo.supporter.ModuleItemSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "contextItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContextItemImpl extends ModuleItemSupporter implements ContextItem
{

	private static final long serialVersionUID = -4084289682327632659L;

	public ContextItemImpl()
	{}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		return builder.toString();
	}

	public Object clone()
	{
		ContextItemImpl copy = null;
		copy = (ContextItemImpl) super.clone();
		return copy;
	}

}

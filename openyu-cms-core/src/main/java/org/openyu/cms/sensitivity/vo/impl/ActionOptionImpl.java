package org.openyu.cms.sensitivity.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.sensitivity.vo.ActionOption;
import org.openyu.cms.sensitivity.vo.ActionType;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;

/**
 * 操作選項
 */
//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "actionOption")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionOptionImpl extends NamesBeanSupporter implements ActionOption
{

	private static final long serialVersionUID = 4644282406281853593L;

	/**
	 * 操作類別,key
	 */
	private ActionType id;

	public ActionOptionImpl(ActionType id)
	{
		this.id = id;
	}

	public ActionOptionImpl()
	{
		this(null);
	}

	public ActionType getId()
	{
		return id;
	}

	public void setId(ActionType id)
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
		ActionOptionImpl copy = null;
		copy = (ActionOptionImpl) super.clone();
		return copy;
	}

}

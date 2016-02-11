package org.openyu.cms.vote.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.cms.vote.vo.supporter.VoteItemSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "voteItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class VoteItemImpl extends VoteItemSupporter implements VoteItem
{

	private static final long serialVersionUID = 7140238273680570396L;

	public VoteItemImpl()
	{}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		return builder.toString();
	}

	public Object clone()
	{
		VoteItemImpl copy = null;
		copy = (VoteItemImpl) super.clone();
		return copy;
	}

}

package org.openyu.cms.tag.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.tag.vo.Tag;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "tag")
@XmlAccessorType(XmlAccessType.FIELD)
public class TagImpl extends SeqIdAuditNamesBeanSupporter implements Tag
{
	private static final long serialVersionUID = -5898105263087054995L;

	public TagImpl(String id)
	{
		setId(id);
	}

	public TagImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	public TagImpl()
	{
		this(null);
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		return builder.toString();
	}

	public Object clone()
	{
		TagImpl copy = null;
		copy = (TagImpl) super.clone();
		return copy;
	}

}

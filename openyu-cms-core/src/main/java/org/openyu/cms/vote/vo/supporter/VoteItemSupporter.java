package org.openyu.cms.vote.vo.supporter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.commons.bean.supporter.IdNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "voteItem")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class VoteItemSupporter extends IdNamesBeanSupporter implements VoteItem
{

	private static final long serialVersionUID = -6928682526430256948L;

	/**
	 * 投票數量
	 */
	private int voteCount;

	/**
	 * 排序
	 */
	private int sort;

	public VoteItemSupporter()
	{}

	public int getVoteCount()
	{
		return voteCount;
	}

	public void setVoteCount(int voteCount)
	{
		this.voteCount = voteCount;
	}

	public int getSort()
	{
		return sort;
	}

	public void setSort(int priority)
	{
		this.sort = priority;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		//
		builder.append("voteCount", voteCount);
		builder.append("sort", sort);
		//
		return builder.toString();
	}

	public Object clone()
	{
		VoteItemSupporter copy = null;
		copy = (VoteItemSupporter) super.clone();
		return copy;
	}

}

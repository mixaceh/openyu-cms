package org.openyu.cms.vote.vo.impl;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.vote.vo.VoteItemView;
import org.openyu.commons.bean.supporter.IdBeanSupporter;

public class VoteItemViewImpl extends IdBeanSupporter implements VoteItemView
{

	private static final long serialVersionUID = 7210966527824126167L;
	
	/**
	 * 投票標題
	 */
	private String voteName;
	
	/**
	 * 投票數量
	 */
	private int voteCount;

	/**
	 * 排序
	 */
	private int sort;
	
	public VoteItemViewImpl()
	{}

	public String getVoteName()
	{
		return voteName;
	}


	public void setVoteName(String voteName)
	{
		this.voteName = voteName;
	}

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

	public void setSort(int sort)
	{
		this.sort = sort;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("voteName", voteName);
		builder.append("voteCount", voteCount);
		builder.append("sort", sort);
		return builder.toString();
	}

	public Object clone()
	{
		VoteItemViewImpl copy = null;
		copy = (VoteItemViewImpl) super.clone();
		return copy;
	}

}

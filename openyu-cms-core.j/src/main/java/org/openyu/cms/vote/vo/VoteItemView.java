package org.openyu.cms.vote.vo;


import org.openyu.commons.bean.IdBean;

/**
 * 投票項目
 * 
 * VoteItem
 */
public interface VoteItemView extends IdBean
{
	String KEY = VoteItemView.class.getName();

	/**
	 * 投票數量
	 * @return
	 */
	String getVoteName();

	void setVoteName(String voteName);

	/**
	 * 投票數量
	 * @return
	 */
	int getVoteCount();

	void setVoteCount(int voteCount);

	/**
	 * 排序
	 */
	int getSort();

	void setSort(int sort);


}
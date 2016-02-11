package org.openyu.cms.vote.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.site.vo.Site;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.vo.impl.VoteImpl;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.vote.dao.VoteDao;
import org.openyu.cms.vote.po.VotePo;
import org.openyu.cms.vote.service.VoteService;
import org.openyu.cms.vote.vo.VoteCollector;
import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class VoteServiceImpl extends AppServiceSupporter implements VoteService {
	private static transient final Logger log = LogManager
			.getLogger(VoteServiceImpl.class);

	protected transient VoteCollector voteCollector = VoteCollector
			.getInstance();

	public VoteServiceImpl() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {

	}

	public VoteDao getVoteDao() {
		return (VoteDao) getOjDao();
	}

	@Autowired
	@Qualifier("voteDao")
	public void setVoteDao(VoteDao voteDao) {
		setOjDao(voteDao);
	}

	/**
	 * 查詢是否有效投票
	 * 
	 * @param valid
	 * @return
	 */
	public List<Vote> findVote(boolean valid) {
		List<VotePo> orig = getVoteDao().findVote(valid);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢是否有效投票
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	public List<Vote> findVote(Locale locale, boolean valid) {
		List<VotePo> orig = getVoteDao().findVote(locale, valid);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢預設投票
	 * 
	 * @return
	 */
	public Vote findDefaultVote() {
		VotePo orig = getVoteDao().findDefaultVote();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param vote
	 * @return
	 */
	public List<Vote> findVote(Locale locale, Vote searcher) {
		List<VotePo> orig = getVoteDao().findVote(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param vote
	 * @return
	 */
	public List<Vote> findVote(Inquiry inquiry, Locale locale, Vote searcher) {
		List<VotePo> orig = getVoteDao().findVote(inquiry, locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 修改排列順序
	 * 
	 * @param seqs
	 * @param sorts
	 * @param valids
	 * @param defaultSeq
	 * @return
	 */
	public List<Vote> updateSort(List<Long> seqs, List<Integer> sorts,
			List<Boolean> valids, long defaultSeq) {
		List<Vote> result = new LinkedList<Vote>();
		// //
		// int size = seqs.size();
		// for (int i = 0; i < size; i++)
		// {
		// Vote vote = find(VoteImpl.class, seqs.get(i));
		// if (vote == null)
		// {
		// continue;
		// }
		// //
		// vote.setSort(sorts.get(i));
		// vote.setValid(valids.get(i));
		// //
		// if (vote.getSeq() == defaultSeq)
		// {
		// vote.setDefault(true);
		// }
		// else
		// {
		// vote.setDefault(false);
		// }
		// //
		// int upd = update(vote);
		// if (upd > 0)
		// {
		// result.add(vote);
		// }
		// }
		// //
		return result;
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<Vote> findVote(Inquiry inquiry, Locale locale,
			Site siteSearcher, Vote searcher) {
		List<VotePo> orig = getVoteDao().findVote(inquiry, locale,
				siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<Vote> findVote(Inquiry inquiry, Locale locale, long siteSeq,
			Vote searcher) {
		List<VotePo> orig = getVoteDao().findVote(inquiry, locale, siteSeq,
				searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param voteId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public Vote createVote(String voteId) {
		return voteCollector.createVote(voteId);
	}
}

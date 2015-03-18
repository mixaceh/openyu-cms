package org.openyu.cms.vote.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.openyu.cms.site.vo.Site;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.app.service.AppService;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 投票服務
 */
public interface VoteService extends AppService {

	/**
	 * 查詢是否有效投票
	 * 
	 * @param valid
	 * @return
	 */
	List<Vote> findVote(boolean valid);

	/**
	 * 查詢是否有效投票
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<Vote> findVote(Locale locale, boolean valid);

	/**
	 * 查詢預設投票
	 * 
	 * @return
	 */
	Vote findDefaultVote();

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Vote> findVote(Locale locale, Vote searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Vote> findVote(Inquiry inquiry, Locale locale, Vote searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<Vote> findVote(Inquiry inquiry, Locale locale, Site siteSearcher,
			Vote searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<Vote> findVote(Inquiry inquiry, Locale locale, long siteSeq,
			Vote searcher);

	/**
	 * 修改排列順序
	 * 
	 * @param seqs
	 * @param sorts
	 * @param valids
	 * @param defaultSeq
	 * @return
	 */
	List<Vote> updateSort(List<Long> seqs, List<Integer> sorts,
			List<Boolean> valids, long defaultSeq);

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param voteId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	Vote createVote(String voteId);

}
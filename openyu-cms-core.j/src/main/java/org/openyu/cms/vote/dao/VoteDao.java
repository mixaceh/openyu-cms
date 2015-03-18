package org.openyu.cms.vote.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.vote.po.VotePo;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface VoteDao extends AppDao
{

	/**
	 * 查詢是否有效投票
	 * 
	 * @param valid
	 * @return
	 */
	List<VotePo> findVote(boolean valid);

	/**
	 * 查詢是否有效投票
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<VotePo> findVote(Locale locale, boolean valid);

	/**
	 * 查詢預設投票
	 * 
	 * @return
	 */
	VotePo findDefaultVote();

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<VotePo> findVote(Locale locale, Vote searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<VotePo> findVote(Inquiry inquiry, Locale locale, Vote searcher);
	
	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<VotePo> findVote(Inquiry inquiry, Locale locale, long siteSeq, Vote searcher);
	
	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<VotePo> findVote(Inquiry inquiry, Locale locale, Site siteSearcher, Vote searcher);

}
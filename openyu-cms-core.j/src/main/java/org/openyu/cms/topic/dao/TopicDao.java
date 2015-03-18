package org.openyu.cms.topic.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.topic.po.TopicPo;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface TopicDao extends AppDao
{

	/**
	 * 查詢專題
	 * 
	 * @return
	 */
	List<TopicPo> findTopic();

	/**
	 * 查詢專題
	 * 
	 * @param locale
	 * @return
	 */
	List<TopicPo> findTopic(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢專題,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<TopicPo> findTopic(long siteSeq);

	/**
	 * 查詢專題,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<TopicPo> findTopic(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<TopicPo> findTopic(Locale locale, Topic searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<TopicPo> findTopic(Inquiry inquiry, Locale locale, Topic searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<TopicPo> findTopic(Locale locale, long siteSeq, Topic searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<TopicPo> findTopic(Locale locale, Site siteSearcher, Topic searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<TopicPo> findTopic(Inquiry inquiry, Locale locale, long siteSeq, Topic searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<TopicPo> findTopic(Inquiry inquiry, Locale locale, Site siteSearchers, Topic searcher);

}
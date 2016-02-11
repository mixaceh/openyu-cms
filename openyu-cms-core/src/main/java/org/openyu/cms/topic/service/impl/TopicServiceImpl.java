package org.openyu.cms.topic.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.topic.dao.TopicDao;
import org.openyu.cms.topic.po.TopicPo;
import org.openyu.cms.topic.service.TopicService;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.topic.vo.TopicCollector;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class TopicServiceImpl extends AppServiceSupporter implements
		TopicService {
	private static transient final Logger log = LogManager
			.getLogger(TopicServiceImpl.class);

	protected transient TopicCollector topicCollector = TopicCollector
			.getInstance();

	public TopicServiceImpl() {
	}

	public TopicDao getTopicDao() {
		return (TopicDao) getOjDao();
	}

	@Autowired
	@Qualifier("topicDao")
	public void setTopicDao(TopicDao topicDao) {
		setOjDao(topicDao);
	}

	/**
	 * 查詢專題
	 * 
	 * @return
	 */
	public List<Topic> findTopic() {
		List<TopicPo> orig = getTopicDao().findTopic();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢專題
	 * 
	 * @param locale
	 * @return
	 */
	public List<Topic> findTopic(Locale locale) {
		List<TopicPo> orig = getTopicDao().findTopic(locale);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢專題,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<Topic> findTopic(long siteSeq) {
		List<TopicPo> orig = getTopicDao().findTopic(siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢專題,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	public List<Topic> findTopic(Locale locale, long siteSeq) {
		List<TopicPo> orig = getTopicDao().findTopic(locale, siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param topic
	 * @return
	 */
	public List<Topic> findTopic(Locale locale, Topic searcher) {
		List<TopicPo> orig = getTopicDao().findTopic(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param topic
	 * @return
	 */
	public List<Topic> findTopic(Inquiry inquiry, Locale locale, Topic searcher) {
		List<TopicPo> orig = getTopicDao().findTopic(inquiry, locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<Topic> findTopic(Locale locale, long siteSeq, Topic searcher) {
		List<TopicPo> orig = getTopicDao().findTopic(locale, siteSeq, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<Topic> findTopic(Locale locale, Site siteSearcher,
			Topic searcher) {
		List<TopicPo> orig = getTopicDao().findTopic(locale, siteSearcher,
				searcher);
		return ClassHelper.copyProperties(orig);
	}

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
	public List<Topic> findTopic(Inquiry inquiry, Locale locale, long siteSeq,
			Topic searcher) {
		List<TopicPo> orig = getTopicDao().findTopic(inquiry, locale, siteSeq,
				searcher);
		return ClassHelper.copyProperties(orig);
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
	public List<Topic> findTopic(Inquiry inquiry, Locale locale,
			Site siteSearcher, Topic searcher) {
		List<TopicPo> orig = getTopicDao().findTopic(inquiry, locale,
				siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param topicId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public Topic createTopic(String topicId) {
		return topicCollector.createTopic(topicId);
	}

}

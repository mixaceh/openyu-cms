package org.openyu.cms.topic.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.service.AppService;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 專題服務
 */
public interface TopicService extends AppService {

	/**
	 * 查詢專題
	 * 
	 * @param valid
	 * @return
	 */
	List<Topic> findTopic();

	/**
	 * 查詢專題
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<Topic> findTopic(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢專題,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<Topic> findTopic(long siteSeq);

	/**
	 * 查詢專題,,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	List<Topic> findTopic(Locale locale, long siteSeq);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Topic> findTopic(Locale locale, Topic searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Topic> findTopic(Inquiry inquiry, Locale locale, Topic searcher);

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<Topic> findTopic(Locale locale, long siteSeq, Topic searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<Topic> findTopic(Locale locale, Site siteSearcher, Topic searcher);

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
	List<Topic> findTopic(Inquiry inquiry, Locale locale, long siteSeq,
			Topic searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	List<Topic> findTopic(Inquiry inquiry, Locale locale, Site siteSearcher,
			Topic searcher);

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
	Topic createTopic(String topicId);

}
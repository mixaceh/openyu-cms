package org.openyu.cms.topic.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.topic.TopicTestSupporter;
import org.openyu.cms.topic.po.TopicPo;
import org.openyu.cms.topic.po.impl.TopicPoImpl;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.topic.vo.impl.TopicImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class TopicDaoImplTest extends TopicTestSupporter {
	/**
	 * 隨機產生專題資料
	 * 
	 * @return
	 */
	public static TopicPo randomTopicPo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_TOPIC" + UNIQUE;
		final String ZH_TW_NAME = "測試專題" + UNIQUE;
		final String ZH_CN_NAME = "測試专题" + UNIQUE;
		final String EN_US_NAME = "Test Topic" + UNIQUE;
		//
		TopicPo result = new TopicPoImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setShortName(Locale.TRADITIONAL_CHINESE, "測試簡稱");
		result.setShortName(Locale.SIMPLIFIED_CHINESE, "测试简称");
		result.setShortName(Locale.US, "Test Topic Short Name");
		//
		result.setDescription(Locale.TRADITIONAL_CHINESE, "測試描述");
		result.setDescription(Locale.SIMPLIFIED_CHINESE, "测试描述");
		result.setDescription(Locale.US, "Test Description");
		//
		result.setTitleImg(randomString());
		result.setContextImg(randomString());
		result.setTemplate(randomString());
		result.setSort(randomInt(100));
		result.setRecommend(randomBoolean());

		return result;
	}

	/**
	 * 檢核專題資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertTopicPo(TopicPo expected, TopicPo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getShortName(), actual.getShortName());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getTitleImg(), actual.getTitleImg());
		assertEquals(expected.getContextImg(), actual.getContextImg());
		assertEquals(expected.getTemplate(), actual.getTemplate());
		assertEquals(expected.getSort(), actual.getSort());
		assertEquals(expected.getRecommend(), actual.getRecommend());
		// assertEquals(expected.getSite(), actual.getSite());
	}

	@Test
	// insert -> find -> delete
	//
	// 10 times: 7237 mills.
	// 10 times: 6825 mills.
	// 10 times: 6693 mills.
	//
	// verified: ok
	public void crud() {
		int count = 10;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// 隨機
			TopicPo topicPo = randomTopicPo();
			// create
			Serializable pk = topicDao.insert(topicPo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			TopicPo foundEntity = topicDao.find(TopicPoImpl.class,
					topicPo.getSeq());
			printFind(i, foundEntity);
			assertTopicPo(topicPo, foundEntity);

			// update
			topicPo.setName("ooo");
			int updated = topicDao.update(topicPo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			TopicPo deletedEntity = topicDao.delete(TopicPoImpl.class,
					topicPo.getSeq());
			printDelete(i, deletedEntity);
			assertNotNull(deletedEntity);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// verified: ok
	public void insert() {
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// 隨機
			TopicPo topicPo = randomTopicPo();
			//
			Serializable pk = topicDao.insert(topicPo);
			printInsert(i, pk);
			assertNotNull(pk);

			TopicPo foundEntity = topicDao.find(TopicPoImpl.class,
					topicPo.getSeq());
			assertTopicPo(topicPo, foundEntity);

			System.out.println(topicPo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findTopic() {
		List<TopicPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = topicDao.findTopic();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size());
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findTopicBySite() {
		List<TopicPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = topicDao.findTopic(1L);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size());
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	/**
	 * 查詢條件
	 */
	public void findTopicByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Topic searcher = new TopicImpl();
		//
		List<TopicPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = topicDao.findTopic(inquiry, Locale.TRADITIONAL_CHINESE,
					searcher);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(inquiry.getPagination());
		System.out.println(result.size() + ", " + result);
		//
		assertNotNull(result);
	}

	@Test
	/**
	 * 查詢條件
	 */
	public void findTopicByInquirySite() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Topic searcher = new TopicImpl();
		//
		List<TopicPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = topicDao.findTopic(inquiry, Locale.TRADITIONAL_CHINESE,
					1L, searcher);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(inquiry.getPagination());
		System.out.println(result.size() + ", " + result);
		//
		assertNotNull(result);
		//
		searcher = new TopicImpl();
		searcher.setSeq(1);
		result = topicDao.findTopic(inquiry, Locale.TRADITIONAL_CHINESE, null,
				searcher);
		System.out.println(result.size() + ", " + result);
	}
}

package org.openyu.cms.topic.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;

import org.junit.Test;

import org.openyu.cms.topic.TopicTestSupporter;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.topic.vo.impl.TopicImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class TopicServiceImplTest extends TopicTestSupporter
{
	/**
	 * 隨機產生專題資料
	 * 
	 * @return
	 */
	public static Topic randomTopic()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_AD_SPACE" + UNIQUE;
		final String ZH_TW_NAME = "測試專題" + UNIQUE;
		final String ZH_CN_NAME = "测试专题" + UNIQUE;
		final String EN_US_NAME = "Test Topic Space" + UNIQUE;
		//
		Topic result = new TopicImpl();
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
	public static void assertTopic(Topic expected, Topic actual)
	{
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
//		assertEquals(expected.getValid(), actual.getValid());
		assertEquals(expected.getSite(), actual.getSite());
	}

	@Test
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void findTopic()
	{

		List<Topic> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = topicService.findTopic();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size());
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void findTopicBySite()
	{

		List<Topic> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = topicService.findTopic(1L);
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
	public void findTopicByInquiry()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		Topic searcher = new TopicImpl();
		//
		List<Topic> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = topicService.findTopic(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
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
	public void findTopicByInquirySite()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		Topic searcher = new TopicImpl();
		//
		List<Topic> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = topicService.findTopic(inquiry, Locale.TRADITIONAL_CHINESE, 1L, searcher);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(inquiry.getPagination());
		System.out.println(result.size() + ", " + result);
		//
		assertNotNull(result);
	}
}

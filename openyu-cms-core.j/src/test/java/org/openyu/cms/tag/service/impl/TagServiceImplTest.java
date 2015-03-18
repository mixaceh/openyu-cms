package org.openyu.cms.tag.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;

import org.junit.Test;

import org.openyu.cms.tag.TagTestSupporter;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.tag.vo.impl.TagImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class TagServiceImplTest extends TagTestSupporter
{
	/**
	 * 隨機產生標籤資料
	 * 
	 * @return
	 */
	public static Tag randomTag()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_TAG" + UNIQUE;
		final String ZH_TW_NAME = "測試標籤" + UNIQUE;
		final String ZH_CN_NAME = "测试标签" + UNIQUE;
		final String EN_US_NAME = "Test Tag" + UNIQUE;
		//
		Tag result = new TagImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		return result;
	}

	/**
	 * 檢核標籤資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertTag(Tag expected, Tag actual)
	{
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
	}

	@Test
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void findTag()
	{

		List<Tag> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = tagService.findTag();
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
	public void findTagBySite()
	{

		List<Tag> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = tagService.findTag();
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
	public void findTagByInquiry()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		Tag searcher = new TagImpl();
		//
		List<Tag> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = tagService.findTag(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
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
	public void findTagByInquirySite()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		Tag searcher = new TagImpl();
		//
		List<Tag> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = tagService.findTag(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
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

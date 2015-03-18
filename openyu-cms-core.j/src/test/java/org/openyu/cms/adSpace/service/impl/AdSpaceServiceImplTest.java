package org.openyu.cms.adSpace.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;

import org.junit.Test;

import org.openyu.cms.adSpace.AdSpaceTestSupporter;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.vo.impl.AdSpaceImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class AdSpaceServiceImplTest extends AdSpaceTestSupporter
{
	/**
	 * 隨機產生廣告版位資料
	 * 
	 * @return
	 */
	public static AdSpace randomAdSpace()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_AD_SPACE" + UNIQUE;
		final String ZH_TW_NAME = "測試廣告版位" + UNIQUE;
		final String ZH_CN_NAME = "測試广告版位" + UNIQUE;
		final String EN_US_NAME = "Test Ad Space" + UNIQUE;
		//
		AdSpace result = new AdSpaceImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setValid(randomBoolean());
		//
		result.setDescription(Locale.TRADITIONAL_CHINESE, "測試描述");
		result.setDescription(Locale.SIMPLIFIED_CHINESE, "测试描述");
		result.setDescription(Locale.US, "Test Description");
		return result;
	}

	/**
	 * 檢核廣告版位資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertAdSpace(AdSpace expected, AdSpace actual)
	{
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getValid(), actual.getValid());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getSite(), actual.getSite());
	}

	@Test
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void findAdSpace()
	{

		List<AdSpace> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = adSpaceService.findAdSpace();
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
	public void findAdSpaceBySite()
	{

		List<AdSpace> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = adSpaceService.findAdSpace(1L);
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
	public void findAdSpaceByInquiry()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		AdSpace searcher = new AdSpaceImpl();
		//
		List<AdSpace> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = adSpaceService.findAdSpace(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
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
	public void findAdSpaceByInquirySite()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		AdSpace searcher = new AdSpaceImpl();
		//
		List<AdSpace> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = adSpaceService.findAdSpace(inquiry, Locale.TRADITIONAL_CHINESE, 1L, searcher);
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

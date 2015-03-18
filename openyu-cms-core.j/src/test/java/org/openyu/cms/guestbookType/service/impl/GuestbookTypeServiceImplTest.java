package org.openyu.cms.guestbookType.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;

import org.junit.Test;

import org.openyu.cms.guestbookType.GuestbookTypeTestSupporter;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class GuestbookTypeServiceImplTest extends GuestbookTypeTestSupporter
{
	/**
	 * 隨機產生留言類型資料
	 * 
	 * @return
	 */
	public static GuestbookType randomGuestbookType()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_GUESTBOOK_CTG" + UNIQUE;
		final String ZH_TW_NAME = "測試留言類型" + UNIQUE;
		final String ZH_CN_NAME = "測試留言类型" + UNIQUE;
		final String EN_US_NAME = "Test Gustbook Type" + UNIQUE;
		//
		GuestbookType result = new GuestbookTypeImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setSort(randomInt());
		//
		result.setDescription(Locale.TRADITIONAL_CHINESE, "測試描述");
		result.setDescription(Locale.SIMPLIFIED_CHINESE, "测试描述");
		result.setDescription(Locale.US, "Test Description");
		return result;
	}

	/**
	 * 檢核留言類型資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertGuestbookType(GuestbookType expected, GuestbookType actual)
	{
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getSort(), actual.getSort());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getSite(), actual.getSite());
	}

	@Test
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void findGuestbookType()
	{

		List<GuestbookType> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = guestbookTypeService.findGuestbookType();
			//.findGuestbookType();
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
	public void findGuestbookTypeBySite()
	{

		List<GuestbookType> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = guestbookTypeService.findGuestbookType(1L);
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
	public void findGuestbookTypeByInquiry()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		GuestbookType searcher = new GuestbookTypeImpl();
		//
		List<GuestbookType> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = guestbookTypeService.findGuestbookType(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
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
	public void findGuestbookTypeByInquirySite()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		GuestbookType searcher = new GuestbookTypeImpl();
		//
		List<GuestbookType> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = guestbookTypeService.findGuestbookType(inquiry, Locale.TRADITIONAL_CHINESE, 1L, searcher);
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

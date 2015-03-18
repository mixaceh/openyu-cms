package org.openyu.cms.guestbookType.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.guestbookType.GuestbookTypeTestSupporter;
import org.openyu.cms.guestbookType.po.GuestbookTypePo;
import org.openyu.cms.guestbookType.po.impl.GuestbookTypePoImpl;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class GuestbookTypeDaoImplTest extends GuestbookTypeTestSupporter
{
	/**
	 * 隨機產生留言類型資料
	 * 
	 * @return
	 */
	public static GuestbookTypePo randomGuestbookTypePo()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_GUESTBOOK_CTG" + UNIQUE;
		final String ZH_TW_NAME = "測試留言類型" + UNIQUE;
		final String ZH_CN_NAME = "测试留言类型" + UNIQUE;
		final String EN_US_NAME = "Test Ad Space" + UNIQUE;
		//
		GuestbookTypePo result = new GuestbookTypePoImpl();
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
	public static void assertGuestbookTypePo(GuestbookTypePo expected, GuestbookTypePo actual)
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
	//insert -> find -> delete
	//
	//10 times: 7237 mills. 
	//10 times: 6825 mills.
	//10 times: 6693 mills. 
	//
	//verified: ok
	public void crud()
	{
		int count = 10;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			//隨機
			GuestbookTypePo guestbookTypePo = randomGuestbookTypePo();
			//create
			Serializable pk  = guestbookTypeDao.insert(guestbookTypePo);
			printInsert(i, pk);
			assertNotNull(pk);

			//retrieve  
			GuestbookTypePo foundEntity = guestbookTypeDao.find(GuestbookTypePoImpl.class,
				guestbookTypePo.getSeq());
			printFind(i, foundEntity);
			assertGuestbookTypePo(guestbookTypePo, foundEntity);

			//update
			guestbookTypePo.setName("ooo");
			int updated  = guestbookTypeDao.update(guestbookTypePo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			//delete
			GuestbookTypePo deletedEntity = guestbookTypeDao.delete(
				GuestbookTypePoImpl.class, guestbookTypePo.getSeq());
			printDelete(i, deletedEntity);
			assertNotNull(deletedEntity);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	//verified: ok
	public void insert()
	{
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			//隨機
			GuestbookTypePo guestbookTypePo = randomGuestbookTypePo();
			//
			Serializable pk  = guestbookTypeDao.insert(guestbookTypePo);
			printInsert(i, pk);
			assertNotNull(pk);

			GuestbookTypePo existGuestbookTypePo = guestbookTypeDao.find(GuestbookTypePoImpl.class,
				guestbookTypePo.getSeq());
			assertGuestbookTypePo(guestbookTypePo, existGuestbookTypePo);

			System.out.println(guestbookTypePo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void findGuestbookType()
	{
		List<GuestbookTypePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = guestbookTypeDao.findGuestbookType();
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
		List<GuestbookTypePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = guestbookTypeDao.findGuestbookType(1L);
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
		List<GuestbookTypePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = guestbookTypeDao.findGuestbookType(inquiry, Locale.TRADITIONAL_CHINESE,
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
		List<GuestbookTypePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = guestbookTypeDao.findGuestbookType(inquiry, Locale.TRADITIONAL_CHINESE, 1L,
				searcher);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(inquiry.getPagination());
		System.out.println(result.size() + ", " + result);
		//
		assertNotNull(result);
		//
		searcher = new GuestbookTypeImpl();
		searcher.setSeq(1);
		result = guestbookTypeDao.findGuestbookType(inquiry, Locale.TRADITIONAL_CHINESE, null,
			searcher);
		System.out.println(result.size() + ", " + result);
	}
}

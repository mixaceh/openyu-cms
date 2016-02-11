package org.openyu.cms.friendType.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

import org.openyu.cms.friendType.FriendTypeTestSupporter;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class FriendTypeServiceImplTest extends FriendTypeTestSupporter
{
	/**
	 * 隨機產生友情類型資料
	 * 
	 * @return
	 */
	public static FriendType randomFriendType()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_FRIEND_TYPE" + UNIQUE;
		final String ZH_TW_NAME = "測試友情類型" + UNIQUE;
		final String ZH_CN_NAME = "測試友情类型" + UNIQUE;
		final String EN_US_NAME = "Test FriendType" + UNIQUE;
		//
		FriendType result = new FriendTypeImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setSort(randomInt(100));
		return result;
	}

	/**
	 * 檢核友情類型資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertFriendType(FriendType expected, FriendType actual)
	{
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getSort(), actual.getSort());
	}

	@Test
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void findFriendType()
	{

		List<FriendType> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = friendTypeService.findFriendType();
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
	public void findFriendTypeBySite()
	{

		List<FriendType> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = friendTypeService.findFriendType(1L);
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
	public void findFriendTypeByInquiry()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		FriendType searcher = new FriendTypeImpl();
		//
		List<FriendType> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = friendTypeService
					.findFriendType(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
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
	public void findFriendTypeByInquirySite()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		FriendType searcher = new FriendTypeImpl();
		//
		List<FriendType> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = friendTypeService.findFriendType(inquiry, Locale.TRADITIONAL_CHINESE, 1L,
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
	//100000 times: 1325 mills. 
	public void addFriendType()
	{
		FriendType result = null;
		int count = 100000;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = friendTypeService.addFriendType("1", randomFriendType());
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(friendTypeService.getFriendTypes("1").size());//1w
		//

	}

	@Test
	//  orig
	//	ThreadA: 31,449,116 mills. 
	//	ThreadB: 29,856,614 mills.
	//
	//	ThreadA: 28454495 mills. 
	//	ThreadB: 28131946 mills. 
	//
	//	ThreadA: 40423751 mills. 
	//	ThreadB: 36944893 mills. 
	public void thread() throws Exception
	{
		int count = 1000;
		long beg = System.currentTimeMillis();

		final AtomicLong countA = new AtomicLong(0);
		final AtomicLong countB = new AtomicLong(0);
		//
		for (int i = 0; i < count; i++)
		{
			Thread threadA = new Thread(new Runnable()
			{
				public void run()
				{
					FriendType friendType = randomFriendType();
					long beg = System.nanoTime();
					friendTypeService.addFriendType("1", friendType);
					//					try
					//					{
					//						Thread.sleep(100);
					//					}
					//					catch (InterruptedException e)
					//					{
					//						e.printStackTrace();
					//					}
					long end = System.nanoTime();
					//
					countA.addAndGet((end - beg));
				}
			});
			threadA.start();
			//
			Thread threadB = new Thread(new Runnable()
			{
				public void run()
				{
					FriendType friendType = randomFriendType();
					long beg = System.nanoTime();
					friendTypeService.addFriendType("1", friendType);
					long end = System.nanoTime();
					//
					countB.addAndGet((end - beg));
				}
			});
			threadB.start();
		}
		long end = System.currentTimeMillis();
		//System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		Thread.sleep(5 * 1000);
		//
		System.out.println("ThreadA: " + countA.get() + " mills. ");
		System.out.println("ThreadB: " + countB.get() + " mills. ");

	}
}

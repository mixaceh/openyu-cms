package org.openyu.cms.friend.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;

import org.junit.Test;

import org.openyu.cms.friend.FriendTestSupporter;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friend.vo.impl.FriendImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class FriendServiceImplTest extends FriendTestSupporter
{
	/**
	 * 隨機產生友情連結資料
	 * 
	 * @return
	 */
	public static Friend randomFriend()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_FRIEND" + UNIQUE;
		final String ZH_TW_NAME = "測試友情連結" + UNIQUE;
		final String ZH_CN_NAME = "測試友情类型" + UNIQUE;
		final String EN_US_NAME = "Test Friend" + UNIQUE;
		//
		Friend result = new FriendImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setValid(randomBoolean());
		result.setUrl(randomString());
		result.setLogo(randomString());
		result.setEmail(randomString());
		//
		result.setDescription(Locale.TRADITIONAL_CHINESE, "測試描述");
		result.setDescription(Locale.SIMPLIFIED_CHINESE, "测试描述");
		result.setDescription(Locale.US, "Test Description");
		//
		result.setClick(randomInt(100));
		result.setSort(randomInt(100));
		return result;
	}

	/**
	 * 檢核友情連結資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertFriend(Friend expected, Friend actual)
	{
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getValid(), actual.getValid());
		assertEquals(expected.getUrl(), actual.getUrl());
		assertEquals(expected.getLogo(), actual.getLogo());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getClick(), actual.getClick());
		assertEquals(expected.getSort(), actual.getSort());
		assertEquals(expected.getSite(), actual.getSite());
		assertEquals(expected.getFriendType(), actual.getFriendType());
	}

	@Test
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void findFriend()
	{

		List<Friend> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = friendService.findFriend();
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
	public void findFriendBySite()
	{

		List<Friend> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = friendService.findFriend(1L);
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
	public void findFriendByInquiry()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		Friend searcher = new FriendImpl();
		//
		List<Friend> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = friendService.findFriend(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
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
	public void findFriendByInquirySite()
	{
		Inquiry inquiry = new InquiryImpl();
		//分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);//每頁筆數
		inquiry.setPagination(pagination);
		//
		Friend searcher = new FriendImpl();
		//
		List<Friend> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = friendService.findFriend(inquiry, Locale.TRADITIONAL_CHINESE, 1L, searcher);
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
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void findSingleFriend()
	{
		Friend result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = friendService.find(FriendImpl.class, 27L);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		System.out.println(result.getFriendType().getNames());//locale=zh_TW,name=文字連結
	}
}

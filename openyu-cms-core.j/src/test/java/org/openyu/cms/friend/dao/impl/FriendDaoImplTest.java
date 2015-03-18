package org.openyu.cms.friend.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.friend.FriendTestSupporter;
import org.openyu.cms.friend.po.FriendPo;
import org.openyu.cms.friend.po.impl.FriendPoImpl;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friend.vo.impl.FriendImpl;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class FriendDaoImplTest extends FriendTestSupporter {
	/**
	 * 隨機產生友情連結資料
	 * 
	 * @return
	 */
	public static FriendPo randomFriendPo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_FRIEND" + UNIQUE;
		final String ZH_TW_NAME = "測試友情連結" + UNIQUE;
		final String ZH_CN_NAME = "測試友情类型" + UNIQUE;
		final String EN_US_NAME = "Test Friend" + UNIQUE;
		//
		FriendPo result = new FriendPoImpl();
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
	public static void assertFriendPo(FriendPo expected, FriendPo actual) {
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
			FriendPo friendPo = randomFriendPo();
			// create
			Serializable pk = friendDao.insert(friendPo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			FriendPo foundEntity = friendDao.find(FriendPoImpl.class,
					friendPo.getSeq());
			printFind(i, foundEntity);
			assertFriendPo(friendPo, foundEntity);

			// update
			friendPo.setName("ooo");
			friendPo.setSort(999);
			int updated = friendDao.update(friendPo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			FriendPo deletedEntity = friendDao.delete(FriendPoImpl.class,
					friendPo.getSeq());
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
			FriendPo friendPo = randomFriendPo();
			//
			Serializable pk = friendDao.insert(friendPo);
			printInsert(i, pk);
			assertNotNull(pk);
			FriendPo foundEntity = friendDao.find(FriendPoImpl.class,
					friendPo.getSeq());
			assertFriendPo(friendPo, foundEntity);

			System.out.println(friendPo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findFriend() {
		List<FriendPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendDao.findFriend();
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
	public void findFriendBySite() {
		List<FriendPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendDao.findFriend(1L);
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
	public void findFriendByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Friend searcher = new FriendImpl();
		//
		List<FriendPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendDao.findFriend(inquiry, Locale.TRADITIONAL_CHINESE,
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
	public void findFriendByInquirySite() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Friend searcher = new FriendImpl();
		//
		List<FriendPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendDao.findFriend(inquiry, Locale.TRADITIONAL_CHINESE,
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
		searcher = new FriendImpl();
		searcher.setSeq(1);
		result = friendDao.findFriend(inquiry, Locale.TRADITIONAL_CHINESE,
				null, searcher);
		System.out.println(result.size() + ", " + result);
	}

	@Test
	/**
	 * 查詢條件
	 */
	public void findFriendByInquiryFriendType() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Friend searcher = new FriendImpl();
		// friendTypeSearcher
		FriendType friendTypeSearcher = new FriendTypeImpl();
		friendTypeSearcher.setSeq(1);
		//
		List<FriendPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendDao.findFriend(inquiry, Locale.TRADITIONAL_CHINESE,
					1L, friendTypeSearcher, searcher);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(inquiry.getPagination());
		System.out.println(result.size() + ", " + result);
		//
		assertNotNull(result);
		//
		searcher = new FriendImpl();
		// friendType
		friendTypeSearcher = new FriendTypeImpl();
		friendTypeSearcher.setId("picture");
		//
		result = friendDao.findFriend(inquiry, Locale.TRADITIONAL_CHINESE, 1L,
				friendTypeSearcher, searcher);
		System.out.println(result.size() + ", " + result);
	}
}

package org.openyu.cms.friendType.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.friendType.FriendTypeTestSupporter;
import org.openyu.cms.friendType.po.FriendTypePo;
import org.openyu.cms.friendType.po.impl.FriendTypePoImpl;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class FriendTypeDaoImplTest extends FriendTypeTestSupporter {
	/**
	 * 隨機產生友情類型資料
	 * 
	 * @return
	 */
	public static FriendTypePo randomFriendTypePo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_FRIEND_TYPE" + UNIQUE;
		final String ZH_TW_NAME = "測試友情類型" + UNIQUE;
		final String ZH_CN_NAME = "測試友情类型" + UNIQUE;
		final String EN_US_NAME = "Test FriendType" + UNIQUE;
		//
		FriendTypePo result = new FriendTypePoImpl();
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
	public static void assertFriendTypePo(FriendTypePo expected,
			FriendTypePo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getSort(), actual.getSort());
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
			FriendTypePo friendTypePo = randomFriendTypePo();
			// create
			Serializable pk = friendTypeDao.insert(friendTypePo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			FriendTypePo foundEntity = friendTypeDao.find(
					FriendTypePoImpl.class, friendTypePo.getSeq());
			printFind(i, foundEntity);
			assertFriendTypePo(friendTypePo, foundEntity);

			// update
			friendTypePo.setName("ooo");
			friendTypePo.setSort(999);
			int updated = friendTypeDao.update(friendTypePo);
			printUpdate(i, updated);

			// delete
			FriendTypePo deletedEntity = friendTypeDao.delete(
					FriendTypePoImpl.class, friendTypePo.getSeq());
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
			FriendTypePo friendTypePo = randomFriendTypePo();
			//
			Serializable pk = friendTypeDao.insert(friendTypePo);
			printInsert(i, pk);
			assertNotNull(pk);

			FriendTypePo foundEntity = friendTypeDao.find(
					FriendTypePoImpl.class, friendTypePo.getSeq());
			assertFriendTypePo(friendTypePo, foundEntity);

			System.out.println(friendTypePo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findFriendType() {
		List<FriendTypePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendTypeDao.findFriendType();
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
	public void findFriendTypeBySite() {
		List<FriendTypePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendTypeDao.findFriendType(1L);
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
	public void findFriendTypeByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		FriendType searcher = new FriendTypeImpl();
		//
		List<FriendTypePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendTypeDao.findFriendType(inquiry,
					Locale.TRADITIONAL_CHINESE, searcher);
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
	public void findFriendTypeByInquirySite() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		FriendType searcher = new FriendTypeImpl();
		//
		List<FriendTypePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendTypeDao.findFriendType(inquiry,
					Locale.TRADITIONAL_CHINESE, 1L, searcher);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(inquiry.getPagination());
		System.out.println(result.size() + ", " + result);
		//
		assertNotNull(result);
		//
		searcher = new FriendTypeImpl();
		searcher.setSeq(1);
		result = friendTypeDao.findFriendType(inquiry,
				Locale.TRADITIONAL_CHINESE, null, searcher);
		System.out.println(result.size() + ", " + result);
	}
}

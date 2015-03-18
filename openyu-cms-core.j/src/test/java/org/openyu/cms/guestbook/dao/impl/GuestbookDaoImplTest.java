package org.openyu.cms.guestbook.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.guestbook.GuestbookTestSupporter;
import org.openyu.cms.guestbook.po.GuestbookPo;
import org.openyu.cms.guestbook.po.impl.GuestbookPoImpl;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbook.vo.impl.GuestbookImpl;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class GuestbookDaoImplTest extends GuestbookTestSupporter {
	/**
	 * 隨機產生留言資料
	 * 
	 * @return
	 */
	public static GuestbookPo randomGuestbookPo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_GUESTBOOK" + UNIQUE;
		final String ZH_TW_NAME = "測試留言" + UNIQUE;
		final String ZH_CN_NAME = "测试留言" + UNIQUE;
		final String EN_US_NAME = "Test Guestbook" + UNIQUE;
		//
		GuestbookPo result = new GuestbookPoImpl();
		result.setId(ID);
		//
		result.setTitle(Locale.TRADITIONAL_CHINESE, "測試標題");
		result.setTitle(Locale.SIMPLIFIED_CHINESE, "测试标题");
		result.setTitle(Locale.US, "Test Title");

		result.setContent(Locale.TRADITIONAL_CHINESE, "測試留言內容");
		result.setContent(Locale.SIMPLIFIED_CHINESE, "测试留言内容");
		result.setContent(Locale.US, "Test Content");

		result.setReply(Locale.TRADITIONAL_CHINESE, "測試回復內容");
		result.setReply(Locale.SIMPLIFIED_CHINESE, "测试回复内容");
		result.setReply(Locale.US, "Test Reply");
		//
		result.setEmail(randomString());
		result.setPhone(randomString());
		result.setQq(randomString());
		result.setIp(randomString());
		result.setGuestbookDate(randomDate());
		result.setReplayDate(randomDate());
		result.setChecked(randomBoolean());
		result.setRecommend(randomBoolean());
		//
		return result;
	}

	/**
	 * 檢核留言連結資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertGuestbookPo(GuestbookPo expected,
			GuestbookPo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		//
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getContent(), actual.getContent());
		assertEquals(expected.getReply(), actual.getReply());
		//
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getPhone(), actual.getPhone());
		assertEquals(expected.getQq(), actual.getQq());
		//
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getGuestbookDate(), actual.getGuestbookDate());
		assertEquals(expected.getReplayDate(), actual.getReplayDate());
		//
		assertEquals(expected.getChecked(), actual.getChecked());
		assertEquals(expected.getRecommend(), actual.getRecommend());
		//
		assertEquals(expected.getGuestbookType(), actual.getGuestbookType());
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
			GuestbookPo guestbookPo = randomGuestbookPo();
			// create
			Serializable pk = guestbookDao.insert(guestbookPo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			GuestbookPo foundEntity = guestbookDao.find(GuestbookPoImpl.class,
					guestbookPo.getSeq());
			printFind(i, foundEntity);
			assertGuestbookPo(guestbookPo, foundEntity);

			// update
			guestbookPo.setTitle("000");
			int updated = guestbookDao.update(guestbookPo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			GuestbookPo deletedEntity = guestbookDao.delete(
					GuestbookPoImpl.class, guestbookPo.getSeq());
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
			GuestbookPo guestbookPo = randomGuestbookPo();
			//
			Serializable pk = guestbookDao.insert(guestbookPo);
			printInsert(i, pk);
			assertNotNull(pk);

			GuestbookPo existGuestbookPo = guestbookDao.find(
					GuestbookPoImpl.class, guestbookPo.getSeq());
			assertGuestbookPo(guestbookPo, existGuestbookPo);

			System.out.println(guestbookPo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findGuestbook() {
		List<GuestbookPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = guestbookDao.findGuestbook();
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
	public void findGuestbookBySite() {
		List<GuestbookPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = guestbookDao.findGuestbook(1L);
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
	public void findGuestbookByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Guestbook searcher = new GuestbookImpl();
		//
		List<GuestbookPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = guestbookDao.findGuestbook(inquiry,
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
	public void findGuestbookByInquirySite() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Guestbook searcher = new GuestbookImpl();
		//
		List<GuestbookPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = guestbookDao.findGuestbook(inquiry,
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
		searcher = new GuestbookImpl();
		searcher.setSeq(1);
		result = guestbookDao.findGuestbook(inquiry,
				Locale.TRADITIONAL_CHINESE, null, searcher);
		System.out.println(result.size() + ", " + result);
	}

	@Test
	/**
	 * 查詢條件
	 */
	public void findGuestbookByInquiryGuestbookType() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Guestbook searcher = new GuestbookImpl();
		// guestbookTypeSearcher
		GuestbookType guestbookTypeSearcher = new GuestbookTypeImpl();
		guestbookTypeSearcher.setSeq(1);
		//
		List<GuestbookPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = guestbookDao.findGuestbook(inquiry,
					Locale.TRADITIONAL_CHINESE, 1L, guestbookTypeSearcher,
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
		searcher = new GuestbookImpl();
		// guestbookType
		guestbookTypeSearcher = new GuestbookTypeImpl();
		guestbookTypeSearcher.setId("picture");
		//
		result = guestbookDao
				.findGuestbook(inquiry, Locale.TRADITIONAL_CHINESE, 1L,
						guestbookTypeSearcher, searcher);
		System.out.println(result.size() + ", " + result);
	}
}

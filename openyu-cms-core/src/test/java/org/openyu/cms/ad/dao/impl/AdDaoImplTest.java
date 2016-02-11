package org.openyu.cms.ad.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.ad.AdTestSupporter;
import org.openyu.cms.ad.po.AdPo;
import org.openyu.cms.ad.po.impl.AdPoImpl;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.ad.vo.Ad.AdType;
import org.openyu.cms.ad.vo.impl.AdImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class AdDaoImplTest extends AdTestSupporter {
	/**
	 * 隨機產生廣告資料
	 * 
	 * @return
	 */
	public static AdPo randomAdPo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_AD_SPACE" + UNIQUE;
		final String ZH_TW_NAME = "測試廣告" + UNIQUE;
		final String ZH_CN_NAME = "測試广告版位" + UNIQUE;
		final String EN_US_NAME = "Test Ad Space" + UNIQUE;
		//
		AdPo result = new AdPoImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		//
		result.setValid(randomBoolean());
		result.setAdType(randomType(AdType.class));
		result.setUrl(randomString());
		result.setTarget(randomString());
		result.setClick(randomInt(100));
		result.setDisplay(randomInt(100));
		result.setWeight(randomInt(100));
		result.setBegDate(randomDate());
		result.setEndDate(randomDate());

		return result;
	}

	/**
	 * 檢核廣告資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertAdPo(AdPo expected, AdPo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getValid(), actual.getValid());
		assertEquals(expected.getSite(), actual.getSite());
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
			AdPo adPo = randomAdPo();
			// create
			Serializable pk = adDao.insert(adPo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			AdPo foundEntity = adDao.find(AdPoImpl.class, adPo.getSeq());
			printFind(i, foundEntity);
			assertAdPo(adPo, foundEntity);

			// update
			adPo.setName("ooo");
			int updated = adDao.update(adPo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			AdPo deletedEntity = adDao.delete(AdPoImpl.class, adPo.getSeq());
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
			AdPo adPo = randomAdPo();
			//
			Serializable pk = adDao.insert(adPo);
			printInsert(i, pk);
			assertNotNull(pk);

			AdPo foundEntity = adDao.find(AdPoImpl.class, adPo.getSeq());
			assertAdPo(adPo, foundEntity);

			System.out.println(adPo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findAd() {
		List<AdPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = adDao.findAd();
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
	public void findAdBySite() {
		List<AdPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = adDao.findAd(1L);
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
	public void findAdByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Ad searcher = new AdImpl();
		//
		List<AdPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = adDao
					.findAd(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
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
	public void findAdByInquirySite() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Ad searcher = new AdImpl();
		//
		List<AdPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = adDao.findAd(inquiry, Locale.TRADITIONAL_CHINESE, 1L,
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
		searcher = new AdImpl();
		searcher.setSeq(1);
		result = adDao.findAd(inquiry, Locale.TRADITIONAL_CHINESE, null,
				searcher);
		System.out.println(result.size() + ", " + result);
	}
}

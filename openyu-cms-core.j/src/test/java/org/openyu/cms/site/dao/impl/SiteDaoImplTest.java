package org.openyu.cms.site.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.site.SiteTestSupporter;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.po.impl.SitePoImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class SiteDaoImplTest extends SiteTestSupporter {
	/**
	 * 隨機產生網站資料
	 * 
	 * @return
	 */
	public static SitePo randomSitePo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_SITE" + UNIQUE;
		final String ZH_TW_NAME = "測試網站" + UNIQUE;
		final String ZH_CN_NAME = "测试网站" + UNIQUE;
		final String EN_US_NAME = "Test Site" + UNIQUE;
		//
		SitePo result = new SitePoImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setShortName(Locale.TRADITIONAL_CHINESE, "測試網站簡稱");
		result.setShortName(Locale.SIMPLIFIED_CHINESE, "测试网站简称");
		result.setShortName(Locale.US, "Test Short Name");
		result.setResourcePath(randomString());
		result.setProtocol(randomString());
		result.setDynamicSuffix(randomString());
		result.setStaticSuffix(randomString());
		result.setStaticPath(randomString());
		//
		result.setIndexRoot(randomBoolean());
		result.setStaticIndex(randomBoolean());
		// result.setBackLocale(randomString());
		// result.setFrontLocale(randomString());
		result.setTemplatePath(randomString());
		result.setVerifyType(randomType(VerifyType.class));
		result.setModifyType(randomType(ModifyType.class));
		//
		result.setRelativePath(randomBoolean());
		result.setRecover(randomBoolean());
		;
		result.setAlias(randomString());
		result.setRedirect(randomString());
		//
		result.getAttributes().put(randomString(), randomString());
		result.getAttributes().put(randomString(), randomString());
		//
		result.getTexts().put(randomString(), randomString());
		result.getTexts().put(randomString(), randomString());
		//
		result.getConfigs().put(randomString(), randomString());
		result.getConfigs().put(randomString(), randomString());
		return result;
	}

	/**
	 * 檢核網站資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertSitePo(SitePo expected, SitePo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getResourcePath(), actual.getResourcePath());
		assertEquals(expected.getShortName(), actual.getShortName());
		assertEquals(expected.getProtocol(), actual.getProtocol());
		assertEquals(expected.getDynamicSuffix(), actual.getDynamicSuffix());
		assertEquals(expected.getStaticSuffix(), actual.getStaticSuffix());
		assertEquals(expected.getStaticPath(), actual.getStaticPath());
		assertEquals(expected.getIndexRoot(), actual.getIndexRoot());
		//
		assertEquals(expected.getIndexRoot(), actual.getIndexRoot());
		assertEquals(expected.getIndexRoot(), actual.getIndexRoot());
		//
		assertEquals(expected.getAttributes(), actual.getAttributes());
		assertEquals(expected.getTexts(), actual.getTexts());
		assertEquals(expected.getConfigs(), actual.getConfigs());
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
			SitePo sitePo = randomSitePo();
			// create
			Serializable pk = siteDao.insert(sitePo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			SitePo foundEntity = siteDao
					.find(SitePoImpl.class, sitePo.getSeq());
			printFind(i, foundEntity);
			assertSitePo(sitePo, foundEntity);

			// update
			sitePo.setName("ooo");
			sitePo.setResourcePath("res/123");

			int updated = siteDao.update(sitePo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			SitePo deleteSitePo = siteDao.delete(SitePoImpl.class,
					sitePo.getSeq());
			printDelete(i, deleteSitePo);
			assertNotNull(deleteSitePo);
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
			SitePo sitePo = randomSitePo();

			//
			Serializable pk = siteDao.insert(sitePo);
			printInsert(i, pk);
			assertNotNull(pk);

			SitePo foundEntity = siteDao
					.find(SitePoImpl.class, sitePo.getSeq());
			assertSitePo(sitePo, foundEntity);

			System.out.println(sitePo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findSite() {
		List<SitePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = siteDao.findSite();
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
	public void findFirstSite() {
		SitePo result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = siteDao.findFirstSite();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
	}

	@Test
	/**
	 * 查詢條件
	 */
	public void findSiteByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Site searcher = new SiteImpl();
		//
		List<SitePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = siteDao.findSite(inquiry, Locale.TRADITIONAL_CHINESE,
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
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findSingleSite() {
		SitePo result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = siteDao.find(SitePoImpl.class, 18L);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		System.out.println(result.getFtp());
		System.out.println(result.getAttributes());
	}

}

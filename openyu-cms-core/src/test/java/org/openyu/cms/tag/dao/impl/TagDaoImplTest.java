package org.openyu.cms.tag.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.tag.TagTestSupporter;
import org.openyu.cms.tag.po.TagPo;
import org.openyu.cms.tag.po.impl.TagPoImpl;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.tag.vo.impl.TagImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class TagDaoImplTest extends TagTestSupporter {
	/**
	 * 隨機產生標籤資料
	 * 
	 * @return
	 */
	public static TagPo randomTagPo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_TAG" + UNIQUE;
		final String ZH_TW_NAME = "測試標籤" + UNIQUE;
		final String ZH_CN_NAME = "测试标签" + UNIQUE;
		final String EN_US_NAME = "Test Tag" + UNIQUE;
		//
		TagPo result = new TagPoImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		return result;
	}

	/**
	 * 檢核標籤資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertTagPo(TagPo expected, TagPo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
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
			TagPo tagPo = randomTagPo();
			// create
			Serializable pk = tagDao.insert(tagPo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			TagPo foundEntity = tagDao.find(TagPoImpl.class, tagPo.getSeq());
			printFind(i, foundEntity);
			assertTagPo(tagPo, foundEntity);

			// update
			tagPo.setName("ooo");
			// tagPo.setSort(999);
			int updated = tagDao.update(tagPo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			TagPo deletedEntity = tagDao
					.delete(TagPoImpl.class, tagPo.getSeq());
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
			TagPo tagPo = randomTagPo();
			//
			Serializable pk = tagDao.insert(tagPo);
			printInsert(i, pk);
			assertNotNull(pk);

			TagPo foundEntity = tagDao.find(TagPoImpl.class, tagPo.getSeq());
			assertTagPo(tagPo, foundEntity);

			System.out.println(tagPo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findTag() {
		List<TagPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = tagDao.findTag();
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
	public void findTagByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Tag searcher = new TagImpl();
		//
		List<TagPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = tagDao.findTag(inquiry, Locale.TRADITIONAL_CHINESE,
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
	public void findTagByInquirySite() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Tag searcher = new TagImpl();
		//
		List<TagPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = tagDao.findTag(inquiry, Locale.TRADITIONAL_CHINESE,
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
		searcher = new TagImpl();
		searcher.setSeq(1);
		result = tagDao.findTag(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
		System.out.println(result.size() + ", " + result);
	}
}

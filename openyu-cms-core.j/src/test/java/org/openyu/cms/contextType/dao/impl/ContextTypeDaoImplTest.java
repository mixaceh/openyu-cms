package org.openyu.cms.contextType.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.contextType.ContextTypeTestSupporter;
import org.openyu.cms.contextType.po.ContextTypePo;
import org.openyu.cms.contextType.po.impl.ContextTypePoImpl;
import org.openyu.cms.contextType.vo.ContextType;
import org.openyu.cms.contextType.vo.impl.ContextTypeImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class ContextTypeDaoImplTest extends ContextTypeTestSupporter {
	/**
	 * 隨機產生本文類型資料
	 * 
	 * @return
	 */
	public static ContextTypePo randomContextTypePo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_CONTEXT_TYPE" + UNIQUE;
		final String ZH_TW_NAME = "測試本文類型" + UNIQUE;
		final String ZH_CN_NAME = "測試本文类型" + UNIQUE;
		final String EN_US_NAME = "Test ContextType" + UNIQUE;
		//
		ContextTypePo result = new ContextTypePoImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setValid(randomBoolean());
		//
		result.setImgWidth(randomInt(100));
		result.setImgHeight(randomInt(100));
		result.setImage(randomBoolean());
		return result;
	}

	/**
	 * 檢核本文類型資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertContextTypePo(ContextTypePo expected,
			ContextTypePo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getValid(), actual.getValid());
		assertEquals(expected.getImgWidth(), actual.getImgWidth());
		assertEquals(expected.getImgHeight(), actual.getImgHeight());
		assertEquals(expected.getImage(), actual.getImage());
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
			ContextTypePo contextTypePo = randomContextTypePo();
			// create
			Serializable pk = contextTypeDao.insert(contextTypePo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			ContextTypePo foundEntity = contextTypeDao.find(
					ContextTypePoImpl.class, contextTypePo.getSeq());
			printFind(i, foundEntity);
			assertContextTypePo(contextTypePo, foundEntity);

			// update
			contextTypePo.setName("ooo");
			contextTypePo.setValid(true);
			int updated = contextTypeDao.update(contextTypePo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			ContextTypePo deletedEntity = contextTypeDao.delete(
					ContextTypePoImpl.class, contextTypePo.getSeq());
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
			ContextTypePo contextTypePo = randomContextTypePo();
			//
			Serializable pk = contextTypeDao.insert(contextTypePo);
			printInsert(i, pk);
			assertNotNull(pk);

			ContextTypePo foundEntity = contextTypeDao.find(
					ContextTypePoImpl.class, contextTypePo.getSeq());
			assertContextTypePo(contextTypePo, foundEntity);

			System.out.println(contextTypePo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findContextType() {
		List<ContextTypePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = contextTypeDao.findContextType(true);
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
	public void findContextTypeByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		ContextType searcher = new ContextTypeImpl();
		//
		List<ContextTypePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = contextTypeDao.findContextType(inquiry,
					Locale.TRADITIONAL_CHINESE, searcher);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(inquiry.getPagination());
		System.out.println(result.size() + ", " + result);
		//
		assertNotNull(result);
		//
		searcher = new ContextTypeImpl();
		searcher.setSeq(1);
		result = contextTypeDao.findContextType(inquiry,
				Locale.TRADITIONAL_CHINESE, searcher);
		System.out.println(result.size() + ", " + result);
	}

}

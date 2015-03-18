package org.openyu.cms.group.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.group.GroupTestSupporter;
import org.openyu.cms.group.po.GroupPo;
import org.openyu.cms.group.po.impl.GroupPoImpl;
import org.openyu.cms.group.vo.Group;
import org.openyu.cms.group.vo.impl.GroupImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class GroupDaoImplTest extends GroupTestSupporter {
	/**
	 * 隨機產生群組資料
	 * 
	 * @return
	 */
	public static GroupPo randomGroupPo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_GROUP" + UNIQUE;
		final String ZH_TW_NAME = "測試群組" + UNIQUE;
		final String ZH_CN_NAME = "测试群组" + UNIQUE;
		final String EN_US_NAME = "Test Group" + UNIQUE;
		//
		GroupPo result = new GroupPoImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setSort(randomInt(100));
		result.setCaptcha(randomBoolean());
		result.setCheck(randomBoolean());
		result.setDayUpload(randomInt(10000));
		result.setMaxUpload(randomInt(10000));
		result.setUploadSuffix(randomString());
		result.setDefault(randomBoolean());

		return result;
	}

	/**
	 * 檢核群組資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertGroupPo(GroupPo expected, GroupPo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getSort(), actual.getSort());
		assertEquals(expected.getCaptcha(), actual.getCaptcha());
		assertEquals(expected.getCheck(), actual.getCheck());
		assertEquals(expected.getDayUpload(), actual.getDayUpload());
		assertEquals(expected.getMaxUpload(), actual.getMaxUpload());
		assertEquals(expected.getUploadSuffix(), actual.getUploadSuffix());
		assertEquals(expected.getDefault(), actual.getDefault());
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
			GroupPo groupPo = randomGroupPo();
			// create
			Serializable pk = groupDao.insert(groupPo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			GroupPo foundEntity = groupDao.find(GroupPoImpl.class,
					groupPo.getSeq());
			printFind(i, foundEntity);
			assertGroupPo(groupPo, foundEntity);

			// update
			groupPo.setName("ooo");
			groupPo.setDefault(true);
			int updated = groupDao.update(groupPo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			GroupPo deletedEntity = groupDao.delete(GroupPoImpl.class,
					groupPo.getSeq());
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
			GroupPo groupPo = randomGroupPo();
			//
			Serializable pk = groupDao.insert(groupPo);
			printInsert(i, pk);
			assertNotNull(pk);

			GroupPo existGroupPo = groupDao.find(GroupPoImpl.class,
					groupPo.getSeq());
			assertGroupPo(groupPo, existGroupPo);

			System.out.println(groupPo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findGroup() {
		List<GroupPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = groupDao.findGroup();
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
	public void findDefaultGroup() {
		GroupPo result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = groupDao.findDefaultGroup();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	/**
	 * 查詢條件
	 */
	public void findGroupByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Group searcher = new GroupImpl();
		//
		List<GroupPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = groupDao.findGroup(inquiry, Locale.TRADITIONAL_CHINESE,
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
		searcher = new GroupImpl();
		searcher.setSeq(1);
		result = groupDao.findGroup(inquiry, Locale.TRADITIONAL_CHINESE,
				searcher);
		System.out.println(result.size() + ", " + result);
	}

}

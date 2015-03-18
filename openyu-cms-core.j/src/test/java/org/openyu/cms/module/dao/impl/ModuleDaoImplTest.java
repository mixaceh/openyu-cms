package org.openyu.cms.module.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.module.po.ModulePo;
import org.openyu.cms.module.po.impl.ModulePoImpl;
import org.openyu.cms.module.vo.CatalogItem;
import org.openyu.cms.module.vo.ContextItem;
import org.openyu.cms.module.vo.Module;
import org.openyu.cms.module.vo.ModuleItem.ColumnType;
import org.openyu.cms.module.vo.impl.CatalogItemImpl;
import org.openyu.cms.module.vo.impl.ContextItemImpl;
import org.openyu.cms.module.vo.impl.ModuleImpl;
import org.openyu.cms.module.ModuleTestSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class ModuleDaoImplTest extends ModuleTestSupporter {

	/**
	 * 隨機產生模組資料
	 * 
	 * @return
	 */
	public static ModulePo randomModulePo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_MODULE" + UNIQUE;
		final String ZH_TW_NAME = "測試模組" + UNIQUE;
		final String ZH_CN_NAME = "测试模组" + UNIQUE;
		final String EN_US_NAME = "Test Module" + UNIQUE;
		//
		ModulePo result = new ModulePoImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setValid(randomBoolean());
		result.setPath(randomString());
		result.setCatalogPrefix(randomString());
		result.setContextPrefix(randomString());
		//
		result.setCatalogImgWidth(randomInt(100));
		result.setCatalogImgHeight(randomInt(100));
		result.setContextImgWidth(randomInt(100));
		result.setContextImgHeight(randomInt(100));
		//
		result.setSort(randomInt(100));
		result.setContext(randomBoolean());
		result.setDefault(randomBoolean());

		// 目錄模組
		CatalogItem catalogItem = new CatalogItemImpl();
		catalogItem.setId(randomString());
		catalogItem.setSort(randomInt(100));
		catalogItem.setDefault(randomString());
		catalogItem.setColumnType(randomType(ColumnType.class));
		result.getCatalogItems().add(catalogItem);
		//
		catalogItem = new CatalogItemImpl();
		catalogItem.setId(randomString());
		catalogItem.setSort(randomInt(100));
		catalogItem.setDefault(randomString());
		catalogItem.setColumnType(randomType(ColumnType.class));
		result.getCatalogItems().add(catalogItem);
		//
		catalogItem = new CatalogItemImpl();
		catalogItem.setId(randomString());
		catalogItem.setSort(randomInt(100));
		catalogItem.setDefault(randomString());
		catalogItem.setColumnType(randomType(ColumnType.class));
		result.getCatalogItems().add(catalogItem);

		// 本文模組
		ContextItem contextItem = new ContextItemImpl();
		contextItem.setId(randomString());
		contextItem.setSort(randomInt(100));
		contextItem.setDefault(randomString());
		contextItem.setColumnType(randomType(ColumnType.class));
		result.getContextItems().add(contextItem);
		//
		contextItem = new ContextItemImpl();
		contextItem.setId(randomString());
		contextItem.setSort(randomInt(100));
		contextItem.setDefault(randomString());
		contextItem.setColumnType(randomType(ColumnType.class));
		result.getContextItems().add(contextItem);
		//
		contextItem = new ContextItemImpl();
		contextItem.setId(randomString());
		contextItem.setSort(randomInt(100));
		contextItem.setDefault(randomString());
		contextItem.setColumnType(randomType(ColumnType.class));
		result.getContextItems().add(contextItem);
		return result;
	}

	/**
	 * 檢核模組資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertModulePo(ModulePo expected, ModulePo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getValid(), actual.getValid());
		assertEquals(expected.getPath(), actual.getPath());
		assertEquals(expected.getCatalogPrefix(), actual.getCatalogPrefix());
		assertEquals(expected.getContextPrefix(), actual.getContextPrefix());
		assertEquals(expected.getCatalogImgWidth(), actual.getCatalogImgWidth());
		assertEquals(expected.getCatalogImgHeight(),
				actual.getCatalogImgHeight());
		assertEquals(expected.getContextImgWidth(), actual.getContextImgWidth());
		assertEquals(expected.getContextImgHeight(),
				actual.getContextImgHeight());
		assertEquals(expected.getSort(), actual.getSort());
		assertEquals(expected.getContext(), actual.getContext());
		assertEquals(expected.getDefault(), actual.getDefault());
		//
		assertCollectionEquals(expected.getCatalogItems(), actual.getCatalogItems());
		assertCollectionEquals(expected.getContextItems(), actual.getContextItems());
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
			ModulePo modulePo = randomModulePo();
			// create
			Serializable pk = moduleDao.insert(modulePo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			ModulePo foundEntity = moduleDao.find(ModulePoImpl.class,
					modulePo.getSeq());
			printFind(i, foundEntity);
			assertModulePo(modulePo, foundEntity);

			// update
			modulePo.setPath("ppp");
			modulePo.setValid(true);
			int updated = moduleDao.update(modulePo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			ModulePo deletedEntity = moduleDao.delete(ModulePoImpl.class,
					modulePo.getSeq());
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
			ModulePo modulePo = randomModulePo();
			//
			Serializable pk = moduleDao.insert(modulePo);
			printInsert(i, pk);
			assertNotNull(pk);

			ModulePo foundEntity = moduleDao.find(ModulePoImpl.class,
					modulePo.getSeq());
			assertModulePo(modulePo, foundEntity);

			System.out.println(modulePo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void find() {
		ModulePo result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleDao.find(ModulePoImpl.class, 197L);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findModule() {

		List<ModulePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleDao.findModule(true);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size());
		System.out.println(result);
		assertNotNull(result);
		//
		result = moduleDao.findModule(false);
		System.out.println(result.size());
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findDefaultModule() {

		ModulePo result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleDao.findDefaultModule();
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
	public void findModuleByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Module searcher = new ModuleImpl();
		searcher.setValid(true);
		//
		List<ModulePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleDao.findModule(inquiry, Locale.TRADITIONAL_CHINESE,
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
		searcher = new ModuleImpl();
		searcher.setSeq(1);
		result = moduleDao.findModule(inquiry, Locale.TRADITIONAL_CHINESE,
				searcher);
		System.out.println(result.size() + ", " + result);
	}
}

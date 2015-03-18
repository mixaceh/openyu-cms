package org.openyu.cms.module.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Id;

import org.junit.Test;

import org.openyu.cms.module.ModuleTestSupporter;
import org.openyu.cms.module.po.impl.ModulePoImpl;
import org.openyu.cms.module.vo.CatalogItem;
import org.openyu.cms.module.vo.Module;
import org.openyu.cms.module.vo.ModuleItem.ColumnType;
import org.openyu.cms.module.vo.impl.CatalogItemImpl;
import org.openyu.cms.module.vo.impl.ModuleImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;
import org.openyu.commons.util.CollectionHelper;

public class ModuleServiceImplTest extends ModuleTestSupporter {

	/**
	 * 隨機產生模組資料
	 * 
	 * @return
	 */
	public static Module randomModule() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_MODULE" + UNIQUE;
		final String ZH_TW_NAME = "測試模組" + UNIQUE;
		final String ZH_CN_NAME = "测试模组" + UNIQUE;
		final String EN_US_NAME = "Test Module" + UNIQUE;
		//
		Module result = new ModuleImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		result.setValid(randomBoolean());
		//
		result.setPath(randomString());
		result.setCatalogPrefix(randomString());
		result.setContextPrefix(randomString());
		//
		result.setCatalogImgWidth(randomInt());
		result.setCatalogImgHeight(randomInt());
		result.setContextImgWidth(randomInt());
		result.setContextImgHeight(randomInt());
		//
		result.setSort(randomInt());
		result.setContext(randomBoolean());
		result.setDefault(randomBoolean());
		//
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

		return result;
	}

	/**
	 * 檢核模組資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertModule(Module expected, Module actual) {
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
		assertCollectionEquals(expected.getCatalogItems(),
				actual.getCatalogItems());
		assertCollectionEquals(expected.getContextItems(),
				actual.getContextItems());
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
			Module module = randomModule();
			// create
			Serializable pk = moduleService.insert(module);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			Module foundEntity = moduleService.find(ModuleImpl.class,
					module.getSeq());
			printFind(i, foundEntity);
			assertModule(module, foundEntity);

			// update
			module.setPath("ppp");
			module.setValid(true);
			int updated = moduleService.update(module);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			Module deletedEntity = moduleService.delete(ModuleImpl.class,
					module.getSeq());
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
			Module module = randomModule();
			//
			Serializable pk = moduleService.insert(module);
			printInsert(i, pk);
			assertNotNull(pk);

			Module foundEntity = moduleService.find(ModuleImpl.class,
					module.getSeq());
			assertModule(module, foundEntity);

			System.out.println(module);
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
		Module result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleService.find(ModuleImpl.class, 197L);
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
		List<Module> result = null;
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleService.findModule(true);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size() + ", " + result);
		assertNotNull(result);
		//
		result = moduleService.findModule(false);
		System.out.println(result.size() + ", " + result);
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findDefaultModule() {
		Module result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleService.findDefaultModule();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.toString());
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
		List<Module> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleService.findModule(inquiry,
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
	// verified: ok
	public void updateSort() {
		List<Module> result = null;
		//
		List<Long> seqs = new LinkedList<Long>();
		seqs.add(4L);
		seqs.add(5L);
		//
		List<Integer> sorts = new LinkedList<Integer>();
		sorts.add(10);
		sorts.add(20);
		//
		List<Boolean> valids = new LinkedList<Boolean>();
		valids.add(true);
		valids.add(true);
		long defaultSeq = 5L;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleService.updateSort(seqs, sorts, valids, defaultSeq);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertTrue(result.size() > 0);
	}

	@Test
	public void delete() {
		List<Serializable> seqs = new LinkedList<Serializable>();
		seqs.add(25L);
		seqs.add(26L);
		//
		List<Module> result = null;
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleService.delete(ModuleImpl.class, seqs);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size() + ", " + result);
		assertNotNull(result);
		//
		result = null;
		seqs.clear();
		seqs.add("40");
		seqs.add("41");
		//
		List<Serializable> longs = CollectionHelper.toLongs(seqs);
		result = moduleService.delete(ModuleImpl.class, longs);
		System.out.println(result.size() + ", " + result);
		assertNotNull(result);
	}

	@Test
	public void deleteBySeq() {
		Module result = null;
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = moduleService.delete(ModuleImpl.class, 66L);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void annotation() {
		Annotation[] annotations = ModulePoImpl.class.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
			// System.out.println(annotation.annotationType());
		}
		//
		Method pkMethod = null;
		Method[] methods = ModulePoImpl.class.getMethods();
		for (Method method : methods) {
			Annotation[] annos = method.getAnnotations();
			for (Annotation anno : annos) {
				// System.out.println(anno);
				Class<?> clazz = anno.annotationType();
				if (clazz == Id.class) {
					System.out.println("pk: " + clazz);
					pkMethod = method;
					break;
				}
			}
		}
		//
		if (pkMethod != null) {
			Annotation[] annos = pkMethod.getAnnotations();
			for (Annotation anno : annos) {
				Class<?> clazz = anno.annotationType();
				if (clazz == Column.class) {
					System.out.println("pk: " + clazz);
				}
			}
		}

	}
}

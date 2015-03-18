package org.openyu.cms.sensitivity.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.sensitivity.po.SensitivityPo;
import org.openyu.cms.sensitivity.po.impl.SensitivityPoImpl;
import org.openyu.cms.sensitivity.SensitivityTestSupporter;

public class SensitivityDaoImplTest extends SensitivityTestSupporter {
	/**
	 * 隨機產生模組資料
	 * 
	 * @return
	 */
	public static SensitivityPo randomSensitivityPo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_SENSITIVITY" + UNIQUE;
		//
		SensitivityPo result = new SensitivityPoImpl();
		//
		result.setId(ID);
		//
		// result.setSearch(randomString());
		// result.setReplace(randomString());
		Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();

		int count = 5000;
		for (int i = 0; i < count; i++) {
			// dictionarys.put(randomAlphabet(5), randomAlphabet(5));
			Dictionary dictionary = new DictionaryImpl();
			String key = randomAlphabet(5);
			dictionary.setKey(key);
			dictionary.setValue(randomAlphabet(5));
			dictionary.setValid(randomBoolean());
			dictionarys.put(key, dictionary);
		}

		result.setDictionarys(dictionarys);

		result.setLocale(Locale.TRADITIONAL_CHINESE);
		// result.setLocale(Locale.SIMPLIFIED_CHINESE);
		// result.setLocale(Locale.US);
		return result;
	}

	/**
	 * 檢核模組資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertSensitivityPo(SensitivityPo expected,
			SensitivityPo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		//
		assertEquals(expected.getDictionarys(), actual.getDictionarys());
		assertEquals(expected.getLocale(), actual.getLocale());
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
			SensitivityPo sensitivityPo = randomSensitivityPo();
			// create
			Serializable pk = sensitivityDao.insert(sensitivityPo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			SensitivityPo foundEntity = sensitivityDao.find(
					SensitivityPoImpl.class, sensitivityPo.getSeq());
			printFind(i, foundEntity);
			assertSensitivityPo(sensitivityPo, foundEntity);

			// update
			Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();

			Dictionary dictionary = new DictionaryImpl();
			String key = randomAlphabet(5);
			dictionary.setKey(key);
			dictionary.setValue(randomAlphabet(5));
			dictionary.setValid(randomBoolean());
			dictionarys.put(key, dictionary);
			// dictionarys.put("123", "***");
			sensitivityPo.setDictionarys(dictionarys);
			// sensitivityPo.setReplacement("xxx");
			int updated = sensitivityDao.update(sensitivityPo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			SensitivityPo deletedEntity = sensitivityDao.delete(
					SensitivityPoImpl.class, sensitivityPo.getSeq());
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
			SensitivityPo sensitivityPo = randomSensitivityPo();
			//
			Serializable pk = sensitivityDao.insert(sensitivityPo);
			printInsert(i, pk);
			assertNotNull(pk);

			SensitivityPo foundEntity = sensitivityDao.find(
					SensitivityPoImpl.class, sensitivityPo.getSeq());
			assertSensitivityPo(sensitivityPo, foundEntity);

			System.out.println(sensitivityPo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findSensitivity() {

		List<SensitivityPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// result = sensitivityDao.find(SensitivityPo.class);
			result = sensitivityDao.findSensitivity(Locale.TRADITIONAL_CHINESE);
			// result =
			// sensitivityDao.findSensitivity(Locale.SIMPLIFIED_CHINESE);
			// result = sensitivityDao.findSensitivity(Locale.US);
			// result = sensitivityDao.findSensitivity();
		}
		long end = System.currentTimeMillis();
		System.out.println("findSensitivity::" + count + " times: "
				+ (end - beg) + " mills. ");

		System.out.println(result.get(0).getDictionarys().size());

		// System.out.println(result.size());

		beg = System.currentTimeMillis();
		Map<String, Dictionary> dictionarys = result.get(0).getDictionarys();

		List<String> keylist = new LinkedList<String>(dictionarys.keySet());

		// List<String> list = new LinkedList<String>(dictionarys.values());
		end = System.currentTimeMillis();
		System.out.println("convert Map to List::" + count + " times: "
				+ (end - beg) + " mills. ");
		System.out.println(keylist.toString());
		// System.out.println(list.toString());

		// System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// verified: ok
	public void insertDictionary() {
		List<SensitivityPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// result = sensitivityDao.find(SensitivityPo.class);
			result = sensitivityDao.findSensitivity(Locale.TRADITIONAL_CHINESE);
			// result =
			// sensitivityDao.findSensitivity(Locale.SIMPLIFIED_CHINESE);
			// result = sensitivityDao.findSensitivity(Locale.US);
			// result = sensitivityDao.findSensitivity();
		}
		long end = System.currentTimeMillis();
		System.out.println("findSensitivity::" + count + " times: "
				+ (end - beg) + " mills. ");

		System.out.println(result.get(0).getDictionarys().size());

		beg = System.currentTimeMillis();
		Map<String, Dictionary> dictionarys = result.get(0).getDictionarys();

		List<String> keylist = new LinkedList<String>(dictionarys.keySet());

		// List<String> list = new LinkedList<String>(dictionarys.values());
		end = System.currentTimeMillis();
		System.out.println("convert Map to List::" + count + " times: "
				+ (end - beg) + " mills. ");
		System.out.println(keylist.toString());
		// System.out.println(list.toString());
		/*
		 * [QlXaz, 3ecMV] [EHvnh, 8ENqD]
		 */

	}
}

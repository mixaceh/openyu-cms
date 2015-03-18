package org.openyu.cms.keyword.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.openyu.cms.keyword.po.KeywordPo;
import org.openyu.cms.keyword.po.impl.KeywordPoImpl;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.keyword.KeywordTestSupporter;

public class KeywordDaoImplTest extends KeywordTestSupporter {
	/**
	 * 隨機產生關鍵字資料
	 * 
	 * @return
	 */
	public static KeywordPo randomKeywordPo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_KEYWORD" + UNIQUE;
		//
		KeywordPo result = new KeywordPoImpl();
		//
		result.setId(ID);
		//
		// result.setSearch(randomString());
		// result.setReplace(randomString());
		Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();

		int count = 5000;
		for (int i = 0; i < count; i++) {
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
	 * 檢核關鍵字資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertKeywordPo(KeywordPo expected, KeywordPo actual) {
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
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// 隨機
			KeywordPo keywordPo = randomKeywordPo();
			// create
			Serializable pk = keywordDao.insert(keywordPo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			KeywordPo foundEntity = keywordDao.find(KeywordPoImpl.class,
					keywordPo.getSeq());
			printFind(i, foundEntity);
			assertKeywordPo(keywordPo, foundEntity);

			// update
			Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();
			Dictionary dictionary = new DictionaryImpl();
			String key = randomAlphabet(5);
			dictionary.setKey(key);
			dictionary.setValue(randomAlphabet(5));
			dictionary.setValid(randomBoolean());
			dictionarys.put(key, dictionary);
			keywordPo.setDictionarys(dictionarys);
			int updated = keywordDao.update(keywordPo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			KeywordPo deletedEntity = keywordDao.delete(KeywordPoImpl.class,
					keywordPo.getSeq());
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
			KeywordPo keywordPo = randomKeywordPo();
			//
			Serializable pk = keywordDao.insert(keywordPo);
			printInsert(i, pk);
			assertNotNull(pk);

			KeywordPo foundEntity = keywordDao.find(KeywordPoImpl.class,
					keywordPo.getSeq());
			assertKeywordPo(keywordPo, foundEntity);

			System.out.println(keywordPo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findKeyword() {

		List<KeywordPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// result = keywordDao.find(KeywordPo.class);
			result = keywordDao.findKeyword(Locale.TRADITIONAL_CHINESE);
			// result = keywordDao.findKeyword(Locale.SIMPLIFIED_CHINESE);
			// result = keywordDao.findKeyword(Locale.US);
			// result = keywordDao.findKeyword();
		}
		long end = System.currentTimeMillis();
		System.out.println("findKeyword::" + count + " times: " + (end - beg)
				+ " mills. ");

		System.out.println(result.get(0).getDictionarys().size());

		// System.out.println(result.size());

		beg = System.currentTimeMillis();
		Map<String, Dictionary> dictionarys = result.get(0).getDictionarys();

		// List<String> keylist = new LinkedList<String>(dictionarys.keySet());

		// List<String> list = new LinkedList<String>(dictionarys.values());
		end = System.currentTimeMillis();
		System.out.println("convert Map to List::" + count + " times: "
				+ (end - beg) + " mills. ");
		// System.out.println(keylist.toString());
		// System.out.println(list.toString());

		// System.out.println(result);
		assertNotNull(result);
	}

}

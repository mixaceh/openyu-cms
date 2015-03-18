package org.openyu.cms.keyword.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.keyword.vo.ActionType;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.keyword.log.KeywordLog;
import org.openyu.cms.keyword.log.impl.KeywordLogImpl;
import org.openyu.cms.keyword.KeywordTestSupporter;
import org.openyu.commons.util.DateHelper;

public class KeywordLogDaoImplTest extends KeywordTestSupporter {
	/**
	 * 隨機產生關鍵字資料
	 * 
	 * @return
	 */
	public static KeywordLog randomKeywordLog() {
		final String UNIQUE = randomUnique();
		final String keywordId = "TEST_KEYWORD_LOG" + UNIQUE;
		//
		KeywordLog result = new KeywordLogImpl();
		// id
		result.setKeywordId(keywordId);
		// ActionType
		result.setActionType(randomType(ActionType.class));
		// before Dictionary
		Dictionary beforeDictionary = new DictionaryImpl();
		String key = randomAlphabet(5);
		beforeDictionary.setKey(key);
		beforeDictionary.setValue(randomAlphabet(5));
		beforeDictionary.setValid(randomBoolean());
		result.setBeforeDictionary(beforeDictionary);
		// after Dictionary
		Dictionary afterDictionary = (Dictionary) beforeDictionary.clone();
		afterDictionary.setValue(randomAlphabet(5));
		afterDictionary.setValid(randomBoolean());
		result.setAfterDictionary(afterDictionary);
		//
		result.setLocale(Locale.TRADITIONAL_CHINESE);

		return result;
	}

	/**
	 * 檢核關鍵字資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertKeywordPo(KeywordLog expected, KeywordLog actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getKeywordId(), actual.getKeywordId());
		//
		assertEquals(expected.getActionType(), actual.getActionType());
		assertEquals(expected.getBeforeDictionary(),
				actual.getBeforeDictionary());
		assertEquals(expected.getAfterDictionary(), actual.getAfterDictionary());
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
			KeywordLog keywordLog = randomKeywordLog();
			// create
			Serializable pk = keywordLogDao.insert(keywordLog);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			KeywordLog foundEntity = keywordLogDao.find(KeywordLogImpl.class,
					keywordLog.getSeq());
			printFind(i, foundEntity);
			assertKeywordPo(keywordLog, foundEntity);

			// update
			keywordLog.setLogDate(DateHelper.today());
			int updated = keywordLogDao.update(keywordLog);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			KeywordLog deletedEntity = keywordLogDao.delete(
					KeywordLogImpl.class, keywordLog.getSeq());
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
			KeywordLog keywordLog = randomKeywordLog();
			// create
			Serializable pk = keywordLogDao.insert(keywordLog);
			printInsert(i, pk);
			assertNotNull(pk);

			KeywordLog foundEntity = keywordLogDao.find(KeywordLogImpl.class,
					keywordLog.getSeq());
			assertKeywordPo(keywordLog, foundEntity);

			System.out.println(keywordLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findKeywordLog() {
		List<KeywordLog> result = null;
		long siteSeq = 1;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = keywordLogDao.findKeywordLog(siteSeq, userId, clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findKeyword::" + count + " times: " + (end - beg)
				+ " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

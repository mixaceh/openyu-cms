package org.openyu.cms.sensitivity.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.sensitivity.log.SensitivityLog;
import org.openyu.cms.sensitivity.log.impl.SensitivityLogImpl;
import org.openyu.cms.sensitivity.vo.ActionType;
import org.openyu.cms.sensitivity.SensitivityTestSupporter;
import org.openyu.commons.util.DateHelper;

public class SensitivityLogImplTest extends SensitivityTestSupporter {
	/**
	 * 隨機產生敏感詞資料
	 * 
	 * @return
	 */
	public static SensitivityLog randomSensitivityLog() {
		final String UNIQUE = randomUnique();
		final String sensitivityId = "TEST_SENSITIVITY_LOG" + UNIQUE;
		//
		SensitivityLog result = new SensitivityLogImpl();
		// id
		result.setSensitivityId(sensitivityId);
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
	 * 檢核敏感詞資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertSensitivityPo(SensitivityLog expected,
			SensitivityLog actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getSensitivityId(), actual.getSensitivityId());
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
			SensitivityLog sensitivityLog = randomSensitivityLog();
			// create
			Serializable pk = sensitivityLogDao.insert(sensitivityLog);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			SensitivityLog foundEntity = sensitivityLogDao.find(
					SensitivityLogImpl.class, sensitivityLog.getSeq());
			printFind(i, foundEntity);
			assertSensitivityPo(sensitivityLog, foundEntity);

			// update
			sensitivityLog.setLogDate(DateHelper.today());
			int updated = sensitivityLogDao.update(sensitivityLog);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			SensitivityLog deletedEntity = sensitivityLogDao.delete(
					SensitivityLogImpl.class, sensitivityLog.getSeq());
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
			SensitivityLog sensitivityLog = randomSensitivityLog();
			// create
			Serializable pk = sensitivityLogDao.insert(sensitivityLog);
			printInsert(i, pk);
			assertNotNull(pk);

			SensitivityLog findSensitivityLog = sensitivityLogDao.find(
					SensitivityLogImpl.class, sensitivityLog.getSeq());
			assertSensitivityPo(sensitivityLog, findSensitivityLog);

			System.out.println(sensitivityLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findSensitivityLog() {

		List<SensitivityLog> result = null;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = sensitivityLogDao.findSensitivityLog(userId, clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findSensitivity::" + count + " times: "
				+ (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

package org.openyu.cms.ad.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.ad.vo.ActionType;
import org.openyu.cms.ad.log.AdLog;
import org.openyu.cms.ad.log.impl.AdLogImpl;
import org.openyu.cms.ad.AdTestSupporter;
import org.openyu.commons.util.DateHelper;

public class AdLogDaoImplTest extends AdTestSupporter {
	/**
	 * 隨機產生廣告資料
	 * 
	 * @return
	 */
	public static AdLog randomAdLog() {
		final String UNIQUE = randomUnique();
		final String adId = "TEST_AD_LOG" + UNIQUE;
		//
		AdLog result = new AdLogImpl();
		// id
		result.setAdId(adId);
		// ActionType
		result.setActionType(randomType(ActionType.class));

		return result;
	}

	/**
	 * 檢核廣告資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertAdPo(AdLog expected, AdLog actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getAdId(), actual.getAdId());
		//
		assertEquals(expected.getActionType(), actual.getActionType());
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
			AdLog adLog = randomAdLog();
			// create
			Serializable pk = adLogDao.insert(adLog);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			AdLog foundEntity = adLogDao.find(AdLogImpl.class, adLog.getSeq());
			printFind(i, foundEntity);
			assertAdPo(adLog, foundEntity);

			// update
			adLog.setLogDate(DateHelper.today());
			int updated = adLogDao.update(adLog);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			AdLog deletedEntity = adLogDao.delete(AdLogImpl.class,
					adLog.getSeq());
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
			AdLog adLog = randomAdLog();
			// create
			Serializable pk = adLogDao.insert(adLog);
			printInsert(i, pk);
			assertNotNull(pk);

			AdLog foundEntity = adLogDao.find(AdLogImpl.class, adLog.getSeq());
			assertAdPo(adLog, foundEntity);

			System.out.println(adLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findAdLog() {
		List<AdLog> result = null;
		long siteSeq = 1;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = adLogDao.findAdLog(siteSeq, userId, clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findAd::" + count + " times: " + (end - beg)
				+ " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

package org.openyu.cms.adSpace.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.adSpace.vo.ActionType;
import org.openyu.cms.adSpace.log.AdSpaceLog;
import org.openyu.cms.adSpace.log.impl.AdSpaceLogImpl;
import org.openyu.cms.adSpace.AdSpaceTestSupporter;
import org.openyu.commons.util.DateHelper;

public class AdSpaceLogDaoImplTest extends AdSpaceTestSupporter {
	/**
	 * 隨機產生廣告板位資料
	 * 
	 * @return
	 */
	public static AdSpaceLog randomAdSpaceLog() {
		final String UNIQUE = randomUnique();
		final String adSpaceId = "TEST_ADSPACE_LOG" + UNIQUE;
		//
		AdSpaceLog result = new AdSpaceLogImpl();
		// id
		result.setAdSpaceId(adSpaceId);
		// ActionType
		result.setActionType(ActionType.INSERT);
		//

		return result;
	}

	/**
	 * 檢核廣告板位資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertAdSpacePo(AdSpaceLog expected, AdSpaceLog actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getAdSpaceId(), actual.getAdSpaceId());
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
			AdSpaceLog adSpaceLog = randomAdSpaceLog();
			// create
			Serializable pk = adSpaceLogDao.insert(adSpaceLog);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			AdSpaceLog foundEntity = adSpaceLogDao.find(AdSpaceLogImpl.class,
					adSpaceLog.getSeq());
			printFind(i, foundEntity);
			assertAdSpacePo(adSpaceLog, foundEntity);

			// update
			adSpaceLog.setLogDate(DateHelper.today());
			int updated = adSpaceLogDao.update(adSpaceLog);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			AdSpaceLog deletedEntity = adSpaceLogDao.delete(
					AdSpaceLogImpl.class, adSpaceLog.getSeq());
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
			AdSpaceLog adSpaceLog = randomAdSpaceLog();
			// create
			Serializable pk = adSpaceLogDao.insert(adSpaceLog);
			printInsert(i, pk);
			assertNotNull(pk);

			AdSpaceLog foundEntity = adSpaceLogDao.find(AdSpaceLogImpl.class,
					adSpaceLog.getSeq());
			assertAdSpacePo(adSpaceLog, foundEntity);

			System.out.println(adSpaceLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findAdSpaceLog() {
		List<AdSpaceLog> result = null;
		long siteSeq = 1;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = adSpaceLogDao.findAdSpaceLog(siteSeq, userId, clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findAdSpace::" + count + " times: " + (end - beg)
				+ " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

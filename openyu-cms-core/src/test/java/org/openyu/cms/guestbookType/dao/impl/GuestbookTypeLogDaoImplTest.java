package org.openyu.cms.guestbookType.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.guestbookType.vo.ActionType;
import org.openyu.cms.guestbookType.log.GuestbookTypeLog;
import org.openyu.cms.guestbookType.log.impl.GuestbookTypeLogImpl;
import org.openyu.cms.guestbookType.GuestbookTypeTestSupporter;
import org.openyu.commons.util.DateHelper;

public class GuestbookTypeLogDaoImplTest extends GuestbookTypeTestSupporter {
	/**
	 * 隨機產生留言類型資料
	 * 
	 * @return
	 */
	public static GuestbookTypeLog randomGuestbookTypeLog() {
		final String UNIQUE = randomUnique();
		final String guestbookTypeId = "TEST_GUESTBOOKTYPE_LOG" + UNIQUE;
		//
		GuestbookTypeLog result = new GuestbookTypeLogImpl();
		// id
		result.setGuestbookTypeId(guestbookTypeId);
		// ActionType
		result.setActionType(randomType(ActionType.class));

		return result;
	}

	/**
	 * 檢核留言類型資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertGuestbookTypePo(GuestbookTypeLog expected,
			GuestbookTypeLog actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getGuestbookTypeId(), actual.getGuestbookTypeId());
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
			GuestbookTypeLog guestbookTypeLog = randomGuestbookTypeLog();
			// create
			Serializable pk = guestbookTypeLogDao.insert(guestbookTypeLog);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			GuestbookTypeLog foundEntity = guestbookTypeLogDao.find(
					GuestbookTypeLogImpl.class, guestbookTypeLog.getSeq());
			printFind(i, foundEntity);
			assertGuestbookTypePo(guestbookTypeLog, foundEntity);

			// update
			guestbookTypeLog.setLogDate(DateHelper.today());
			int updated = guestbookTypeLogDao.update(guestbookTypeLog);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			GuestbookTypeLog deletedEntity = guestbookTypeLogDao.delete(
					GuestbookTypeLogImpl.class, guestbookTypeLog.getSeq());
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
			GuestbookTypeLog guestbookTypeLog = randomGuestbookTypeLog();
			//
			Serializable pk = guestbookTypeLogDao.insert(guestbookTypeLog);
			printInsert(i, pk);
			assertNotNull(pk);

			GuestbookTypeLog findGuestbookTypeLog = guestbookTypeLogDao.find(
					GuestbookTypeLogImpl.class, guestbookTypeLog.getSeq());
			assertGuestbookTypePo(guestbookTypeLog, findGuestbookTypeLog);

			System.out.println(guestbookTypeLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findGuestbookTypeLog() {
		List<GuestbookTypeLog> result = null;
		long siteSeq = 1;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = guestbookTypeLogDao.findGuestbookTypeLog(siteSeq, userId,
					clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findGuestbookType::" + count + " times: "
				+ (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

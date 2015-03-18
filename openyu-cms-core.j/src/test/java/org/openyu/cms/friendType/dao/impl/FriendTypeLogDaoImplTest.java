package org.openyu.cms.friendType.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.friendType.vo.ActionType;
import org.openyu.cms.friendType.log.FriendTypeLog;
import org.openyu.cms.friendType.log.impl.FriendTypeLogImpl;
import org.openyu.cms.friendType.FriendTypeTestSupporter;
import org.openyu.commons.util.DateHelper;

public class FriendTypeLogDaoImplTest extends FriendTypeTestSupporter {
	/**
	 * 隨機產生友情類型資料
	 * 
	 * @return
	 */
	public static FriendTypeLog randomFriendTypeLog() {
		final String UNIQUE = randomUnique();
		final String friendTypeId = "TEST_FRIENDTYPE_LOG" + UNIQUE;
		//
		FriendTypeLog result = new FriendTypeLogImpl();
		// id
		result.setFriendTypeId(friendTypeId);
		// ActionType
		result.setActionType(randomType(ActionType.class));

		return result;
	}

	/**
	 * 檢核友情類型資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertFriendTypePo(FriendTypeLog expected,
			FriendTypeLog actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getFriendTypeId(), actual.getFriendTypeId());
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
			FriendTypeLog friendTypeLog = randomFriendTypeLog();
			// create
			Serializable pk = friendTypeLogDao.insert(friendTypeLog);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			FriendTypeLog foundEntity = friendTypeLogDao.find(
					FriendTypeLogImpl.class, friendTypeLog.getSeq());
			printFind(i, foundEntity);
			assertFriendTypePo(friendTypeLog, foundEntity);

			// update
			friendTypeLog.setLogDate(DateHelper.today());
			int updated = friendTypeLogDao.update(friendTypeLog);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			FriendTypeLog deletedEntity = friendTypeLogDao.delete(
					FriendTypeLogImpl.class, friendTypeLog.getSeq());
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
			FriendTypeLog friendTypeLog = randomFriendTypeLog();
			// create
			Serializable pk = friendTypeLogDao.insert(friendTypeLog);
			printInsert(i, pk);
			assertNotNull(pk);

			FriendTypeLog foundEntity = friendTypeLogDao.find(
					FriendTypeLogImpl.class, friendTypeLog.getSeq());
			assertFriendTypePo(friendTypeLog, foundEntity);

			System.out.println(friendTypeLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findFriendTypeLog() {
		List<FriendTypeLog> result = null;
		long siteSeq = 1;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendTypeLogDao.findFriendTypeLog(siteSeq, userId,
					clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findFriendType::" + count + " times: "
				+ (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

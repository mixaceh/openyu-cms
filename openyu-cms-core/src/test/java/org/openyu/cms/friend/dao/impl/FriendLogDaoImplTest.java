package org.openyu.cms.friend.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.friend.vo.ActionType;
import org.openyu.cms.friend.log.FriendLog;
import org.openyu.cms.friend.log.impl.FriendLogImpl;
import org.openyu.cms.friend.FriendTestSupporter;
import org.openyu.commons.util.DateHelper;

public class FriendLogDaoImplTest extends FriendTestSupporter {
	/**
	 * 隨機產生友情連結資料
	 * 
	 * @return
	 */
	public static FriendLog randomFriendLog() {
		final String UNIQUE = randomUnique();
		final String friendId = "TEST_FRIEND_LOG" + UNIQUE;
		//
		FriendLog result = new FriendLogImpl();
		// id
		result.setFriendId(friendId);
		// ActionType
		result.setActionType(randomType(ActionType.class));

		return result;
	}

	/**
	 * 檢核友情連結資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertFriendPo(FriendLog expected, FriendLog actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getFriendId(), actual.getFriendId());
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
			FriendLog friendLog = randomFriendLog();
			// create
			Serializable pk = friendLogDao.insert(friendLog);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			FriendLog foundEntity = friendLogDao.find(FriendLogImpl.class,
					friendLog.getSeq());
			printFind(i, foundEntity);
			assertFriendPo(friendLog, foundEntity);

			// update
			friendLog.setLogDate(DateHelper.today());
			int updated = friendLogDao.update(friendLog);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			FriendLog deletedEntity = friendLogDao.delete(FriendLogImpl.class,
					friendLog.getSeq());
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
			FriendLog friendLog = randomFriendLog();
			// create
			Serializable pk = friendLogDao.insert(friendLog);
			printInsert(i, pk);
			assertNotNull(pk);

			FriendLog foundEntity = friendLogDao.find(FriendLogImpl.class,
					friendLog.getSeq());
			assertFriendPo(friendLog, foundEntity);

			System.out.println(friendLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findFriendLog() {
		List<FriendLog> result = null;
		long siteSeq = 1;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = friendLogDao.findFriendLog(siteSeq, userId, clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findFriend::" + count + " times: " + (end - beg)
				+ " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

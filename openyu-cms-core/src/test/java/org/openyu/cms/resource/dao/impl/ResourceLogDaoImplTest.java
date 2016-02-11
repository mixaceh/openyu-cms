package org.openyu.cms.resource.dao.impl;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.resource.ResourceTestSupporter;
import org.openyu.cms.resource.log.ResourceLog;
import org.openyu.cms.resource.log.impl.ResourceLogImpl;
import org.openyu.commons.util.DateHelper;

public class ResourceLogDaoImplTest extends ResourceTestSupporter {
	// --------------------------------------------------
	// ResourceLog
	// --------------------------------------------------
	public static class ResourceLogTest extends ResourceTestSupporter {
		/**
		 * 隨機資源改變log
		 * 
		 * @return
		 */
		public static ResourceLog randomResourceLog() {
			final String UNIQUE = randomUnique();
			//
			final String USER_ID = "TEST_USER" + UNIQUE;
			final String USER_NAME = "測試使用者" + UNIQUE;
			//
			ResourceLog result = new ResourceLogImpl();
			result.setLogDate(DateHelper.today());
			result.setUserId(USER_ID);
			result.setUserName(USER_NAME);
			result.setClientIp(randomIp("200.0.0"));
			result.setServerIp(randomIp("10.0.0"));
			//
			result.setActionType(randomType(ActionType.class));
			result.setBeforePath(randomString(20));
			result.setAfterPath(randomString(20));
			return result;
		}

		/**
		 * 檢核資源改變log
		 * 
		 * @param expected
		 * @param actual
		 */
		public static void assertResourceLog(ResourceLog expected,
				ResourceLog actual) {
			if (expected == null) {
				assertNull(actual);
			} else {
				// #issue assert date 值不對
				// #fix assertDate,轉成字串比對
				assertEquals(expected.getLogDate(), actual.getLogDate());
				assertEquals(expected.getUserId(), actual.getUserId());
				assertEquals(expected.getUserName(), actual.getUserName());
				assertEquals(expected.getClientIp(), actual.getClientIp());
				assertEquals(expected.getServerIp(), actual.getServerIp());
				//
				assertEquals(expected.getActionType(), actual.getActionType());
				assertEquals(expected.getBeforePath(), actual.getBeforePath());
				assertEquals(expected.getAfterPath(), actual.getAfterPath());
			}
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
			int result = 0;
			int count = 10;
			long beg = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				// 隨機
				ResourceLog resourceLog = randomResourceLog();
				// create
				Serializable pk = resourceLogDao.insert(resourceLog);
				printInsert(i, pk);
				assertNotNull(pk);

				// retrieve
				ResourceLog foundEntity = resourceLogDao.find(
						ResourceLogImpl.class, resourceLog.getSeq());
				printFind(i, foundEntity);
				assertResourceLog(resourceLog, foundEntity);

				// update
				resourceLog.setLogDate(DateHelper.today());
				int updated = resourceLogDao.update(resourceLog);
				printUpdate(i, updated);
				assertTrue(updated > 0);

				// delete
				ResourceLog deletedEntity = resourceLogDao.delete(
						ResourceLogImpl.class, resourceLog.getSeq());
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
				ResourceLog resourceLog = randomResourceLog();
				//
				Serializable pk = resourceLogDao.insert(resourceLog);
				printInsert(i, pk);
				assertNotNull(pk);

				ResourceLog foundEntity = resourceLogDao.find(
						ResourceLogImpl.class, resourceLog.getSeq());
				assertResourceLog(resourceLog, foundEntity);

				System.out.println(resourceLog);
			}
			long end = System.currentTimeMillis();
			System.out.println(count + " times: " + (end - beg) + " mills. ");
		}

		@Test
		// 1 times: 272 mills.
		// 1 times: 276 mills.
		// 1 times: 276 mills.
		// verified: ok
		public void findResourceLog() {
			List<ResourceLog> result = null;
			//
			long siteId = 1;
			String userId = "sys";
			String clientId = "127.0.0.1";
			//
			int count = 1;
			long beg = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				result = resourceLogDao.findResourceLog(siteId, userId,
						clientId);
			}
			long end = System.currentTimeMillis();
			System.out.println(count + " times: " + (end - beg) + " mills. ");

			System.out.println(result.size() + ", " + result);
			assertNotNull(result);
		}

		@Test
		public void deleteResourceLog() {
			long siteId = 1;
			String userId = "sys";
			//
			int result = 0;
			int count = 1;
			long beg = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				result = resourceLogDao.deleteResourceLog(siteId, userId);
			}
			long end = System.currentTimeMillis();
			System.out.println(count + " times: " + (end - beg) + " mills. ");

			System.out.println(result);
			assertTrue(result > 0);
		}
	}
}

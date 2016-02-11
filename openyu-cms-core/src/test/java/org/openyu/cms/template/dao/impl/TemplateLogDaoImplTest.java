package org.openyu.cms.template.dao.impl;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.template.TemplateTestSupporter;
import org.openyu.cms.template.log.TemplateLog;
import org.openyu.cms.template.log.impl.TemplateLogImpl;
import org.openyu.commons.util.DateHelper;

public class TemplateLogDaoImplTest extends TemplateTestSupporter {
	// --------------------------------------------------
	// TemplateLog
	// --------------------------------------------------
	public static class TemplateLogTest extends TemplateTestSupporter {
		/**
		 * 隨機資源改變log
		 * 
		 * @return
		 */
		public static TemplateLog randomTemplateLog() {
			final String UNIQUE = randomUnique();
			//
			final String USER_ID = "TEST_USER" + UNIQUE;
			final String USER_NAME = "測試使用者" + UNIQUE;
			//
			TemplateLog result = new TemplateLogImpl();
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
		public static void assertTemplateLog(TemplateLog expected,
				TemplateLog actual) {
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
			int count = 10;
			long beg = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				// 隨機
				TemplateLog templateLog = randomTemplateLog();
				// create
				Serializable pk = templateLogDao.insert(templateLog);
				printInsert(i, pk);
				assertNotNull(pk);

				// retrieve
				TemplateLog foundEntity = templateLogDao.find(
						TemplateLogImpl.class, templateLog.getSeq());
				printFind(i, foundEntity);
				assertTemplateLog(templateLog, foundEntity);

				// update
				templateLog.setLogDate(DateHelper.today());
				int updated = templateLogDao.update(templateLog);
				printUpdate(i, updated);
				assertTrue(updated > 0);

				// delete
				TemplateLog deletedEntity = templateLogDao.delete(
						TemplateLogImpl.class, templateLog.getSeq());
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
				TemplateLog templateLog = randomTemplateLog();
				//
				Serializable pk = templateLogDao.insert(templateLog);
				printInsert(i, pk);
				assertNotNull(pk);

				TemplateLog foundEntity = templateLogDao.find(
						TemplateLogImpl.class, templateLog.getSeq());
				assertTemplateLog(templateLog, foundEntity);

				System.out.println(templateLog);
			}
			long end = System.currentTimeMillis();
			System.out.println(count + " times: " + (end - beg) + " mills. ");
		}

		@Test
		// 1 times: 272 mills.
		// 1 times: 276 mills.
		// 1 times: 276 mills.
		// verified: ok
		public void findTemplateLog() {
			List<TemplateLog> result = null;
			//
			long siteSeq = 1;
			String userId = "sys";
			String clientId = "127.0.0.1";
			//
			int count = 1;
			long beg = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				result = templateLogDao.findTemplateLog(siteSeq, userId,
						clientId);
			}
			long end = System.currentTimeMillis();
			System.out.println(count + " times: " + (end - beg) + " mills. ");

			System.out.println(result.size() + ", " + result);
			assertNotNull(result);
		}

		@Test
		public void deleteTemplateLog() {
			long siteSeq = 1;
			String userId = "sys";
			//
			int result = 0;
			int count = 1;
			long beg = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				result = templateLogDao.deleteTemplateLog(siteSeq, userId);
			}
			long end = System.currentTimeMillis();
			System.out.println(count + " times: " + (end - beg) + " mills. ");

			System.out.println(result);
			assertTrue(result > 0);
		}
	}
}

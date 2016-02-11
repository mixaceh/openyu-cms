package org.openyu.cms.tag.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.tag.vo.ActionType;
import org.openyu.cms.tag.log.TagLog;
import org.openyu.cms.tag.log.impl.TagLogImpl;
import org.openyu.cms.tag.TagTestSupporter;
import org.openyu.commons.util.DateHelper;

public class TagLogDaoImplTest extends TagTestSupporter {
	/**
	 * 隨機產生標籤資料
	 * 
	 * @return
	 */
	public static TagLog randomTagLog() {
		final String UNIQUE = randomUnique();
		final String tagId = "TEST_TAG_LOG" + UNIQUE;
		//
		TagLog result = new TagLogImpl();
		// id
		result.setTagId(tagId);
		// ActionType
		result.setActionType(randomType(ActionType.class));

		return result;
	}

	/**
	 * 檢核標籤資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertTagPo(TagLog expected, TagLog actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getTagId(), actual.getTagId());
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
			TagLog tagLog = randomTagLog();
			// create
			Serializable pk = tagLogDao.insert(tagLog);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			TagLog foundEntity = tagLogDao.find(TagLogImpl.class,
					tagLog.getSeq());
			printFind(i, foundEntity);
			assertTagPo(tagLog, foundEntity);

			// update
			tagLog.setLogDate(DateHelper.today());
			int updated = tagLogDao.update(tagLog);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			TagLog deletedEntity = tagLogDao.delete(TagLogImpl.class,
					tagLog.getSeq());
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
			TagLog tagLog = randomTagLog();
			// create
			Serializable pk = tagLogDao.insert(tagLog);
			printInsert(i, pk);
			assertNotNull(pk);

			TagLog foundEntity = tagLogDao.find(TagLogImpl.class,
					tagLog.getSeq());
			assertTagPo(tagLog, foundEntity);

			System.out.println(tagLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findTagLog() {
		List<TagLog> result = null;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = tagLogDao.findTagLog(userId, clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findTag::" + count + " times: " + (end - beg)
				+ " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

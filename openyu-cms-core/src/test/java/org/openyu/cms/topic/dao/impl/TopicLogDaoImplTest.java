package org.openyu.cms.topic.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.topic.vo.ActionType;
import org.openyu.cms.topic.log.TopicLog;
import org.openyu.cms.topic.log.impl.TopicLogImpl;
import org.openyu.cms.topic.TopicTestSupporter;
import org.openyu.commons.util.DateHelper;

public class TopicLogDaoImplTest extends TopicTestSupporter {
	/**
	 * 隨機產生專題資料
	 * 
	 * @return
	 */
	public static TopicLog randomTopicLog() {
		final String UNIQUE = randomUnique();
		final String topicId = "TEST_TOPIC_LOG" + UNIQUE;
		//
		TopicLog result = new TopicLogImpl();
		// id
		result.setTopicId(topicId);
		// ActionType
		result.setActionType(randomType(ActionType.class));

		return result;
	}

	/**
	 * 檢核專題資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertTopicPo(TopicLog expected, TopicLog actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getTopicId(), actual.getTopicId());
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
			TopicLog topicLog = randomTopicLog();
			// create
			Serializable pk = topicLogDao.insert(topicLog);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			TopicLog foundEntity = topicLogDao.find(TopicLogImpl.class,
					topicLog.getSeq());
			printFind(i, foundEntity);
			assertTopicPo(topicLog, foundEntity);

			// update
			topicLog.setLogDate(DateHelper.today());
			int updated = topicLogDao.update(topicLog);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			TopicLog deletedEntity = topicLogDao.delete(TopicLogImpl.class,
					topicLog.getSeq());
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
			TopicLog topicLog = randomTopicLog();
			// create
			Serializable pk = topicLogDao.insert(topicLog);
			printInsert(i, pk);
			assertNotNull(pk);

			TopicLog foundEntity = topicLogDao.find(TopicLogImpl.class,
					topicLog.getSeq());
			assertTopicPo(topicLog, foundEntity);

			System.out.println(topicLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findTopicLog() {
		List<TopicLog> result = null;
		long siteSeq = 1;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = topicLogDao.findTopicLog(siteSeq, userId, clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findTopic::" + count + " times: " + (end - beg)
				+ " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

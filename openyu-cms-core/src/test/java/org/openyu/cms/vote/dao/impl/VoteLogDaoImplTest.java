package org.openyu.cms.vote.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.vote.vo.ActionType;
import org.openyu.cms.vote.log.VoteLog;
import org.openyu.cms.vote.log.impl.VoteLogImpl;
import org.openyu.cms.vote.VoteTestSupporter;

public class VoteLogDaoImplTest extends VoteTestSupporter {
	/**
	 * 隨機產生投票資料
	 * 
	 * @return
	 */
	public static VoteLog randomVoteLog() {
		final String UNIQUE = randomUnique();
		final String voteId = "TEST_VOTE_LOG" + UNIQUE;
		//
		VoteLog result = new VoteLogImpl();
		// id
		result.setVoteId(voteId);
		// ActionType
		result.setActionType(ActionType.INSERT);
		//

		return result;
	}

	/**
	 * 檢核投票資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertVotePo(VoteLog expected, VoteLog actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getVoteId(), actual.getVoteId());
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
			VoteLog voteLog = randomVoteLog();
			// create
			Serializable pk = voteLogDao.insert(voteLog);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			VoteLog foundEntity = voteLogDao.find(VoteLogImpl.class,
					voteLog.getSeq());
			printFind(i, foundEntity);
			assertVotePo(voteLog, foundEntity);

			// delete
			VoteLog deletedEntity = voteLogDao.delete(VoteLogImpl.class,
					voteLog.getSeq());
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
			VoteLog voteLog = randomVoteLog();
			// create
			Serializable pk = voteLogDao.insert(voteLog);
			printInsert(i, pk);
			assertNotNull(pk);

			VoteLog foundEntity = voteLogDao.find(VoteLogImpl.class,
					voteLog.getSeq());
			assertVotePo(voteLog, foundEntity);

			System.out.println(voteLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findVoteLog() {
		List<VoteLog> result = null;
		long siteSeq = 1;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteLogDao.findVoteLog(siteSeq, userId, clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findVote::" + count + " times: " + (end - beg)
				+ " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

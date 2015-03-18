package org.openyu.cms.vote.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.vote.vo.ActionType;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.vo.impl.VoteImpl;
import org.openyu.cms.vote.VoteTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class VoteLogServiceImplTest extends VoteTestSupporter
{

	@After
	public void tearDown() throws Exception
	{
		ThreadHelper.sleep(5 * 1000);
	}

	@Test
	public void recordChangeDictionary()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_VOTE_LOG" + UNIQUE;
		//
		User user = new UserImpl();
		Vote vote = new VoteImpl(ID);

		voteLogService.recordChangeVote(user, ActionType.INSERT, vote);
	}

}

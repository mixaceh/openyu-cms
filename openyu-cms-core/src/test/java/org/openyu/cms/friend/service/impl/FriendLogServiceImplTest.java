package org.openyu.cms.friend.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.friend.vo.ActionType;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friend.vo.impl.FriendImpl;
import org.openyu.cms.friend.FriendTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class FriendLogServiceImplTest extends FriendTestSupporter
{

	@After
	public void tearDown() throws Exception
	{
		ThreadHelper.sleep(5 * 1000);
	}

	@Test
	public void recordChangeFriend()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_FRIEND" + UNIQUE;
		//
		User user = new UserImpl();
		Friend friend = new FriendImpl(ID);
		//
		friendLogService.recordChangeFriend(user, ActionType.INSERT, friend);
	}

}

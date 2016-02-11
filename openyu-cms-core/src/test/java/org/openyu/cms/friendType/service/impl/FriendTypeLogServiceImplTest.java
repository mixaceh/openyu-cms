package org.openyu.cms.friendType.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.friendType.vo.ActionType;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.cms.friendType.FriendTypeTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class FriendTypeLogServiceImplTest extends FriendTypeTestSupporter
{

	@After
	public void tearDown() throws Exception
	{
		ThreadHelper.sleep(5 * 1000);
	}

	@Test
	public void recordChangeFriendType()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_FRIENDTYPE" + UNIQUE;
		//
		User user = new UserImpl();
		FriendType friendType = new FriendTypeImpl(ID);
		//
		friendTypeLogService.recordChangeFriendType(user, ActionType.INSERT, friendType);
	}

}

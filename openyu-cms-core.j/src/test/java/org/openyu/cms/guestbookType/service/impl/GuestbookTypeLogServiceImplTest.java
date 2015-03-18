package org.openyu.cms.guestbookType.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.guestbookType.vo.ActionType;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.cms.guestbookType.GuestbookTypeTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class GuestbookTypeLogServiceImplTest extends GuestbookTypeTestSupporter
{

	@After
	public void tearDown() throws Exception
	{
		ThreadHelper.sleep(5 * 1000);
	}

	@Test
	public void recordChangeGuestbookType()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_GUESTBOOKTYPE" + UNIQUE;
		//
		User user = new UserImpl();
		GuestbookType guestbookType = new GuestbookTypeImpl(ID);
		//
		guestbookTypeLogService.recordChangeGuestbookType(user, ActionType.INSERT, guestbookType);
	}

}

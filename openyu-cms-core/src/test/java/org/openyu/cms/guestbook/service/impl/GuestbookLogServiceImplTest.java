package org.openyu.cms.guestbook.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.guestbook.vo.ActionType;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbook.vo.impl.GuestbookImpl;
import org.openyu.cms.guestbook.GuestbookTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class GuestbookLogServiceImplTest extends GuestbookTestSupporter
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
		final String ID = "TEST_GUESTBOOK_LOG" + UNIQUE;
		//
		User user = new UserImpl();
		Guestbook guestbook = new GuestbookImpl(ID);

		guestbookLogService.recordChangeGuestbook(user, ActionType.INSERT, guestbook);
	}

}

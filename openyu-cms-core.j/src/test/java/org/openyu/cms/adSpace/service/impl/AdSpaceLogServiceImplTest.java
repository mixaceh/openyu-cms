package org.openyu.cms.adSpace.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.adSpace.vo.ActionType;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.vo.impl.AdSpaceImpl;
import org.openyu.cms.adSpace.AdSpaceTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class AdSpaceLogServiceImplTest extends AdSpaceTestSupporter
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
		final String ID = "TEST_ADSPACE_LOG" + UNIQUE;
		//
		User user = new UserImpl();
		AdSpace adSpace = new AdSpaceImpl(ID);

		adSpaceLogService.recordChangeAdSpace(user, ActionType.INSERT, adSpace);
	}

}

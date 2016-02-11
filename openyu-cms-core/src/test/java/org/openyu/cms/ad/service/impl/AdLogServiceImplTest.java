package org.openyu.cms.ad.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.ad.vo.ActionType;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.ad.vo.impl.AdImpl;
import org.openyu.cms.ad.AdTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class AdLogServiceImplTest extends AdTestSupporter
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
		final String ID = "TEST_AD_LOG" + UNIQUE;
		//
		User user = new UserImpl();
		Ad ad = new AdImpl(ID);

		adLogService.recordChangeAd(user, ActionType.INSERT, ad);
	}

}

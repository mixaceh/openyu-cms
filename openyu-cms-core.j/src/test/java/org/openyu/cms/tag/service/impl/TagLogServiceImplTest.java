package org.openyu.cms.tag.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.tag.vo.ActionType;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.tag.vo.impl.TagImpl;
import org.openyu.cms.tag.TagTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class TagLogServiceImplTest extends TagTestSupporter
{

	@After
	public void tearDown() throws Exception
	{
		ThreadHelper.sleep(5 * 1000);
	}

	@Test
	public void recordChangeTag()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_TAG" + UNIQUE;
		//
		User user = new UserImpl();
		Tag tag = new TagImpl(ID);
		//
		tagLogService.recordChangeTag(user, ActionType.INSERT, tag);
	}

}

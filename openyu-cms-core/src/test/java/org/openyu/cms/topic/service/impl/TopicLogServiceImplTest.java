package org.openyu.cms.topic.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.topic.vo.ActionType;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.topic.vo.impl.TopicImpl;
import org.openyu.cms.topic.TopicTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class TopicLogServiceImplTest extends TopicTestSupporter
{

	@After
	public void tearDown() throws Exception
	{
		ThreadHelper.sleep(5 * 1000);
	}

	@Test
	public void recordChangeTopic()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_TOPIC" + UNIQUE;
		//
		User user = new UserImpl();
		Topic topic = new TopicImpl(ID);
		//
		topicLogService.recordChangeTopic(user, ActionType.INSERT, topic);
	}

}

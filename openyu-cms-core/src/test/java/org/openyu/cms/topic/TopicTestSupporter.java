package org.openyu.cms.topic;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.topic.dao.TopicDao;
import org.openyu.cms.topic.dao.TopicLogDao;
import org.openyu.cms.topic.service.TopicLogService;
import org.openyu.cms.topic.service.TopicService;
import org.openyu.cms.topic.web.freeMarker.TopicDirective;
import org.openyu.cms.topic.web.freeMarker.TopicListDirective;

import org.openyu.cms.app.AppTestSupporter;

public class TopicTestSupporter extends AppTestSupporter
{

	protected static TopicDao topicDao;

	protected static TopicService topicService;

	protected static TopicLogDao topicLogDao;

	protected static TopicLogService topicLogService;

	protected static TopicListDirective topicListDirective;

	protected static TopicDirective topicDirective;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
				"applicationContext-init.xml",//
				"META-INF/applicationContext-commons-core.xml",//
				"applicationContext-message.xml",//
				"applicationContext-database.xml",//
				"applicationContext-database-log.xml",//
				"applicationContext-cxf.xml",//
				"applicationContext-junit.xml",//
				"org/openyu/cms/app/applicationContext-app.xml",//
				"org/openyu/cms/user/applicationContext-user.xml",//
				"org/openyu/cms/site/applicationContext-site.xml",//
				"org/openyu/cms/topic/applicationContext-topic.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		topicDao = (TopicDao) applicationContext.getBean("topicDao");
		topicService = (TopicService) applicationContext.getBean("topicService");
		topicLogDao = (TopicLogDao) applicationContext.getBean("topicLogDao");
		topicLogService = (TopicLogService) applicationContext.getBean("topicLogService");
		topicListDirective = (TopicListDirective) applicationContext.getBean("cms_topic_list");
		topicDirective = (TopicDirective) applicationContext.getBean("cms_topic");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{}

	@Before
	public void setUp() throws Exception
	{}

	@After
	public void tearDown() throws Exception
	{}

	@Test
	public void topicDao()
	{
		System.out.println(topicDao);
		assertNotNull(topicDao);
	}

	@Test
	public void topicService()
	{
		System.out.println(topicService);
		assertNotNull(topicService);
	}

	@Test
	public void topicLogDao()
	{
		System.out.println(topicLogDao);
		assertNotNull(topicLogDao);
	}

	@Test
	public void topicLogService()
	{
		System.out.println(topicLogService);
		assertNotNull(topicLogService);
	}

	@Test
	public void topicListDirective()
	{
		System.out.println(topicListDirective);
		assertNotNull(topicListDirective);
	}

	@Test
	public void topicDirective()
	{
		System.out.println(topicDirective);
		assertNotNull(topicDirective);
	}
}

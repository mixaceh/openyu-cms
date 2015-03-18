package org.openyu.cms.friend;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.friend.dao.FriendDao;
import org.openyu.cms.friend.service.FriendService;
import org.openyu.cms.friend.web.freeMarker.FriendDirective;
import org.openyu.cms.friend.web.freeMarker.FriendListDirective;
import org.openyu.cms.friend.dao.FriendLogDao;
import org.openyu.cms.friend.service.FriendLogService;

import org.openyu.cms.app.AppTestSupporter;

public class FriendTestSupporter extends AppTestSupporter
{

	protected static FriendDao friendDao;

	protected static FriendService friendService;

	protected static FriendLogDao friendLogDao;

	protected static FriendLogService friendLogService;
	
	protected static FriendListDirective friendListDirective;

	protected static FriendDirective friendDirective;

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
				"org/openyu/cms/friendType/applicationContext-friendType.xml",//
				"org/openyu/cms/friend/applicationContext-friend.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		friendDao = (FriendDao) applicationContext.getBean("friendDao");
		friendService = (FriendService) applicationContext.getBean("friendService");
		friendLogDao = (FriendLogDao) applicationContext.getBean("friendLogDao");
		friendLogService = (FriendLogService) applicationContext.getBean("friendLogService");
		friendListDirective = (FriendListDirective) applicationContext.getBean("cms_friend_list");
		friendDirective = (FriendDirective) applicationContext.getBean("cms_friend");
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
	public void friendDao()
	{
		System.out.println(friendDao);
		assertNotNull(friendDao);
	}

	@Test
	public void friendService()
	{
		System.out.println(friendService);
		assertNotNull(friendService);
	}

	@Test
	public void friendLogDao()
	{
		System.out.println(friendLogDao);
		assertNotNull(friendLogDao);
	}

	@Test
	public void friendLogService()
	{
		System.out.println(friendLogService);
		assertNotNull(friendLogService);
	}
	
	@Test
	public void friendListDirective()
	{
		System.out.println(friendListDirective);
		assertNotNull(friendListDirective);
	}

	@Test
	public void friendDirective()
	{
		System.out.println(friendDirective);
		assertNotNull(friendDirective);
	}
}

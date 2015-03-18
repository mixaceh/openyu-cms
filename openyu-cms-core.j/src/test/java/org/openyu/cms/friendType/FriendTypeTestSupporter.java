package org.openyu.cms.friendType;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.app.AppTestSupporter;
import org.openyu.cms.friendType.dao.FriendTypeDao;
import org.openyu.cms.friendType.service.FriendTypeService;
import org.openyu.cms.friendType.service.adapter.FriendTypeBeanAdapter;
import org.openyu.cms.friendType.web.freeMarker.FriendTypeDirective;
import org.openyu.cms.friendType.web.freeMarker.FriendTypeListDirective;
import org.openyu.cms.friendType.dao.FriendTypeLogDao;
import org.openyu.cms.friendType.service.FriendTypeLogService;

public class FriendTypeTestSupporter extends AppTestSupporter
{

	protected static FriendTypeDao friendTypeDao;

	protected static FriendTypeService friendTypeService;

	protected static FriendTypeLogDao friendTypeLogDao;

	protected static FriendTypeLogService friendTypeLogService;
	
	protected static FriendTypeBeanAdapter friendTypeBeanAdapter;

	protected static FriendTypeListDirective friendTypeListDirective;

	protected static FriendTypeDirective friendTypeDirective;

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
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		friendTypeDao = (FriendTypeDao) applicationContext.getBean("friendTypeDao");
		friendTypeService = (FriendTypeService) applicationContext.getBean("friendTypeService");
		friendTypeLogDao = (FriendTypeLogDao) applicationContext.getBean("friendTypeLogDao");
		friendTypeLogService = (FriendTypeLogService) applicationContext.getBean("friendTypeLogService");
		friendTypeBeanAdapter = (FriendTypeBeanAdapter) applicationContext
				.getBean("friendTypeBeanAdapter");
		friendTypeListDirective = (FriendTypeListDirective) applicationContext
				.getBean("cms_friend_type_list");
		friendTypeDirective = (FriendTypeDirective) applicationContext.getBean("cms_friend_type");
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
	public void friendTypeDao()
	{
		System.out.println(friendTypeDao);
		assertNotNull(friendTypeDao);
	}

	@Test
	public void friendTypeService()
	{
		System.out.println(friendTypeService);
		assertNotNull(friendTypeService);
	}

	@Test
	public void friendTypeLogDao()
	{
		System.out.println(friendTypeLogDao);
		assertNotNull(friendTypeLogDao);
	}

	@Test
	public void friendTypeLogService()
	{
		System.out.println(friendTypeLogService);
		assertNotNull(friendTypeLogService);
	}
	
	@Test
	public void friendTypeBeanAdapter()
	{
		System.out.println(friendTypeBeanAdapter);
		assertNotNull(friendTypeBeanAdapter);
	}

	@Test
	public void friendTypeListDirective()
	{
		System.out.println(friendTypeListDirective);
		assertNotNull(friendTypeListDirective);
	}

	@Test
	public void friendTypeDirective()
	{
		System.out.println(friendTypeDirective);
		assertNotNull(friendTypeDirective);
	}
}

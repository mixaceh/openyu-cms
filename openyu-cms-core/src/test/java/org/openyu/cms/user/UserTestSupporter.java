package org.openyu.cms.user;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.app.AppTestSupporter;

public class UserTestSupporter extends AppTestSupporter
{

	//	protected static UserDao userDao;
	//
	//	protected static UserService userService;
	//
	//	protected static UserBeanAdapter userBeanAdapter;

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
				"org/openyu/cms/ftp/applicationContext-ftp.xml",//
				"org/openyu/cms/user/applicationContext-user.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		//		userDao = (UserDao) applicationContext.getBean("userDao");
		//		userService = (UserService) applicationContext.getBean("userService");
		//		userBeanAdapter = (UserBeanAdapter) applicationContext.getBean("userBeanAdapter");
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

	//	@Test
	//	public void userDao()
	//	{
	//		System.out.println(userDao);
	//		assertNotNull(userDao);
	//	}
	//
	//	@Test
	//	public void userService()
	//	{
	//		System.out.println(userService);
	//		assertNotNull(userService);
	//	}
	//
	//	@Test
	//	public void userBeanAdapter()
	//	{
	//		System.out.println(userBeanAdapter);
	//		assertNotNull(userBeanAdapter);
	//	}
}

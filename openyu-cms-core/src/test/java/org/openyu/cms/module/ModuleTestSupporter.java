package org.openyu.cms.module;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.module.dao.ModuleDao;
import org.openyu.cms.module.service.ModuleService;
//import org.openyu.cms.module.web.struts2.ModuleAction;
//import org.openyu.cms.module.ws.ModuleWs;
import org.openyu.cms.app.AppTestSupporter;

public class ModuleTestSupporter extends AppTestSupporter
{

	protected static ModuleDao moduleDao;

	protected static ModuleService moduleService;

	//	protected static ModuleWs moduleWs;

	//	protected static ModuleAction moduleAction;

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
				"org/openyu/cms/module/applicationContext-module.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		moduleDao = (ModuleDao) applicationContext.getBean("moduleDao");
		moduleService = (ModuleService) applicationContext.getBean("moduleService");
		//		moduleWs = (ModuleWs) applicationContext.getBean("moduleWsTarget");
		//		moduleAction = (ModuleAction) applicationContext.getBean("moduleAction");
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
	public void moduleDao()
	{
		System.out.println(moduleDao);
		assertNotNull(moduleDao);
	}

	@Test
	public void moduleService()
	{
		System.out.println(moduleService);
		assertNotNull(moduleService);
	}

	//	@Test
	//	public void moduleWs()
	//	{
	//		System.out.println(moduleWs);
	//		assertNotNull(moduleWs);
	//	}

	//	@Test
	//	public void moduleAction()
	//	{
	//		System.out.println(moduleAction);
	//		assertNotNull(moduleAction);
	//	}

}

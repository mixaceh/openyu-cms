package org.openyu.cms.resource;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.resource.dao.ResourceLogDao;
import org.openyu.cms.resource.service.ResourceLogService;
import org.openyu.cms.resource.service.ResourceService;
import org.openyu.cms.app.AppTestSupporter;

public class ResourceTestSupporter extends AppTestSupporter
{

	protected static ResourceService resourceService;

	protected static ResourceLogDao resourceLogDao;

	protected static ResourceLogService resourceLogService;

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
				"org/openyu/cms/archive/applicationContext-archive.xml",//
				"org/openyu/cms/resource/applicationContext-resource.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		resourceService = (ResourceService) applicationContext.getBean("resourceService");
		resourceLogDao = (ResourceLogDao) applicationContext.getBean("resourceLogDao");
		resourceLogService = (ResourceLogService) applicationContext.getBean("resourceLogService");
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
	public void resourceService()
	{
		System.out.println(resourceService);
		assertNotNull(resourceService);
	}

	@Test
	public void resourceLogDao()
	{
		System.out.println(resourceLogDao);
		assertNotNull(resourceLogDao);
	}

	@Test
	public void resourceLogService()
	{
		System.out.println(resourceLogService);
		assertNotNull(resourceLogService);
	}
}

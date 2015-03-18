package org.openyu.cms.contextType;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.app.AppTestSupporter;
import org.openyu.cms.contextType.dao.ContextTypeDao;
import org.openyu.cms.contextType.service.ContextTypeService;
import org.openyu.cms.contextType.service.adapter.ContextTypeBeanAdapter;

public class ContextTypeTestSupporter extends AppTestSupporter
{

	protected static ContextTypeDao contextTypeDao;

	protected static ContextTypeService contextTypeService;

	protected static ContextTypeBeanAdapter contextTypeBeanAdapter;

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
				"org/openyu/cms/contextType/applicationContext-contextType.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		contextTypeDao = (ContextTypeDao) applicationContext.getBean("contextTypeDao");
		contextTypeService = (ContextTypeService) applicationContext.getBean("contextTypeService");
		contextTypeBeanAdapter = (ContextTypeBeanAdapter) applicationContext
				.getBean("contextTypeBeanAdapter");
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
	public void contextTypeDao()
	{
		System.out.println(contextTypeDao);
		assertNotNull(contextTypeDao);
	}

	@Test
	public void contextTypeService()
	{
		System.out.println(contextTypeService);
		assertNotNull(contextTypeService);
	}

	@Test
	public void contextTypeBeanAdapter()
	{
		System.out.println(contextTypeBeanAdapter);
		assertNotNull(contextTypeBeanAdapter);
	}
}

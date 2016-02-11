package org.openyu.cms.guestbookType;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.guestbookType.web.freeMarker.GuestbookTypeDirective;
import org.openyu.cms.guestbookType.web.freeMarker.GuestbookTypeListDirective;
import org.openyu.cms.guestbookType.dao.GuestbookTypeDao;
import org.openyu.cms.guestbookType.service.GuestbookTypeService;
import org.openyu.cms.guestbookType.service.adapter.GuestbookTypeBeanAdapter;
import org.openyu.cms.guestbookType.dao.GuestbookTypeLogDao;
import org.openyu.cms.guestbookType.service.GuestbookTypeLogService;

import org.openyu.cms.app.AppTestSupporter;

public class GuestbookTypeTestSupporter extends AppTestSupporter
{

	protected static GuestbookTypeDao guestbookTypeDao;

	protected static GuestbookTypeService guestbookTypeService;

	protected static GuestbookTypeLogDao guestbookTypeLogDao;

	protected static GuestbookTypeLogService guestbookTypeLogService;

	protected static GuestbookTypeBeanAdapter guestbookTypeBeanAdapter;

	protected static GuestbookTypeListDirective guestbookTypeListDirective;

	protected static GuestbookTypeDirective guestbookTypeDirective;

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
				"org/openyu/cms/guestbookType/applicationContext-guestbookType.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		guestbookTypeDao = (GuestbookTypeDao) applicationContext.getBean("guestbookTypeDao");
		guestbookTypeService = (GuestbookTypeService) applicationContext
				.getBean("guestbookTypeService");
		guestbookTypeLogDao = (GuestbookTypeLogDao) applicationContext
				.getBean("guestbookTypeLogDao");
		guestbookTypeLogService = (GuestbookTypeLogService) applicationContext
				.getBean("guestbookTypeLogService");
		guestbookTypeBeanAdapter = (GuestbookTypeBeanAdapter) applicationContext
				.getBean("guestbookTypeBeanAdapter");
		guestbookTypeListDirective = (GuestbookTypeListDirective) applicationContext
				.getBean("cms_guestbook_type_list");
		guestbookTypeDirective = (GuestbookTypeDirective) applicationContext
				.getBean("cms_guestbook_type");
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
	public void guestbookTypeDao()
	{
		System.out.println(guestbookTypeDao);
		assertNotNull(guestbookTypeDao);
	}

	@Test
	public void guestbookTypeService()
	{
		System.out.println(guestbookTypeService);
		assertNotNull(guestbookTypeService);
	}

	@Test
	public void guestbookTypeLogDao()
	{
		System.out.println(guestbookTypeLogDao);
		assertNotNull(guestbookTypeLogDao);
	}

	@Test
	public void guestbookTypeLogService()
	{
		System.out.println(guestbookTypeLogService);
		assertNotNull(guestbookTypeLogService);
	}

	@Test
	public void guestbookTypeBeanAdapter()
	{
		System.out.println(guestbookTypeBeanAdapter);
		assertNotNull(guestbookTypeBeanAdapter);
	}

	@Test
	public void guestbookTypeListDirective()
	{
		System.out.println(guestbookTypeListDirective);
		assertNotNull(guestbookTypeListDirective);
	}

	@Test
	public void guestbookTypeDirective()
	{
		System.out.println(guestbookTypeDirective);
		assertNotNull(guestbookTypeDirective);
	}
}

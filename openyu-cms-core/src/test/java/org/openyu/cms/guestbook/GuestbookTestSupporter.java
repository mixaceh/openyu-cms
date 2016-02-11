package org.openyu.cms.guestbook;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.guestbook.dao.GuestbookDao;
import org.openyu.cms.guestbook.service.GuestbookService;
import org.openyu.cms.guestbook.web.freeMarker.GuestbookDirective;
import org.openyu.cms.guestbook.web.freeMarker.GuestbookListDirective;
import org.openyu.cms.guestbook.dao.GuestbookLogDao;
import org.openyu.cms.guestbook.service.GuestbookLogService;

import org.openyu.cms.app.AppTestSupporter;

public class GuestbookTestSupporter extends AppTestSupporter
{

	protected static GuestbookDao guestbookDao;

	protected static GuestbookService guestbookService;

	protected static GuestbookListDirective guestbookListDirective;

	protected static GuestbookDirective guestbookDirective;

	protected static GuestbookLogDao guestbookLogDao;

	protected static GuestbookLogService guestbookLogService;
	
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
				"org/openyu/cms/guestbook/applicationContext-guestbook.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		guestbookDao = (GuestbookDao) applicationContext.getBean("guestbookDao");
		guestbookService = (GuestbookService) applicationContext.getBean("guestbookService");
		guestbookListDirective = (GuestbookListDirective) applicationContext
				.getBean("cms_guestbook_list");
		guestbookDirective = (GuestbookDirective) applicationContext.getBean("cms_guestbook");
		//
		guestbookLogDao = (GuestbookLogDao) applicationContext.getBean("guestbookLogDao");
		guestbookLogService = (GuestbookLogService) applicationContext.getBean("guestbookLogService");
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
	public void guestbookDao()
	{
		System.out.println(guestbookDao);
		assertNotNull(guestbookDao);
	}

	@Test
	public void guestbookService()
	{
		System.out.println(guestbookService);
		assertNotNull(guestbookService);
	}

	@Test
	public void guestbookListDirective()
	{
		System.out.println(guestbookListDirective);
		assertNotNull(guestbookListDirective);
	}

	@Test
	public void guestbookDirective()
	{
		System.out.println(guestbookDirective);
		assertNotNull(guestbookDirective);
	}
	
	@Test
	public void guestbookLogDao()
	{
		System.out.println(guestbookLogDao);
		assertNotNull(guestbookLogDao);
	}

	@Test
	public void guestbookLogService()
	{
		System.out.println(guestbookLogService);
		assertNotNull(guestbookLogService);
	}
}

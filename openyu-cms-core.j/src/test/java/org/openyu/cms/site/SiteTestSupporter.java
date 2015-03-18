package org.openyu.cms.site;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.app.AppTestSupporter;
import org.openyu.cms.site.dao.SiteDao;
import org.openyu.cms.site.service.SiteService;
import org.openyu.cms.site.service.adapter.SiteBeanAdapter;

public class SiteTestSupporter extends AppTestSupporter
{

	protected static SiteDao siteDao;

	protected static SiteService siteService;

	protected static SiteBeanAdapter siteBeanAdapter;

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
				"org/openyu/cms/site/applicationContext-site.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		siteDao = (SiteDao) applicationContext.getBean("siteDao");
		siteService = (SiteService) applicationContext.getBean("siteService");
		siteBeanAdapter = (SiteBeanAdapter) applicationContext.getBean("siteBeanAdapter");
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
	public void siteDao()
	{
		System.out.println(siteDao);
		assertNotNull(siteDao);
	}

	@Test
	public void siteService()
	{
		System.out.println(siteService);
		assertNotNull(siteService);
	}

	@Test
	public void siteBeanAdapter()
	{
		System.out.println(siteBeanAdapter);
		assertNotNull(siteBeanAdapter);
	}
}

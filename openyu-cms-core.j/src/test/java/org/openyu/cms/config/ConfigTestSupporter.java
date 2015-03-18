
package org.openyu.cms.config;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.config.dao.ConfigDao;
import org.openyu.cms.config.service.ConfigService;
import org.openyu.cms.app.AppTestSupporter;

public class ConfigTestSupporter extends AppTestSupporter
{

	protected static ConfigDao configDao;

	protected static ConfigService configService;

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
				"org/openyu/cms/config/applicationContext-config.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		configDao = (ConfigDao) applicationContext.getBean("configDao");
		configService = (ConfigService) applicationContext.getBean("configService");
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
	public void configDao()
	{
		System.out.println(configDao);
		assertNotNull(configDao);
	}

	@Test
	public void configService()
	{
		System.out.println(configService);
		assertNotNull(configService);
	}

}

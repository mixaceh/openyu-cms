package org.openyu.cms.template;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.template.dao.TemplateLogDao;
import org.openyu.cms.template.service.TemplateLogService;
import org.openyu.cms.template.service.TemplateService;
import org.openyu.cms.app.AppTestSupporter;

public class TemplateTestSupporter extends AppTestSupporter
{

	protected static TemplateService templateService;

	protected static TemplateLogDao templateLogDao;

	protected static TemplateLogService templateLogService;

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
				"org/openyu/cms/template/applicationContext-template.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		templateService = (TemplateService) applicationContext.getBean("templateService");
		templateLogDao = (TemplateLogDao) applicationContext.getBean("templateLogDao");
		templateLogService = (TemplateLogService) applicationContext.getBean("templateLogService");
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
	public void templateService()
	{
		System.out.println(templateService);
		assertNotNull(templateService);
	}
	@Test
	public void templateLogDao()
	{
		System.out.println(templateLogDao);
		assertNotNull(templateLogDao);
	}

	@Test
	public void templateLogService()
	{
		System.out.println(templateLogService);
		assertNotNull(templateLogService);
	}
}

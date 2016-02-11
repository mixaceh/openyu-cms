package org.openyu.cms.sensitivity;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openyu.cms.sensitivity.dao.SensitivityDao;
import org.openyu.cms.sensitivity.dao.SensitivityLogDao;
import org.openyu.cms.sensitivity.service.SensitivityLogService;
import org.openyu.cms.sensitivity.service.SensitivityService;
import org.openyu.cms.app.AppTestSupporter;

public class SensitivityTestSupporter extends AppTestSupporter
{

	protected static SensitivityDao sensitivityDao;

	protected static SensitivityService sensitivityService;

	protected static SensitivityLogDao sensitivityLogDao;

	protected static SensitivityLogService sensitivityLogService;

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
				"applicationContext-schedule.xml",//
				"org/openyu/cms/app/applicationContext-app.xml",//
				"org/openyu/cms/user/applicationContext-user.xml",//
				"org/openyu/cms/site/applicationContext-site.xml",//
				"org/openyu/cms/sensitivity/applicationContext-sensitivity.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		sensitivityDao = (SensitivityDao) applicationContext.getBean("sensitivityDao");
		sensitivityService = (SensitivityService) applicationContext.getBean("sensitivityService");
		//
		sensitivityLogDao = (SensitivityLogDao) applicationContext.getBean("sensitivityLogDao");
		sensitivityLogService = (SensitivityLogService) applicationContext
				.getBean("sensitivityLogService");
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
	public void sensitivityDao()
	{
		System.out.println(sensitivityDao);
		assertNotNull(sensitivityDao);
	}

	@Test
	public void sensitivityService()
	{
		System.out.println(sensitivityService);
		assertNotNull(sensitivityService);
	}

	@Test
	public void sensitivityLogDao()
	{
		System.out.println(sensitivityLogDao);
		assertNotNull(sensitivityLogDao);
	}

	@Test
	public void sensitivityLogService()
	{
		System.out.println(sensitivityLogService);
		assertNotNull(sensitivityLogService);
	}
}

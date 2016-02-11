package org.openyu.cms.ftp;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.ftp.dao.FtpDao;
import org.openyu.cms.ftp.service.FtpService;
import org.openyu.cms.ftp.service.adapter.FtpBeanAdapter;

import org.openyu.cms.app.AppTestSupporter;

public class FtpTestSupporter extends AppTestSupporter
{

	protected static FtpDao ftpDao;

	protected static FtpService ftpService;

	protected static FtpBeanAdapter ftpBeanAdapter;

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
				"org/openyu/cms/ftp/applicationContext-ftp.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		ftpDao = (FtpDao) applicationContext.getBean("ftpDao");
		ftpService = (FtpService) applicationContext.getBean("ftpService");
		ftpBeanAdapter = (FtpBeanAdapter) applicationContext.getBean("ftpBeanAdapter");
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
	public void ftpDao()
	{
		System.out.println(ftpDao);
		assertNotNull(ftpDao);
	}

	@Test
	public void ftpService()
	{
		System.out.println(ftpService);
		assertNotNull(ftpService);
	}

	@Test
	public void ftpBeanAdapter()
	{
		System.out.println(ftpBeanAdapter);
		assertNotNull(ftpBeanAdapter);
	}
}

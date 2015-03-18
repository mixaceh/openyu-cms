package org.openyu.cms.keyword;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openyu.cms.keyword.dao.KeywordDao;
import org.openyu.cms.keyword.dao.KeywordLogDao;
import org.openyu.cms.keyword.service.KeywordLogService;
import org.openyu.cms.keyword.service.KeywordService;
import org.openyu.cms.app.AppTestSupporter;

public class KeywordTestSupporter extends AppTestSupporter
{

	protected static KeywordDao keywordDao;

	protected static KeywordService keywordService;

	protected static KeywordLogDao keywordLogDao;

	protected static KeywordLogService keywordLogService;

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
				"org/openyu/cms/keyword/applicationContext-keyword.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		keywordDao = (KeywordDao) applicationContext.getBean("keywordDao");
		keywordService = (KeywordService) applicationContext.getBean("keywordService");
		//
		keywordLogDao = (KeywordLogDao) applicationContext.getBean("keywordLogDao");
		keywordLogService = (KeywordLogService) applicationContext.getBean("keywordLogService");
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
	public void keywordDao()
	{
		System.out.println(keywordDao);
		assertNotNull(keywordDao);
	}

	@Test
	public void keywordService()
	{
		System.out.println(keywordService);
		assertNotNull(keywordService);
	}

	@Test
	public void keywordLogDao()
	{
		System.out.println(keywordLogDao);
		assertNotNull(keywordLogDao);
	}

	@Test
	public void keywordLogService()
	{
		System.out.println(keywordLogService);
		assertNotNull(keywordLogService);
	}
}

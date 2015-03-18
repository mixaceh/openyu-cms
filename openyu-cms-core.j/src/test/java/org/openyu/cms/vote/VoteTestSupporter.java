package org.openyu.cms.vote;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.vote.dao.VoteLogDao;
import org.openyu.cms.vote.service.VoteLogService;
import org.openyu.cms.vote.dao.VoteDao;
import org.openyu.cms.vote.service.VoteService;
import org.openyu.cms.vote.web.struts2.VoteAction;
import org.openyu.cms.app.AppTestSupporter;

public class VoteTestSupporter extends AppTestSupporter
{

	protected static VoteDao voteDao;

	protected static VoteService voteService;

	protected static VoteAction voteAction;

	protected static VoteLogDao voteLogDao;

	protected static VoteLogService voteLogService;

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
				"org/openyu/cms/vote/applicationContext-vote.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		voteDao = (VoteDao) applicationContext.getBean("voteDao");
		voteService = (VoteService) applicationContext.getBean("voteService");
		//		voteAction = (VoteAction) applicationContext.getBean("voteAction");
		//
		voteLogDao = (VoteLogDao) applicationContext.getBean("voteLogDao");
		voteLogService = (VoteLogService) applicationContext.getBean("voteLogService");
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
	public void voteDao()
	{
		System.out.println(voteDao);
		assertNotNull(voteDao);
	}

	@Test
	public void voteService()
	{
		System.out.println(voteService);
		assertNotNull(voteService);
	}

	@Test
	public void voteAction()
	{
		System.out.println(voteAction);
		assertNotNull(voteAction);
	}

	@Test
	public void voteLogDao()
	{
		System.out.println(voteLogDao);
		assertNotNull(voteLogDao);
	}

	@Test
	public void voteLogService()
	{
		System.out.println(voteLogService);
		assertNotNull(voteLogService);
	}
}

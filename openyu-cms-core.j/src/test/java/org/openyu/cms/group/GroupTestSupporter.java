package org.openyu.cms.group;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.group.dao.GroupDao;
import org.openyu.cms.group.service.GroupService;
import org.openyu.cms.group.service.adapter.GroupBeanAdapter;
import org.openyu.cms.group.web.struts2.GroupAction;
import org.openyu.cms.app.AppTestSupporter;

public class GroupTestSupporter extends AppTestSupporter
{

	protected static GroupDao groupDao;

	protected static GroupService groupService;

	protected static GroupBeanAdapter groupBeanAdapter;

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
				"org/openyu/cms/group/applicationContext-group.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		groupDao = (GroupDao) applicationContext.getBean("groupDao");
		groupService = (GroupService) applicationContext.getBean("groupService");
		groupBeanAdapter = (GroupBeanAdapter) applicationContext.getBean("groupBeanAdapter");
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
	public void groupDao()
	{
		System.out.println(groupDao);
		assertNotNull(groupDao);
	}

	@Test
	public void groupService()
	{
		System.out.println(groupService);
		assertNotNull(groupService);
	}

	@Test
	public void groupBeanAdapter()
	{
		System.out.println(groupBeanAdapter);
		assertNotNull(groupBeanAdapter);
	}
}

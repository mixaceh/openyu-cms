package org.openyu.cms.tag;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.app.AppTestSupporter;
import org.openyu.cms.tag.dao.TagDao;
import org.openyu.cms.tag.dao.TagLogDao;
import org.openyu.cms.tag.service.TagLogService;
import org.openyu.cms.tag.service.TagService;
import org.openyu.cms.tag.service.adapter.TagBeanAdapter;
import org.openyu.cms.tag.web.freeMarker.TagDirective;
import org.openyu.cms.tag.web.freeMarker.TagListDirective;

public class TagTestSupporter extends AppTestSupporter
{

	protected static TagDao tagDao;

	protected static TagService tagService;

	protected static TagLogDao tagLogDao;

	protected static TagLogService tagLogService;

	protected static TagBeanAdapter tagBeanAdapter;

	protected static TagListDirective tagListDirective;

	protected static TagDirective tagDirective;

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
				"org/openyu/cms/tag/applicationContext-tag.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		tagDao = (TagDao) applicationContext.getBean("tagDao");
		tagService = (TagService) applicationContext.getBean("tagService");
		tagLogDao = (TagLogDao) applicationContext.getBean("tagLogDao");
		tagLogService = (TagLogService) applicationContext.getBean("tagLogService");
		tagBeanAdapter = (TagBeanAdapter) applicationContext.getBean("tagBeanAdapter");
		tagListDirective = (TagListDirective) applicationContext.getBean("cms_tag_list");
		tagDirective = (TagDirective) applicationContext.getBean("cms_tag");
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
	public void tagDao()
	{
		System.out.println(tagDao);
		assertNotNull(tagDao);
	}

	@Test
	public void tagService()
	{
		System.out.println(tagService);
		assertNotNull(tagService);
	}

	@Test
	public void tagLogDao()
	{
		System.out.println(tagLogDao);
		assertNotNull(tagLogDao);
	}

	@Test
	public void tagLogService()
	{
		System.out.println(tagLogService);
		assertNotNull(tagLogService);
	}

	@Test
	public void tagBeanAdapter()
	{
		System.out.println(tagBeanAdapter);
		assertNotNull(tagBeanAdapter);
	}

	@Test
	public void tagListDirective()
	{
		System.out.println(tagListDirective);
		assertNotNull(tagListDirective);
	}

	@Test
	public void tagDirective()
	{
		System.out.println(tagDirective);
		assertNotNull(tagDirective);
	}
}

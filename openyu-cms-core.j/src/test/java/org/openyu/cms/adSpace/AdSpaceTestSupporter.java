package org.openyu.cms.adSpace;

import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.adSpace.dao.AdSpaceDao;
import org.openyu.cms.adSpace.service.AdSpaceService;
import org.openyu.cms.adSpace.service.adapter.AdSpaceBeanAdapter;
import org.openyu.cms.adSpace.web.freeMarker.AdSpaceDirective;
import org.openyu.cms.adSpace.web.freeMarker.AdSpaceListDirective;

import org.openyu.cms.app.AppTestSupporter;
import org.openyu.cms.adSpace.dao.AdSpaceLogDao;
import org.openyu.cms.adSpace.service.AdSpaceLogService;

public class AdSpaceTestSupporter extends AppTestSupporter
{

	protected static AdSpaceDao adSpaceDao;

	protected static AdSpaceService adSpaceService;

	protected static AdSpaceBeanAdapter adSpaceBeanAdapter;

	protected static AdSpaceListDirective adSpaceListDirective;

	protected static AdSpaceDirective adSpaceDirective;

	protected static AdSpaceLogDao adSpaceLogDao;

	protected static AdSpaceLogService adSpaceLogService;

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
				"org/openyu/cms/adSpace/applicationContext-adSpace.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		adSpaceDao = (AdSpaceDao) applicationContext.getBean("adSpaceDao");
		adSpaceService = (AdSpaceService) applicationContext.getBean("adSpaceService");
		adSpaceBeanAdapter = (AdSpaceBeanAdapter) applicationContext.getBean("adSpaceBeanAdapter");
		adSpaceListDirective = (AdSpaceListDirective) applicationContext
				.getBean("cms_ad_space_list");
		adSpaceDirective = (AdSpaceDirective) applicationContext.getBean("cms_ad_space");
		//
		adSpaceLogDao = (AdSpaceLogDao) applicationContext.getBean("adSpaceLogDao");
		adSpaceLogService = (AdSpaceLogService) applicationContext.getBean("adSpaceLogService");
	}

	@Test
	public void adSpaceDao()
	{
		System.out.println(adSpaceDao);
		assertNotNull(adSpaceDao);
	}

	@Test
	public void adSpaceService()
	{
		System.out.println(adSpaceService);
		assertNotNull(adSpaceService);
	}

	@Test
	public void adSpaceBeanAdapter()
	{
		System.out.println(adSpaceBeanAdapter);
		assertNotNull(adSpaceBeanAdapter);
	}

	@Test
	public void adSpaceListDirective()
	{
		System.out.println(adSpaceListDirective);
		assertNotNull(adSpaceListDirective);
	}

	@Test
	public void adSpaceDirective()
	{
		System.out.println(adSpaceDirective);
		assertNotNull(adSpaceDirective);
	}

	@Test
	public void adSpaceLogDao()
	{
		System.out.println(adSpaceLogDao);
		assertNotNull(adSpaceLogDao);
	}

	@Test
	public void adSpaceLogService()
	{
		System.out.println(adSpaceLogService);
		assertNotNull(adSpaceLogService);
	}
}

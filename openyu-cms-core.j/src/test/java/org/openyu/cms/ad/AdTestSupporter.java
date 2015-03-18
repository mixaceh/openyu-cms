package org.openyu.cms.ad;

import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.ad.dao.AdDao;
import org.openyu.cms.ad.service.AdService;
import org.openyu.cms.ad.web.freeMarker.AdDirective;
import org.openyu.cms.ad.web.freeMarker.AdListDirective;

import org.openyu.cms.app.AppTestSupporter;
import org.openyu.cms.ad.dao.AdLogDao;
import org.openyu.cms.ad.service.AdLogService;

public class AdTestSupporter extends AppTestSupporter {

	protected static AdDao adDao;

	protected static AdService adService;

	protected static AdListDirective adListDirective;

	protected static AdDirective adDirective;

	protected static AdLogDao adLogDao;

	protected static AdLogService adLogService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
				"applicationContext-init.xml",//
				"META-INF/applicationContext-commons-core.xml",//
				"applicationContext-message.xml",//
				"applicationContext-database.xml",//
				"applicationContext-database-log.xml",//
				// "applicationContext-cxf.xml",//
				"applicationContext-junit.xml",//
				"org/openyu/cms/app/applicationContext-app.xml",//
				"org/openyu/cms/user/applicationContext-user.xml",//
				"org/openyu/cms/site/applicationContext-site.xml",//
				"org/openyu/cms/ad/applicationContext-ad.xml",//
				"org/openyu/cms/adSpace/applicationContext-adSpace.xml",//
		});
		// ---------------------------------------------------
		initialize();
		// ---------------------------------------------------
		adDao = (AdDao) applicationContext.getBean("adDao");
		adService = (AdService) applicationContext.getBean("adService");
		adListDirective = (AdListDirective) applicationContext
				.getBean("cms_ad_list");
		adDirective = (AdDirective) applicationContext.getBean("cms_ad");
		//
		adLogDao = (AdLogDao) applicationContext.getBean("adLogDao");
		adLogService = (AdLogService) applicationContext
				.getBean("adLogService");
	}

	@Test
	public void adDao() {
		System.out.println(adDao);
		assertNotNull(adDao);
	}

	@Test
	public void adService() {
		System.out.println(adService);
		assertNotNull(adService);
	}

	@Test
	public void adListDirective() {
		System.out.println(adListDirective);
		assertNotNull(adListDirective);
	}

	@Test
	public void adDirective() {
		System.out.println(adDirective);
		assertNotNull(adDirective);
	}

	@Test
	public void adLogDao() {
		System.out.println(adLogDao);
		assertNotNull(adLogDao);
	}

	@Test
	public void adLogService() {
		System.out.println(adLogService);
		assertNotNull(adLogService);
	}
}

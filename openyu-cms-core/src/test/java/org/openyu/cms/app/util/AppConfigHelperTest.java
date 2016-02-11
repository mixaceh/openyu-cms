package org.openyu.cms.app.util;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openyu.commons.junit.supporter.BaseTestSupporter;

public class AppConfigHelperTest extends BaseTestSupporter {
	private static AppConfigHelper appConfigHelper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext-init.xml"//
				});
		//
		appConfigHelper = (AppConfigHelper) applicationContext
				.getBean("configHelper");
	}

	@Test
	public void resourcePath() {
		// webapp\custom\resource
		// web: /localhost/cms/custom/resource
		System.out.println(AppConfigHelper.getResourcePath());

		// URL [file:webapp/custom/resource]
		System.out.println(appConfigHelper.getResourcePathLocation());

		// D:\dev\openyu7\trunk\openyu-cms.j\openyu-cms-core.j\custom\resource
		System.out.println(AppConfigHelper.getResourceAbsolutePath());
	}

	@Test
	public void templatePath() {
		// webapp\WEB-INF\custom\template
		System.out.println(AppConfigHelper.getTemplatePath());

		// URL [file:webapp/WEB-INF/custom/template]
		System.out.println(appConfigHelper.getTemplatePathLocation());

		// D:\dev\openyu7\trunk\openyu-cms.j\openyu-cms-core.j\custom\template
		System.out.println(AppConfigHelper.getTemplateAbsolutePath());
	}
}

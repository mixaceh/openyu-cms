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
		// WebContent\custom\resource
		// web: /localhost/csm/custom/resource
		System.out.println(AppConfigHelper.getResourcePath());

		// URL [file:WebContent/custom/resource]
		System.out.println(appConfigHelper.getResourcePathLocation());

		// C:\java\apps\jyu6\trunk\wd\csm\WebContent\custom\resource
		System.out.println(AppConfigHelper.getResourceAbsolutePath());
	}

	@Test
	public void templatePath() {
		// WebContent\WEB-INF\custom\template
		System.out.println(AppConfigHelper.getTemplatePath());

		// URL [file:WebContent/WEB-INF/custom/template]
		System.out.println(appConfigHelper.getTemplatePathLocation());

		// C:\java\apps\jyu6\trunk\wd\csm\WebContent\WEB-INF\custom\template
		System.out.println(AppConfigHelper.getTemplateAbsolutePath());
	}
}

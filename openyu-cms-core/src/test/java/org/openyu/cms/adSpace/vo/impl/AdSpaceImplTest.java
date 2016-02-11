package org.openyu.cms.adSpace.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.vo.impl.AdSpaceImpl;
import org.openyu.cms.app.AppBeanTestSupporter;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;

public class AdSpaceImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_AD_SPACE";
		//
		AdSpace value = new AdSpaceImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試廣告版位");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试广告版位");
		value.addName(Locale.US, "Test Ad Space");
		//
		value.setValid(randomBoolean());
		//
		value.setDescription(Locale.TRADITIONAL_CHINESE, "測試描述");
		value.setDescription(Locale.SIMPLIFIED_CHINESE, "测试描述");
		value.setDescription(Locale.US, "Test Short Name");
		//
		// site
		Site site = new SiteImpl("TEST_SITE");
		site.setSeq(1);
		site.setResourcePath(randomString());
		value.setSite(site);
		//
		String result = beanCollector.writeToXml(AdSpaceImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		AdSpace result = beanCollector.readFromXml(AdSpaceImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

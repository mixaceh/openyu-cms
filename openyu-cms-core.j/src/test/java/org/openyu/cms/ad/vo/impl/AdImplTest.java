package org.openyu.cms.ad.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.ad.vo.Ad.AdType;
import org.openyu.cms.ad.vo.impl.AdImpl;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.vo.impl.AdSpaceImpl;
import org.openyu.cms.app.AppBeanTestSupporter;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;

public class AdImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_AD";
		//
		Ad value = new AdImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試廣告");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试广告");
		value.addName(Locale.US, "Test Ad");
		//
		value.setValid(randomBoolean());
		value.setAdType(randomType(AdType.class));
		value.setUrl(randomString());
		value.setTarget(randomString());
		value.setClick(randomInt(100));
		value.setDisplay(randomInt(100));
		value.setWeight(randomInt(100));
		value.setBegDate(randomDate());
		value.setEndDate(randomDate());

		// site
		Site site = new SiteImpl("TEST_SITE");
		site.setSeq(1);
		site.setResourcePath(randomString());
		value.setSite(site);
		//
		// adSpace
		AdSpace adSpace = new AdSpaceImpl("TEST_AD_SPACE");
		adSpace.setSeq(1);
		value.setAdSpace(adSpace);
		//
		String result = beanCollector.writeToXml(AdImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Ad result = beanCollector.readFromXml(AdImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

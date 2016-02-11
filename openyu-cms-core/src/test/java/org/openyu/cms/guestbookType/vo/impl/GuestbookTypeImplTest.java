package org.openyu.cms.guestbookType.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class GuestbookTypeImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_GUESTBOOK_CTG";
		//
		GuestbookType value = new GuestbookTypeImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試留言類別");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试留言类别");
		value.addName(Locale.US, "Test Comment category");
		//
		value.setSort(randomInt());
		//
		value.setDescription(Locale.TRADITIONAL_CHINESE, "測試描述");
		value.setDescription(Locale.SIMPLIFIED_CHINESE, "测试描述");
		value.setDescription(Locale.US, "Test Description");
		//
		// site
		Site site = new SiteImpl("TEST_SITE");
		site.setSeq(1);
		site.setResourcePath(randomString());
		value.setSite(site);
		//
		String result = beanCollector
				.writeToXml(GuestbookTypeImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		GuestbookType result = beanCollector
				.readFromXml(GuestbookTypeImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

package org.openyu.cms.topic.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class TopicImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_TOPIC";
		//
		Topic value = new TopicImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試專題");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试专题");
		value.addName(Locale.US, "Test Topic");
		//
		value.setShortName(Locale.TRADITIONAL_CHINESE, "測試簡稱");
		value.setShortName(Locale.SIMPLIFIED_CHINESE, "测试简称");
		value.setShortName(Locale.US, "Test Topic Short Name");
		//
		value.setDescription(Locale.TRADITIONAL_CHINESE, "測試描述");
		value.setDescription(Locale.SIMPLIFIED_CHINESE, "测试描述");
		value.setDescription(Locale.US, "Test Description");
		//
		value.setTitleImg(randomString());
		value.setContextImg(randomString());
		value.setTemplate(randomString());
		value.setSort(randomInt(100));
		value.setRecommend(randomBoolean());

		// site
		Site site = new SiteImpl("TEST_SITE");
		site.setSeq(1);
		site.setResourcePath(randomString());
		value.setSite(site);
		//
		String result = beanCollector.writeToXml(TopicImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Topic result = beanCollector.readFromXml(TopicImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

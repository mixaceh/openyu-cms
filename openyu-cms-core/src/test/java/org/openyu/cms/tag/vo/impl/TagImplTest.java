package org.openyu.cms.tag.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.tag.vo.impl.TagImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class TagImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_TAG";
		//
		Tag value = new TagImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試標籤");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试标签");
		value.addName(Locale.US, "Test Tag");
		//
		String result = beanCollector.writeToXml(TagImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Tag result = beanCollector.readFromXml(TagImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

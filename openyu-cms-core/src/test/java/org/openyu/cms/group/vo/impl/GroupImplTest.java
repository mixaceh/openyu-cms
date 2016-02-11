package org.openyu.cms.group.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.group.vo.Group;
import org.openyu.cms.group.vo.impl.GroupImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class GroupImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_GROUP";
		//
		Group value = new GroupImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試群組");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试群组");
		value.addName(Locale.US, "Test Group");
		//
		value.setSort(randomInt(100));
		//
		value.setCaptcha(randomBoolean());
		value.setCheck(randomBoolean());
		//
		value.setDayUpload(randomInt(10000));
		value.setMaxUpload(randomInt(10000));
		//
		value.setUploadSuffix(randomString());
		//
		value.setDft(randomBoolean());
		//
		String result = beanCollector.writeToXml(GroupImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Group result = beanCollector.readFromXml(GroupImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

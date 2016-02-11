package org.openyu.cms.contextType.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.contextType.vo.ContextType;
import org.openyu.cms.contextType.vo.impl.ContextTypeImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class ContextTypeImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_CONTEXT_TYPE";

		ContextType value = new ContextTypeImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試本文類型");
		value.addName(Locale.SIMPLIFIED_CHINESE, "測試本文类型");
		value.addName(Locale.US, "Test ContextType");
		//
		value.setValid(randomBoolean());
		value.setImgWidth(randomInt(100));
		value.setImgHeight(randomInt(100));
		value.setImage(randomBoolean());
		//
		String result = beanCollector.writeToXml(ContextTypeImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		ContextType result = beanCollector.readFromXml(ContextTypeImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

package org.openyu.cms.sensitivity.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.sensitivity.vo.Sensitivity;
import org.openyu.cms.sensitivity.vo.impl.SensitivityImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class SensitivityImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_SENSITIVITY";

		Sensitivity value = new SensitivityImpl();
		value.setId(ID);
		//
		Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();
		int count = 100;
		for (int i = 0; i < count; i++) {
			Dictionary dictionary = new DictionaryImpl();
			String key = randomAlphabet(5);
			dictionary.setKey(key);
			dictionary.setValue(randomAlphabet(5));
			dictionary.setValid(randomBoolean());
			dictionarys.put(key, dictionary);
		}
		value.setDictionarys(dictionarys);
		value.setLocale(Locale.TRADITIONAL_CHINESE);
		//
		String result = beanCollector.writeToXml(SensitivityImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Sensitivity result = (Sensitivity) beanCollector
				.readFromXml(SensitivityImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

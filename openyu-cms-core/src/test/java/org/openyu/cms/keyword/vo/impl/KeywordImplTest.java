package org.openyu.cms.keyword.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.keyword.vo.impl.KeywordImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class KeywordImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_KEYWORD";

		Keyword value = new KeywordImpl();
		value.setId(ID);
		//
		// keyword.setSearch("測試敏感詞");
		// keyword.setReplace("測試替換");
		// keyword.setSearch(Locale.CHINA, "测试敏感词");
		// keyword.setReplace(Locale.CHINA, "测试替换");
		Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();
		int count = 1;
		for (int i = 0; i < count; i++) {
			Dictionary dictionary = new DictionaryImpl();
			String key = randomAlphabet(5);
			dictionary.setKey(key);
			dictionary.setValue(randomAlphabet(5));
			dictionary.setValid(randomBoolean());
			dictionarys.put(key, dictionary);
			// dictionarys.put(randomAlphabet(5), randomAlphabet(5));
		}
		value.setDictionarys(dictionarys);
		value.setLocale(Locale.TRADITIONAL_CHINESE);
		//
		String result = beanCollector.writeToXml(KeywordImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Keyword result = beanCollector.readFromXml(KeywordImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

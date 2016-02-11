package org.openyu.cms.keyword.po.userType;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;

import org.openyu.commons.junit.supporter.BaseTestSupporter;

public class StringDictionaryUserTypeTest extends BaseTestSupporter {

	private static StringDictionaryUserType userType = new StringDictionaryUserType();

	@Test
	// 1000000 times: 101 mills.
	// 1000000 times: 116 mills.
	// 1000000 times: 100 mills.
	// verified
	public void assemble() {
		Map<String, Dictionary> value = new LinkedHashMap<String, Dictionary>();
		Dictionary dictionary = new DictionaryImpl();
		dictionary.setKey("aaa");
		dictionary.setValue("111");
		dictionary.setValid(true);
		value.put(dictionary.getKey(), dictionary);
		//
		dictionary = new DictionaryImpl();
		dictionary.setKey("bbb");
		dictionary.setValue("222");
		dictionary.setValid(false);
		value.put(dictionary.getKey(), dictionary);
		//
		dictionary = new DictionaryImpl();
		dictionary.setKey("ccc");
		dictionary.setValue("333");
		dictionary.setValid(false);
		value.put(dictionary.getKey(), dictionary);
		//
		String result = null;
		//
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = userType.marshal(value, null);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		// 1
		System.out.println(result);
		//
		assertEquals("♥1♠3♦aaa♣111♣1♦bbb♣222♣0♦ccc♣333♣0", result);
	}

	@Test
	// 1000000 times: 1358 mills.
	// 1000000 times: 1307 mills.
	// 1000000 times: 1339 mills.
	// verified
	public void disassemble() {
		String value = "♥1♠3♦aaa♣111♣1♦bbb♣222♣0♦ccc♣333♣0";
		//
		Map<String, Dictionary> result = null;
		//
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = userType.unmarshal(value, null, null);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertEquals(3, result.size());
	}
}

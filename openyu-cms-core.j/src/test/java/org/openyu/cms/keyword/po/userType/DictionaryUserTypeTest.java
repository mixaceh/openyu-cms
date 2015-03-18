package org.openyu.cms.keyword.po.userType;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;

import org.openyu.commons.junit.supporter.BaseTestSupporter;

public class DictionaryUserTypeTest extends BaseTestSupporter {

	private static DictionaryUserType userType = new DictionaryUserType();

	@Test
	// 1000000 times: 101 mills.
	// 1000000 times: 116 mills.
	// 1000000 times: 100 mills.
	// verified
	public void assemble() {
		Dictionary value = new DictionaryImpl();
		value.setKey("aaa");
		value.setValue("111");
		value.setValid(true);
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
		assertEquals("♥1♠aaa♠111♠1", result);
	}

	@Test
	// 1000000 times: 1358 mills.
	// 1000000 times: 1307 mills.
	// 1000000 times: 1339 mills.
	// verified
	public void disassemble() {
		String value = "♥1♠aaa♠111♠1";
		//
		Dictionary result = null;
		//
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = userType.unmarshal(value, null, null);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertEquals("aaa", result.getKey());
		assertEquals("111", result.getValue());
		assertEquals(true, result.getValid());
	}
}

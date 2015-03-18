package org.openyu.cms.site.po.userType;

import static org.junit.Assert.*;
import org.junit.Test;

import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.commons.junit.supporter.BaseTestSupporter;

public class VerifyTypeUserTypeTest extends BaseTestSupporter {

	private static VerifyTypeUserType userType = new VerifyTypeUserType();

	@Test
	// 1000000 times: 101 mills.
	// 1000000 times: 116 mills.
	// 1000000 times: 100 mills.
	// verified
	public void assemble() {
		VerifyType value = VerifyType._2;
		//
		String result = null;
		//
		int count = 1000000;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = userType.marshal(value, null);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		// 1
		System.out.println(result);
		//
		assertEquals("♥1♠2", result);
	}

	@Test
	// #issue
	// 1000000 times: 4345 mills.
	// 1000000 times: 3584 mills.
	// 1000000 times: 3704 mills.
	public void disassemble() {
		String value = "♥1♠2";
		//
		VerifyType result = null;
		//
		int count = 1000000;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = userType.unmarshal(value, null, null);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertEquals(VerifyType._2, result);
		//
		value = "♥1♠99";
		result = userType.unmarshal(value, null, null);
		System.out.println(result);
		assertNull(result);
	}
}

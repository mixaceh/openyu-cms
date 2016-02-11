package org.openyu.cms.module.po.userType;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Test;

import org.openyu.cms.module.po.userType.ContextItemsUserType;
import org.openyu.cms.module.vo.ContextItem;
import org.openyu.cms.module.vo.ModuleItem.ColumnType;
import org.openyu.cms.module.vo.impl.ContextItemImpl;

import org.openyu.commons.junit.supporter.BaseTestSupporter;

public class ContextItemsUserTypeTest extends BaseTestSupporter {

	private static ContextItemsUserType userType = new ContextItemsUserType();

	@Test
	// 1000000 times: 101 mills.
	// 1000000 times: 116 mills.
	// 1000000 times: 100 mills.
	// verified
	public void assemble() {
		// contextItem
		Set<ContextItem> value = new LinkedHashSet<ContextItem>();
		ContextItem contextItem = new ContextItemImpl();
		contextItem.setId("path");
		contextItem.setName("測試本文項目");
		contextItem.setSort(10);
		contextItem.setColumnType(ColumnType.STRING);
		value.add(contextItem);
		//
		contextItem = new ContextItemImpl();
		contextItem.setId("title");
		contextItem.setColumnType(ColumnType.STRING);
		value.add(contextItem);
		//
		contextItem = new ContextItemImpl();
		contextItem.setId("keywords");
		contextItem.setColumnType(ColumnType.TEXT_AREA);
		value.add(contextItem);
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
		assertEquals(
				"♥1♠3ⓓpathⓒ1♦zh_TW♣測試本文項目ⓒ10ⓒⓒⓒⓒⓒⓒⓒⓒ1ⓒ0ⓒ0ⓒ0ⓓtitleⓒ0ⓒ0ⓒⓒⓒⓒⓒⓒⓒⓒ1ⓒ0ⓒ0ⓒ0ⓓkeywordsⓒ0ⓒ0ⓒⓒⓒⓒⓒⓒⓒⓒ4ⓒ0ⓒ0ⓒ0",
				result);
	}

	@Test
	// 1000000 times: 1358 mills.
	// 1000000 times: 1307 mills.
	// 1000000 times: 1339 mills.
	// verified
	public void disassemble() {
		String value = "♥1♠3ⓓpathⓒ1♦zh_TW♣測試本文項目ⓒ10ⓒⓒⓒⓒⓒⓒⓒⓒ1ⓒ0ⓒ0ⓒ0ⓓtitleⓒ0ⓒ0ⓒⓒⓒⓒⓒⓒⓒⓒ1ⓒ0ⓒ0ⓒ0ⓓkeywordsⓒ0ⓒ0ⓒⓒⓒⓒⓒⓒⓒⓒ4ⓒ0ⓒ0ⓒ0";
		//
		Set<ContextItem> result = null;
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

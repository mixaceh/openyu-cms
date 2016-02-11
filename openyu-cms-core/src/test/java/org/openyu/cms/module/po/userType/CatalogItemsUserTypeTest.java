package org.openyu.cms.module.po.userType;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Test;

import org.openyu.cms.module.po.userType.CatalogItemsUserType;
import org.openyu.cms.module.vo.CatalogItem;
import org.openyu.cms.module.vo.ModuleItem.ColumnType;
import org.openyu.cms.module.vo.impl.CatalogItemImpl;

import org.openyu.commons.junit.supporter.BaseTestSupporter;

public class CatalogItemsUserTypeTest extends BaseTestSupporter {

	private static CatalogItemsUserType userType = new CatalogItemsUserType();

	@Test
	// 1000000 times: 101 mills.
	// 1000000 times: 116 mills.
	// 1000000 times: 100 mills.
	// verified
	public void assemble() {
		// catalogItem
		Set<CatalogItem> value = new LinkedHashSet<CatalogItem>();
		CatalogItem catalogItem = new CatalogItemImpl();
		catalogItem.setId("path");
		catalogItem.setName("測試目錄項目");
		catalogItem.setSort(10);
		catalogItem.setColumnType(ColumnType.STRING);
		value.add(catalogItem);
		//
		catalogItem = new CatalogItemImpl();
		catalogItem.setId("title");
		catalogItem.setColumnType(ColumnType.STRING);
		value.add(catalogItem);
		//
		catalogItem = new CatalogItemImpl();
		catalogItem.setId("keywords");
		catalogItem.setColumnType(ColumnType.TEXT_AREA);
		value.add(catalogItem);
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
				"♥1♠3ⓓpathⓒ1♦zh_TW♣測試目錄項目ⓒ10ⓒⓒⓒⓒⓒⓒⓒⓒ1ⓒ0ⓒ0ⓒ0ⓓtitleⓒ0ⓒ0ⓒⓒⓒⓒⓒⓒⓒⓒ1ⓒ0ⓒ0ⓒ0ⓓkeywordsⓒ0ⓒ0ⓒⓒⓒⓒⓒⓒⓒⓒ4ⓒ0ⓒ0ⓒ0",
				result);
	}

	@Test
	// 1000000 times: 1358 mills.
	// 1000000 times: 1307 mills.
	// 1000000 times: 1339 mills.
	// verified
	public void disassemble() {
		String value = "♥1♠3ⓓpathⓒ1♦zh_TW♣測試目錄項目ⓒ10ⓒⓒⓒⓒⓒⓒⓒⓒ1ⓒ0ⓒ0ⓒ0ⓓtitleⓒ0ⓒ0ⓒⓒⓒⓒⓒⓒⓒⓒ1ⓒ0ⓒ0ⓒ0ⓓkeywordsⓒ0ⓒ0ⓒⓒⓒⓒⓒⓒⓒⓒ4ⓒ0ⓒ0ⓒ0";
		//
		Set<CatalogItem> result = null;
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

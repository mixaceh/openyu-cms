package org.openyu.cms.module.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.module.vo.ContextItem;
import org.openyu.cms.module.vo.Module;
import org.openyu.cms.module.vo.CatalogItem;
import org.openyu.cms.module.vo.ModuleItem.ColumnType;
import org.openyu.cms.app.AppBeanTestSupporter;

public class ModuleImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_MODULE";
		//
		Module value = new ModuleImpl();
		value.setId(ID);
		value.addName(Locale.TRADITIONAL_CHINESE, "測試模組");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试模组");
		value.addName(Locale.US, "Test Group");
		//
		value.setValid(randomBoolean());
		value.setPath(randomString());
		value.setCatalogPrefix(randomString());
		value.setContextPrefix(randomString());
		//
		value.setCatalogImgWidth(randomInt(100));
		value.setCatalogImgHeight(randomInt(100));
		value.setContextImgWidth(randomInt(100));
		value.setContextImgHeight(randomInt(100));
		//
		value.setSort(randomInt(100));
		value.setContext(randomBoolean());
		value.setDefault(randomBoolean());

		// catalogItem
		CatalogItem catalogItem = new CatalogItemImpl();
		catalogItem.setId("path");
		catalogItem.setColumnType(ColumnType.STRING);
		value.getCatalogItems().add(catalogItem);
		//
		catalogItem = new CatalogItemImpl();
		catalogItem.setId("title");
		catalogItem.setColumnType(ColumnType.STRING);
		value.getCatalogItems().add(catalogItem);
		//
		catalogItem = new CatalogItemImpl();
		catalogItem.setId("keywords");
		catalogItem.setColumnType(ColumnType.TEXT_AREA);
		value.getCatalogItems().add(catalogItem);

		// contextItem
		ContextItem contextItem = new ContextItemImpl();
		contextItem.setId("path");
		contextItem.setColumnType(ColumnType.STRING);
		value.getContextItems().add(contextItem);
		//
		contextItem = new ContextItemImpl();
		contextItem.setId("title");
		contextItem.setColumnType(ColumnType.STRING);
		value.getContextItems().add(contextItem);
		//
		contextItem = new ContextItemImpl();
		contextItem.setId("keywords");
		contextItem.setColumnType(ColumnType.TEXT_AREA);
		value.getContextItems().add(contextItem);
		//
		String result = beanCollector.writeToXml(ModuleImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Module result = beanCollector.readFromXml(ModuleImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}

}

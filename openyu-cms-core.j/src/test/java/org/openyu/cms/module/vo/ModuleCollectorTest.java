package org.openyu.cms.module.vo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.openyu.cms.module.vo.ModuleItem.ColumnType;
import org.openyu.cms.module.vo.impl.CatalogItemImpl;
import org.openyu.cms.module.vo.impl.ContextItemImpl;
import org.openyu.cms.module.vo.impl.ModuleImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Sort;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.SortImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class ModuleCollectorTest extends AppBeanTestSupporter {

	@Test
	// mock data
	// verification: ok
	public void writeToXml() {
		String result = null;
		ModuleCollector collector = ModuleCollector.getInstance(false);
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// 查詢條件
			Inquiry inquiry = new InquiryImpl();
			collector.setInquiry(inquiry);

			// //每頁筆數選項
			// Pagination pagination = new PaginationImpl();
			// pagination.getPageSizes().add(1);
			// pagination.getPageSizes().add(2);
			// pagination.getPageSizes().add(5);
			// pagination.getPageSizes().add(10);
			// collector.getInquiry().setPagination(pagination);

			// //排序方向選項
			// Order order = new OrderImpl();
			// order.setId(OrderType.ASC);
			// order.addName(Locale.TRADITIONAL_CHINESE, "升冪");
			// order.addName(Locale.SIMPLIFIED_CHINESE, "升幂");
			// order.addName(Locale.US, "Ascending");
			// collector.getInquiry().getOrders().add(order);
			// //
			// order = new OrderImpl();
			// order.setId(OrderType.DESC);
			// order.addName(Locale.TRADITIONAL_CHINESE, "降冪");
			// order.addName(Locale.SIMPLIFIED_CHINESE, "降幂");
			// order.addName(Locale.US, "Descending");
			// collector.getInquiry().getOrders().add(order);

			// 排序欄位選項
			Sort sort = new SortImpl();
			sort.setId("sort");
			sort.addName(Locale.TRADITIONAL_CHINESE, "排序");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "排序");
			sort.addName(Locale.US, "Sort");
			collector.getInquiry().getSorts().add(sort);
			//
			sort = new SortImpl();
			sort.setId("seq");
			sort.addName(Locale.TRADITIONAL_CHINESE, "序號");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "序号");
			sort.addName(Locale.US, "Sequence");
			collector.getInquiry().getSorts().add(sort);
			//
			sort = new SortImpl();
			sort.setId("names");
			sort.addName(Locale.TRADITIONAL_CHINESE, "名稱");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "名称");
			sort.addName(Locale.US, "Name");
			collector.getInquiry().getSorts().add(sort);
			//
			sort = new SortImpl();
			sort.setId("valid");
			sort.addName(Locale.TRADITIONAL_CHINESE, "啟用");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "启用");
			sort.addName(Locale.US, "Valid");
			collector.getInquiry().getSorts().add(sort);

			// 預設模組
			collector.setDefaultModule("DEFAULT");

			// 所有模組
			Module module = new ModuleImpl();
			module.setId("DEFAULT");
			module.setSort(10);
			module.setValid(true);
			module.setContext(true);
			module.setCatalogImgWidth(150);
			module.setCatalogImgHeight(150);
			module.setContextImgWidth(300);
			module.setContextImgHeight(300);

			// 目錄項目
			CatalogItem catalogItem = new CatalogItemImpl();
			catalogItem.setId("name");
			catalogItem.setColumnType(ColumnType.STRING);
			catalogItem.addName(Locale.TRADITIONAL_CHINESE, "目錄名稱");
			catalogItem.addName(Locale.SIMPLIFIED_CHINESE, "目录名称");
			catalogItem.addName(Locale.US, "Catalog name");
			catalogItem.setSort(10);
			catalogItem.setSingle(false);
			catalogItem.setDisplay(true);
			module.getCatalogItems().add(catalogItem);
			//
			catalogItem = new CatalogItemImpl();
			catalogItem.setId("path");
			catalogItem.setColumnType(ColumnType.STRING);
			catalogItem.addName(Locale.TRADITIONAL_CHINESE, "路徑");
			catalogItem.addName(Locale.SIMPLIFIED_CHINESE, "路径");
			catalogItem.addName(Locale.US, "Path");
			catalogItem.setSort(10);
			catalogItem.setSingle(false);
			catalogItem.setDisplay(true);
			module.getCatalogItems().add(catalogItem);

			// 本文項目
			ContextItem contextItem = new ContextItemImpl();
			contextItem.setId("catalogId");
			contextItem.setColumnType(ColumnType.SELECT);
			contextItem.addName(Locale.TRADITIONAL_CHINESE, "目錄");
			contextItem.addName(Locale.SIMPLIFIED_CHINESE, "目录");
			contextItem.addName(Locale.US, "Catalog");
			contextItem.setSort(10);
			catalogItem.setSingle(true);
			contextItem.setDisplay(true);
			module.getContextItems().add(contextItem);
			//
			contextItem = new ContextItemImpl();
			contextItem.setId("title");
			contextItem.setColumnType(ColumnType.STRING);
			contextItem.addName(Locale.TRADITIONAL_CHINESE, "標題");
			contextItem.addName(Locale.SIMPLIFIED_CHINESE, "标题");
			contextItem.addName(Locale.US, "Title");
			contextItem.setSort(10);
			catalogItem.setSingle(true);
			contextItem.setDisplay(true);
			module.getContextItems().add(contextItem);
			//
			collector.getModules().put(module.getId(), module);
			//
			result = collector.writeToXml(ModuleCollector.class, collector);
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 100 times: 1872 mills.
	// 100 times: 1786 mills.
	// 100 times: 1832 mills.
	public void readFromXml() {
		ModuleCollector result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = beanCollector.readFromXml(ModuleCollector.class);
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 常用的是這個
	// verification: ok
	public void writeToSerFromXml() {
		String result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = beanCollector.writeToSerFromXml(ModuleCollector.class);
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 10000 times: 4133 mills.
	// 10000 times: 4140 mills.
	// 10000 times: 4166 mills.
	// verification: ok
	public void readFromSer() {
		ModuleCollector result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = beanCollector.readFromSer(ModuleCollector.class);
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 10000 times: 4142 mills.
	// 10000 times: 4221 mills.
	// 10000 times: 4255 mills.
	// #issue 會重複初始,影響效率
	//
	// #fix 加initialized判斷
	// 1000000 times: 399 mills.
	// 1000000 times: 398 mills.
	// 1000000 times: 401 mills.
	public void initialize() {
		int count = 1000000;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			ModuleCollector.getInstance().initialize();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(ModuleCollector.getInstance().isInitialized());
	}

	@Test
	// 10000 times: 1587 mills.
	// 10000 times: 1583 mills.
	// 10000 times: 1683 mills.
	public void reset() {
		int count = 10000;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			ModuleCollector.getInstance().reset();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(ModuleCollector.getInstance().isInitialized());
	}

	@Test
	// 1000000 times: 400 mills.
	// 1000000 times: 396 mills.
	// 1000000 times: 399 mills.
	public void getInstance() {
		// 初始化
		// ValidModuleCollector.getInstance().initialize();
		// #fix
		// 已放入 getInstance()中,讓它初始化

		ModuleCollector result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = ModuleCollector.getInstance();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
	}

	@Test
	// 1000000 times: 443 mills.
	// 1000000 times: 400 mills.
	// 1000000 times: 403 mills.
	// verified
	public void getModules() {
		Map<String, Module> result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = ModuleCollector.getInstance().getModules();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertTrue(result.size() > 0);
	}

	@Test
	// 1000000 times: 443 mills.
	// 1000000 times: 400 mills.
	// 1000000 times: 403 mills.
	// verified
	public void getModule() {
		ModuleCollector collector = ModuleCollector.getInstance();
		Module result = null;
		//
		int count = 1; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.getModule("DEFAULT");
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertEquals("DEFAULT", result.getId());
		//
		result = collector.getModule("DEFAULT");
		System.out.println(result);// clone的物件
		assertEquals("DEFAULT", result.getId());
	}

	@Test
	// 1000000 times: 443 mills.
	// 1000000 times: 400 mills.
	// 1000000 times: 403 mills.
	// verified
	public void createModule() {
		ModuleCollector collector = ModuleCollector.getInstance();
		Module result = null;
		//
		int count = 1; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.createModule("DEFAULT");
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);// DEFAULT_4581518sfp3vGlFM
		assertNotNull(result);
		//
		result = collector.createModule("xxx");
		System.out.println(result);
		assertNotNull(result);
	}
}

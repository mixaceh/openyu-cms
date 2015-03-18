package org.openyu.cms.resource.vo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;
import org.junit.Test;
import org.openyu.cms.archive.vo.ActionOption;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.archive.vo.impl.ActionOptionImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Order;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.Sort;
import org.openyu.commons.dao.inquiry.Order.OrderType;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.OrderImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;
import org.openyu.commons.dao.inquiry.impl.SortImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class ResourceCollectorTest extends AppBeanTestSupporter {

	@Test
	// mock data
	// verification: ok
	public void writeToXml() {
		String result = null;
		ResourceCollector collector = ResourceCollector.getInstance(false);
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// 操作(-1/1/2/3)選項
			ActionOption actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.UNKNOWN);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "未知");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "未知");
			actionOption.addName(Locale.US, "Unknown");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.CREATE_DIR);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "新建目錄");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "新建目录");
			actionOption.addName(Locale.US, "Create Directory");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.CREATE_FILE);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "新建資源");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "新建资源");
			actionOption.addName(Locale.US, "Create Resource");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.SAVE_FILE);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "儲存資源");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "保存资源");
			actionOption.addName(Locale.US, "Save Resource");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.RENAME_FILE);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "更名資源");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "重命名资源");
			actionOption.addName(Locale.US, "Rename Resource");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.RENAME_DIR);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "更名目錄");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "重命名目录");
			actionOption.addName(Locale.US, "Rename Directory");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.DELETE_FILE);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "刪除資源");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "删除资源");
			actionOption.addName(Locale.US, "Delete Resource");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.DELETE_DIR);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "刪除目錄");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "删除目录");
			actionOption.addName(Locale.US, "Delete Directory");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.UPLOAD_FILE);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "上傳資源");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "上傳资源");
			actionOption.addName(Locale.US, "Upload Resource");
			collector.getActionOptions().add(actionOption);

			// --------------------------------------------------
			// log查詢條件
			// --------------------------------------------------
			Inquiry logInquiry = new InquiryImpl();
			collector.setLogInquiry(logInquiry);

			// 每頁筆數選項
			Pagination pagination = new PaginationImpl();
			pagination.getPageSizes().add(10);
			pagination.getPageSizes().add(20);
			pagination.getPageSizes().add(50);
			pagination.getPageSizes().add(100);
			collector.getLogInquiry().setPagination(pagination);

			// 排序欄位選項
			Sort sort = new SortImpl();
			sort.setId("logDate");
			sort.addName(Locale.TRADITIONAL_CHINESE, "記錄日期");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "记录日期");
			sort.addName(Locale.US, "Log Date");
			collector.getLogInquiry().getSorts().add(sort);
			//
			sort = new SortImpl();
			sort.setId("userId");
			sort.addName(Locale.TRADITIONAL_CHINESE, "使用者");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "用户");
			sort.addName(Locale.US, "User ID");
			collector.getLogInquiry().getSorts().add(sort);
			//
			sort = new SortImpl();
			sort.setId("clientIp");
			sort.addName(Locale.TRADITIONAL_CHINESE, "IP");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "IP");
			sort.addName(Locale.US, "IP");
			collector.getLogInquiry().getSorts().add(sort);

			// 排序方向選項
			Order order = new OrderImpl();
			order.setId(OrderType.DESC);
			order.addName(Locale.TRADITIONAL_CHINESE, "降冪");
			order.addName(Locale.SIMPLIFIED_CHINESE, "降幂");
			order.addName(Locale.US, "Descending");
			collector.getLogInquiry().getOrders().add(order);
			//
			order = new OrderImpl();
			order.setId(OrderType.ASC);
			order.addName(Locale.TRADITIONAL_CHINESE, "升冪");
			order.addName(Locale.SIMPLIFIED_CHINESE, "升幂");
			order.addName(Locale.US, "Ascending");
			collector.getLogInquiry().getOrders().add(order);
			//
			result = collector.writeToXml(ResourceCollector.class, collector);
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
		ResourceCollector result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = beanCollector.readFromXml(ResourceCollector.class);
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
			result = beanCollector.writeToSerFromXml(ResourceCollector.class);
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
		ResourceCollector result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = beanCollector.readFromSer(ResourceCollector.class);
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
			ResourceCollector.getInstance().initialize();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(ResourceCollector.getInstance().isInitialized());
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
			ResourceCollector.getInstance().reset();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(ResourceCollector.getInstance().isInitialized());
	}

	@Test
	// 1000000 times: 400 mills.
	// 1000000 times: 396 mills.
	// 1000000 times: 399 mills.
	public void getInstance() {
		// 初始化
		// ValidResourceCollector.getInstance().initialize();
		// #fix
		// 已放入 getInstance()中,讓它初始化

		ResourceCollector result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = ResourceCollector.getInstance();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
	}

	@Test
	// 1000000 times: 144 mills.
	// 1000000 times: 141 mills.
	// 1000000 times: 156 mills.
	public void getActionName() {
		ResourceCollector collector = ResourceCollector.getInstance();
		String result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.getActionName(1, Locale.TRADITIONAL_CHINESE);
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertEquals("新建目錄", result);
	}

}

package org.openyu.cms.guestbookType.vo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.cms.guestbookType.vo.ActionOption;
import org.openyu.cms.guestbookType.vo.ActionType;
import org.openyu.cms.guestbookType.vo.impl.ActionOptionImpl;
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

public class GuestbookTypeCollectorTest extends AppBeanTestSupporter {

	@Test
	// mock data
	// verification: ok
	public void writeToXml() {
		String result = null;
		GuestbookTypeCollector collector = GuestbookTypeCollector
				.getInstance(false);
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

			// 排序欄位選項
			//
			Sort sort = new SortImpl();
			sort.setId("id");
			sort.addName(Locale.TRADITIONAL_CHINESE, "ID");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "ID");
			sort.addName(Locale.US, "ID");
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
			sort.setId("sort");
			sort.addName(Locale.TRADITIONAL_CHINESE, "排序");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "排序");
			sort.addName(Locale.US, "Sort");
			collector.getInquiry().getSorts().add(sort);

			// 預設廣告版位
			collector.setDefaultGuestbookType("DEFAULT");

			// 所有廣告版位
			GuestbookType GuestbookType = new GuestbookTypeImpl();
			GuestbookType.setId("DEFAULT");
			// GuestbookType.setValid(true);
			GuestbookType.setSort(10);
			//
			collector.getGuestbookTypes().put(GuestbookType.getId(),
					GuestbookType);
			// 連結目標選項
			collector.getTargetOptions().add("_balnk");
			collector.getTargetOptions().add("_self");

			// 操作(-1/1/2/3)選項
			ActionOption actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.UNKNOWN);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "未知");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "未知");
			actionOption.addName(Locale.US, "Unknown");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.INSERT);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "新增");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "新增");
			actionOption.addName(Locale.US, "Insert");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.UPDATE);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "修改");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "修改");
			actionOption.addName(Locale.US, "Update");
			collector.getActionOptions().add(actionOption);
			//
			actionOption = new ActionOptionImpl();
			actionOption.setId(ActionType.DELETE);
			actionOption.addName(Locale.TRADITIONAL_CHINESE, "刪除");
			actionOption.addName(Locale.SIMPLIFIED_CHINESE, "删除");
			actionOption.addName(Locale.US, "Delete");
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
			sort = new SortImpl();
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
			result = beanCollector.writeToXml(GuestbookTypeCollector.class,
					collector);
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
		GuestbookTypeCollector result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = beanCollector.readFromXml(GuestbookTypeCollector.class);
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
			result = beanCollector
					.writeToSerFromXml(GuestbookTypeCollector.class);
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
		GuestbookTypeCollector result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = beanCollector.readFromSer(GuestbookTypeCollector.class);
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
			GuestbookTypeCollector.getInstance().initialize();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out
				.println(GuestbookTypeCollector.getInstance().isInitialized());
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
			GuestbookTypeCollector.getInstance().reset();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out
				.println(GuestbookTypeCollector.getInstance().isInitialized());
	}

	@Test
	// 1000000 times: 400 mills.
	// 1000000 times: 396 mills.
	// 1000000 times: 399 mills.
	public void getInstance() {
		// 初始化
		// ValidGuestbookTypeCollector.getInstance().initialize();
		// #fix
		// 已放入 getInstance()中,讓它初始化

		GuestbookTypeCollector result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = GuestbookTypeCollector.getInstance();
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
	public void getGuestbookTypes() {
		GuestbookTypeCollector collector = GuestbookTypeCollector.getInstance();
		Map<String, GuestbookType> result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.getGuestbookTypes();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertTrue(result.size() > 0);
	}

	@Test
	// 1000000 times: 5812 mills.
	// 1000000 times: 5507 mills.
	// 1000000 times: 5809 mills.
	// verified
	public void getGuestbookType() {
		GuestbookTypeCollector collector = GuestbookTypeCollector.getInstance();
		GuestbookType result = null;
		//
		int count = 1; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.getGuestbookType("DEFAULT");
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertEquals("DEFAULT", result.getId());
		//
		result = collector.getGuestbookType("DEFAULT");
		System.out.println(result);// clone的物件
		assertEquals("DEFAULT", result.getId());
	}

	@Test
	// 1000000 times: 5812 mills.
	// 1000000 times: 5507 mills.
	// 1000000 times: 5809 mills.
	// verified
	public void createGuestbookType() {
		GuestbookTypeCollector collector = GuestbookTypeCollector.getInstance();
		GuestbookType result = null;
		//
		int count = 1; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.createGuestbookType("DEFAULT");
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);// DEFAULT_4581518sfp3vGlFM
		assertNotNull(result);
		//
		result = collector.createGuestbookType("xxx");
		System.out.println(result);
		assertNotNull(result);
	}

}

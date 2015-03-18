package org.openyu.cms.site.vo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.cms.site.vo.impl.ModifyTypeOptionImpl;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.site.vo.impl.VerifyTypeOptionImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Sort;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.SortImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class SiteCollectorTest extends AppBeanTestSupporter {

	@Test
	// mock data
	// verification: ok
	public void writeToXml() {
		String result = null;
		SiteCollector collector = SiteCollector.getInstance(false);
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
			Sort sort = new SortImpl();
			sort.setId("id");
			sort.addName(Locale.TRADITIONAL_CHINESE, "網域");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "网域");
			sort.addName(Locale.US, "Domain");
			collector.getInquiry().getSorts().add(sort);
			//
			sort = new SortImpl();
			sort.setId("seq");
			sort.addName(Locale.TRADITIONAL_CHINESE, "序號");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "序号");
			sort.addName(Locale.US, "Sequence");
			collector.getInquiry().getSorts().add(sort);
			//
			//
			sort = new SortImpl();
			sort.setId("names");
			sort.addName(Locale.TRADITIONAL_CHINESE, "名稱");
			sort.addName(Locale.SIMPLIFIED_CHINESE, "名称");
			sort.addName(Locale.US, "Name");
			collector.getInquiry().getSorts().add(sort);

			// 預設模組
			collector.setDefaultSite("DEFAULT");

			// 所有模組
			Site site = new SiteImpl();
			site.setId("DEFAULT");
			site.setProtocol("http://");
			site.setDynamicSuffix(".yhtml");
			site.setStaticSuffix(".html");
			//
			// site.setBackLocale("zh_TW");
			// site.setFrontLocale("zh_TW");
			site.setRecover(true);
			site.setVerifyType(VerifyType._2);
			site.setModifyType(ModifyType.UNCHANGING);
			//
			collector.getSites().put(site.getId(), site);

			// 訪問協定選項
			collector.getProtocolOptions().add("http://");
			collector.getProtocolOptions().add("https://");

			// 動態頁尾碼選項,建議使用.yhtml為尾碼，以避免衝突
			collector.getDynamicSuffixOptions().add(".yhtml");

			// 靜態頁尾碼選項
			collector.getStaticSuffixOptions().add(".html");
			collector.getStaticSuffixOptions().add(".shtml");

			// 終審選項
			VerifyTypeOption verifyTypeOption = new VerifyTypeOptionImpl(
					VerifyType._0);
			verifyTypeOption.addName(Locale.TRADITIONAL_CHINESE, "0級");
			verifyTypeOption.addName(Locale.SIMPLIFIED_CHINESE, "0级");
			verifyTypeOption.addName(Locale.US, "level 0");
			collector.getVerifyTypeOptions().add(verifyTypeOption);
			//
			verifyTypeOption = new VerifyTypeOptionImpl(VerifyType._1);
			verifyTypeOption.addName(Locale.TRADITIONAL_CHINESE, "1級");
			verifyTypeOption.addName(Locale.SIMPLIFIED_CHINESE, "1级");
			verifyTypeOption.addName(Locale.US, "level 1");
			collector.getVerifyTypeOptions().add(verifyTypeOption);
			//
			verifyTypeOption = new VerifyTypeOptionImpl(VerifyType._2);
			verifyTypeOption.addName(Locale.TRADITIONAL_CHINESE, "2級");
			verifyTypeOption.addName(Locale.SIMPLIFIED_CHINESE, "2级");
			verifyTypeOption.addName(Locale.US, "level 2");
			collector.getVerifyTypeOptions().add(verifyTypeOption);

			// 審核後修改選項
			ModifyTypeOption modifyTypeOption = new ModifyTypeOptionImpl(
					ModifyType.CAN_NOT_MODIFY);
			modifyTypeOption.addName(Locale.TRADITIONAL_CHINESE, "不能修改刪除");
			modifyTypeOption.addName(Locale.SIMPLIFIED_CHINESE, "不能修改删除");
			modifyTypeOption.addName(Locale.US, "can not modify");
			collector.getModifyTypeOptions().add(modifyTypeOption);
			//
			modifyTypeOption = new ModifyTypeOptionImpl(ModifyType.WITHDRAW);
			modifyTypeOption.addName(Locale.TRADITIONAL_CHINESE, "修改後退回");
			modifyTypeOption.addName(Locale.SIMPLIFIED_CHINESE, "修改后退回");
			modifyTypeOption.addName(Locale.US, "withdraw after modified");
			collector.getModifyTypeOptions().add(modifyTypeOption);
			//
			modifyTypeOption = new ModifyTypeOptionImpl(ModifyType.UNCHANGING);
			modifyTypeOption.addName(Locale.TRADITIONAL_CHINESE, "修改後不變");
			modifyTypeOption.addName(Locale.SIMPLIFIED_CHINESE, "修改后不变");
			modifyTypeOption.addName(Locale.US, "unchanging after modified");
			collector.getModifyTypeOptions().add(modifyTypeOption);
			//
			result = collector.writeToXml(SiteCollector.class, collector);
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
		SiteCollector result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = beanCollector.readFromXml(SiteCollector.class);
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
			result = beanCollector.writeToSerFromXml(SiteCollector.class);
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
		SiteCollector result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = beanCollector.readFromSer(SiteCollector.class);
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
			SiteCollector.getInstance().initialize();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(SiteCollector.getInstance().isInitialized());
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
			SiteCollector.getInstance().reset();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(SiteCollector.getInstance().isInitialized());
	}

	@Test
	// 1000000 times: 400 mills.
	// 1000000 times: 396 mills.
	// 1000000 times: 399 mills.
	public void getInstance() {
		// 初始化
		// ValidSiteCollector.getInstance().initialize();
		// #fix
		// 已放入 getInstance()中,讓它初始化

		SiteCollector result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = SiteCollector.getInstance();
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
	public void getSites() {
		SiteCollector collector = SiteCollector.getInstance();
		Map<String, Site> result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.getSites();
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
	public void getSite() {
		SiteCollector collector = SiteCollector.getInstance();
		Site result = null;
		//
		int count = 1; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.getSite("DEFAULT");
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertEquals("DEFAULT", result.getId());
		//
		result = collector.getSite("DEFAULT");
		System.out.println(result);// clone的物件
		assertEquals("DEFAULT", result.getId());
	}

	@Test
	// 1000000 times: 443 mills.
	// 1000000 times: 400 mills.
	// 1000000 times: 403 mills.
	// verified
	public void createSite() {
		SiteCollector collector = SiteCollector.getInstance();
		Site result = null;
		//
		int count = 1; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.createSite("DEFAULT");
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);// DEFAULT_4581518sfp3vGlFM
		assertNotNull(result);
		//
		result = collector.createSite("xxx");
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1000000 times: 443 mills.
	// 1000000 times: 400 mills.
	// 1000000 times: 403 mills.
	// verified
	public void getVerifys() {
		SiteCollector collector = SiteCollector.getInstance();
		List<VerifyTypeOption> result = null;
		//
		int count = 1; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.getVerifyTypeOptions();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1000000 times: 443 mills.
	// 1000000 times: 400 mills.
	// 1000000 times: 403 mills.
	// verified
	public void getVerifyName() {
		SiteCollector collector = SiteCollector.getInstance();
		String result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.getVerifyTypeName(VerifyType._2,
					Locale.TRADITIONAL_CHINESE);
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1000000 times: 443 mills.
	// 1000000 times: 400 mills.
	// 1000000 times: 403 mills.
	// verified
	public void getModifys() {
		SiteCollector collector = SiteCollector.getInstance();
		List<ModifyTypeOption> result = null;
		//
		int count = 1; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.getModifyTypeOptions();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1000000 times: 443 mills.
	// 1000000 times: 400 mills.
	// 1000000 times: 403 mills.
	// verified
	public void getModifyName() {
		SiteCollector collector = SiteCollector.getInstance();
		String result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = collector.getModifyTypeName(ModifyType.WITHDRAW,
					Locale.TRADITIONAL_CHINESE);
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}
}

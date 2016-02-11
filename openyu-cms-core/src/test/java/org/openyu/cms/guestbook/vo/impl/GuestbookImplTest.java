package org.openyu.cms.guestbook.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbook.vo.impl.GuestbookImpl;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class GuestbookImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_GUESTBOOK";
		//
		Guestbook value = new GuestbookImpl();
		value.setId(ID);
		//
		value.setTitle(Locale.TRADITIONAL_CHINESE, "測試標題");
		value.setTitle(Locale.SIMPLIFIED_CHINESE, "测试标题");
		value.setTitle(Locale.US, "Test Title");

		value.setContent(Locale.TRADITIONAL_CHINESE, "測試留言內容");
		value.setContent(Locale.SIMPLIFIED_CHINESE, "测试留言内容");
		value.setContent(Locale.US, "Test Content");

		value.setReply(Locale.TRADITIONAL_CHINESE, "測試回復內容");
		value.setReply(Locale.SIMPLIFIED_CHINESE, "测试回复内容");
		value.setReply(Locale.US, "Test Reply");
		//
		value.setEmail("");
		value.setPhone("");
		value.setQq("");
		value.setIp("");
		value.setGuestbookDate(null);
		value.setReplayDate(null);
		value.setChecked(false);
		value.setRecommend(false);
		// site
		Site site = new SiteImpl("TEST_SITE");
		site.setSeq(1);
		site.setResourcePath(randomString());
		value.setSite(site);

		// frinedType
		GuestbookType frinedType = new GuestbookTypeImpl("TEST_GUESTBOOK_TYPE");
		frinedType.setSeq(1);
		frinedType.setSort(randomInt(100));
		value.setGuestbookType(frinedType);
		//
		String result = beanCollector.writeToXml(GuestbookImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Guestbook result = beanCollector.readFromXml(GuestbookImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

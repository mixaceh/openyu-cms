package org.openyu.cms.friendType.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;
import org.junit.Test;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class FriendTypeImplTest extends AppBeanTestSupporter {
	@Test
	public void writeToXml() {
		final String ID = "TEST_FRIEND_TYPE";
		//
		FriendType value = new FriendTypeImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試友情類別");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试友情类别");
		value.addName(Locale.US, "Test FriendType");
		//
		value.setSort(randomInt(100));

		// site
		Site site = new SiteImpl("TEST_SITE");
		site.setSeq(1);
		site.setResourcePath("xxx");
		value.setSite(site);
		//
		String result = beanCollector.writeToXml(FriendTypeImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		FriendType result = beanCollector.readFromXml(FriendTypeImpl.class);
		System.out.println(result);
		assertNotNull(result);

		// SiteXmlAdapter
		// SiteImpl,只會有這兩個欄位, seq=1, id=TEST_SITE, 其他欄位都應該是空的
		System.out.println(result.getSite());
	}
}

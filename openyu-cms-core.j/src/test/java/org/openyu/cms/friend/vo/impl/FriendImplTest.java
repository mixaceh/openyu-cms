package org.openyu.cms.friend.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friend.vo.impl.FriendImpl;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class FriendImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_FRIEND";
		//
		Friend value = new FriendImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試Friend");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试Friend");
		value.addName(Locale.US, "Test friend");
		//
		value.setValid(randomBoolean());
		value.setUrl(randomString());
		value.setLogo(randomString());
		value.setEmail(randomString());
		//
		value.setDescription(Locale.TRADITIONAL_CHINESE, "測試描述");
		value.setDescription(Locale.SIMPLIFIED_CHINESE, "测试描述");
		value.setDescription(Locale.US, "Test Description");
		//
		value.setClick(randomInt(100));
		value.setSort(randomInt(100));
		//
		// site
		Site site = new SiteImpl("TEST_SITE");
		site.setSeq(1);
		site.setResourcePath(randomString());
		value.setSite(site);

		// frinedType
		FriendType frinedType = new FriendTypeImpl("TEST_FRIEND_TYPE");
		frinedType.setSeq(1);
		frinedType.setSort(randomInt(100));
		value.setFriendType(frinedType);
		//
		String result = beanCollector.writeToXml(FriendImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Friend result = beanCollector.readFromXml(FriendImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

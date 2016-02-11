package org.openyu.cms.site.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.cms.ftp.vo.impl.FtpImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class SiteImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_SITE";
		//
		Site value = new SiteImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試網站");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试网站");
		value.addName(Locale.US, "Test Site");
		//
		//
		value.setShortName(Locale.TRADITIONAL_CHINESE, "測試網站簡稱");
		value.setShortName(Locale.SIMPLIFIED_CHINESE, "测试网站简称");
		value.setShortName(Locale.US, "Test Short Name");
		value.setResourcePath(randomString());
		value.setProtocol(randomString());
		value.setDynamicSuffix(randomString());
		value.setStaticSuffix(randomString());
		value.setStaticPath(randomString());
		//
		value.setIndexRoot(randomBoolean());
		value.setStaticIndex(randomBoolean());
		// result.setBackLocale(randomString());
		// result.setFrontLocale(randomString());
		value.setTemplatePath(randomString());
		value.setVerifyType(randomType(VerifyType.class));
		value.setModifyType(randomType(ModifyType.class));
		//
		value.setRelativePath(randomBoolean());
		value.setRecover(randomBoolean());
		;
		value.setAlias(randomString());
		value.setRedirect(randomString());
		//
		value.getAttributes().put(randomString(), randomString());
		value.getAttributes().put(randomString(), randomString());
		//
		value.getTexts().put(randomString(), randomString());
		value.getTexts().put(randomString(), randomString());
		//
		value.getConfigs().put(randomString(), randomString());
		value.getConfigs().put(randomString(), randomString());

		// ftp
		Ftp ftp = new FtpImpl("TEST_FTP");
		ftp.setSeq(1);
		ftp.setAccount("root");
		ftp.setPassword("12345678");
		value.setFtp(ftp);
		//
		String result = beanCollector.writeToXml(SiteImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Site result = beanCollector.readFromXml(SiteImpl.class);
		System.out.println(result);
		// FtpXmlAdapter
		System.out.println(result.getFtp());// FtpImpl, seq=1,id=TEST_FTP
	}
}

package org.openyu.cms.ftp.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.cms.ftp.vo.impl.FtpImpl;
import org.openyu.cms.app.AppBeanTestSupporter;

public class FtpImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_FTP";
		//
		Ftp value = new FtpImpl();
		value.setId(ID);
		//
		value.addName(Locale.TRADITIONAL_CHINESE, "測試Ftp");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试Ftp");
		value.addName(Locale.US, "Test ftp");
		//
		value.setIp(randomIp("192.168.100"));
		value.setPort(randomInt(65536));
		value.setTimeout(randomInt(60));
		value.setAccount(randomString());
		value.setPassword(randomString());
		value.setPath(randomString());
		value.setEncoding(randomString());
		value.setUrl(randomString());
		//
		String result = beanCollector.writeToXml(FtpImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Ftp result = beanCollector.readFromXml(FtpImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

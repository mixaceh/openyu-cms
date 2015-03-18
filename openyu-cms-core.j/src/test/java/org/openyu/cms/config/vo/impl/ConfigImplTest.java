package org.openyu.cms.config.vo.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.openyu.cms.app.AppBeanTestSupporter;
import org.openyu.cms.config.vo.Config;
import org.openyu.commons.io.IoHelper;

public class ConfigImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_CONFIG";

		Config value = new ConfigImpl();
		value.setId(ID);
		value.setMarkAlpha(randomInt());
		value.setMarkColor(randomString());
		value.setMarkContent(randomString());
		value.setSysDeployeePath(randomString());
		value.setCountClearTime(randomDate());
		value.setCountCopyTime(randomDate());
		value.setSysDbFileUri(randomString());
		value.setSysDefaultImg(randomString());
		value.setDownloadCode(randomString());
		value.setDownloadTime(randomInt());
		value.setSysEmailValidate(randomBoolean());
		value.setMarkImagePath(randomString());
		value.setLoginUrl(randomString());
		value.setMarkHeight(randomInt());
		value.setMarkWidth(randomInt());
		value.setMarkOffsetX(randomInt());
		value.setMarkOffsetY(randomInt());
		value.setMark(randomBoolean());
		value.setMarkPos(randomInt());
		value.setProcessUrl(randomString());
		value.setServletPoint(randomString());
		value.setMarkSize(randomInt());
		value.setSysUploadToDb(randomBoolean());
		//
		value.setRegister(randomBoolean());
		value.setMember(randomBoolean());
		value.setUsernameMinLen(randomInt());
		value.setPasswordMinLen(randomInt());
		value.setUserImgWidth(randomInt());
		value.setUserImgHeight(randomInt());
		value.setUsernameReserved(randomString());

		value.setLoginErrorTimes(randomInt());
		value.setLoginErrorInterval(randomInt());
		value.setEmailHost(randomString());
		value.setEmailPort(randomInt());
		value.setEmailUsername(randomString());
		value.setEmailPassword(randomString());
		value.setEmailEncoding(randomString());
		value.setEmailPersonal(randomString());
		value.setForgotPasswordSubject(randomString());
		value.setForgotPasswordText(randomString());
		value.setRegisterSubject(randomString());
		value.setRegisterText(randomString());
		//
		String result = beanCollector.writeToXml(ConfigImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Config result = beanCollector.readFromXml(ConfigImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

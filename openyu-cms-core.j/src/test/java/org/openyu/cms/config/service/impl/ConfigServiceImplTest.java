package org.openyu.cms.config.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.config.ConfigTestSupporter;
import org.openyu.cms.config.vo.Config;
import org.openyu.cms.config.vo.impl.ConfigImpl;

public class ConfigServiceImplTest extends ConfigTestSupporter {

	/**
	 * 隨機產生資料
	 * 
	 * @return
	 */
	public static Config randomConfig() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_CONFIG" + UNIQUE;
		//
		Config result = new ConfigImpl();
		result.setId(ID);
		result.setMarkAlpha(randomInt());
		result.setMarkColor(randomString());
		result.setMarkContent(randomString());
		result.setSysDeployeePath(randomString());
		result.setCountClearTime(randomDate());
		result.setCountCopyTime(randomDate());
		result.setSysDbFileUri(randomString());
		result.setSysDefaultImg(randomString());
		result.setDownloadCode(randomString());
		result.setDownloadTime(randomInt());
		result.setSysEmailValidate(randomBoolean());
		result.setMarkImagePath(randomString());
		result.setLoginUrl(randomString());
		result.setMarkHeight(randomInt());
		result.setMarkWidth(randomInt());
		result.setMarkOffsetX(randomInt());
		result.setMarkOffsetY(randomInt());
		result.setMark(randomBoolean());
		result.setMarkPos(randomInt());
		result.setProcessUrl(randomString());
		result.setServletPoint(randomString());
		result.setMarkSize(randomInt());
		result.setSysUploadToDb(randomBoolean());

		result.setRegister(randomBoolean());
		result.setMember(randomBoolean());
		result.setUsernameMinLen(randomInt());
		result.setPasswordMinLen(randomInt());
		result.setUserImgWidth(randomInt());
		result.setUserImgHeight(randomInt());
		result.setUsernameReserved(randomString());

		result.setLoginErrorTimes(randomInt());
		result.setLoginErrorInterval(randomInt());
		result.setEmailHost(randomString());
		result.setEmailPort(randomInt());
		result.setEmailUsername(randomString());
		result.setEmailPassword(randomString());
		result.setEmailEncoding(randomString());
		result.setEmailPersonal(randomString());
		result.setForgotPasswordSubject(randomString());
		result.setForgotPasswordText(randomString());
		result.setRegisterSubject(randomString());
		result.setRegisterText(randomString());
		return result;
	}

	/**
	 * 檢核設定資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertConfig(Config expected, Config actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		//
		assertEquals(expected.getMarkColor(), actual.getMarkColor());
		assertEquals(expected.getMarkContent(), actual.getMarkContent());
		assertEquals(expected.getSysDeployeePath(), actual.getSysDeployeePath());
		assertEquals(expected.getCountClearTime(), actual.getCountClearTime());
		assertEquals(expected.getCountCopyTime(), actual.getCountCopyTime());
		assertEquals(expected.getSysDbFileUri(), actual.getSysDbFileUri());
		assertEquals(expected.getSysDefaultImg(), actual.getSysDefaultImg());
		assertEquals(expected.getDownloadCode(), actual.getDownloadCode());
		assertEquals(expected.getDownloadTime(), actual.getDownloadTime());
		assertEquals(expected.getSysEmailValidate(),
				actual.getSysEmailValidate());
		assertEquals(expected.getMarkImagePath(), actual.getMarkImagePath());
		assertEquals(expected.getLoginUrl(), actual.getLoginUrl());
		assertEquals(expected.getMarkHeight(), actual.getMarkHeight());
		assertEquals(expected.getMarkWidth(), actual.getMarkWidth());
		assertEquals(expected.getMarkOffsetX(), actual.getMarkOffsetX());
		assertEquals(expected.getMarkOffsetY(), actual.getMarkOffsetY());
		assertEquals(expected.getMark(), actual.getMark());
		assertEquals(expected.getMarkPos(), actual.getMarkPos());
		assertEquals(expected.getProcessUrl(), actual.getProcessUrl());
		assertEquals(expected.getServletPoint(), actual.getServletPoint());
		assertEquals(expected.getMarkSize(), actual.getMarkSize());
		assertEquals(expected.getSysUploadToDb(), actual.getSysUploadToDb());
		//
		assertEquals(expected.getRegister(), actual.getRegister());
		assertEquals(expected.getMember(), actual.getMember());
		assertEquals(expected.getUsernameMinLen(), actual.getUsernameMinLen());
		assertEquals(expected.getPasswordMinLen(), actual.getPasswordMinLen());
		assertEquals(expected.getUserImgWidth(), actual.getUserImgWidth());
		assertEquals(expected.getUserImgHeight(), actual.getUserImgHeight());
		assertEquals(expected.getUsernameReserved(),
				actual.getUsernameReserved());

		assertEquals(expected.getLoginErrorTimes(), actual.getLoginErrorTimes());
		assertEquals(expected.getLoginErrorInterval(),
				actual.getLoginErrorInterval());
		assertEquals(expected.getEmailHost(), actual.getEmailHost());
		assertEquals(expected.getEmailPort(), actual.getEmailPort());
		assertEquals(expected.getEmailUsername(), actual.getEmailUsername());
		assertEquals(expected.getEmailPassword(), actual.getEmailPassword());
		assertEquals(expected.getEmailEncoding(), actual.getEmailEncoding());
		assertEquals(expected.getEmailPersonal(), actual.getEmailPersonal());
		assertEquals(expected.getForgotPasswordSubject(),
				actual.getForgotPasswordSubject());
		assertEquals(expected.getForgotPasswordText(),
				actual.getForgotPasswordText());
		assertEquals(expected.getRegisterSubject(), actual.getRegisterSubject());
		assertEquals(expected.getRegisterText(), actual.getRegisterText());
	}

	@Test
	// insert -> find -> delete
	//
	// 10 times: 7237 mills.
	// 10 times: 6825 mills.
	// 10 times: 6693 mills.
	//
	// verified: ok
	public void crud() {
		int count = 10;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// 隨機
			Config config = randomConfig();
			// create
			Serializable pk = configService.insert(config);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			Config foundEntity = configService.find(ConfigImpl.class,
					config.getSeq());
			printFind(i, foundEntity);
			assertConfig(config, foundEntity);

			// update
			config.setId("ppp");
			config.setMark(true);
			int updated = configService.update(config);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			Config deletedEntity = configService.delete(ConfigImpl.class,
					config.getSeq());
			printDelete(i, deletedEntity);
			assertNotNull(deletedEntity);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// verified: ok
	public void insert() {
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// 隨機
			Config config = randomConfig();
			//
			Serializable pk = configService.insert(config);
			printInsert(i, pk);
			assertNotNull(pk);

			Config findConfig = configService.find(ConfigImpl.class,
					config.getSeq());
			assertConfig(config, findConfig);

			System.out.println(config);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void find() {
		Config result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = configService.find(ConfigImpl.class, 10L);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findConfig() {
		List<Config> result = null;
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = configService.findConfig();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size());
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findFirstConfig() {
		Config result = null;
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = configService.findFirstConfig();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
	}
}

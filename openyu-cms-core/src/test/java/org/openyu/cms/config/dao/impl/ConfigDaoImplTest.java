package org.openyu.cms.config.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.config.ConfigTestSupporter;
import org.openyu.cms.config.po.ConfigPo;
import org.openyu.cms.config.po.impl.ConfigPoImpl;

public class ConfigDaoImplTest extends ConfigTestSupporter {

	/**
	 * 隨機產生設定資料
	 * 
	 * @return
	 */
	public static ConfigPo randomConfigPo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_CONFIG" + UNIQUE;
		//
		ConfigPo result = new ConfigPoImpl();
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
	public static void assertConfigPo(ConfigPo expected, ConfigPo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		//
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
			ConfigPo configPo = randomConfigPo();
			// create
			Serializable pk = configDao.insert(configPo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			ConfigPo foundEntity = configDao.find(ConfigPoImpl.class,
					configPo.getSeq());
			printFind(i, foundEntity);
			assertConfigPo(configPo, foundEntity);

			// update
			configPo.setSysDefaultImg("ppp");
			int updated = configDao.update(configPo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			ConfigPo deletedEntity = configDao.delete(ConfigPoImpl.class,
					configPo.getSeq());
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
			ConfigPo configPo = randomConfigPo();
			//
			Serializable pk = configDao.insert(configPo);
			printInsert(i, pk);
			assertNotNull(pk);

			ConfigPo foundEntity = configDao.find(ConfigPoImpl.class,
					configPo.getSeq());
			assertConfigPo(configPo, foundEntity);

			System.out.println(configPo);
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
		List<ConfigPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {

			result = configDao.find(ConfigPoImpl.class);
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

		List<ConfigPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = configDao.findConfig();
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
		ConfigPo result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = configDao.findFirstConfig();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
	}
}

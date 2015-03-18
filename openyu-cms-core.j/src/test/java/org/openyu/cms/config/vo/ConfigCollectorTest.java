package org.openyu.cms.config.vo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.openyu.cms.app.AppBeanTestSupporter;
import org.openyu.cms.config.vo.MarkPosOption.MarkPosType;
import org.openyu.cms.config.vo.impl.ConfigImpl;
import org.openyu.cms.config.vo.impl.MarkPosOptionImpl;

public class ConfigCollectorTest extends AppBeanTestSupporter {

	@Test
	// mock data
	// verification: ok
	public void writeToXml() {
		String result = null;
		ConfigCollector collector = ConfigCollector.getInstance(false);
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {

			// 預設設定
			collector.setDefaultConfig("DEFAULT");

			// 所有設定
			Config config = new ConfigImpl();
			config.setId("DEFAULT");
			// 系統設定
			config.setSysDeployeePath("");
			config.setSysPort(80);
			config.setSysDefaultImg("/csm/back/theme/no_picture.gif");
			config.setSysEmailValidate(false);
			config.setSysUploadToDb(false);
			config.setSysDbFileUri("/dbfile.svl?n=");
			// 登錄設定
			config.setLoginErrorTimes(3);
			config.setLoginErrorInterval(30);
			config.setEmailHost("smtp.gmail.com");
			config.setEmailPort(465);
			config.setEmailUsername("fish20110610@gmail.com");
			config.setEmailPassword("");
			config.setEmailEncoding("UTF-8");
			config.setEmailPersonal("OIT");

			// config.setForgotPasswordSubject("");
			config.setForgotPasswordSubject(Locale.TRADITIONAL_CHINESE, "");
			config.setForgotPasswordSubject(Locale.SIMPLIFIED_CHINESE, "");
			config.setForgotPasswordSubject(Locale.US, "");
			// config.setForgotPasswordText("");
			config.setForgotPasswordText(Locale.TRADITIONAL_CHINESE, "");
			config.setForgotPasswordText(Locale.SIMPLIFIED_CHINESE, "");
			config.setForgotPasswordText(Locale.US, "");

			// config.setRegisterSubject("");
			config.setRegisterSubject(Locale.TRADITIONAL_CHINESE, "");
			config.setRegisterSubject(Locale.SIMPLIFIED_CHINESE, "");
			config.setRegisterSubject(Locale.US, "");
			// config.setRegisterText("");
			config.setRegisterText(Locale.TRADITIONAL_CHINESE, "");
			config.setRegisterText(Locale.SIMPLIFIED_CHINESE, "");
			config.setRegisterText(Locale.US, "");

			// 會員設定
			config.setRegister(false);
			config.setMember(false);
			config.setUsernameMinLen(3);
			config.setPasswordMinLen(3);
			config.setUserImgWidth(143);
			config.setUserImgHeight(98);
			config.setUsernameReserved("");
			// 浮水印
			config.setMark(true);
			config.setMarkWidth(120);
			config.setMarkHeight(120);
			config.setMarkImagePath("/csm/back/theme/watermark.png");
			config.setMarkContent("www.jeecms.com");
			config.setMarkSize(20);
			config.setMarkColor("#FF0000");
			config.setMarkAlpha(50);
			config.setMarkPos(1);
			config.setMarkOffsetX(0);
			config.setMarkOffsetY(0);
			//
			// 排序方向選項
			MarkPosOption markPos = new MarkPosOptionImpl();
			markPos.setId(MarkPosType.RANDOM);
			markPos.addName(Locale.TRADITIONAL_CHINESE, "隨機");
			markPos.addName(Locale.SIMPLIFIED_CHINESE, "随机");
			markPos.addName(Locale.US, "Random");
			collector.getMarkPosOptions().add(markPos);
			//
			markPos = new MarkPosOptionImpl();
			markPos.setId(MarkPosType.LEFT_TOP);
			markPos.addName(Locale.TRADITIONAL_CHINESE, "左上");
			markPos.addName(Locale.SIMPLIFIED_CHINESE, "左上");
			markPos.addName(Locale.US, "Left top");
			collector.getMarkPosOptions().add(markPos);
			//
			markPos = new MarkPosOptionImpl();
			markPos.setId(MarkPosType.RIGHT_TOP);
			markPos.addName(Locale.TRADITIONAL_CHINESE, "右上");
			markPos.addName(Locale.SIMPLIFIED_CHINESE, "右上");
			markPos.addName(Locale.US, "right top");
			collector.getMarkPosOptions().add(markPos);
			//
			markPos = new MarkPosOptionImpl();
			markPos.setId(MarkPosType.LEFT_DOWN);
			markPos.addName(Locale.TRADITIONAL_CHINESE, "左下");
			markPos.addName(Locale.SIMPLIFIED_CHINESE, "左下");
			markPos.addName(Locale.US, "Left down");
			collector.getMarkPosOptions().add(markPos);
			//
			markPos = new MarkPosOptionImpl();
			markPos.setId(MarkPosType.RIGHT_DOWN);
			markPos.addName(Locale.TRADITIONAL_CHINESE, "右下");
			markPos.addName(Locale.SIMPLIFIED_CHINESE, "右下");
			markPos.addName(Locale.US, "right down");
			collector.getMarkPosOptions().add(markPos);
			//
			markPos = new MarkPosOptionImpl();
			markPos.setId(MarkPosType.CENTER);
			markPos.addName(Locale.TRADITIONAL_CHINESE, "中央");
			markPos.addName(Locale.SIMPLIFIED_CHINESE, "中央");
			markPos.addName(Locale.US, "Center");
			collector.getMarkPosOptions().add(markPos);
			//
			collector.getConfigs().put(config.getId(), config);
			//
			result = collector.writeToXml(ConfigCollector.class, collector);
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
		ConfigCollector result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = beanCollector.readFromXml(ConfigCollector.class);
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
			result = beanCollector.writeToSerFromXml(ConfigCollector.class);
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
		ConfigCollector result = null;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = beanCollector.readFromSer(ConfigCollector.class);
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
			ConfigCollector.getInstance().initialize();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(ConfigCollector.getInstance().isInitialized());
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
			ConfigCollector.getInstance().reset();
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(ConfigCollector.getInstance().isInitialized());
	}

	@Test
	// 1000000 times: 400 mills.
	// 1000000 times: 396 mills.
	// 1000000 times: 399 mills.
	public void getInstance() {
		// 初始化
		// ValidConfigCollector.getInstance().initialize();
		// #fix
		// 已放入 getInstance()中,讓它初始化

		ConfigCollector result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = ConfigCollector.getInstance();
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
	public void getConfigs() {
		Map<String, Config> result = null;
		//
		int count = 1000000; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = ConfigCollector.getInstance().getConfigs();
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
	public void getConfig() {
		Config result = null;
		//
		int count = 1; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = ConfigCollector.getInstance().getConfig("DEFAULT");
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertEquals("DEFAULT", result.getId());
		//
		result = ConfigCollector.getInstance().getConfig("DEFAULT");
		System.out.println(result);// clone的物件
		assertEquals("DEFAULT", result.getId());
	}

	@Test
	// 1000000 times: 443 mills.
	// 1000000 times: 400 mills.
	// 1000000 times: 403 mills.
	// verified
	public void createConfig() {
		Config result = null;
		//
		int count = 1; // 100w
		long beg = System.currentTimeMillis();
		//
		for (int i = 0; i < count; i++) {
			result = ConfigCollector.getInstance().createConfig("DEFAULT");
		}
		//
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);// DEFAULT_4581518sfp3vGlFM
		assertNotNull(result);
		//
		result = ConfigCollector.getInstance().createConfig("xxx");
		System.out.println(result);
		assertNotNull(result);
	}
}

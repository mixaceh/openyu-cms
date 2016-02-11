package org.openyu.cms.app;

import org.junit.BeforeClass;
import org.openyu.cms.app.util.AppConfigHelper;
import org.openyu.commons.junit.supporter.BeanTestSupporter;

public abstract class AppBeanTestSupporter extends BeanTestSupporter {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// 在web下, 設定檔的目錄會放不同, 所以測試時會發生找不到檔案的狀況,因此單元測試中
		// 更改以下設定的目錄

		// app
		//
		AppConfigHelper.setConfigFile("src/test/config/etc/config.xml");
		AppConfigHelper.setJsonDir("src/test/config/json");
		AppConfigHelper.setKeyDir("src/test/config/key");
		AppConfigHelper.setSerDir("src/test/config/ser");
		AppConfigHelper.setXmlDir("src/test/config/xml");
		AppConfigHelper.setExcelDir("src/test/config/excel");
		//
		AppConfigHelper.setOutputDir("custom/output");
		AppConfigHelper.setDownloadDir("custom/download");
		AppConfigHelper.setUploadDir("custom/upload");
		//
		AppConfigHelper.setResourcePath("custom/resource");
		AppConfigHelper.setTemplatePath("custom/template");
		AppConfigHelper.setBackupPath("custom/backup");

		// web
		//
		// AppConfigHelper
		// .setConfigFile("src/main/webapp/WEB-INF/config/etc/config.xml");
		// AppConfigHelper.setJsonDir("src/main/webapp/WEB-INF/config/json");
		// AppConfigHelper.setKeyDir("src/main/webapp/WEB-INF/config/key");
		// AppConfigHelper.setSerDir("src/main/webapp/WEB-INF/config/ser");
		// AppConfigHelper.setXmlDir("src/main/webapp/WEB-INF/config/xml");
		// AppConfigHelper.setExcelDir("src/main/webapp/WEB-INF/config/excel");
		// //
		// AppConfigHelper.setOutputDir("src/main/webapp/custom/output");
		// AppConfigHelper.setDownloadDir("src/main/webapp/custom/download");
		// AppConfigHelper.setUploadDir("src/main/webapp/custom/upload");
		// //
		// AppConfigHelper.setResourcePath("src/main/webapp/custom/resource");
		// AppConfigHelper
		// .setTemplatePath("src/main/webapp/WEB-INF/custom/template");
		// AppConfigHelper.setBackupPath("src/main/webapp/WEB-INF/custom/backup");
	}
}

package org.openyu.cms.app;

import org.junit.BeforeClass;
import org.openyu.cms.app.util.AppConfigHelper;
import org.openyu.commons.junit.supporter.BeanTestSupporter;

public abstract class AppBeanTestSupporter extends BeanTestSupporter {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// 在web下, 設定檔的目錄會放不同, 所以測試時會發生找不到檔案的狀況,因此單元測試中
		// 更改以下設定的目錄
		AppConfigHelper
				.setConfigFile("src/main/webapp/WEB-INF/data/conf/config.xml");
		AppConfigHelper.setJsonDir("src/main/webapp/WEB-INF/data/json");
		AppConfigHelper.setKeyDir("src/main/webapp/WEB-INF/data/key");
		AppConfigHelper.setSerDir("src/main/webapp/WEB-INF/data/ser");
		AppConfigHelper.setXmlDir("src/main/webapp/WEB-INF/data/xml");
		AppConfigHelper.setExcelDir("src/main/webapp/WEB-INF/data/excel");
		//
		AppConfigHelper.setOutputDir("src/main/webapp/WEB-INF/data/output");
		AppConfigHelper.setDownloadDir("src/main/webapp/WEB-INF/data/download");
		AppConfigHelper.setUploadDir("src/main/webapp/WEB-INF/data/upload");
		//
		AppConfigHelper.setResourcePath("src/main/webapp/custom/resource");
		AppConfigHelper
				.setTemplatePath("src/main/webapp/WEB-INF/custom/template");
		AppConfigHelper.setBackupPath("src/main/webapp/WEB-INF/backup");
	}
}

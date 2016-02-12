package org.openyu.cms.app.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResource;
import org.openyu.commons.helper.supporter.BaseHelperSupporter;
import org.openyu.commons.util.ConfigHelper;

/**
 * 1.預設設定檔: src/test/config/etc/configuration.xml
 * 
 * 2.直接使用static方法取值
 * 
 * 3.可利用spring重新給設定檔路徑configurationResource
 */
public final class AppConfigHelper extends BaseHelperSupporter {

	private static transient final Logger LOGGER = LoggerFactory.getLogger(AppConfigHelper.class);

	// --------------------------------------------------------
	// default path
	// --------------------------------------------------------
	/**
	 * 預設資源相對路徑
	 */
	private final static String DEFAULT_RESOURCE_PATH = "custom" + File.separator + "resource";

	/**
	 * 資源相對路徑
	 */
	private static String resourcePath = DEFAULT_RESOURCE_PATH;

	/**
	 * 資源相對路徑資源,由spring注入
	 */
	private static Resource resourcePathLocation;

	/**
	 * 資源絕對路徑
	 */
	private static String resourceAbsolutePath;

	// --------------------------------------------------------
	/**
	 * 預設樣版相對路徑
	 */
	private final static String DEFAULT_TEMPLATE_PATH = "custom" + File.separator + "template";

	/**
	 * 樣版相對路徑
	 */
	private static String templatePath = DEFAULT_TEMPLATE_PATH;

	/**
	 * 樣版相對路徑資源,由spring注入
	 */
	private static Resource templatePathLocation;

	/**
	 * 樣版絕對路徑
	 */
	private static String templateAbsolutePath;

	// --------------------------------------------------------
	/**
	 * 預設Backup相對路徑
	 */
	private final static String DEFAULT_BACKUP_PATH = "custom" + File.separator + "buckup";

	/**
	 * Backup相對路徑
	 */
	private static String backupPath = DEFAULT_BACKUP_PATH;

	/**
	 * Backup相對路徑資源,由spring注入
	 */
	private static Resource backupPathLocation;

	/**
	 * Backup絕對路徑
	 */
	private static String backupAbsolutePath;

	static {
		new Static();
	}

	protected static class Static {
		public Static() {

		}
	}

	public AppConfigHelper() {
	}

	// --------------------------------------------------------

	public static String getResourcePath() {
		return resourcePath;
	}

	public static void setResourcePath(String resourcePath) {
		try {
			AppConfigHelper.resourcePath = resourcePath;
			AppConfigHelper.resourcePathLocation = new FileSystemResource(resourcePath);
			AppConfigHelper.resourceAbsolutePath = resourcePathLocation.getFile().getAbsolutePath();
			//
			ConfigHelper.buildDir(resourcePathLocation, resourcePath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Resource getResourcePathLocation() {
		return resourcePathLocation;
	}

	public void setResourcePathLocation(Resource resourcePathLocation) {
		try {
			AppConfigHelper.resourcePathLocation = resourcePathLocation;
			if (resourcePathLocation instanceof ServletContextResource) {
				ServletContextResource recource = (ServletContextResource) resourcePathLocation;
				// /custom/resource
				AppConfigHelper.resourcePath = recource.getPathWithinContext();
			} else {
				// WebContent/custom/resource
				AppConfigHelper.resourcePath = resourcePathLocation.getFile().getPath();
			}
			AppConfigHelper.resourceAbsolutePath = resourcePathLocation.getFile().getAbsolutePath();
			//
			ConfigHelper.buildDir(resourcePathLocation, null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getResourceAbsolutePath() {
		return resourceAbsolutePath;
	}

	public static void setResourceAbsolutePath(String resourceAbsolutePath) {
		AppConfigHelper.resourceAbsolutePath = resourceAbsolutePath;
	}

	// --------------------------------------------------------
	public static String getTemplatePath() {
		return templatePath;
	}

	public static void setTemplatePath(String templatePath) {
		try {
			AppConfigHelper.templatePath = templatePath;
			AppConfigHelper.templatePathLocation = new FileSystemResource(templatePath);
			AppConfigHelper.templateAbsolutePath = templatePathLocation.getFile().getAbsolutePath();
			//
			ConfigHelper.buildDir(templatePathLocation, templatePath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Resource getTemplatePathLocation() {
		return templatePathLocation;
	}

	public void setTemplatePathLocation(Resource templatePathLocation) {
		try {
			AppConfigHelper.templatePathLocation = templatePathLocation;
			if (templatePathLocation instanceof ServletContextResource) {
				ServletContextResource recource = (ServletContextResource) templatePathLocation;
				// /WEB-INF/custom/template
				AppConfigHelper.templatePath = recource.getPathWithinContext();
			} else {
				// WebContent/WEB-INF/custom/template
				AppConfigHelper.templatePath = templatePathLocation.getFile().getPath();
			}
			AppConfigHelper.templateAbsolutePath = templatePathLocation.getFile().getAbsolutePath();
			//
			ConfigHelper.buildDir(templatePathLocation, null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getTemplateAbsolutePath() {
		return templateAbsolutePath;
	}

	public static void setTemplateAbsolutePath(String templateAbsolutePath) {
		AppConfigHelper.templateAbsolutePath = templateAbsolutePath;
	}

	// --------------------------------------------------------
	public static String getBackupPath() {
		return backupPath;
	}

	public static void setBackupPath(String backupPath) {
		try {
			AppConfigHelper.backupPath = backupPath;
			AppConfigHelper.backupPathLocation = new FileSystemResource(backupPath);
			AppConfigHelper.backupAbsolutePath = backupPathLocation.getFile().getAbsolutePath();
			//
			ConfigHelper.buildDir(backupPathLocation, backupPath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Resource getBackupPathLocation() {
		return backupPathLocation;
	}

	public void setBackupPathLocation(Resource backupPathLocation) {
		try {
			AppConfigHelper.backupPathLocation = backupPathLocation;
			if (backupPathLocation instanceof ServletContextResource) {
				ServletContextResource recource = (ServletContextResource) backupPathLocation;
				// /WEB-INF/custom/backup
				AppConfigHelper.backupPath = recource.getPathWithinContext();
			} else {
				// WebContent/WEB-INF/custom/backup
				AppConfigHelper.backupPath = backupPathLocation.getFile().getPath();
			}
			AppConfigHelper.backupAbsolutePath = backupPathLocation.getFile().getAbsolutePath();
			//
			ConfigHelper.buildDir(backupPathLocation, null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getBackupAbsolutePath() {
		return backupAbsolutePath;
	}

	public static void setBackupAbsolutePath(String backupAbsolutePath) {
		AppConfigHelper.backupAbsolutePath = backupAbsolutePath;
	}
}

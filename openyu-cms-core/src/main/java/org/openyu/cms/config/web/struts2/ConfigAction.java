package org.openyu.cms.config.web.struts2;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;
import org.openyu.cms.config.service.ConfigService;
import org.openyu.cms.config.vo.Config;
import org.openyu.cms.config.vo.ConfigCollector;
import org.openyu.cms.config.vo.MarkPosOption;
import org.openyu.cms.config.vo.impl.ConfigImpl;
import org.openyu.commons.lang.StringHelper;

/**
 * 環境設定控制器
 */
@ParentPackage("default")
@Namespace("/back/service/config")
@Results({ @Result(name = "success", type = "freemarker", location = "configEdit.ftl") })
public class ConfigAction extends AppListActionSupporter {

	private static final long serialVersionUID = 5493332835863577353L;

	private static transient final Logger log = LogManager
			.getLogger(ConfigAction.class);

	@Autowired
	@Qualifier("configService")
	protected transient ConfigService configService;

	/**
	 * 靜態收集器
	 */
	protected transient ConfigCollector configCollector = ConfigCollector
			.getInstance();

	/**
	 * 單筆資料
	 */
	private Config config = new ConfigImpl();

	public ConfigAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	/**
	 * 浮水印位置選項
	 * 
	 * @return
	 */
	public List<MarkPosOption> getMarkPosOptions() {
		return configCollector.getMarkPosOptions();
	}

	@Action(value = "index")
	public String execute() {
		// 返回編輯
		return edit();
	}

	/**
	 * 讀取單筆
	 */
	protected void retriveSingle() {
		Config config = configService.findFirstConfig();
		if (config != null) {
			this.config = config;
		} else {
			// 若不存在,取預設設定
			this.config = configService.createConfig(configCollector
					.getDefaultConfig());
		}
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit")
	public String edit() {
		try {
			// 讀取單筆
			retriveSingle();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "editSave", results = {
			@Result(name = "input", type = "freemarker", location = "configEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "configEdit.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	public String editSave() {
		Serializable result = null;
		int updated = 0;
		try {
			// 檢查id
			if (StringHelper.isBlank(config.getId())) {
				addActionError(getText("config.id.required"));
				return INPUT;
			}

			// 存在的設定
			Config existConfig = configService.find(ConfigImpl.class,
					config.getSeq());
			if (existConfig == null) {
				result = configService.insert(config, userSession.getUserId());
				if (result != null) {
					String[] msgArgs = new String[] {
							String.valueOf(config.getSeq()),
							config.getSysDeployeePath() };
					addActionMessage(getText("global.add.success", msgArgs));
					//
					return edit();
				}
			} else {
				updated = configService.update(config, userSession.getUserId());
				if (updated > 0) {
					String[] msgArgs = new String[] {
							String.valueOf(config.getSeq()),
							config.getSysDeployeePath() };
					addActionMessage(getText("global.save.success", msgArgs));
					//
					return edit();
				}
			}
			//
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result == null || updated < 1) {
			String[] msgArgs = new String[] { String.valueOf(config.getSeq()),
					config.getSysDeployeePath() };
			addActionError(getText("global.save.fail", msgArgs));
		}
		return INPUT;
	}

}

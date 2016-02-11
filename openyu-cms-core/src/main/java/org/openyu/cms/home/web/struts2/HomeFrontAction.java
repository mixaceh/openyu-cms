package org.openyu.cms.home.web.struts2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.openyu.cms.app.web.struts2.supporter.AppActionSupporter;

/**
 * Home前台控制器
 */
@ParentPackage("defaultFront")
@Namespace("/front/service/home")
@Result(name = "success", type = "freemarker", location = "index.ftl")
public class HomeFrontAction extends AppActionSupporter {

	private static final long serialVersionUID = -6860350811734548252L;

	private static transient final Logger log = LogManager
			.getLogger(HomeFrontAction.class);

	public HomeFrontAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();
		//
	}

	@Action(value = "index")
	public String execute() {
		return SUCCESS;
	}

	@Action(value = "locale")
	public String locale() {
		return SUCCESS;
	}

}

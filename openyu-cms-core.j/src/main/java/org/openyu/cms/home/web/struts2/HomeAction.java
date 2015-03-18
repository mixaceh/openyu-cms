package org.openyu.cms.home.web.struts2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;

/**
 * Home後台控制器
 */
@ParentPackage("default")
@Namespace("/back/service/home")
@Result(name = "success", type = "freemarker", location = "index.ftl")
public class HomeAction extends AppListActionSupporter
{

	private static final long serialVersionUID = 6622614950035689967L;

	private static transient final Logger log = LogManager.getLogger(HomeAction.class);

	public HomeAction()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		super.initialize();
		//
	}

	@Action(value = "index")
	public String execute()
	{
		return SUCCESS;
	}

	/**
	 * 語系
	 * 
	 * @return
	 */
	@Action(value = "locale")
	public String locale()
	{
		return SUCCESS;
	}

}

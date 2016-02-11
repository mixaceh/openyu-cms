package org.openyu.cms.template.web.struts2;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;
import org.openyu.cms.archive.vo.ActionOption;
import org.openyu.cms.template.log.TemplateLog;
import org.openyu.cms.template.log.impl.TemplateLogImpl;
import org.openyu.cms.template.service.TemplateLogService;
import org.openyu.cms.template.vo.TemplateCollector;
import org.openyu.commons.util.CollectionHelper;

/**
 * 資源log控制器
 */
@ParentPackage("default")
@Namespace("/back/service/template/log")
@Results({ @Result(name = "success", type = "freemarker", location = "templateLogList.ftl") })
public class TemplateLogAction extends AppListActionSupporter
{

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger log = LogManager.getLogger(TemplateLogAction.class);

	/**
	 * 資源log服務
	 */
	@Autowired
	@Qualifier("templateLogService")
	protected transient TemplateLogService templateLogService;

	/**
	 * 靜態收集器
	 */
	protected transient TemplateCollector templateCollector = TemplateCollector.getInstance();

	/**
	 * 搜尋使用者id
	 */
	private String userId;

	/**
	 * 搜尋客戶端ip
	 */
	private String clientIp;

	/**
	 * 多筆列表資料
	 */
	private List<TemplateLog> templateLogs = new LinkedList<TemplateLog>();

	public TemplateLogAction()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		super.initialize();

		//初始化log查詢條件
		initializeInquiry(templateCollector.createLogInquiry());
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getClientIp()
	{
		return clientIp;
	}

	public void setClientIp(String clientIp)
	{
		this.clientIp = clientIp;
	}

	public List<TemplateLog> getTemplateLogs()
	{
		return templateLogs;
	}

	public void setTemplateLogs(List<TemplateLog> templateLogs)
	{
		this.templateLogs = templateLogs;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------
	/**
	 * 操作(-1/1/2/3)選項
	 * 
	 * @return
	 */
	public List<ActionOption> getActionOptions()
	{
		return templateCollector.getActionOptions();
	}

	public String getActionName(int value)
	{
		return getActionName(value, getLocale());
	}

	/**
	 * 取得,操作(-1/1/2/3)選項名稱
	 * 
	 * @param value, Action.getId().getValue()
	 * @param locale
	 * @return
	 */
	public String getActionName(int value, Locale locale)
	{
		return templateCollector.getActionName(value, locale);
	}

	@Action(value = "index")
	public String execute()
	{
		//返回列表
		return list();
	}

	/**
	 * 列表
	 * 
	 * @return
	 */
	@Action(value = "list")
	public String list()
	{
		try
		{
			//取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1)
			{
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return SUCCESS;
			}

			//讀取列表
			retrieveList(siteSeq);
		}
		catch (Exception ex)
		{
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 讀取列表
	 * 
	 * @return
	 */
	protected void retrieveList(long siteSeq)
	{
		templateLogs = templateLogService.findTemplateLog(inquiry, siteSeq, userId, clientIp);
	}

	/**
	 * 查詢, sql
	 * 
	 * @return
	 */
	@Action(value = "find")
	public String find()
	{
		try
		{
			//取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1)
			{
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return SUCCESS;
			}

			//讀取列表
			retrieveList(siteSeq);
		}
		catch (Exception ex)
		{
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 搜尋, lql
	 * 
	 * @return
	 */
	@Action(value = "search")
	public String search()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * 刪除, 多筆刪除,by seqs
	 * 
	 * @return
	 */
	@Action(value = "delete")
	public String delete()
	{
		List<TemplateLog> result = new LinkedList<TemplateLog>();
		int size = 0;
		try
		{
			//String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = templateLogService.delete(TemplateLogImpl.class, buffs);
			size = result.size();
			if (size > 0)
			{
				addActionMessage(getText("global.delete.success",
					new String[] { String.valueOf(size) }));
				//返回列表
				return list();
			}
		}
		catch (Exception ex)
		{
			log.warn(ex);
			ex.printStackTrace();
		}
		//
		if (!seqs.isEmpty() && size < 1)
		{
			addActionMessage(getText("global.delete.no.data"));
		}
		return SUCCESS;
	}
}

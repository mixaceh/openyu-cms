package org.openyu.cms.adSpace.web.struts2;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;
import org.openyu.cms.adSpace.log.AdSpaceLog;
import org.openyu.cms.adSpace.log.impl.AdSpaceLogImpl;
import org.openyu.cms.adSpace.service.AdSpaceLogService;
import org.openyu.cms.adSpace.vo.ActionOption;
import org.openyu.cms.adSpace.vo.AdSpaceCollector;
import org.openyu.commons.util.CollectionHelper;

/**
 * 廣告版位log控制器
 */
@ParentPackage("default")
@Namespace("/back/service/adSpace/log")
@Results({ @Result(name = "success", type = "freemarker", location = "adSpaceLogList.ftl") })
public class AdSpaceLogAction extends AppListActionSupporter
{

	private static final long serialVersionUID = 4622339944631800339L;

	private static transient final Logger log = LogManager.getLogger(AdSpaceLogAction.class);

	/**
	 * 廣告版位log服務
	 */
	@Autowired
	@Qualifier("adSpaceLogService")
	protected transient AdSpaceLogService adSpaceLogService;

	/**
	 * 靜態收集器
	 */
	protected transient AdSpaceCollector adSpaceCollector = AdSpaceCollector.getInstance();

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
	private List<AdSpaceLog> adSpaceLogs = new LinkedList<AdSpaceLog>();

	public AdSpaceLogAction()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		super.initialize();

		//初始化log查詢條件
		initializeInquiry(adSpaceCollector.createLogInquiry());
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

	public List<AdSpaceLog> getAdSpaceLogs()
	{
		return adSpaceLogs;
	}

	public void setAdSpaceLogs(List<AdSpaceLog> adSpaceLogs)
	{
		this.adSpaceLogs = adSpaceLogs;
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
		return adSpaceCollector.getActionOptions();
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
		return adSpaceCollector.getActionName(value, locale);
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
		adSpaceLogs = adSpaceLogService.findAdSpaceLog(inquiry, siteSeq, userId, clientIp);
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
		List<AdSpaceLog> result = new LinkedList<AdSpaceLog>();
		int size = 0;
		try
		{
			//String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = adSpaceLogService.delete(AdSpaceLogImpl.class, buffs);
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

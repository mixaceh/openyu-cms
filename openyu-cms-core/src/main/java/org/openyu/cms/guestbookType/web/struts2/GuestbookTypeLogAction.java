package org.openyu.cms.guestbookType.web.struts2;

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
import org.openyu.cms.guestbookType.log.GuestbookTypeLog;
import org.openyu.cms.guestbookType.log.impl.GuestbookTypeLogImpl;
import org.openyu.cms.guestbookType.service.GuestbookTypeLogService;
import org.openyu.cms.guestbookType.vo.ActionOption;
import org.openyu.cms.guestbookType.vo.GuestbookTypeCollector;
import org.openyu.commons.util.CollectionHelper;

/**
 * 留言類型log控制器
 */
@ParentPackage("default")
@Namespace("/back/service/guestbookType/log")
@Results({ @Result(name = "success", type = "freemarker", location = "guestbookTypeLogList.ftl") })
public class GuestbookTypeLogAction extends AppListActionSupporter
{

	private static final long serialVersionUID = -977451112867052776L;

	private static transient final Logger log = LogManager.getLogger(GuestbookTypeLogAction.class);

	/**
	 * 留言類型log服務
	 */
	@Autowired
	@Qualifier("guestbookTypeLogService")
	protected transient GuestbookTypeLogService guestbookTypeLogService;

	/**
	 * 靜態收集器
	 */
	protected transient GuestbookTypeCollector guestbookTypeCollector = GuestbookTypeCollector.getInstance();

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
	private List<GuestbookTypeLog> guestbookTypeLogs = new LinkedList<GuestbookTypeLog>();

	public GuestbookTypeLogAction()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		super.initialize();

		//初始化log查詢條件
		initializeInquiry(guestbookTypeCollector.createLogInquiry());
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

	public List<GuestbookTypeLog> getGuestbookTypeLogs()
	{
		return guestbookTypeLogs;
	}

	public void setGuestbookTypeLogs(List<GuestbookTypeLog> guestbookTypeLogs)
	{
		this.guestbookTypeLogs = guestbookTypeLogs;
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
		return guestbookTypeCollector.getActionOptions();
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
		return guestbookTypeCollector.getActionName(value, locale);
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
		guestbookTypeLogs = guestbookTypeLogService.findGuestbookTypeLog(inquiry, siteSeq, userId, clientIp);
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
		List<GuestbookTypeLog> result = new LinkedList<GuestbookTypeLog>();
		int size = 0;
		try
		{
			//String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = guestbookTypeLogService.delete(GuestbookTypeLogImpl.class, buffs);
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

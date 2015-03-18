package org.openyu.cms.topic.web.struts2;

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
import org.openyu.cms.topic.log.TopicLog;
import org.openyu.cms.topic.log.impl.TopicLogImpl;
import org.openyu.cms.topic.service.TopicLogService;
import org.openyu.cms.topic.vo.ActionOption;
import org.openyu.cms.topic.vo.TopicCollector;
import org.openyu.commons.util.CollectionHelper;

/**
 * 專題log控制器
 */
@ParentPackage("default")
@Namespace("/back/service/topic/log")
@Results({ @Result(name = "success", type = "freemarker", location = "topicLogList.ftl") })
public class TopicLogAction extends AppListActionSupporter
{

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger log = LogManager.getLogger(TopicLogAction.class);

	/**
	 * 專題log服務
	 */
	@Autowired
	@Qualifier("topicLogService")
	protected transient TopicLogService topicLogService;

	/**
	 * 靜態收集器
	 */
	protected transient TopicCollector topicCollector = TopicCollector.getInstance();

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
	private List<TopicLog> topicLogs = new LinkedList<TopicLog>();

	public TopicLogAction()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		super.initialize();

		//初始化log查詢條件
		initializeInquiry(topicCollector.createLogInquiry());
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

	public List<TopicLog> getTopicLogs()
	{
		return topicLogs;
	}

	public void setTopicLogs(List<TopicLog> topicLogs)
	{
		this.topicLogs = topicLogs;
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
		return topicCollector.getActionOptions();
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
		return topicCollector.getActionName(value, locale);
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
		topicLogs = topicLogService.findTopicLog(inquiry, siteSeq, userId, clientIp);
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
		List<TopicLog> result = new LinkedList<TopicLog>();
		int size = 0;
		try
		{
			//String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = topicLogService.delete(TopicLogImpl.class, buffs);
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

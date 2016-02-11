package org.openyu.cms.backup.web.struts2;

import java.util.LinkedList;
import java.util.List;

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

import org.openyu.cms.app.util.AppConfigHelper;
import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;
import org.openyu.cms.backup.service.BackupService;
import org.openyu.cms.backup.vo.Field;

/**
 * 資料控制器
 */
@ParentPackage("default")
@Namespace("/back/service/backup")
@Results({ @Result(name = "success", type = "freemarker", location = "backupList.ftl") })
public class BackupAction extends AppListActionSupporter
{

	private static final long serialVersionUID = 504022483805107500L;

	private static transient final Logger log = LogManager.getLogger(BackupAction.class);

	/**
	 * 資料服務
	 */
	@Autowired
	@Qualifier("backupService")
	protected transient BackupService backupService;

	/**
	 * 資源相對路徑
	 * 
	 * /custom/resource
	 */
	private String backupPath;

	/**
	 * 資源絕對路徑
	 * 
	 * C:\java\apps\jyu6\ide\Eclipse
	 * 4.2\.metadata\.plugins\org.eclipse.wst.server
	 * .core\tmp3\wtpwebapps\csm\backup
	 */
	private String backupAbsolutePath;

	/**
	 * 靜態收集器
	 */
	//	protected transient AdCollector backupCollector = AdCollector.getInstance();

	/**
	 * 單筆資料
	 */
	private List<Field> fields = new LinkedList<Field>();

	/**
	 * 多筆列表資料
	 */
	private List<String> backups = new LinkedList<String>();

	/**
	 * 選擇的網站 seq
	 */
	private long siteSeq;

	/**
	 * 選擇多筆Table
	 */
	private List<String> tables = new LinkedList<String>();

	/**
	 * Table 名稱
	 */
	private String tableName;

	public BackupAction()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		super.initialize();

		//資源相對路徑
		// /backup
		backupPath = AppConfigHelper.getBackupPath();

		//資源絕對路徑 
		//D:\working\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\csm\WEB-INF\backup
		backupAbsolutePath = AppConfigHelper.getBackupAbsolutePath();

	}

	public String getBackupPath()
	{
		return backupPath;
	}

	public String getBackupAbsolutePath()
	{
		return backupAbsolutePath;
	}

	public void setBackupAbsolutePath(String backupAbsolutePath)
	{
		this.backupAbsolutePath = backupAbsolutePath;
	}

	public List<Field> getFields()
	{
		return fields;
	}

	public void setFields(List<Field> fields)
	{
		this.fields = fields;
	}
	
	public List<String> getBackups()
	{
		return backups;
	}

	public void setBackups(List<String> backups)
	{
		this.backups = backups;
	}

	public long getSiteSeq()
	{
		return siteSeq;
	}

	public void setSiteSeq(long siteSeq)
	{
		this.siteSeq = siteSeq;
	}

	public List<String> getTables()
	{
		return tables;
	}

	public void setTables(List<String> tables)
	{
		this.tables = tables;
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
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
		backups = backupService.findBackup();
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
	 * 顯示單筆,網站別
	 * 
	 * @return
	 */
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "backupView.ftl") })
	public String view()
	{
		
		if (StringHelper.isEmpty(tableName))
		{
			//返回列表
			return list();
		}

		try
		{
			fields = backupService.listFields(tableName);
		}
		catch (Exception ex)
		{
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 備份, 多筆備份,by tables
	 * 
	 * @return
	 */
	@Action(value = "backup")
	public String backup()
	{
		List<String> result = new LinkedList<String>();
		int size = 0;
		try
		{
			//
			result = backupService.backupTables(userSession.getUser(), tables, backupPath);
			//絕對路徑
			//			result = backupService.backupTables(userSession.getUser(), tables, backupAbsolutePath);
			size = result.size();
			if (size > 0)
			{
				addActionMessage(getText("backup.success", new String[] { String.valueOf(size) }));
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
			addActionMessage(getText("backup.no.data"));
		}
		return SUCCESS;
	}

}

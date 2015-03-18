package org.openyu.cms.backup.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//import com.jeecms.cms.Constants;
//import com.jeecms.common.util.StrUtils;
//import com.jeecms.cms.Constants;
//import com.jeecms.cms.action.admin.assist.DataAct.DateBackupTableThread;
//import com.jeecms.common.util.DateUtils;
import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.backup.dao.BackupDao;
import org.openyu.cms.backup.service.BackupService;
import org.openyu.cms.backup.vo.Field;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.io.IoHelper;
import org.openyu.commons.lang.ClassHelper;
import org.openyu.commons.util.DateHelper;

public class BackupServiceImpl extends AppServiceSupporter implements BackupService
{
	private static transient final Logger log = LogManager.getLogger(BackupServiceImpl.class);

	private File file;

	public BackupServiceImpl()
	{}

	public BackupDao getBackupDao()
	{
		return (BackupDao) getOjDao();
	}

	@Autowired
	@Qualifier("backupDao")
	public void setBackupDao(BackupDao backupDao)
	{
		setOjDao(backupDao);
	}

	/**
	 * 查詢廣告
	 * 
	 * @return
	 */
	public List<String> findBackup()
	{
		List<String> orig = getBackupDao().findBackup();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 備份Table
	 * 
	 * @param user
	 * @param tables
	 * @return
	 */
	public List<String> backupTables(User user, List<String> tables, String backupPath)
	{
		List<String> result = null;

		File backDirectory = new File(backupPath);
		if (!backDirectory.exists())
		{
			backDirectory.mkdir();
		}
		DateHelper.toString(DateHelper.today(), "yyyyMMddhhmmss");
		String backFilePath = backupPath + File.separator
				+ DateHelper.toString(DateHelper.today(), "yyyyMMddhhmmss") + ".sql";
		File file = new File(backFilePath);
		Thread thread = new DateBackupTableThread(file, tables);
		thread.start();

		return tables;
	}

	private class DateBackupTableThread extends Thread
	{
		private File file;

		private List<String> tablenames;

		public DateBackupTableThread(File file, List<String> tablenames)
		{
			super();
			this.file = file;
			this.tablenames = tablenames;
		}

		public void run()
		{
			FileOutputStream out = null;
			OutputStreamWriter writer = null;
			try
			{
				out = new FileOutputStream(file);
				writer = new OutputStreamWriter(out, "utf8");
				//writer.write(Constants.ONESQL_PREFIX + DISABLEFOREIGN);
				writer.write("cms_BACKUP_" + "SET FOREIGN_KEY_CHECKS = 0;\r\n");
				// + SET FOREIGN_KEY_CHECKS = 0;\r\n
				//				for (int i=0;i<tablenames.length;i++) {
				//					backup_table=tablenames[i];
				//					backupTable(writer,tablenames[i]);
				//				}
				for (String tablename : tablenames)
				{
					System.out.println("tablename:" + tablename);
					backupTable(writer, tablename);
				}
				writer.write("cms_BACKUP_" + "SET FOREIGN_KEY_CHECKS = 0;\r\n");
				//				writer.close();
				//				out.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				IoHelper.close(out);
				IoHelper.close(writer);
			}
		}

		private String backupTable(OutputStreamWriter writer, String tablename) throws IOException
		{
			writer.write(createOneTableSql(tablename));
			writer.flush();
			return tablename;
		}

		private String createOneTableSql(String tablename)
		{
			StringBuilder buffer = new StringBuilder();
			Object[] oneResult;
			//			buffer.append(Constants.ONESQL_PREFIX + "DROP TABLE IF EXISTS " + tablename + BRANCH + BR);
			//"JEECMS_BACKUP_" + "DROP TABLE IF EXISTS " + tablename + ";" + "\r\n"
			buffer.append("cms_BACKUP_" + "DROP TABLE IF EXISTS " + tablename + ";" + "\r\n");
			//			buffer.append(Constants.ONESQL_PREFIX + dataBackMng.createTableDDL(tablename) + BRANCH + BR + Constants.ONESQL_PREFIX);
			buffer.append("cms_BACKUP_" + "DROP TABLE IF EXISTS " + createTableDDL(tablename) + ";"
					+ "\r\n" + "cms_BACKUP_");
			//
			List<Object[]> results = createTableData(tablename);
			for (int i = 0; i < results.size(); i++)
			{
				// one insert sql
				oneResult = results.get(i);
				buffer.append(createOneInsertSql(oneResult, tablename));
			}
			return buffer.toString();
		}

		private String createOneInsertSql(Object[] oneResult, String tablename)
		{
			String result = null;
			StringBuilder buffer = new StringBuilder();
			buffer.append("cms_BACKUP_" + " INSERT INTO " + "`" + tablename + "`" + " " + "VALUES"
					+ "(");
			for (int j = 0; j < oneResult.length; j++)
			{
				if (oneResult[j] != null)
				{
					if (oneResult[j] instanceof Date)
					{
						buffer.append("'" + oneResult[j] + "'");
					}
					else if (oneResult[j] instanceof String)
					{
						buffer.append("'" + replaceKeyString((String) oneResult[j]) + "'");
					}
					else if (oneResult[j] instanceof Boolean)
					{
						if ((Boolean) oneResult[j])
						{
							buffer.append(1);
						}
						else
						{
							buffer.append(0);
						}
					}
					else
					{
						buffer.append(oneResult[j]);
					}
				}
				else
				{
					buffer.append(oneResult[j]);
				}
				buffer.append(",");
			}

			buffer = buffer.deleteCharAt(buffer.lastIndexOf(","));
			buffer.append(")" + ";" + "\r\n");
			return buffer.toString();
		}

	}

	public String createTableDDL(String tablename)
	{
		String result = null;
		result = getBackupDao().createTableDDL(tablename);
		return result;
	}

	public List<Object[]> createTableData(String tablename)
	{
		List<Object[]> result = null;
		result = getBackupDao().createTableData(tablename);
		return result;
	}

	private boolean containsKeyString(String str)
	{
		if (StringUtils.isBlank(str))
		{
			return false;
		}
		if (str.contains("'") || str.contains("\"") || str.contains("\r") || str.contains("\n")
				|| str.contains("\t") || str.contains("\b") || str.contains("\f"))
		{
			return true;
		}
		return false;
	}

	// 將""和'轉義
	private String replaceKeyString(String str)
	{
		if (containsKeyString(str))
		{
			return str.replace("'", "\\'").replace("\"", "\\\"").replace("\r", "\\r")
					.replace("\n", "\\n").replace("\t", "\\t").replace("\b", "\\b")
					.replace("\f", "\\f");
		}
		else
		{
			return str;
		}
	}

	/**
	 * 查詢欄位設定
	 * 
	 * @param tableName
	 * @return
	 */
	public List<Field> listFields(String tableName)
	{
		List<Field> result = null;
		result = getBackupDao().listFields(tableName);
		return result;
	}
}

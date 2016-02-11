package org.openyu.cms.backup.service;

import java.util.List;

import org.openyu.cms.app.service.AppService;
import org.openyu.cms.backup.vo.Field;
import org.openyu.cms.user.vo.User;

/**
 * 資料服務
 */
public interface BackupService extends AppService
{

	/**
	 * 查詢Table
	 * 
	 * @param valid
	 * @return
	 */
	List<String> findBackup();

	//	List<String> backupTables(User user, Keyword keyword, List<String> tables);
	/**
	 * 備份Table
	 * 
	 * @param user
	 * @param tables
	 * @return
	 */
	List<String> backupTables(User user, List<String> tables, String backupPath);

	/**
	 * 
	 * @param tablename
	 * @return
	 */
	String createTableDDL(String tablename);

	/**
	 * 
	 * @param tablename
	 * @return
	 */
	List<Object[]> createTableData(String tablename);

	/**
	 * 備份目錄
	 * 
	 * @param path
	 * @param dirAndEditable
	 * @return
	 */
	//	List<FileWrap> listFile(String path, boolean dirAndEditable)

	/**
	 * 查詢欄位
	 * 
	 * @param tableName
	 * @return
	 */
	List<Field> listFields(String tableName);
}
package org.openyu.cms.backup.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.ad.po.AdPo;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.backup.vo.Field;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface BackupDao extends AppDao
{

	/**
	 * 查詢Table
	 * 
	 * @return
	 */
	List<String> findBackup();

	/**
	 * 備份(建立Table Data)
	 * 
	 * @param tablename
	 * @return
	 */
	List<Object[]> createTableData(String tablename);
	
	/**
	 * 查詢 Table DDL
	 * @param tablename
	 * @return
	 */
	String createTableDDL(String tablename);
	
	/**
	 * 查詢欄位
	 * @param tablename
	 * @return
	 */
	List<Field> listFields(String tablename);
}
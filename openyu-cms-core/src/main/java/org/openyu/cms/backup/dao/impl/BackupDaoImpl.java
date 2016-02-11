package org.openyu.cms.backup.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.backup.dao.BackupDao;
import org.openyu.cms.backup.vo.Field;
import org.openyu.cms.backup.vo.impl.FieldImpl;

public class BackupDaoImpl extends AppDaoSupporter implements BackupDao
{
	private static transient final Logger log = LogManager.getLogger(BackupDaoImpl.class);

	/**
	 * 查詢 Tables
	 * 
	 * @return
	 */
	public List<String> findBackup()
	{
		List<String> result = new LinkedList<String>();
		StringBuilder hql = new StringBuilder();
		hql.append("show tables ");
		
		List<Object[]> tables = this.findBySql(null, hql.toString(), null);
		for (Object s:tables) {
			result.add(s.toString());
		}
		
		return result;
	}

	public List<Object[]> createTableData(String tablename)
	{
		int filedNum = getTableFieldNums(tablename);
		List<Object[]> results = new LinkedList<Object[]>();
		StringBuilder hql = new StringBuilder();
		hql.append("select * from ");
		hql.append(tablename);
		
		results = this.findBySql(null, hql.toString(), null);
		
		return results;
	}

	private int getTableFieldNums(String tablename) {
		StringBuilder hql = new StringBuilder();
		hql.append("desc ");
		hql.append(tablename);
		List<Object[]> tables = this.findBySql(null, hql.toString(), null);
		return tables.size();
	}
	
	public String createTableDDL(String tablename) {
//		String sql = " show create table " + tablename;
		String result = null;
		StringBuilder hql = new StringBuilder();
		hql.append(" show create table ");
		hql.append(tablename);
		List<Object[]> list = this.findBySql(null, hql.toString(), null);
		result = list.get(0)[1].toString();
		
		return result;
	}

	/**
	 * 查詢欄位
	 * @param tablename
	 * @return
	 */
	public List<Field> listFields(String tablename)
	{
		List<Field> result = new LinkedList<Field>();
		
		StringBuilder hql = new StringBuilder();
		hql.append("desc ");
		hql.append(tablename);
		List<Object[]> fields = this.findBySql(null, hql.toString(), null);
		
		Field  field = null;
		for (Object[] f :fields) {
			field = new FieldImpl();
			field.setName(f[0]!=null?f[0].toString():null);
			field.setFieldType(f[1]!=null?f[1].toString():null);
			field.setNullable(f[2]!=null?f[2].toString():null);
			field.setFieldProperty(f[3]!=null?f[3].toString():null);
			field.setFieldDefault(f[4]!=null?f[4].toString():null);
			field.setExtra(f[5]!=null?f[5].toString():null);
			result.add(field);
		}
		
		return result;
	}
	
}

package org.openyu.cms.backup.dao.impl;


import java.io.File;
import java.util.List;

import org.junit.Test;

import org.openyu.cms.backup.BackupTestSupporter;
import org.openyu.commons.util.DateHelper;

public class BackupDaoImplTest extends BackupTestSupporter
{

	@Test
	//verified: ok
	public void findBackup()
	{
		List<String> result = null;
		long beg = System.currentTimeMillis();
		result = backupDao.findBackup();
		System.out.println(result);
		long end = System.currentTimeMillis();
		System.out.println(" times: " + (end - beg) + " mills. ");
	}

	@Test
	//verified: ok
	public void createTableData()
	{
		List<Object[]> result = null;
		long beg = System.currentTimeMillis();
		result = backupDao.createTableData("cms_ad");
		for (Object[] o : result)
		{
			for (Object ob : o)
			{
				System.out.println((ob != null) ? ob.toString() : "");
			}
		}
		//		System.out.println(result.toString());
		long end = System.currentTimeMillis();
		System.out.println(" times: " + (end - beg) + " mills. ");
	}
	
	@Test
	//verified: ok
	public void createTableDDL()
	{
		String result = null;
		long beg = System.currentTimeMillis();
		result = backupDao.createTableDDL("cms_ad");
		System.out.println(result);
		long end = System.currentTimeMillis();
		System.out.println(" times: " + (end - beg) + " mills. ");
	}
	
	@Test
	//verified: ok
	public void testFileName()
	{
		String result = null;
		long beg = System.currentTimeMillis();
//		result = backupDao.createTableDDL("cms_ad");
		DateHelper.toString(DateHelper.today(), "yyyyMMddhhmmss");
		String backFilePath = "backup" + File.separator
				+ DateHelper.toString(DateHelper.today(), "yyyyMMddhhmmss") + ".sql";
		System.out.println(backFilePath);
		long end = System.currentTimeMillis();
		System.out.println(" times: " + (end - beg) + " mills. ");
	}
	
	//	@Test
	//	//verified: ok
	//	public void insert()
	//	{
	//		int result = 0;
	//		int count = 21;
	//		long beg = System.currentTimeMillis();
	//		for (int i = 0; i < count; i++)
	//		{
	//			//隨機
	//			BackupPo backupPo = randomBackupPo();
	//			//
	//			result = backupDao.insert(backupPo);
	//			printInsert(i, result);
	//			assertTrue(result > 0);
	//
	//			BackupPo existBackupPo = backupDao.find(BackupPoImpl.class, backupPo.getSeq());
	//			assertBackupPo(backupPo, existBackupPo);
	//
	//			System.out.println(backupPo);
	//		}
	//		long end = System.currentTimeMillis();
	//		System.out.println(count + " times: " + (end - beg) + " mills. ");
	//	}
	//
	//	@Test
	//	//1 times: 272 mills. 
	//	//1 times: 276 mills. 
	//	//1 times: 276 mills. 
	//	//verified: ok
	//	public void findBackup()
	//	{
	//		List<BackupPo> result = null;
	//		int count = 1;
	//
	//		long beg = System.currentTimeMillis();
	//		for (int i = 0; i < count; i++)
	//		{
	//			result = backupDao.findBackup();
	//		}
	//		long end = System.currentTimeMillis();
	//		System.out.println(count + " times: " + (end - beg) + " mills. ");
	//
	//		System.out.println(result.size());
	//		System.out.println(result);
	//		assertNotNull(result);
	//	}
	//
	//	@Test
	//	//1 times: 272 mills. 
	//	//1 times: 276 mills. 
	//	//1 times: 276 mills. 
	//	//verified: ok
	//	public void findBackupBySite()
	//	{
	//		List<BackupPo> result = null;
	//		int count = 1;
	//
	//		long beg = System.currentTimeMillis();
	//		for (int i = 0; i < count; i++)
	//		{
	//			result = backupDao.findBackup(1L);
	//		}
	//		long end = System.currentTimeMillis();
	//		System.out.println(count + " times: " + (end - beg) + " mills. ");
	//
	//		System.out.println(result.size());
	//		System.out.println(result);
	//		assertNotNull(result);
	//	}
	//
	//	@Test
	//	/**
	//	 * 查詢條件
	//	 */
	//	public void findBackupByInquiry()
	//	{
	//		Inquiry inquiry = new InquiryImpl();
	//		//分頁
	//		Pagination pagination = new PaginationImpl();
	//		pagination.setPageSize(5);//每頁筆數
	//		inquiry.setPagination(pagination);
	//		//
	//		Backup searcher = new BackupImpl();
	//		//
	//		List<BackupPo> result = null;
	//		int count = 1;
	//
	//		long beg = System.currentTimeMillis();
	//		for (int i = 0; i < count; i++)
	//		{
	//			result = backupDao.findBackup(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
	//		}
	//		long end = System.currentTimeMillis();
	//		System.out.println(count + " times: " + (end - beg) + " mills. ");
	//		//
	//		System.out.println(inquiry.getPagination());
	//		System.out.println(result.size() + ", " + result);
	//		//
	//		assertNotNull(result);
	//	}
	//
	//	@Test
	//	/**
	//	 * 查詢條件
	//	 */
	//	public void findBackupByInquirySite()
	//	{
	//		Inquiry inquiry = new InquiryImpl();
	//		//分頁
	//		Pagination pagination = new PaginationImpl();
	//		pagination.setPageSize(5);//每頁筆數
	//		inquiry.setPagination(pagination);
	//		//
	//		Backup searcher = new BackupImpl();
	//		//
	//		List<BackupPo> result = null;
	//		int count = 1;
	//
	//		long beg = System.currentTimeMillis();
	//		for (int i = 0; i < count; i++)
	//		{
	//			result = backupDao.findBackup(inquiry, Locale.TRADITIONAL_CHINESE, 1L, searcher);
	//		}
	//		long end = System.currentTimeMillis();
	//		System.out.println(count + " times: " + (end - beg) + " mills. ");
	//		//
	//		System.out.println(inquiry.getPagination());
	//		System.out.println(result.size() + ", " + result);
	//		//
	//		assertNotNull(result);
	//		//
	//		searcher = new BackupImpl();
	//		searcher.setSeq(1);
	//		result = backupDao.findBackup(inquiry, Locale.TRADITIONAL_CHINESE, null, searcher);
	//		System.out.println(result.size() + ", " + result);
	//	}
}

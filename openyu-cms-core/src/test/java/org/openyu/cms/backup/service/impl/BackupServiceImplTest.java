package org.openyu.cms.backup.service.impl;


import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import org.openyu.cms.backup.BackupTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class BackupServiceImplTest extends BackupTestSupporter
{
//	@After
//	public void tearDown() throws Exception
//	{
//		ThreadHelper.sleep(5 * 1000);
//	}

	@Test
	//verified: ok
	public void backupTables()
	{

		List<String> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		User user = new UserImpl();
		List<String> tables = new LinkedList<String>();
		tables.add("cms_ad");
		String backupPath = "D:\\OO";
		
		result = backupService.backupTables(user, tables, backupPath);
		
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		System.out.println(result);
		ThreadHelper.sleep(10 * 1000);
	}

}

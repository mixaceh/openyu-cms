package org.openyu.cms.backup;

import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.backup.dao.BackupDao;
import org.openyu.cms.backup.service.BackupService;

import org.openyu.cms.app.AppTestSupporter;

public class BackupTestSupporter extends AppTestSupporter
{

	protected static BackupDao backupDao;

	protected static BackupService backupService;

//	protected static BackupLogDao backupLogDao;
//
//	protected static BackupLogService backupLogService;
//
//	protected static BackupListDirective backupListDirective;
//
//	protected static BackupDirective backupDirective;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
				"applicationContext-init.xml",//
				"META-INF/applicationContext-commons-core.xml",//
				"applicationContext-message.xml",//
				"applicationContext-database.xml",//
				"applicationContext-database-log.xml",//
				"applicationContext-cxf.xml",//
				"applicationContext-junit.xml",//
				"org/openyu/cms/app/applicationContext-app.xml",//
				"org/openyu/cms/user/applicationContext-user.xml",//
				"org/openyu/cms/site/applicationContext-site.xml",//
				"org/openyu/cms/backup/applicationContext-backup.xml",//
		});
		//---------------------------------------------------
		initialize();
		//---------------------------------------------------
		backupDao = (BackupDao) applicationContext.getBean("backupDao");
		backupService = (BackupService) applicationContext.getBean("backupService");
//		backupLogDao = (BackupLogDao) applicationContext.getBean("backupLogDao");
//		backupLogService = (BackupLogService) applicationContext.getBean("backupLogService");
//		backupListDirective = (BackupListDirective) applicationContext.getBean("cms_backup_list");
//		backupDirective = (BackupDirective) applicationContext.getBean("cms_backup");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{}

	@Before
	public void setUp() throws Exception
	{}

	@After
	public void tearDown() throws Exception
	{}

	@Test
	public void backupDao()
	{
		System.out.println(backupDao);
		assertNotNull(backupDao);
	}

	@Test
	public void backupService()
	{
		System.out.println(backupService);
		assertNotNull(backupService);
	}

//	@Test
//	public void backupLogDao()
//	{
//		System.out.println(backupLogDao);
//		assertNotNull(backupLogDao);
//	}
//
//	@Test
//	public void backupLogService()
//	{
//		System.out.println(backupLogService);
//		assertNotNull(backupLogService);
//	}
//
//	@Test
//	public void backupListDirective()
//	{
//		System.out.println(backupListDirective);
//		assertNotNull(backupListDirective);
//	}
//
//	@Test
//	public void backupDirective()
//	{
//		System.out.println(backupDirective);
//		assertNotNull(backupDirective);
//	}
}

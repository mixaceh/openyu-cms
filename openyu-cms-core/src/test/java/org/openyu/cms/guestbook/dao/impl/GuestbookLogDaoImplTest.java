package org.openyu.cms.guestbook.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.guestbook.vo.ActionType;
import org.openyu.cms.guestbook.log.GuestbookLog;
import org.openyu.cms.guestbook.log.impl.GuestbookLogImpl;
import org.openyu.cms.guestbook.GuestbookTestSupporter;
import org.openyu.commons.util.DateHelper;

public class GuestbookLogDaoImplTest extends GuestbookTestSupporter
{
	/**
	 * 隨機產生專題資料
	 * 
	 * @return
	 */
	public static GuestbookLog randomGuestbookLog()
	{
		final String UNIQUE = randomUnique();
		final String guestbookId = "TEST_GUESTBOOK_LOG" + UNIQUE;
		//
		GuestbookLog result = new GuestbookLogImpl();
		//id
		result.setGuestbookId(guestbookId);
		//ActionType
		result.setActionType(ActionType.INSERT);
		//

		return result;
	}

	/**
	 * 檢核專題資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertGuestbookPo(GuestbookLog expected, GuestbookLog actual)
	{
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getGuestbookId(), actual.getGuestbookId());
		//
		assertEquals(expected.getActionType(), actual.getActionType());
	}

	@Test
	//insert -> find -> delete
	//
	//10 times: 7237 mills. 
	//10 times: 6825 mills.
	//10 times: 6693 mills. 
	//
	//verified: ok
	public void crud()
	{
		int count = 10;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			//隨機
			GuestbookLog guestbookLog = randomGuestbookLog();
			//create
			Serializable pk = guestbookLogDao.insert(guestbookLog);
			printInsert(i, pk);
			assertNotNull(pk);

			//retrieve  
			GuestbookLog foundEntity = guestbookLogDao.find(GuestbookLogImpl.class,
				guestbookLog.getSeq());
			printFind(i, foundEntity);
			assertGuestbookPo(guestbookLog, foundEntity);
			
			//update
			guestbookLog.setLogDate(DateHelper.today());
			int updated  = guestbookLogDao.update(guestbookLog);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			//delete
			GuestbookLog deletedEntity = guestbookLogDao.delete(GuestbookLogImpl.class,
				guestbookLog.getSeq());
			printDelete(i, deletedEntity);
			assertNotNull(deletedEntity);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	//verified: ok
	public void insert()
	{
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			//隨機
			GuestbookLog guestbookLog = randomGuestbookLog();
			//create
			Serializable pk  = guestbookLogDao.insert(guestbookLog);
			printInsert(i, pk);
			assertNotNull(pk);

			GuestbookLog findGuestbookLog = guestbookLogDao.find(GuestbookLogImpl.class,
				guestbookLog.getSeq());
			assertGuestbookPo(guestbookLog, findGuestbookLog);

			System.out.println(guestbookLog);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	//1 times: 272 mills. 
	//1 times: 276 mills. 
	//1 times: 276 mills. 
	//verified: ok
	public void findGuestbookLog()
	{
		List<GuestbookLog> result = null;
		long siteSeq = 1;
		String userId = "sys";
		String clientId = "127.0.0.1";
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
			result = guestbookLogDao.findGuestbookLog(siteSeq, userId, clientId);
		}
		long end = System.currentTimeMillis();
		System.out.println("findGuestbook::" + count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

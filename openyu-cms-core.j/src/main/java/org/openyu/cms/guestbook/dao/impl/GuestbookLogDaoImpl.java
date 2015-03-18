package org.openyu.cms.guestbook.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppLogDaoSupporter;
import org.openyu.cms.guestbook.dao.GuestbookLogDao;
import org.openyu.cms.guestbook.log.GuestbookLog;
import org.openyu.cms.guestbook.log.impl.GuestbookLogImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class GuestbookLogDaoImpl extends AppLogDaoSupporter implements GuestbookLogDao
{

	private static transient final Logger log = LogManager.getLogger(GuestbookLogDaoImpl.class);

	/**
	 * 留言改變log
	 */
	private static final String GUESTBOOK_LOG_PO_NAME = GuestbookLogImpl.class.getName();

	public GuestbookLogDaoImpl()
	{}

	// --------------------------------------------------
	// GuestbookLevelLog
	// --------------------------------------------------
	/**
	 * 查詢留言改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<GuestbookLog> findGuestbookLog(long siteSeq, String userId, String clientIp)
	{

		return findGuestbookLog(null, siteSeq, userId, clientIp);
	}

	/**
	 * 分頁查詢留言改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	public List<GuestbookLog> findGuestbookLog(Inquiry inquiry, long siteSeq)
	{
		return findGuestbookLog(inquiry, siteSeq, null, null);
	}

	/**
	 * 分頁查詢留言改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	public List<GuestbookLog> findGuestbookLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(GUESTBOOK_LOG_PO_NAME + " ");
		hql.append("where 1=1 ");

		//siteSeq
		if (siteSeq > 0)
		{
			hql.append("and siteSeq = :siteSeq ");
			params.put("siteSeq", siteSeq);
		}

		//userId
		if (StringHelper.notBlank(userId))
		{
			hql.append("and userId = :userId ");
			params.put("userId", userId);
		}

		//clientIp
		if (StringHelper.notBlank(clientIp))
		{
			hql.append("and clientIp = :clientIp ");
			params.put("clientIp", clientIp);
		}
		//
		return findByHql(inquiry, null, hql, params);
	}

	/**
	 * 刪除留言改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	public int deleteGuestbookLog(long siteSeq, String userId)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("delete from ");
		hql.append(GUESTBOOK_LOG_PO_NAME + " ");
		hql.append("where 1=1 ");

		//siteSeq
		hql.append("and siteSeq = :siteSeq ");
		params.put("siteSeq", siteSeq);

		//userId
		hql.append("and userId = :userId ");
		params.put("userId", userId);
		//
		return executeByHql(hql, params);
	}

}

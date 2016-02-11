package org.openyu.cms.guestbook.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.guestbook.log.GuestbookLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface GuestbookLogDao extends AppLogDao
{
	// --------------------------------------------------
	// GuestbookLog
	// --------------------------------------------------
	/**
	 * 查詢留言改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<GuestbookLog> findGuestbookLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢留言改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<GuestbookLog> findGuestbookLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢留言改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<GuestbookLog> findGuestbookLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除留言改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteGuestbookLog(long siteSeq, String userId);

}

package org.openyu.cms.guestbookType.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.guestbookType.log.GuestbookTypeLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface GuestbookTypeLogDao extends AppLogDao
{
	// --------------------------------------------------
	// GuestbookTypeLog
	// --------------------------------------------------
	/**
	 * 查詢留言類型改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<GuestbookTypeLog> findGuestbookTypeLog(long siteSeq, String userId, String clientIp);

	/**
	 * 分頁查詢留言類型改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @return
	 */
	List<GuestbookTypeLog> findGuestbookTypeLog(Inquiry inquiry, long siteSeq);

	/**
	 * 分頁查詢留言類型改變log
	 * 
	 * @param inquiry
	 * @param siteSeq
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<GuestbookTypeLog> findGuestbookTypeLog(Inquiry inquiry, long siteSeq, String userId, String clientIp);

	/**
	 * 刪除留言類型改變log
	 * 
	 * @param siteSeq
	 * @param userId
	 * @return
	 */
	int deleteGuestbookTypeLog(long siteSeq, String userId);

}

package org.openyu.cms.tag.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.tag.log.TagLog;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface TagLogDao extends AppLogDao
{
	// --------------------------------------------------
	// TagLog
	// --------------------------------------------------
	/**
	 * 查詢標籤改變log
	 * 
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<TagLog> findTagLog(String userId, String clientIp);

	/**
	 * 分頁查詢標籤改變log
	 * 
	 * @param inquiry
	 * @return
	 */
	List<TagLog> findTagLog(Inquiry inquiry);

	/**
	 * 分頁查詢標籤改變log
	 * 
	 * @param inquiry
	 * @param userId
	 * @param clientIp
	 * @return
	 */
	List<TagLog> findTagLog(Inquiry inquiry, String userId, String clientIp);

	/**
	 * 刪除標籤改變log
	 * 
	 * @param userId
	 * @return
	 */
	int deleteTagLog(String userId);

}

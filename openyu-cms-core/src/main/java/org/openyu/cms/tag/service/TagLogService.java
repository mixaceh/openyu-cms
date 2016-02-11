package org.openyu.cms.tag.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.tag.vo.ActionType;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.tag.log.TagLog;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface TagLogService extends AppLogService
{
	// --------------------------------------------------
	// db
	// --------------------------------------------------

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

	// --------------------------------------------------
	// biz
	// --------------------------------------------------

	// --------------------------------------------------
	// RoleLevelLog
	// --------------------------------------------------
	/**
	 * 紀錄標籤改變
	 * 
	 * @param user
	 * @param actionType
	 * @param tag
	 */
	void recordChangeTag(User user, ActionType actionType, Tag tag);
}

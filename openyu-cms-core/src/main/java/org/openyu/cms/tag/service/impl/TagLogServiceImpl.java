package org.openyu.cms.tag.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.tag.dao.TagLogDao;
import org.openyu.cms.tag.log.TagLog;
import org.openyu.cms.tag.log.impl.TagLogImpl;
import org.openyu.cms.tag.service.TagLogService;
import org.openyu.cms.tag.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.entity.EntityHelper;

public class TagLogServiceImpl extends AppLogServiceSupporter implements TagLogService
{

	@Autowired
	@Qualifier("tagLogDao")
	protected transient TagLogDao tagLogDao;

	public TagLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// TagLog
	// --------------------------------------------------
	public List<TagLog> findTagLog(String userId, String clientIp)
	{
		return tagLogDao.findTagLog(userId, clientIp);
	}

	public List<TagLog> findTagLog(Inquiry inquiry)
	{
		return tagLogDao.findTagLog(inquiry);
	}

	public List<TagLog> findTagLog(Inquiry inquiry, String userId, String clientIp)
	{
		return tagLogDao.findTagLog(inquiry, userId, clientIp);
	}

	public int deleteTagLog(String userId)
	{
		return tagLogDao.deleteTagLog(userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------
	/**
	 * 紀錄標籤改變
	 * 
	 * @param user
	 * @param actionType
	 * @param tag
	 */
	public void recordChangeTag(User user, ActionType actionType, Tag tag)
	{
		TagLog log = new TagLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);
		//
		log.setTagSeq(tag.getSeq());
		log.setTagId(tag.getId());
		log.setActionType(actionType);
		//名稱
		log.setNames(EntityHelper.toNames(tag.getNames()));
		//
		offerInsert(log);
	}

}

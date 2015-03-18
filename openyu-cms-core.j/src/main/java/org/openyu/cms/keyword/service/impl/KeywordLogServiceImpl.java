package org.openyu.cms.keyword.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.keyword.dao.KeywordLogDao;
import org.openyu.cms.keyword.log.KeywordLog;
import org.openyu.cms.keyword.log.impl.KeywordLogImpl;
import org.openyu.cms.keyword.service.KeywordLogService;
import org.openyu.cms.keyword.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

public class KeywordLogServiceImpl extends AppLogServiceSupporter implements KeywordLogService
{

	@Autowired
	@Qualifier("keywordLogDao")
	protected transient KeywordLogDao keywordLogDao;

	public KeywordLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// KeywordLog
	// --------------------------------------------------
	public List<KeywordLog> findKeywordLog(long siteSeq, String userId, String clientIp)
	{
		return keywordLogDao.findKeywordLog(siteSeq, userId, clientIp);
	}

	public List<KeywordLog> findKeywordLog(Inquiry inquiry, long siteSeq)
	{
		return keywordLogDao.findKeywordLog(inquiry, siteSeq);
	}

	public List<KeywordLog> findKeywordLog(Inquiry inquiry, long siteSeq, String userId,
											String clientIp)
	{
		return keywordLogDao.findKeywordLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteKeywordLog(long siteSeq, String userId)
	{
		return keywordLogDao.deleteKeywordLog(siteSeq, userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------
	/**
	 * 紀錄關鍵字改變
	 * 
	 * @param user
	 * @param actionType
	 * @param keyword
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	public void recordChangeDictionary(User user, ActionType actionType, Keyword keyword,
										Dictionary beforeDictionary, Dictionary afterDictionary)
	{
		KeywordLog log = new KeywordLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站seq
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setLocale(user.getSessionLocale());
		log.setKeywordSeq(keyword.getSeq());
		log.setKeywordId(keyword.getId());
		log.setActionType(actionType);
		log.setAfterDictionary(afterDictionary);
		log.setBeforeDictionary(beforeDictionary);
		//
		offerInsert(log);
	}

}

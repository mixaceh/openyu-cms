package org.openyu.cms.ad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppLogServiceSupporter;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.ad.dao.AdLogDao;
import org.openyu.cms.ad.log.AdLog;
import org.openyu.cms.ad.log.impl.AdLogImpl;
import org.openyu.cms.ad.service.AdLogService;
import org.openyu.cms.ad.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.entity.EntityHelper;

public class AdLogServiceImpl extends AppLogServiceSupporter implements AdLogService
{

	@Autowired
	@Qualifier("adLogDao")
	protected transient AdLogDao adLogDao;

	public AdLogServiceImpl()
	{}

	// --------------------------------------------------
	// db
	// --------------------------------------------------

	// --------------------------------------------------
	// AdLog
	// --------------------------------------------------
	public List<AdLog> findAdLog(long siteSeq, String userId, String clientIp)
	{
		return adLogDao.findAdLog(siteSeq, userId, clientIp);
	}

	public List<AdLog> findAdLog(Inquiry inquiry, long siteSeq)
	{
		return adLogDao.findAdLog(inquiry, siteSeq);
	}

	public List<AdLog> findAdLog(Inquiry inquiry, long siteSeq, String userId, String clientIp)
	{
		return adLogDao.findAdLog(inquiry, siteSeq, userId, clientIp);
	}

	public int deleteAdLog(long siteSeq, String userId)
	{
		return adLogDao.deleteAdLog(siteSeq, userId);
	}

	// --------------------------------------------------
	// biz
	// --------------------------------------------------
	/**
	 * 紀錄廣告改變
	 * 
	 * @param user
	 * @param actionType
	 * @param ad
	 * @param beforeDictionary
	 * @param afterDictionary
	 */
	public void recordChangeAd(User user, ActionType actionType, Ad ad)
	{
		AdLog log = new AdLogImpl();
		//紀錄使用者相關資訊
		recordUser(user, log);

		//紀錄網站seq
		log.setSiteSeq(user.getSessionSiteSeq());
		//
		log.setAdSeq(ad.getSeq());
		log.setAdId(ad.getId());
		log.setActionType(actionType);
		//名稱
		log.setNames(EntityHelper.toNames(ad.getNames()));
		//
		offerInsert(log);
	}

}

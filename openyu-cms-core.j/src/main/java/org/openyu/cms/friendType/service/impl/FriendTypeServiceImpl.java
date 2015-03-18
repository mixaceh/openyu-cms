package org.openyu.cms.friendType.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.friendType.dao.FriendTypeDao;
import org.openyu.cms.friendType.po.FriendTypePo;
import org.openyu.cms.friendType.service.FriendTypeService;
import org.openyu.cms.friendType.vo.FriendTypeCollector;
import org.openyu.cms.site.service.SiteService;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class FriendTypeServiceImpl extends AppServiceSupporter implements
		FriendTypeService {
	private static transient final Logger log = LogManager
			.getLogger(FriendTypeServiceImpl.class);

	/**
	 * 網站服務
	 */
	@Autowired
	@Qualifier("siteService")
	protected transient SiteService siteService;

	protected transient FriendTypeCollector friendTypeCollector = FriendTypeCollector
			.getInstance();

	// /**
	// * 讀寫鎖
	// */
	// private ReadWriteLock beansLock = new ReentrantReadWriteLock();
	//
	// /**
	// * 讀鎖
	// */
	// private Lock readLock = beansLock.readLock();
	//
	// /**
	// * 寫鎖
	// */
	// private Lock writeLock = beansLock.writeLock();

	public FriendTypeServiceImpl() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		// 初始化cache,<siteId,List<FriendType>>
		List<Site> sites = siteService.getSites();
		for (Site site : sites) {
			List<FriendType> friendTypes = findFriendType(site.getSeq());
			beans.put(site.getId(), friendTypes);
		}
	}

	public FriendTypeDao getFriendTypeDao() {
		return (FriendTypeDao) getOjDao();
	}

	@Autowired
	@Qualifier("friendTypeDao")
	public void setFriendTypeDao(FriendTypeDao friendTypeDao) {
		setOjDao(friendTypeDao);
	}

	/**
	 * 查詢友情類型
	 * 
	 * @return
	 */
	public List<FriendType> findFriendType() {
		List<FriendTypePo> orig = getFriendTypeDao().findFriendType();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢友情類型
	 * 
	 * @param locale
	 * @return
	 */
	public List<FriendType> findFriendType(Locale locale) {
		List<FriendTypePo> orig = getFriendTypeDao().findFriendType(locale);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢友情類型,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<FriendType> findFriendType(long siteSeq) {
		List<FriendTypePo> orig = getFriendTypeDao().findFriendType(siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢友情類型,網站別
	 * 
	 * @param siteSeq
	 * @param locale
	 * @return
	 */
	public List<FriendType> findFriendType(Locale locale, long siteSeq) {
		List<FriendTypePo> orig = getFriendTypeDao().findFriendType(locale,
				siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param friendType
	 * @return
	 */
	public List<FriendType> findFriendType(Locale locale, FriendType searcher) {
		List<FriendTypePo> orig = getFriendTypeDao().findFriendType(locale,
				searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param friendType
	 * @return
	 */
	public List<FriendType> findFriendType(Inquiry inquiry, Locale locale,
			FriendType searcher) {
		List<FriendTypePo> orig = getFriendTypeDao().findFriendType(inquiry,
				locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<FriendType> findFriendType(Locale locale, long siteSeq,
			FriendType searcher) {
		List<FriendTypePo> orig = getFriendTypeDao().findFriendType(locale,
				siteSeq, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<FriendType> findFriendType(Locale locale, Site siteSearcher,
			FriendType searcher) {
		List<FriendTypePo> orig = getFriendTypeDao().findFriendType(locale,
				siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<FriendType> findFriendType(Inquiry inquiry, Locale locale,
			long siteSeq, FriendType searcher) {
		List<FriendTypePo> orig = getFriendTypeDao().findFriendType(inquiry,
				locale, siteSeq, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<FriendType> findFriendType(Inquiry inquiry, Locale locale,
			Site siteSearcher, FriendType searcher) {
		List<FriendTypePo> orig = getFriendTypeDao().findFriendType(inquiry,
				locale, siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 加入友情類型,網站別
	 * 
	 * @param siteId
	 * @param friendType
	 * @return
	 */
	public synchronized FriendType addFriendType(String siteId,
			FriendType friendType) {
		FriendType result = null;
		if (siteId != null && friendType != null) {
			List<FriendType> list = getFriendTypes(siteId);
			int index = list.indexOf(friendType);
			if (index > -1) {
				list.remove(index);
			}
			list.add(friendType);
		}
		return result;
	}

	// public FriendType ___addFriendType(String siteId, FriendType friendType)
	// {
	// FriendType result = null;
	// try
	// {
	// writeLock.lockInterruptibly();
	// try
	// {
	// if (siteId != null && friendType != null)
	// {
	// List<FriendType> list = getFriendTypes(siteId);
	// int index = list.indexOf(friendType);
	// if (index > -1)
	// {
	// list.remove(index);
	// }
	// list.add(friendType);
	// }
	// }
	// catch (Exception ex)
	// {
	// ex.printStackTrace();
	// }
	// finally
	// {
	// writeLock.unlock();
	// }
	// }
	// catch (InterruptedException ex)
	// {
	// ex.printStackTrace();
	// }
	// return result;
	// }

	/**
	 * 移除友情類型,網站別
	 * 
	 * @param siteId
	 * @param friendTypeId
	 * @return
	 */
	public synchronized FriendType removeFriendType(String siteId,
			FriendType friendType) {
		FriendType result = null;
		if (siteId != null && friendType != null) {
			List<FriendType> list = getFriendTypes(siteId);
			int index = list.indexOf(friendType);
			if (index > -1) {
				list.remove(index);
			}
		}
		return result;
	}

	/**
	 * 取得所有友情類型,網站別
	 * 
	 * @return
	 */
	public synchronized List<FriendType> getFriendTypes(String siteId) {
		@SuppressWarnings("unchecked")
		List<FriendType> result = (List<FriendType>) beans.get(siteId);
		if (result == null) {
			result = new LinkedList<FriendType>();
			beans.put(siteId, result);
		}
		return result;
	}

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param friendTypeId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public FriendType createFriendType(String friendTypeId) {
		return friendTypeCollector.createFriendType(friendTypeId);
	}

}

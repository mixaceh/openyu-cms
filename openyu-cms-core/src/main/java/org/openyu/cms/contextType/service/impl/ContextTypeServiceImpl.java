package org.openyu.cms.contextType.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.contextType.dao.ContextTypeDao;
import org.openyu.cms.contextType.po.ContextTypePo;
import org.openyu.cms.contextType.service.ContextTypeService;
import org.openyu.cms.contextType.vo.ContextType;
import org.openyu.cms.contextType.vo.ContextTypeCollector;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class ContextTypeServiceImpl extends AppServiceSupporter implements ContextTypeService
{
	private static transient final Logger log = LogManager.getLogger(ContextTypeServiceImpl.class);

	protected transient ContextTypeCollector contextTypeCollector = ContextTypeCollector
			.getInstance();

	public ContextTypeServiceImpl()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//初始化cache,<id,ContextType>
		List<ContextType> contextTypes = findContextType(true);
		for (ContextType contextType : contextTypes)
		{
			addContextType(contextType);
		}
	}

	public ContextTypeDao getContextTypeDao()
	{
		return (ContextTypeDao) getOjDao();
	}

	@Autowired
	@Qualifier("contextTypeDao")
	public void setContextTypeDao(ContextTypeDao contextTypeDao)
	{
		setOjDao(contextTypeDao);
	}

	/**
	 * 查詢是否有效本文類型
	 * 
	 * @param valid
	 * @return
	 */
	public List<ContextType> findContextType(boolean valid)
	{
		List<ContextTypePo> orig = getContextTypeDao().findContextType(valid);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢是否有效本文類型
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	public List<ContextType> findContextType(Locale locale, boolean valid)
	{
		List<ContextTypePo> orig = getContextTypeDao().findContextType(locale, valid);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param contextType
	 * @return
	 */
	public List<ContextType> findContextType(Locale locale, ContextType searcher)
	{
		List<ContextTypePo> orig = getContextTypeDao().findContextType(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param contextType
	 * @return
	 */
	public List<ContextType> findContextType(Inquiry inquiry, Locale locale, ContextType searcher)
	{
		List<ContextTypePo> orig = getContextTypeDao().findContextType(inquiry, locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------
	/**
	 * 加入本文類型
	 * 
	 * @param contextType
	 * @return
	 */
	public synchronized ContextType addContextType(ContextType contextType)
	{
		ContextType result = null;
		if (contextType != null)
		{
			result = (ContextType) beans.put(contextType.getId(), contextType);
		}
		return result;
	}

	/**
	 * 更新網站
	 * 
	 * @param origContextType
	 * @param newContextType
	 * @return
	 */
	public synchronized ContextType updateContextType(ContextType origContextType,
														ContextType newContextType)
	{
		ContextType result = null;
		if (origContextType != null)
		{
			String contextTypeId = origContextType.getId();
			boolean contains = beans.contains(contextTypeId);
			if (contains)
			{
				result = removeContextType(contextTypeId);
			}
			addContextType(newContextType);
		}
		return result;
	}

	/**
	 * 移除本文類型
	 * 
	 * @param contextTypeId
	 * @return
	 */
	public synchronized ContextType removeContextType(String contextTypeId)
	{
		ContextType result = null;
		if (contextTypeId != null)
		{
			result = (ContextType) beans.remove(contextTypeId);
		}
		return result;
	}

	/**
	 * 取得所有本文類型
	 * 
	 * @return
	 */
	public synchronized List<ContextType> getContextTypes()
	{
		List<ContextType> result = new LinkedList<ContextType>();
		for (Object entry : beans.getValues())
		{
			if (entry instanceof ContextType)
			{
				result.add((ContextType) entry);
			}
		}
		return result;
	}

	// --------------------------------------------------
	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param contextTypeId, DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public ContextType createContextType(String contextTypeId)
	{
		return contextTypeCollector.createContextType(contextTypeId);
	}

}

package org.openyu.cms.contextType.service;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.service.AppService;
import org.openyu.cms.contextType.vo.ContextType;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 本文類型服務
 */
public interface ContextTypeService extends AppService
{

	/**
	 * 查詢是否有效本文類型
	 * 
	 * @param valid
	 * @return
	 */
	List<ContextType> findContextType(boolean valid);

	/**
	 * 查詢是否有效本文類型
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<ContextType> findContextType(Locale locale, boolean valid);

	// --------------------------------------------------
	/**
	 * 加入本文類型
	 * 
	 * @param contextType
	 * @return
	 */
	ContextType addContextType(ContextType contextType);

	/**
	 * 更新本文類型
	 * 
	 * @param origContextType
	 * @param newContextType
	 * @return
	 */
	ContextType updateContextType(ContextType origContextType, ContextType newContextType);

	/**
	 * 移除本文類型
	 * 
	 * @param contextTypeId
	 * @return
	 */
	ContextType removeContextType(String contextTypeId);

	/**
	 * 取得所有本文類型
	 * 
	 * @return
	 */
	List<ContextType> getContextTypes();

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<ContextType> findContextType(Locale locale, ContextType searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<ContextType> findContextType(Inquiry inquiry, Locale locale, ContextType searcher);

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param contextTypeId, DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	ContextType createContextType(String contextTypeId);

}
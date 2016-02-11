package org.openyu.cms.contextType.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.contextType.po.ContextTypePo;
import org.openyu.cms.contextType.vo.ContextType;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface ContextTypeDao extends AppDao
{

	/**
	 * 查詢是否有效本文類型
	 * 
	 * @param valid
	 * @return
	 */
	List<ContextTypePo> findContextType(boolean valid);

	/**
	 * 查詢是否有效本文類型
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<ContextTypePo> findContextType(Locale locale, boolean valid);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<ContextTypePo> findContextType(Locale locale, ContextType searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<ContextTypePo> findContextType(Inquiry inquiry, Locale locale, ContextType searcher);

}
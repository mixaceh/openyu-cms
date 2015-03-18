package org.openyu.cms.module.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.module.po.ModulePo;
import org.openyu.cms.module.vo.Module;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface ModuleDao extends AppDao
{

	/**
	 * 查詢是否有效模組
	 * 
	 * @param valid
	 * @return
	 */
	List<ModulePo> findModule(boolean valid);

	/**
	 * 查詢是否有效模組
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<ModulePo> findModule(Locale locale, boolean valid);

	/**
	 * 查詢預設模組
	 * 
	 * @return
	 */
	ModulePo findDefaultModule();

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<ModulePo> findModule(Locale locale, Module searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<ModulePo> findModule(Inquiry inquiry, Locale locale, Module searcher);

}
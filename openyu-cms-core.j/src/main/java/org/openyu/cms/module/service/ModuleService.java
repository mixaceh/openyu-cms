package org.openyu.cms.module.service;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.module.vo.Module;
import org.openyu.cms.app.service.AppService;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 模組服務
 */
public interface ModuleService extends AppService
{

	/**
	 * 查詢是否有效模組
	 * 
	 * @param valid
	 * @return
	 */
	List<Module> findModule(boolean valid);

	/**
	 * 查詢是否有效模組
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<Module> findModule(Locale locale, boolean valid);

	/**
	 * 查詢預設模組
	 * 
	 * @return
	 */
	Module findDefaultModule();

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Module> findModule(Locale locale, Module searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Module> findModule(Inquiry inquiry, Locale locale, Module searcher);

	/**
	 * 修改排列順序
	 * 
	 * @param seqs
	 * @param sorts
	 * @param valids
	 * @param defaultSeq
	 * @return
	 */
	List<Module> updateSort(List<Long> seqs, List<Integer> sorts, List<Boolean> valids,
							long defaultSeq);

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param moduleId, DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	Module createModule(String moduleId);

}
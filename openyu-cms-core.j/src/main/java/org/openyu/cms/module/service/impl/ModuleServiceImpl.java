package org.openyu.cms.module.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.module.dao.ModuleDao;
import org.openyu.cms.module.po.ModulePo;
import org.openyu.cms.module.service.ModuleService;
import org.openyu.cms.module.vo.Module;
import org.openyu.cms.module.vo.ModuleCollector;
import org.openyu.cms.module.vo.impl.ModuleImpl;
import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class ModuleServiceImpl extends AppServiceSupporter implements ModuleService
{
	private static transient final Logger log = LogManager.getLogger(ModuleServiceImpl.class);

	protected transient ModuleCollector moduleCollector = ModuleCollector.getInstance();

	public ModuleServiceImpl()
	{}

	public ModuleDao getModuleDao()
	{
		return (ModuleDao) getOjDao();
	}

	@Autowired
	@Qualifier("moduleDao")
	public void setModuleDao(ModuleDao moduleDao)
	{
		setOjDao(moduleDao);
	}

	/**
	 * 查詢是否有效模組
	 * 
	 * @param valid
	 * @return
	 */
	public List<Module> findModule(boolean valid)
	{
		List<ModulePo> orig = getModuleDao().findModule(valid);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢是否有效模組
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	public List<Module> findModule(Locale locale, boolean valid)
	{
		List<ModulePo> orig = getModuleDao().findModule(locale, valid);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢預設模組
	 * 
	 * @return
	 */
	public Module findDefaultModule()
	{
		ModulePo orig = getModuleDao().findDefaultModule();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param module
	 * @return
	 */
	public List<Module> findModule(Locale locale, Module searcher)
	{
		List<ModulePo> orig = getModuleDao().findModule(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param module
	 * @return
	 */
	public List<Module> findModule(Inquiry inquiry, Locale locale, Module searcher)
	{
		List<ModulePo> orig = getModuleDao().findModule(inquiry, locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 修改排列順序
	 * 
	 * @param seqs
	 * @param sorts
	 * @param valids
	 * @param defaultSeq
	 * @return
	 */
	public List<Module> updateSort(List<Long> seqs, List<Integer> sorts, List<Boolean> valids,
									long defaultSeq)
	{
		List<Module> result = new LinkedList<Module>();
		//
		int size = seqs.size();
		for (int i = 0; i < size; i++)
		{
			Module module = find(ModuleImpl.class, seqs.get(i));
			if (module == null)
			{
				continue;
			}
			//
			module.setSort(sorts.get(i));
			module.setValid(valids.get(i));
			//
			if (module.getSeq() == defaultSeq)
			{
				module.setDefault(true);
			}
			else
			{
				module.setDefault(false);
			}
			//
			int upd = update(module);
			if (upd > 0)
			{
				result.add(module);
			}
		}
		//
		return result;
	}

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param moduleId, DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public Module createModule(String moduleId)
	{
		return moduleCollector.createModule(moduleId);
	}

}

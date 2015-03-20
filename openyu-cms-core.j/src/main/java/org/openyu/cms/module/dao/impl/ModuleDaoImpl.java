package org.openyu.cms.module.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.module.dao.ModuleDao;
import org.openyu.cms.module.po.ModulePo;
import org.openyu.cms.module.po.impl.ModulePoImpl;
import org.openyu.cms.module.vo.Module;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class ModuleDaoImpl extends AppDaoSupporter implements ModuleDao
{
	private static transient final Logger log = LogManager.getLogger(ModuleDaoImpl.class);

	private static final String MODULE_PO_NAME = ModulePoImpl.class.getName();

	/**
	 * 查詢是否有效模組
	 * 
	 * @param valid
	 * @return
	 */
	public List<ModulePo> findModule(boolean valid)
	{
		return findModule(null, valid);
	}

	/**
	 * 查詢是否有效模組
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	public List<ModulePo> findModule(Locale locale, boolean valid)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(MODULE_PO_NAME + " ");
		hql.append("where 1=1 ");

		//valid
		if (valid)
		{
			hql.append("and valid=:valid ");
		}
		else
		{
			hql.append("and (valid=:valid ");
			hql.append("or valid is null) ");
		}
		params.put("valid", valid);
		//
		return findByHql(null, locale, hql, params);
	}

	/**
	 * 查詢預設模組
	 * 
	 * @return
	 */
	public ModulePo findDefaultModule()
	{
		StringBuilder hql = new StringBuilder();
		//
		hql.append("from ");
		hql.append(MODULE_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		hql.append("and dft=true ");

		return findUniqueByHql(hql);
	}

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<ModulePo> findModule(Locale locale, Module searcher)
	{
		return findModule(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<ModulePo> findModule(Inquiry inquiry, Locale locale, Module searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(MODULE_PO_NAME + " ");
		hql.append("where 1=1 ");

		if (searcher != null)
		{
			//seq
			if (searcher.getSeq() > 0)
			{
				hql.append("and seq=:seq ");
				params.put("seq", searcher.getSeq());
			}

			//id
			if (StringHelper.notBlank(searcher.getId()))
			{
				hql.append("and lower(id) like lower(:id) ");
				params.put("id", "%" + searcher.getId() + "%");
			}

			//valid
			WhetherOption validOption = searcher.getValidOption();
			if (validOption != null)
			{
				WhetherType whetherType = validOption.getId();
				switch (whetherType)
				{
					case TRUE:
						hql.append("and valid=true ");
						break;
					case FALSE:
						hql.append("and (valid=false ");
						hql.append("or valid is null) ");
						break;
					default:
						break;
				}
			}

			//name
			if (StringHelper.notBlank(searcher.getName()))
			{
				hql.append("and lower(names) like lower(:name)");
				params.put("name", "%" + searcher.getName() + "%");
			}
		}
		//
		return findByHql(inquiry, locale, hql, params);
	}

}

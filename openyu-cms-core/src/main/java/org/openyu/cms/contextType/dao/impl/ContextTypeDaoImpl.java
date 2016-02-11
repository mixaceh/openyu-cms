package org.openyu.cms.contextType.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.contextType.dao.ContextTypeDao;
import org.openyu.cms.contextType.po.ContextTypePo;
import org.openyu.cms.contextType.po.impl.ContextTypePoImpl;
import org.openyu.cms.contextType.vo.ContextType;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class ContextTypeDaoImpl extends AppDaoSupporter implements ContextTypeDao
{
	private static transient final Logger log = LogManager.getLogger(ContextTypeDaoImpl.class);

	private static final String CONTEXT_TTYPE_PO_NAME = ContextTypePoImpl.class.getName();

	/**
	 * 查詢是否有效本文類型
	 * 
	 * @param valid
	 * @return
	 */
	public List<ContextTypePo> findContextType(boolean valid)
	{
		return findContextType(null, valid);
	}

	/**
	 * 查詢是否有效本文類型
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	public List<ContextTypePo> findContextType(Locale locale, boolean valid)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(CONTEXT_TTYPE_PO_NAME + " ");
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
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<ContextTypePo> findContextType(Locale locale, ContextType searcher)
	{
		return findContextType(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<ContextTypePo> findContextType(Inquiry inquiry, Locale locale, ContextType searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(CONTEXT_TTYPE_PO_NAME + " ");
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

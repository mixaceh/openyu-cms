package org.openyu.cms.group.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.group.dao.GroupDao;
import org.openyu.cms.group.po.GroupPo;
import org.openyu.cms.group.po.impl.GroupPoImpl;
import org.openyu.cms.group.vo.Group;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class GroupDaoImpl extends AppDaoSupporter implements GroupDao
{
	private static transient final Logger log = LogManager.getLogger(GroupPoImpl.class);

	private static final String GROUP_PO_NAME = GroupPoImpl.class.getName();

	public List<GroupPo> findGroup()
	{
		return findGroup(null);
	}

	public List<GroupPo> findGroup(Locale locale)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		//
		hql.append("from ");
		hql.append(GROUP_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		return findByHql(null, locale, hql, params);
	}

	public GroupPo findDefaultGroup()
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		//
		hql.append("from ");
		hql.append(GROUP_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		hql.append("and dft=true ");

		return findUniqueByHql(hql, params);
	}

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<GroupPo> findGroup(Locale locale, Group searcher)
	{
		return findGroup(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<GroupPo> findGroup(Inquiry inquiry, Locale locale, Group searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(GROUP_PO_NAME + " ");
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

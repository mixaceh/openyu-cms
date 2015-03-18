package org.openyu.cms.tag.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.tag.dao.TagDao;
import org.openyu.cms.tag.po.TagPo;
import org.openyu.cms.tag.po.impl.TagPoImpl;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class TagDaoImpl extends AppDaoSupporter implements TagDao
{
	private static transient final Logger log = LogManager.getLogger(TagDaoImpl.class);

	private static final String TAG_PO_NAME = TagPoImpl.class.getName();

	/**
	 * 查詢標籤
	 * 
	 * @return
	 */
	public List<TagPo> findTag()
	{
		return findTag(null);
	}

	/**
	 * 查詢標籤
	 * 
	 * @param locale
	 * @return
	 */
	public List<TagPo> findTag(Locale locale)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(TAG_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		return findByHql(null, locale, hql, params);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<TagPo> findTag(Locale locale, Tag searcher)
	{
		return findTag(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<TagPo> findTag(Inquiry inquiry, Locale locale, Tag searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(TAG_PO_NAME + " ");
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
				if (searcher.isOnly())
				{
					hql.append("and id=:id ");//唯一
					params.put("id", searcher.getId());
				}
				else
				{
					hql.append("and lower(id) like lower(:id) ");
					params.put("id", "%" + searcher.getId() + "%");
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

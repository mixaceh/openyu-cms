package org.openyu.cms.tag.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.tag.po.TagPo;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface TagDao extends AppDao
{

	/**
	 * 查詢標籤
	 * 
	 * @return
	 */
	List<TagPo> findTag();

	/**
	 * 查詢標籤
	 * 
	 * @param locale
	 * @return
	 */
	List<TagPo> findTag(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<TagPo> findTag(Locale locale, Tag searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<TagPo> findTag(Inquiry inquiry, Locale locale, Tag searcher);

}
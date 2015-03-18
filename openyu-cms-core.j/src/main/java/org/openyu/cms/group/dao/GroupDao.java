package org.openyu.cms.group.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.group.po.GroupPo;
import org.openyu.cms.group.vo.Group;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface GroupDao extends AppDao
{
	/**
	 * 查詢群組
	 * 
	 * @return
	 */
	List<GroupPo> findGroup();

	/**
	 * 查詢群組
	 * 
	 * @param locale
	 * @return
	 */
	List<GroupPo> findGroup(Locale locale);

	/**
	 * 查詢是否預設群組
	 * 
	 * @return
	 */
	GroupPo findDefaultGroup();

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<GroupPo> findGroup(Locale locale, Group searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<GroupPo> findGroup(Inquiry inquiry, Locale locale, Group searcher);

}
package org.openyu.cms.group.service;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.service.AppService;
import org.openyu.cms.group.vo.Group;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface GroupService extends AppService
{

	/**
	 * 查詢群組
	 * 
	 * @return
	 */
	List<Group> findGroup();

	/**
	 * 查詢群組
	 * 
	 * @param locale
	 * @return
	 */
	List<Group> findGroup(Locale locale);

	/**
	 * 查詢是否預設群組
	 * 
	 * @return
	 */
	Group findDefaultGroup();

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Group> findGroup(Locale locale, Group searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Group> findGroup(Inquiry inquiry, Locale locale, Group searcher);

	// --------------------------------------------------

	/**
	 * 加入群組
	 * 
	 * @param group
	 * @return
	 */
	Group addGroup(Group group);

	/**
	 * 更新群組
	 * 
	 * @param origGroup
	 * @param newGroup
	 * @return
	 */
	Group updateGroup(Group origGroup, Group newGroup);

	/**
	 * 移除群組
	 * 
	 * @param groupId
	 * @return
	 */
	Group removeGroup(String groupId);

	/**
	 * 取得所有群組
	 * 
	 * @return
	 */
	List<Group> getGroups();

	// --------------------------------------------------
	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param groupId, DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	Group createGroup(String groupId);
}
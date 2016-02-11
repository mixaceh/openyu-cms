package org.openyu.cms.group.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.group.dao.GroupDao;
import org.openyu.cms.group.po.GroupPo;
import org.openyu.cms.group.service.GroupService;
import org.openyu.cms.group.vo.Group;
import org.openyu.cms.group.service.impl.GroupServiceImpl;
import org.openyu.cms.group.vo.GroupCollector;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class GroupServiceImpl extends AppServiceSupporter implements GroupService
{
	private static transient final Logger log = LogManager.getLogger(GroupServiceImpl.class);

	protected transient GroupCollector groupCollector = GroupCollector.getInstance();

	public GroupServiceImpl()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//初始化cache,<id,Group>
		List<Group> groups = findGroup();
		for (Group group : groups)
		{
			addGroup(group);
		}
	}

	public GroupDao getGroupDao()
	{
		return (GroupDao) getOjDao();
	}

	@Autowired
	@Qualifier("groupDao")
	public void setGroupDao(GroupDao groupDao)
	{
		setOjDao(groupDao);
	}

	public List<Group> findGroup()
	{
		List<GroupPo> orig = getGroupDao().findGroup();
		return ClassHelper.copyProperties(orig);
	}

	public List<Group> findGroup(Locale locale)
	{
		List<GroupPo> orig = getGroupDao().findGroup(locale);
		return ClassHelper.copyProperties(orig);
	}

	public Group findDefaultGroup()
	{
		GroupPo orig = getGroupDao().findDefaultGroup();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param group
	 * @return
	 */
	public List<Group> findGroup(Locale locale, Group searcher)
	{
		List<GroupPo> orig = getGroupDao().findGroup(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param group
	 * @return
	 */
	public List<Group> findGroup(Inquiry inquiry, Locale locale, Group searcher)
	{
		List<GroupPo> orig = getGroupDao().findGroup(inquiry, locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------
	/**
	 * 加入群組
	 * 
	 * @param group
	 * @return
	 */
	public Group addGroup(Group group)
	{
		Group result = null;
		if (group != null)
		{
			result = (Group) beans.put(group.getId(), group);
		}
		return result;
	}

	/**
	 * 更新群組
	 * 
	 * @param origGroup
	 * @param newGroup
	 * @return
	 */
	public synchronized Group updateGroup(Group origGroup, Group newGroup)
	{
		Group result = null;
		if (origGroup != null)
		{
			String groupId = origGroup.getId();
			boolean contains = beans.contains(groupId);
			if (contains)
			{
				result = removeGroup(groupId);
			}
			addGroup(newGroup);
		}
		return result;
	}

	/**
	 * 移除群組
	 * 
	 * @param groupId
	 * @return
	 */
	public Group removeGroup(String groupId)
	{
		Group result = null;
		if (groupId != null)
		{
			result = (Group) beans.remove(groupId);
		}
		return result;
	}

	/**
	 * 取得所有群組
	 * 
	 * @return
	 */
	public List<Group> getGroups()
	{
		List<Group> result = new LinkedList<Group>();
		for (Object entry : beans.getValues())
		{
			if (entry instanceof Group)
			{
				result.add((Group) entry);
			}
		}
		return result;
	}

	// --------------------------------------------------
	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param groupId, DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public Group createGroup(String groupId)
	{
		return groupCollector.createGroup(groupId);
	}
}

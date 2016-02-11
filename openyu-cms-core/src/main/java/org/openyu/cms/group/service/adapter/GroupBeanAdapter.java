package org.openyu.cms.group.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.event.supporter.AppBeanAdapter;
import org.openyu.cms.group.service.GroupService;
import org.openyu.cms.group.vo.Group;
import org.openyu.commons.lang.event.EventAttach;
import org.openyu.commons.service.event.BeanEvent;

/**
 * 群組
 * 
 * 事件監聽器, 當insert,update,delete,find時所觸發的事件處理
 */
public class GroupBeanAdapter extends AppBeanAdapter
{
	@Autowired
	@Qualifier("groupService")
	protected transient GroupService groupService;

	public GroupBeanAdapter()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//加入到service的監聽器中
		groupService.addBeanListener(this);
	}

	/**
	 * 新增後
	 * 
	 * @param beanEvent
	 */
	public void beanInserted(BeanEvent beanEvent)
	{
		@SuppressWarnings("unchecked")
		EventAttach<Object, Object> eventAttach = ((EventAttach<Object, Object>) beanEvent
				.getAttach());
		//
		Group newValue = (Group) eventAttach.getNewValue();
		//
		groupService.addGroup(newValue);
	}

	/**
	 * 修改後
	 * 
	 * @param beanEvent
	 */
	public void beanUpdated(BeanEvent beanEvent)
	{
		@SuppressWarnings("unchecked")
		EventAttach<Object, Object> eventAttach = ((EventAttach<Object, Object>) beanEvent
				.getAttach());
		//
		Group oldValue = (Group) eventAttach.getOldValue();
		Group newValue = (Group) eventAttach.getNewValue();
		//
		groupService.updateGroup(oldValue, newValue);
	}

	/**
	 * 刪除後
	 * 
	 * @param beanEvent
	 */
	public void beanDeleted(BeanEvent beanEvent)
	{
		@SuppressWarnings("unchecked")
		EventAttach<Object, Object> eventAttach = ((EventAttach<Object, Object>) beanEvent
				.getAttach());
		//
		Group newValue = (Group) eventAttach.getNewValue();
		//
		groupService.removeGroup(newValue.getId());
	}

}

package org.openyu.cms.contextType.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.event.supporter.AppBeanAdapter;
import org.openyu.cms.contextType.service.ContextTypeService;
import org.openyu.cms.contextType.vo.ContextType;
import org.openyu.commons.lang.event.EventAttach;
import org.openyu.commons.service.event.BeanEvent;

/**
 * 本文類型
 * 
 * 事件監聽器, 當insert,update,delete,find時所觸發的事件處理
 */
public class ContextTypeBeanAdapter extends AppBeanAdapter
{
	@Autowired
	@Qualifier("contextTypeService")
	protected transient ContextTypeService contextTypeService;

	public ContextTypeBeanAdapter()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//加入到service的監聽器中
		contextTypeService.addBeanListener(this);
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
		ContextType newValue = (ContextType) eventAttach.getNewValue();
		//
		contextTypeService.addContextType(newValue);
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
		ContextType oldValue = (ContextType) eventAttach.getOldValue();
		ContextType newValue = (ContextType) eventAttach.getNewValue();
		//
		contextTypeService.updateContextType(oldValue, newValue);
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
		ContextType newValue = (ContextType) eventAttach.getNewValue();
		//
		contextTypeService.removeContextType(newValue.getId());
	}

}

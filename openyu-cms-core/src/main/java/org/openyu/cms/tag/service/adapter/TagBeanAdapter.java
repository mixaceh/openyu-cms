package org.openyu.cms.tag.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.event.supporter.AppBeanAdapter;
import org.openyu.cms.tag.service.TagService;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.commons.lang.event.EventAttach;
import org.openyu.commons.service.event.BeanEvent;

/**
 * 標籤
 * 
 * 事件監聽器, 當insert,update,delete,find時所觸發的事件處理
 */
public class TagBeanAdapter extends AppBeanAdapter
{
	@Autowired
	@Qualifier("tagService")
	protected transient TagService tagService;

	public TagBeanAdapter()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//加入到service的監聽器中
		tagService.addBeanListener(this);
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
		Tag newValue = (Tag) eventAttach.getNewValue();
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
		Tag newValue = (Tag) eventAttach.getNewValue();
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
		Tag newValue = (Tag) eventAttach.getNewValue();
	}
}

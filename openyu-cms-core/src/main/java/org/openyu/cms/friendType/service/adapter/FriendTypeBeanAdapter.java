package org.openyu.cms.friendType.service.adapter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.event.supporter.AppBeanAdapter;
import org.openyu.cms.friendType.service.FriendTypeService;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.lang.event.EventAttach;
import org.openyu.commons.service.event.BeanEvent;

/**
 * 友情類型
 * 
 * 事件監聽器, 當insert,update,delete,find時所觸發的事件處理
 */
public class FriendTypeBeanAdapter extends AppBeanAdapter
{
	@Autowired
	@Qualifier("friendTypeService")
	protected transient FriendTypeService friendTypeService;

	public FriendTypeBeanAdapter()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//加入到service的監聽器中
		friendTypeService.addBeanListener(this);
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
		FriendType newValue = (FriendType) eventAttach.getNewValue();
		//網站別
		Site site = newValue.getSite();
		if (site != null)
		{
			friendTypeService.addFriendType(site.getId(), newValue);
		}
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
		FriendType newValue = (FriendType) eventAttach.getNewValue();
		//網站別
		Site site = newValue.getSite();
		if (site != null)
		{
			friendTypeService.addFriendType(site.getId(), newValue);
		}
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
		FriendType newValue = (FriendType) eventAttach.getNewValue();
		//網站別
		Site site = newValue.getSite();
		if (site != null)
		{
			friendTypeService.removeFriendType(site.getId(), newValue);
		}
	}
}

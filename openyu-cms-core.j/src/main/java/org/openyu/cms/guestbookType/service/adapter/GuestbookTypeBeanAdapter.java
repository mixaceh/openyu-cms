package org.openyu.cms.guestbookType.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.event.supporter.AppBeanAdapter;
import org.openyu.cms.guestbookType.service.GuestbookTypeService;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.lang.event.EventAttach;
import org.openyu.commons.service.event.BeanEvent;

/**
 * 留言類別
 * 
 * 事件監聽器, 當insert,update,delete,find時所觸發的事件處理
 */
public class GuestbookTypeBeanAdapter extends AppBeanAdapter
{
	@Autowired
	@Qualifier("guestbookTypeService")
	protected transient GuestbookTypeService guestbookTypeService;

	public GuestbookTypeBeanAdapter()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//加入到service的監聽器中
		guestbookTypeService.addBeanListener(this);
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
		GuestbookType newValue = (GuestbookType) eventAttach.getNewValue();
		//網站別
		Site site = newValue.getSite();
		if (site != null)
		{
			guestbookTypeService.addGuestbookType(site.getId(), newValue);
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
		GuestbookType newValue = (GuestbookType) eventAttach.getNewValue();
		//網站別
		Site site = newValue.getSite();
		if (site != null)
		{
			guestbookTypeService.addGuestbookType(site.getId(), newValue);
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
		GuestbookType newValue = (GuestbookType) eventAttach.getNewValue();
		//網站別
		Site site = newValue.getSite();
		if (site != null)
		{
			guestbookTypeService.removeGuestbookType(site.getId(), newValue);
		}
	}
}

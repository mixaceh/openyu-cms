package org.openyu.cms.adSpace.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.event.supporter.AppBeanAdapter;
import org.openyu.cms.adSpace.service.AdSpaceService;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.lang.event.EventAttach;
import org.openyu.commons.service.event.BeanEvent;

/**
 * 廣告版位
 * 
 * 事件監聽器, 當insert,update,delete,find時所觸發的事件處理
 */
public class AdSpaceBeanAdapter extends AppBeanAdapter
{
	@Autowired
	@Qualifier("adSpaceService")
	protected transient AdSpaceService adSpaceService;

	public AdSpaceBeanAdapter()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//加入到service的監聽器中
		adSpaceService.addBeanListener(this);
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
		AdSpace newValue = (AdSpace) eventAttach.getNewValue();
		//網站別
		Site site = newValue.getSite();
		if (site != null)
		{
			adSpaceService.addAdSpace(site.getId(), newValue);
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
		AdSpace newValue = (AdSpace) eventAttach.getNewValue();
		//網站別
		Site site = newValue.getSite();
		if (site != null)
		{
			adSpaceService.addAdSpace(site.getId(), newValue);
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
		AdSpace newValue = (AdSpace) eventAttach.getNewValue();
		//網站別
		Site site = newValue.getSite();
		if (site != null)
		{
			adSpaceService.removeAdSpace(site.getId(), newValue);
		}
	}
}

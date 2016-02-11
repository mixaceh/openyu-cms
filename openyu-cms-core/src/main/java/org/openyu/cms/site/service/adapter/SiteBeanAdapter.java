package org.openyu.cms.site.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.event.supporter.AppBeanAdapter;
import org.openyu.cms.site.service.SiteService;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.lang.event.EventAttach;
import org.openyu.commons.service.event.BeanEvent;

/**
 * 網站
 * 
 * 事件監聽器, 當insert,update,delete,find時所觸發的事件處理
 */
public class SiteBeanAdapter extends AppBeanAdapter
{
	@Autowired
	@Qualifier("siteService")
	protected transient SiteService siteService;

	public SiteBeanAdapter()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//加入到service的監聽器中
		siteService.addBeanListener(this);
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
		Site newValue = (Site) eventAttach.getNewValue();
		//
		siteService.addSite(newValue);
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
		Site oldValue = (Site) eventAttach.getOldValue();
		Site newValue = (Site) eventAttach.getNewValue();
		//
		siteService.updateSite(oldValue, newValue);
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
		Site newValue = (Site) eventAttach.getNewValue();
		//
		siteService.removeSite(newValue.getId());
	}

}

package org.openyu.cms.ftp.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.event.supporter.AppBeanAdapter;
import org.openyu.cms.ftp.service.FtpService;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.commons.lang.event.EventAttach;
import org.openyu.commons.service.event.BeanEvent;

/**
 * Ftp
 * 
 * 事件監聽器, 當insert,update,delete,find時所觸發的事件處理
 */
public class FtpBeanAdapter extends AppBeanAdapter
{
	@Autowired
	@Qualifier("ftpService")
	protected transient FtpService ftpService;

	public FtpBeanAdapter()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		//加入到service的監聽器中
		ftpService.addBeanListener(this);
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
		Ftp newValue = (Ftp) eventAttach.getNewValue();
		//
		ftpService.addFtp(newValue);
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
		Ftp oldValue = (Ftp) eventAttach.getOldValue();
		Ftp newValue = (Ftp) eventAttach.getNewValue();
		//
		ftpService.updateFtp(oldValue, newValue);
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
		Ftp newValue = (Ftp) eventAttach.getNewValue();
		//
		ftpService.removeFtp(newValue.getId());
	}
}

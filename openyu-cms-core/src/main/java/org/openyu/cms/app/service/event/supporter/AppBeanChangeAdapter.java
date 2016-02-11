package org.openyu.cms.app.service.event.supporter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.service.event.AppBeanChangeListener;
import org.openyu.commons.service.event.supporter.BeanChangeAdapter;

public abstract class AppBeanChangeAdapter extends BeanChangeAdapter implements
		AppBeanChangeListener
{
	private static transient final Logger log = LogManager.getLogger(AppBeanChangeAdapter.class);

	public AppBeanChangeAdapter()
	{}
}

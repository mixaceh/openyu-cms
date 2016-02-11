package org.openyu.cms.app.service.event.supporter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.service.event.AppServiceListener;
import org.openyu.commons.service.event.supporter.ServiceAdapter;

public abstract class AppServiceAdapter extends ServiceAdapter implements AppServiceListener
{
	private static transient final Logger log = LogManager.getLogger(AppServiceAdapter.class);

	public AppServiceAdapter()
	{}
}

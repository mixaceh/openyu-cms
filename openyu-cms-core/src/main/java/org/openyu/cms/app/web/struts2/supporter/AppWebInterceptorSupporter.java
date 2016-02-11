package org.openyu.cms.app.web.struts2.supporter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.web.struts2.AppWebInterceptor;
import org.openyu.commons.web.struts2.supporter.BaseWebInterceptorSupporter;

/**
 * app網頁攔截器
 */
public abstract class AppWebInterceptorSupporter extends BaseWebInterceptorSupporter implements
		AppWebInterceptor
{

	private static final long serialVersionUID = 6352813071069845801L;

	private static transient final Logger log = LogManager
			.getLogger(AppWebInterceptorSupporter.class);

	public AppWebInterceptorSupporter()
	{}

}

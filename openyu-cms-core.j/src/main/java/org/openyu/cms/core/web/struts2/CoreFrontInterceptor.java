package org.openyu.cms.core.web.struts2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.web.struts2.supporter.AppWebInterceptorSupporter;
import org.openyu.cms.site.service.SiteService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 前台攔截器
 */
public class CoreFrontInterceptor extends AppWebInterceptorSupporter
{

	private static final long serialVersionUID = -7928307051733214668L;

	private static transient final Logger log = LogManager.getLogger(CoreFrontInterceptor.class);

	/**
	 * 網站服務
	 */
	@Autowired
	@Qualifier("siteService")
	protected transient SiteService siteService;

	public CoreFrontInterceptor()
	{}

	public String intercept(ActionInvocation actionInvocation, ActionContext actionContext,
							Object action)
	{
		//傳回值
		String result = null;
		try
		{
			// --------------------------------------------------
			//invoke前
			// --------------------------------------------------
			//System.out.println("FrontCoreWebInterceptor invoke前 ");
			//System.out.println("CoreFrontInterceptor: " + getRequest().getRequestURL());

			// --------------------------------------------------
			result = actionInvocation.invoke();
			// --------------------------------------------------

			// --------------------------------------------------
			//invoke後
			// --------------------------------------------------
			//System.out.println("FrontCoreWebInterceptor invoke後 ");
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}

}

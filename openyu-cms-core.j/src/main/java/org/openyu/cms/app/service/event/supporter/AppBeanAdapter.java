package org.openyu.cms.app.service.event.supporter;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import org.openyu.cms.app.service.event.AppBeanListener;
import org.openyu.cms.user.vo.UserSession;
import org.openyu.commons.service.event.supporter.BeanAdapter;

/**
 * TODO 尚無法取得application,session,request,userSession
 */
public abstract class AppBeanAdapter extends BeanAdapter implements AppBeanListener,
		ServletRequestAware, SessionAware, ServletContextAware
{
	private static transient final Logger log = LogManager.getLogger(AppBeanAdapter.class);

	/**
	 * application
	 */
	protected ServletContext application;

	/**
	 * session
	 */
	protected Map<String, Object> session;

	/**
	 * request
	 */
	protected HttpServletRequest request;

	/**
	 * 使用者session
	 */
	private UserSession userSession;

	public AppBeanAdapter()
	{

	}

	public ServletContext getApplication()
	{
		return application;
	}

	public void setServletContext(ServletContext application)
	{
		this.application = application;
	}

	public Map<String, Object> getSession()
	{
		return session;
	}

	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public String getSessionId()
	{
		return request.getSession().getId();
	}

	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public UserSession getUserSession()
	{
		this.userSession = (UserSession) session.get(UserSession.KEY);
		return userSession;
	}

	public void setUserSession(UserSession userSession)
	{
		this.userSession = userSession;
	}
}

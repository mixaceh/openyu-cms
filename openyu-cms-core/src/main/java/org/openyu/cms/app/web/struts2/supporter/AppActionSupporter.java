package org.openyu.cms.app.web.struts2.supporter;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.web.struts2.AppAction;
import org.openyu.cms.site.service.SiteService;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.UserSession;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.cms.user.vo.impl.UserSessionImpl;
import org.openyu.commons.web.struts2.supporter.BaseActionSupporter;

/**
 * app控制器
 */
public class AppActionSupporter extends BaseActionSupporter implements AppAction
{

	private static final long serialVersionUID = 4343729739531231590L;

	private static transient final Logger log = LogManager.getLogger(AppActionSupporter.class);

	/**
	 * 使用者session
	 */
	protected UserSession userSession;

	/**
	 * 網站服務
	 */
	@Autowired
	@Qualifier("siteService")
	protected transient SiteService siteService;

	/**
	 * 選擇的網站 seq
	 */
	private long siteOptionSeq;

	public AppActionSupporter()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		super.initialize();

		//初始化使用者session
		this.userSession = (UserSession) session.get(UserSession.KEY);
		if (userSession == null)
		{
			this.userSession = new UserSessionImpl(this);
			session.put(UserSession.KEY, userSession);
		}

		//System.out.println("初始化session網站");
		//初始化session網站
		Site site = userSession.getSite();
		if (site == null)
		{
			//預設抓第一個網站
			Site firstSite = siteService.findFirstSite();
			if (firstSite != null)
			{
				userSession.setSite(firstSite);
			}
		}

		//選擇的網站 seq
		siteOptionSeq = userSession.getSiteSeq();

		//System.out.println("初始試使用者");
		//TODO 初始化測試使用者,等登入權限完成後,這會移除,頂著用先
		User user = new UserImpl();
		user.setSeq(1);
		user.setId("mockuser");
		user.addName(Locale.TRADITIONAL_CHINESE, "模擬使用者");
		user.addName(Locale.SIMPLIFIED_CHINESE, "模拟使用者");
		user.addName(Locale.US, "Mock User");

		//來自於getLocale();
		user.setSessionLocale(getLocale());
		//來自於session
		user.setSessionSite(site);
		//來自於request
		user.setRequestClientIp(getClientIp());
		user.setRequestClientPort(getClientPort());
		user.setRequestServerIp(getServerIp());
		user.setRequestServerPort(getServerPort());
		user.setRequestUri(request.getRequestURI().replace(request.getContextPath(), ""));//移除contextPath字串
		user.setRequestUrl(request.getRequestURL().toString());
		//
		userSession.setUser(user);
	}

	public UserSession getUserSession()
	{
		return userSession;
	}

	public void setUserSession(UserSession userSession)
	{
		this.userSession = userSession;
	}

	public long getSiteOptionSeq()
	{
		return siteOptionSeq;
	}

	public void setSiteOptionSeq(long siteOptionSeq)
	{
		this.siteOptionSeq = siteOptionSeq;
	}

	/**
	 * 網站選項
	 * 
	 * @return
	 */
	public List<Site> getSiteOptions()
	{
		return siteService.getSites();
	}

}

package org.openyu.cms.app.web.struts2.supporter;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.site.service.SiteService;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.UserSession;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.cms.user.vo.impl.UserSessionImpl;
import org.openyu.commons.web.struts2.supporter.ListActionSupporter;

/**
 * app列表控制器
 */
public class AppListActionSupporter extends ListActionSupporter
{

	private static final long serialVersionUID = -4020496071594366009L;

	private static transient final Logger log = LogManager.getLogger(AppListActionSupporter.class);

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

	/**
	 * 使用者session
	 */
	protected UserSession userSession;

	public AppListActionSupporter()
	{}

	/**
	 * 初始化
	 */
	public void initialize()
	{
		super.initialize();

		//System.out.println("初始化使用者session");
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

	/**
	 * 選擇網站
	 * 
	 * @return
	 */
	@Action(value = "selectSite")
	public String selectSite()
	{
		try
		{
			Site site = siteService.find(SiteImpl.class, siteOptionSeq);
			if (site != null)
			{
				//設定session網站
				userSession.setSite(site);
			}
			else
			{
				String[] msgArgs = new String[] { String.valueOf(siteOptionSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
			}
			//
			return execute();
		}
		catch (Exception ex)
		{
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 取得前台首頁網址
	 * 
	 * http://demo1.csm.com:8080/csm
	 * 
	 * http://demo1.csm.com/csm
	 * 
	 * @return
	 */
	public String getFrontUrl()
	{
		String result = null;
		Site site = userSession.getSite();
		if (site != null)
		{
			result = site.getProtocol() + site.getId()
					+ (getServerPort() == 80 ? "" : ":" + getServerPort())
					+ request.getContextPath();
		}
		return result;
	}
}

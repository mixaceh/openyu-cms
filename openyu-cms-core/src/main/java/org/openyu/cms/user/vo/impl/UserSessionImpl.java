package org.openyu.cms.user.vo.impl;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.FrontUser;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.UserSession;
import org.openyu.commons.bean.supporter.BaseBeanSupporter;
import org.openyu.commons.web.struts2.supporter.BaseActionSupporter;

/**
 * 使用者session,web用
 */
public class UserSessionImpl extends BaseBeanSupporter implements UserSession
{

	private static final long serialVersionUID = 6736537064386894816L;

	private static transient final Logger log = LogManager.getLogger(UserSessionImpl.class);

	private BaseActionSupporter aciton;

	/**
	 * 目前使用者
	 */
	private User user;

	/**
	 * 目前前台使用者
	 */
	private FrontUser frontUser;

	/**
	 * 目前網站
	 */
	private Site site;

	public UserSessionImpl(BaseActionSupporter aciton)
	{
		this.aciton = aciton;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * 取得目前使用者seq
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	public long getUserSeq()
	{
		long result = 0;
		if (user != null)
		{
			result = user.getSeq();
		}
		return result;
	}

	/**
	 * 取得目前使用者id
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	public String getUserId()
	{
		String result = null;
		if (user != null)
		{
			result = user.getId();
		}
		return result;
	}

	/**
	 * 取得目前使用者名稱
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	public String getUserName()
	{
		String result = null;
		if (user != null)
		{
			//依區域取名稱
			result = user.getName(aciton.getLocale());
		}
		return result;
	}

	public FrontUser getFrontUser()
	{
		return frontUser;
	}

	public void setFrontUser(FrontUser frontUser)
	{
		this.frontUser = frontUser;
	}

	/**
	 * 取得目前前台使用者seq
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	public long getFrontUserSeq()
	{
		long result = 0;
		if (frontUser != null)
		{
			result = frontUser.getSeq();
		}
		return result;
	}

	/**
	 * 取得目前前台使用者id
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	public String getFrontUserId()
	{
		String result = null;
		if (frontUser != null)
		{
			result = frontUser.getId();
		}
		return result;
	}

	/**
	 * 取得目前前台使用者名稱
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	public String getFrontUserName()
	{
		String result = null;
		if (frontUser != null)
		{
			result = frontUser.getName(aciton.getLocale());
		}
		return result;
	}

	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	/**
	 * 取得目前網站seq
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	public long getSiteSeq()
	{
		long result = 0;
		if (site != null)
		{
			result = site.getSeq();
		}
		return result;
	}

	/**
	 * 取得目前網站id
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	public String getSiteId()
	{
		String result = null;
		if (site != null)
		{
			result = site.getId();
		}
		return result;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("user", user);
		builder.append("frontUser", frontUser);
		builder.append("site", site);
		return builder.toString();
	}

	public Object clone()
	{
		UserSessionImpl copy = null;
		copy = (UserSessionImpl) super.clone();
		//
		copy.user = user;
		copy.frontUser = frontUser;
		copy.site = site;
		return copy;
	}
}

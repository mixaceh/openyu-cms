package org.openyu.cms.user.vo.impl;

import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImpl extends SeqIdAuditNamesBeanSupporter implements User
{

	private static final long serialVersionUID = -4848380800784528602L;

	// --------------------------------------------------
	//來自於web,不需應對entity
	// --------------------------------------------------
	// --------------------------------------------------
	//來自於session
	// --------------------------------------------------
	/**
	 * 區域,來自於session使用者所選取的locale
	 */
	@XmlTransient
	private Locale sessionLocale;

	/**
	 * 網站,來自於session使用者所選取的site
	 */
	@XmlTransient
	private Site sessionSite;

	// --------------------------------------------------
	//來自於request
	// --------------------------------------------------
	/**
	 * 請求客戶端ip
	 */
	@XmlTransient
	private String requestClientIp;

	/**
	 * 請求客戶端port
	 */
	@XmlTransient
	private int requestClientPort;

	/**
	 * 請求伺服器ip
	 */
	@XmlTransient
	private String requestServerIp;

	/**
	 * 請求伺服器port
	 */
	@XmlTransient
	private int requestServerPort;

	/**
	 * 請求uri,request.getRequestURI()
	 */
	@XmlTransient
	private String requestUri;

	/**
	 * 請求url,request.getRequestURL()
	 */
	@XmlTransient
	private String requestUrl;

	public UserImpl(String id)
	{
		setId(id);
	}

	public UserImpl()
	{
		this(null);
	}

	public UserImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	// --------------------------------------------------
	//來自於web,不需應對entity
	// --------------------------------------------------
	public Locale getSessionLocale()
	{
		return sessionLocale;
	}

	public void setSessionLocale(Locale sessionLocale)
	{
		this.sessionLocale = sessionLocale;
	}

	public Site getSessionSite()
	{
		return sessionSite;
	}

	public void setSessionSite(Site sessionSite)
	{
		this.sessionSite = sessionSite;
	}

	public long getSessionSiteSeq()
	{
		long result = 0;
		if (sessionSite != null)
		{
			result = sessionSite.getSeq();
		}
		return result;
	}

	public String getSessionSiteId()
	{
		String result = null;
		if (sessionSite != null)
		{
			result = sessionSite.getId();
		}
		return result;
	}

	public String getRequestClientIp()
	{
		return requestClientIp;
	}

	public void setRequestClientIp(String requestClientIp)
	{
		this.requestClientIp = requestClientIp;
	}

	public int getRequestClientPort()
	{
		return requestClientPort;
	}

	public void setRequestClientPort(int requestClientPort)
	{
		this.requestClientPort = requestClientPort;
	}

	public String getRequestServerIp()
	{
		return requestServerIp;
	}

	public void setRequestServerIp(String requestServerIp)
	{
		this.requestServerIp = requestServerIp;
	}

	public int getRequestServerPort()
	{
		return requestServerPort;
	}

	public void setRequestServerPort(int requestServerPort)
	{
		this.requestServerPort = requestServerPort;
	}

	public String getRequestUri()
	{
		return requestUri;
	}

	public void setRequestUri(String requestUri)
	{
		this.requestUri = requestUri;
	}

	public String getRequestUrl()
	{
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl)
	{
		this.requestUrl = requestUrl;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("sessionLocale", sessionLocale);
		builder.append("sessionSite", (sessionSite != null ? sessionSite.getSeq() + ", "
				+ sessionSite.getId() : null));
		builder.append("requestClientIp", requestClientIp);
		builder.append("requestClientPort", requestClientPort);
		builder.append("requestServerIp", requestServerIp);
		builder.append("requestServerPort", requestServerPort);
		builder.append("requestUri", requestUri);
		builder.append("requestUrl", requestUrl);
		return builder.toString();
	}

	public Object clone()
	{
		UserImpl copy = null;
		copy = (UserImpl) super.clone();
		return copy;
	}

}

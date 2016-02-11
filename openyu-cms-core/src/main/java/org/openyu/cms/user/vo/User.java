package org.openyu.cms.user.vo;

import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 使用者
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface User extends SeqIdAuditNamesBean
{
	String KEY = User.class.getName();

	// --------------------------------------------------
	//來自於web,不需應對entity
	//AppActionSupporter.initialize TODO 目前沒塞值
	//AppListActionSupporter.initialize
	// --------------------------------------------------

	// --------------------------------------------------
	//來自於session
	// --------------------------------------------------
	/**
	 * 區域,來自於session使用者所選取的locale
	 * 
	 * @return
	 */
	Locale getSessionLocale();

	void setSessionLocale(Locale sessionLocale);

	/**
	 * 網站,來自於session使用者所選取的site
	 * 
	 * @return
	 */
	Site getSessionSite();

	void setSessionSite(Site sessionSite);

	/**
	 * 網站seq,來自於session使用者所選取的site
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	long getSessionSiteSeq();

	/**
	 * 網站id,來自於session使用者所選取的site
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	String getSessionSiteId();

	// --------------------------------------------------
	//來自於request
	// --------------------------------------------------
	/**
	 * 請求客戶端ip
	 * 
	 * @return
	 */
	String getRequestClientIp();

	void setRequestClientIp(String requestClientIp);

	/**
	 * 請求客戶端port
	 * 
	 * @return
	 */
	int getRequestClientPort();

	void setRequestClientPort(int requestClientPort);

	/**
	 * 請求伺服器ip
	 * 
	 * @return
	 */
	String getRequestServerIp();

	void setRequestServerIp(String requestServerIp);

	/**
	 * 請求伺服器port
	 * 
	 * @return
	 */
	int getRequestServerPort();

	void setRequestServerPort(int requestServerPort);

	/**
	 * 請求uri,request.getRequestURI()
	 * 
	 * @return
	 */
	String getRequestUri();

	void setRequestUri(String requestUri);

	/**
	 * 請求url,request.getRequestURL()
	 * 
	 * @return
	 */
	String getRequestUrl();

	void setRequestUrl(String requestUrl);
}

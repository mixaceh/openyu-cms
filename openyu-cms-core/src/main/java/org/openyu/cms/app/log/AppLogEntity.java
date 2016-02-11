package org.openyu.cms.app.log;

import org.openyu.commons.entity.SeqLogEntity;

/**
 * log不做bean,直接用entity處理掉
 */
public interface AppLogEntity extends SeqLogEntity
{
	String KEY = AppLogEntity.class.getName();

	/**
	 * 使用者seq
	 * 
	 * @return
	 */
	Long getUserSeq();

	void setUserSeq(Long userSeq);

	/**
	 * 使用者id
	 * 
	 * @return
	 */
	String getUserId();

	void setUserId(String userId);

	/**
	 * 使用者名稱
	 * 
	 * @return
	 */
	String getUserName();

	void setUserName(String userName);

	/**
	 * 請求客戶端ip
	 * 
	 * @return
	 */
	String getClientIp();

	void setClientIp(String clientIp);

	/**
	 * 客戶端port
	 * 
	 * @return
	 */
	Integer getClientPort();

	void setClientPort(Integer clientPort);

	/**
	 * 請求伺服器ip
	 * 
	 * @return
	 */
	String getServerIp();

	void setServerIp(String serverIp);

	/**
	 * 伺服器port
	 * 
	 * @return
	 */
	Integer getServerPort();

	void setServerPort(Integer serverPort);

	/**
	 * 請求uri,request.getRequestURI()
	 * 
	 * @return
	 */
	String getUri();

	void setUri(String uri);

}

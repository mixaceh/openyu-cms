package org.openyu.cms.ftp.po;

import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * FTP
 */
public interface FtpPo extends SeqIdAuditNamesEntity
{
	String KEY = FtpPo.class.getName();

	/**
	 * 伺服器ip
	 * 
	 * @return
	 */
	String getIp();

	void setIp(String ip);

	/**
	 * FTP埠號
	 * 
	 * @return
	 */
	Integer getPort();

	void setPort(Integer port);

	/**
	 * 傳輸超時時間,秒數
	 * 
	 * @return
	 */
	Integer getTimeout();

	void setTimeout(Integer timeout);

	/**
	 * FTP帳號
	 * 
	 * @return
	 */
	String getAccount();

	void setAccount(String account);

	/**
	 * FTP密碼
	 * 
	 * @return
	 */
	String getPassword();

	void setPassword(String password);

	/**
	 * 遠端目錄
	 * 
	 * @return
	 */
	String getPath();

	void setPath(String path);

	/**
	 * 編碼
	 * 
	 * @return
	 */
	String getEncoding();

	void setEncoding(String encoding);

	/**
	 * 訪問URL
	 * 
	 * @return
	 */
	String getUrl();

	void setUrl(String url);

}
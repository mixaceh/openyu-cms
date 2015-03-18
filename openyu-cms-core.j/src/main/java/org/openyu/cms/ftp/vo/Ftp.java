package org.openyu.cms.ftp.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * FTP
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Ftp extends SeqIdAuditNamesBean
{
	String KEY = Ftp.class.getName();

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
	int getPort();

	void setPort(int port);

	/**
	 * 傳輸超時時間,秒數
	 * 
	 * @return
	 */
	int getTimeout();

	void setTimeout(int timeout);

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

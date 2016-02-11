package org.openyu.cms.ftp.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
/**
 * FTP
 */
@XmlRootElement(name = "ftp")
@XmlAccessorType(XmlAccessType.FIELD)
public class FtpImpl extends SeqIdAuditNamesBeanSupporter implements Ftp
{

	private static final long serialVersionUID = -3152230753869635942L;

	/**
	 * 伺服器ip
	 */
	private String ip;

	/**
	 * FTP埠號
	 */
	private int port;

	/**
	 * 傳輸超時時間,秒數
	 */
	private int timeout;

	/**
	 * FTP帳號
	 */
	private String account;

	/**
	 * FTP密碼
	 */
	private String password;

	/**
	 * 遠端目錄
	 */
	private String path;

	/**
	 * 編碼
	 */
	private String encoding;

	/**
	 * 訪問URL
	 */
	private String url;

	public FtpImpl(String id)
	{
		setId(id);
	}

	public FtpImpl()
	{
		this(null);
	}

	public FtpImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public int getTimeout()
	{
		return timeout;
	}

	public void setTimeout(int timeout)
	{
		this.timeout = timeout;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getEncoding()
	{
		return encoding;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("ip", ip);
		builder.append("port", port);
		builder.append("timeout", timeout);
		builder.append("account", account);
		builder.append("password", password);
		builder.append("path", path);
		builder.append("encoding", encoding);
		builder.append("url", url);
		return builder.toString();
	}

	public Object clone()
	{
		FtpImpl copy = null;
		copy = (FtpImpl) super.clone();
		return copy;
	}
}

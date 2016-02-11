package org.openyu.cms.ftp.po.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import org.openyu.cms.ftp.po.FtpPo;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;
//--------------------------------------------------
//hibernate
//--------------------------------------------------

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_ftp")
@SequenceGenerator(name = "cms_ftp_g", sequenceName = "cms_ftp_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.ftp.po.impl.FtpPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_ftp", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_ftp_1", columnNames = { "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class FtpPoImpl extends SeqIdAuditNamesEntitySupporter implements FtpPo
{

	private static final long serialVersionUID = 6695239257469451324L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 伺服器ip
	 */
	private String ip;

	/**
	 * FTP埠號
	 */
	private Integer port;

	/**
	 * 傳輸超時時間,秒數
	 */
	private Integer timeout;

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

	public FtpPoImpl()
	{}

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_ftp_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "ip", length = 30)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	@Column(name = "port")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getPort()
	{
		return port;
	}

	public void setPort(Integer port)
	{
		this.port = port;
	}

	@Column(name = "timeout")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getTimeout()
	{
		return timeout;
	}

	public void setTimeout(Integer timeout)
	{
		this.timeout = timeout;
	}

	@Column(name = "account", length = 30)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	@Column(name = "pass_word", length = 32)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Column(name = "path", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	@Column(name = "encoding", length = 20)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getEncoding()
	{
		return encoding;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	@Column(name = "url", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
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
		FtpPoImpl copy = null;
		copy = (FtpPoImpl) super.clone();
		return copy;
	}
}

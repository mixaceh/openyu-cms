package org.openyu.cms.app.log.supporter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.commons.entity.supporter.SeqLogEntitySupporter;

@MappedSuperclass
public abstract class AppLogEntitySupporter extends SeqLogEntitySupporter
		implements AppLogEntity {

	private static final long serialVersionUID = -4598280202074750416L;

	/**
	 * 使用者seq
	 */
	private Long userSeq;

	/**
	 * 使用者id
	 */
	private String userId;

	/**
	 * 使用者名稱
	 */
	private String userName;

	/**
	 * 請求客戶端ip
	 */
	private String clientIp;

	/**
	 * 客戶端port
	 */
	private Integer clientPort;

	/**
	 * 請求伺服器ip
	 */
	private String serverIp;

	/**
	 * 伺服器port
	 */
	private Integer serverPort;

	/**
	 * 請求uri,request.getRequestURI()
	 */
	private String uri;

	public AppLogEntitySupporter() {
	}

	@Column(name = "user_seq")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	@Column(name = "user_id", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "user_name", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "client_ip", length = 16)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@Column(name = "client_port")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getClientPort() {
		return clientPort;
	}

	public void setClientPort(Integer clientPort) {
		this.clientPort = clientPort;
	}

	@Column(name = "server_ip", length = 16)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	@Column(name = "server_port")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	@Column(name = "uri", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("userSeq", userSeq);
		builder.append("userId", userId);
		builder.append("userName", userName);
		builder.append("clientIp", clientIp);
		builder.append("clientPort", clientPort);
		builder.append("serverIp", serverIp);
		builder.append("serverPort", serverPort);
		builder.append("uri", uri);
		return builder.toString();
	}

	public Object clone() {
		AppLogEntitySupporter copy = null;
		copy = (AppLogEntitySupporter) super.clone();
		return copy;
	}
}

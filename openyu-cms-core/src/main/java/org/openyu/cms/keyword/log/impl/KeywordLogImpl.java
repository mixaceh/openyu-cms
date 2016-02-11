package org.openyu.cms.keyword.log.impl;

import java.util.Locale;

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
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import org.openyu.cms.app.log.supporter.AppLogEntitySupporter;
import org.openyu.cms.keyword.po.bridge.ActionTypeBridge;
import org.openyu.cms.keyword.po.bridge.DictionaryBridge;
import org.openyu.cms.keyword.vo.ActionType;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.log.KeywordLog;
import org.openyu.commons.hibernate.search.bridge.LocaleStringBridge;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_keyword_log")
@SequenceGenerator(name = "cms_keyword_log_g", sequenceName = "cms_keyword_log_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.keyword.log.impl.KeywordLogImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_keyword_log", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_keyword_log_1", columnNames = {
		"site_seq", "client_ip", "user_id", "log_date", "action_type" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class KeywordLogImpl extends AppLogEntitySupporter implements KeywordLog
{

	private static final long serialVersionUID = 4019884295335150274L;

	private Long seq;

	/**
	 * 網站seq
	 */
	private Long siteSeq;

	/**
	 * 區域
	 */
	private Locale locale;

	/**
	 * 關鍵字seq
	 */
	private Long keywordSeq;

	/**
	 * 關鍵字id
	 */
	private String keywordId;

	/**
	 * 操作類別
	 */
	private ActionType actionType;

	/**
	 * 改變前字典
	 */
	private Dictionary beforeDictionary;

	/**
	 * 改變後字典
	 */
	private Dictionary afterDictionary;

	public KeywordLogImpl()
	{}

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_keyword_log_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "site_seq")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Long getSiteSeq()
	{
		return siteSeq;
	}

	public void setSiteSeq(Long siteSeq)
	{
		this.siteSeq = siteSeq;
	}

	@Column(name = "locale", length = 20)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = LocaleStringBridge.class)
	public Locale getLocale()
	{
		return locale;
	}

	public void setLocale(Locale locale)
	{
		this.locale = locale;
	}

	@Column(name = "keyword_seq")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Long getKeywordSeq()
	{
		return keywordSeq;
	}

	public void setKeywordSeq(Long keywordSeq)
	{
		this.keywordSeq = keywordSeq;
	}

	@Column(name = "keyword_id", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getKeywordId()
	{
		return keywordId;
	}

	public void setKeywordId(String keywordId)
	{
		this.keywordId = keywordId;
	}

	@Column(name = "action_type", length = 13)
	@Type(type = "org.openyu.cms.keyword.po.userType.ActionTypeUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = ActionTypeBridge.class)
	public ActionType getActionType()
	{
		return actionType;
	}

	public void setActionType(ActionType actionType)
	{
		this.actionType = actionType;
	}

	@Column(name = "before_dictionary", length = 255)
	@Type(type = "org.openyu.cms.keyword.po.userType.DictionaryUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = DictionaryBridge.class)
	public Dictionary getBeforeDictionary()
	{
		return beforeDictionary;
	}

	public void setBeforeDictionary(Dictionary beforeDictionary)
	{
		this.beforeDictionary = beforeDictionary;
	}

	@Column(name = "after_dictionary", length = 255)
	@Type(type = "org.openyu.cms.keyword.po.userType.DictionaryUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = DictionaryBridge.class)
	public Dictionary getAfterDictionary()
	{
		return afterDictionary;
	}

	public void setAfterDictionary(Dictionary afterDictionary)
	{
		this.afterDictionary = afterDictionary;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("siteSeq", siteSeq);
		builder.append("locale", locale);
		builder.append("keywordSeq", keywordSeq);
		builder.append("keywordId", keywordId);
		builder.append("actionType", actionType);
		builder.append("beforeDictionary", beforeDictionary);
		builder.append("afterDictionary", afterDictionary);
		return builder.toString();
	}

	public Object clone()
	{
		KeywordLogImpl copy = null;
		copy = (KeywordLogImpl) super.clone();
		copy.locale = clone(locale);
		copy.actionType = clone(actionType);
		copy.beforeDictionary = clone(beforeDictionary);
		copy.afterDictionary = clone(afterDictionary);
		return copy;
	}
}

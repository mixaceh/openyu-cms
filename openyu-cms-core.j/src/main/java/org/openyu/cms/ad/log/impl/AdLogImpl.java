package org.openyu.cms.ad.log.impl;

import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
import org.openyu.cms.ad.po.bridge.ActionTypeBridge;
import org.openyu.cms.ad.vo.ActionType;
import org.openyu.cms.ad.log.AdLog;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.NamesEntity;
import org.openyu.commons.entity.bridge.NamesEntityBridge;
import org.openyu.commons.entity.supporter.NamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_ad_log")
@SequenceGenerator(name = "cms_ad_log_g", sequenceName = "cms_ad_log_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.ad.log.impl.AdLogImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_ad_log", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_ad_log_1", columnNames = {
		"site_seq", "client_ip", "user_id", "log_date", "action_type" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class AdLogImpl extends AppLogEntitySupporter implements AdLog
{

	private static final long serialVersionUID = 4019884295335150274L;

	private Long seq;

	/**
	 * 網站seq
	 */
	private Long siteSeq;

	/**
	 * 目錄seq
	 */
	private Long catalogSeq;

	/**
	 * 廣告seq
	 */
	private Long adSeq;

	/**
	 * 廣告id
	 */
	private String adId;

	/**
	 * 操作類別
	 */
	private ActionType actionType;

	/**
	 * 名稱
	 */
	private NamesEntity names = new NamesEntitySupporter();

	public AdLogImpl()
	{}

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_ad_log_g")
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

	@Column(name = "catalog_seq")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Long getCatalogSeq()
	{
		return catalogSeq;
	}

	public void setCatalogSeq(Long catalogSeq)
	{
		this.catalogSeq = catalogSeq;
	}

	@Column(name = "ad_seq")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Long getAdSeq()
	{
		return adSeq;
	}

	public void setAdSeq(Long adSeq)
	{
		this.adSeq = adSeq;
	}

	@Column(name = "ad_id", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getAdId()
	{
		return adId;
	}

	public void setAdId(String adId)
	{
		this.adId = adId;
	}

	@Column(name = "action_type", length = 13)
	@Type(type = "org.openyu.cms.ad.po.userType.ActionTypeUserType")
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

	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "names", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getNames()
	{
		return names.getNames();
	}

	public void setNames(Set<LocaleNameEntity> names)
	{
		this.names.setNames(names);
	}

	/**
	 * 
	 * 用locale作為equlas判斷條件,若有相同locale則不加入
	 * 
	 * @param locale
	 * @param name
	 * @return
	 */
	public boolean addName(Locale locale, String name)
	{
		return names.addName(locale, name);
	}

	public LocaleNameEntity getNameEntry(Locale locale)
	{
		return names.getNameEntry(locale);
	}

	public String getName(Locale locale)
	{
		return names.getName(locale);
	}

	public void setName(Locale locale, String name)
	{
		names.setName(locale, name);
	}

	public boolean removeName(Locale locale)
	{
		return names.removeName(locale);
	}

	@Transient
	public String getName()
	{
		return names.getName();
	}

	public void setName(String name)
	{
		names.setName(name);
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("siteSeq", siteSeq);
		builder.append("catalogSeq", catalogSeq);
		builder.append("adSeq", adSeq);
		builder.append("adId", adId);
		builder.append("actionType", actionType);
		append(builder, "names", names);
		return builder.toString();
	}

	public Object clone()
	{
		AdLogImpl copy = null;
		copy = (AdLogImpl) super.clone();
		//
		copy.names = clone(names);
		return copy;
	}
}

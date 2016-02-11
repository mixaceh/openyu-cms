package org.openyu.cms.sensitivity.po.impl;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

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

import org.openyu.cms.keyword.po.bridge.DictionarysBridge;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.sensitivity.po.SensitivityPo;
import org.openyu.commons.entity.supporter.SeqIdAuditEntitySupporter;
import org.openyu.commons.hibernate.search.bridge.LocaleStringBridge;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_sensitivity")
@SequenceGenerator(name = "cms_sensitivity_g", sequenceName = "cms_sensitivity_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.sensitivity.po.impl.SensitivityPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_sensitivity", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_sensitivity_1", columnNames = { "locale" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class SensitivityPoImpl extends SeqIdAuditEntitySupporter implements SensitivityPo
{

	private static final long serialVersionUID = -4081218627953897624L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 字典<敏感詞, 替換詞>
	 */
	private Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();

	/**
	 * 語系 Locale
	 */
	private Locale locale;

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_sensitivity_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "dictionarys", length = 65535)
	@Type(type = "org.openyu.cms.keyword.po.userType.StringDictionaryUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = DictionarysBridge.class)
	public Map<String, Dictionary> getDictionarys()
	{
		return dictionarys;
	}

	public void setDictionarys(Map<String, Dictionary> dictionarys)
	{
		this.dictionarys = dictionarys;
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

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("dictionarys", dictionarys);
		builder.append("locale", locale);
		return builder.toString();
	}

	public Object clone()
	{
		SensitivityPoImpl copy = null;
		copy = (SensitivityPoImpl) super.clone();
		copy.dictionarys = clone(dictionarys);
		copy.locale = clone(locale);
		return copy;
	}

}

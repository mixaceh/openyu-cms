package org.openyu.cms.keyword.po.impl;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import org.openyu.cms.keyword.po.KeywordPo;
import org.openyu.cms.keyword.po.bridge.DictionarysBridge;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.po.impl.SitePoImpl;
import org.openyu.commons.entity.supporter.SeqIdAuditEntitySupporter;
import org.openyu.commons.hibernate.search.bridge.LocaleStringBridge;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_keyword")
@SequenceGenerator(name = "cms_keyword_g", sequenceName = "cms_keyword_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.keyword.po.impl.KeywordPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_keyword", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_keyword_1", columnNames = { "locale" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class KeywordPoImpl extends SeqIdAuditEntitySupporter implements KeywordPo
{

	private static final long serialVersionUID = -639494932481766828L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 網站
	 */
	private SitePo site;

	/**
	 * 字典<關鍵字, 替換內容>
	 */
	private Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();

	/**
	 * 區域
	 */
	private Locale locale;

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_keyword_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(targetEntity = SitePoImpl.class, cascade = CascadeType.REFRESH)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "site_seq")
	@IndexedEmbedded(targetElement = SitePoImpl.class, depth = 1)
	public SitePo getSite()
	{
		return site;
	}

	public void setSite(SitePo site)
	{
		this.site = site;
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

	@Column(name = "dictionarys", length = 65535)
	//@Column(name = "dictionarys", length = 8192)
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

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("site", site);
		builder.append("dictionarys", dictionarys);
		builder.append("locale", locale);
		return builder.toString();
	}

	public Object clone()
	{
		KeywordPoImpl copy = null;
		copy = (KeywordPoImpl) super.clone();
		copy.site = clone(site);
		copy.locale = clone(locale);
		copy.dictionarys = clone(dictionarys);
		return copy;
	}

}

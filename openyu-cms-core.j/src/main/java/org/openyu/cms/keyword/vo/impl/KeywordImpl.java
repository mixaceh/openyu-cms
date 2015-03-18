package org.openyu.cms.keyword.vo.impl;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.keyword.vo.adapter.StringDictionaryXmlAdapter;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.commons.bean.supporter.SeqIdAuditBeanSupporter;
import org.openyu.commons.jaxb.adapter.LocaleXmlAdapter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "keyword")
@XmlAccessorType(XmlAccessType.FIELD)
public class KeywordImpl extends SeqIdAuditBeanSupporter implements Keyword
{

	private static final long serialVersionUID = 2042808702567663973L;

	/**
	 * 網站
	 */
	@XmlJavaTypeAdapter(SiteXmlAdapter.class)
	private Site site;

	/**
	 * 字典<關鍵字, 替換內容>
	 */
	@XmlJavaTypeAdapter(StringDictionaryXmlAdapter.class)
	private Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();

	/**
	 * 語系 Locale
	 */
	@XmlJavaTypeAdapter(LocaleXmlAdapter.class)
	private Locale locale;

	public KeywordImpl(String id)
	{
		setId(id);
	}

	public KeywordImpl()
	{
		this(null);
	}

	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public Map<String, Dictionary> getDictionarys()
	{
		return dictionarys;
	}

	public void setDictionarys(Map<String, Dictionary> dictionarys)
	{
		this.dictionarys = dictionarys;
	}

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
		builder.append("site", site);
		builder.append("dictionarys", dictionarys);
		builder.append("locale", locale);
		return builder.toString();
	}

	public Object clone()
	{
		KeywordImpl copy = null;
		copy = (KeywordImpl) super.clone();
		copy.site = clone(site);
		copy.dictionarys = clone(dictionarys);
		copy.locale = clone(locale);
		return copy;
	}
}

package org.openyu.cms.guestbookType.vo.impl;

import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.NamesBean;
import org.openyu.commons.bean.adapter.NamesBeanXmlAdapter;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "guestbookType")
@XmlAccessorType(XmlAccessType.FIELD)
public class GuestbookTypeImpl extends SeqIdAuditNamesBeanSupporter implements GuestbookType
{
	private static final long serialVersionUID = -5909945452318064152L;

	/**
	 * 排序
	 */
	private int sort;

	/**
	 * 描述
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean descriptions = new NamesBeanSupporter();

	/**
	 * 網站
	 */
	@XmlJavaTypeAdapter(SiteXmlAdapter.class)
	private Site site;

	public GuestbookTypeImpl(String id)
	{
		setId(id);
	}

	public GuestbookTypeImpl()
	{
		this(null);
	}

	public GuestbookTypeImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	public int getSort()
	{
		return sort;
	}

	public void setSort(int sort)
	{
		this.sort = sort;
	}

	public String getDescription()
	{
		return descriptions.getName();
	}

	public void setDescription(String description)
	{
		descriptions.setName(description);
	}

	public String getDescription(Locale locale)
	{
		return descriptions.getName(locale);
	}

	public void setDescription(Locale locale, String description)
	{
		descriptions.setName(locale, description);
	}

	public Set<LocaleNameBean> getDescriptions()
	{
		return descriptions.getNames();
	}

	public void setDescriptions(Set<LocaleNameBean> descriptions)
	{
		this.descriptions.setNames(descriptions);
	}

	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("sort", sort);
		append(builder, "descriptions", descriptions);
		builder.append("site", (site != null ? site.getSeq() + ", " + site.getId() : null));
		return builder.toString();
	}

	public Object clone()
	{
		GuestbookTypeImpl copy = null;
		copy = (GuestbookTypeImpl) super.clone();
		//
		copy.descriptions = clone(descriptions);
		copy.site = clone(site);
		return copy;
	}

}

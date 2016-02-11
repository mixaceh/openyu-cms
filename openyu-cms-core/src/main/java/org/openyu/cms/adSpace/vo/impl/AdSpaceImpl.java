package org.openyu.cms.adSpace.vo.impl;

import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.NamesBean;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.bean.adapter.NamesBeanXmlAdapter;
import org.openyu.commons.bean.impl.WhetherOptionImpl;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "adSpace")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdSpaceImpl extends SeqIdAuditNamesBeanSupporter implements AdSpace
{
	private static final long serialVersionUID = -7691405783030646263L;

	/**
	 * 是否有效
	 */
	private boolean valid;

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

	/**
	 * 搜尋用,是否啟用選項
	 */
	@XmlTransient
	private WhetherOption validOption = new WhetherOptionImpl(WhetherType.ALL);

	public AdSpaceImpl(String id)
	{
		setId(id);
	}

	public AdSpaceImpl()
	{
		this(null);
	}

	public AdSpaceImpl(long seq)
	{
		this(null);
		setSeq(seq);
	}

	public boolean getValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
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

	public WhetherOption getValidOption()
	{
		return validOption;
	}

	public void setValidOption(WhetherOption validOption)
	{
		this.validOption = validOption;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("valid", valid);
		append(builder, "descriptions", descriptions);
		builder.append("site", (site != null ? site.getSeq() + ", " + site.getId() : null));
		builder.append("validOption", validOption);
		return builder.toString();
	}

	public Object clone()
	{
		AdSpaceImpl copy = null;
		copy = (AdSpaceImpl) super.clone();
		//
		copy.descriptions = clone(descriptions);
		copy.site = clone(site);
		//
		copy.validOption = clone(validOption);
		return copy;
	}
}

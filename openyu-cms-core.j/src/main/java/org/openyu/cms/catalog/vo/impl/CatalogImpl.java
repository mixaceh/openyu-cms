package org.openyu.cms.catalog.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.catalog.vo.Catalog;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogImpl extends SeqIdAuditNamesBeanSupporter implements Catalog
{
	private static final long serialVersionUID = 1456016422894792281L;

	/**
	 * 網站
	 */
	@XmlJavaTypeAdapter(SiteXmlAdapter.class)
	private Site site;

	public CatalogImpl(String id)
	{
		setId(id);
	}

	public CatalogImpl()
	{
		this(null);
	}

	public CatalogImpl(long seq)
	{
		this(null);
		setSeq(seq);
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
		builder.append("site", (site != null ? site.getSeq() + ", " + site.getId() : null));
		return builder.toString();
	}

	public Object clone()
	{
		CatalogImpl copy = null;
		copy = (CatalogImpl) super.clone();
		copy.site = clone(site);
		return copy;
	}
}

package org.openyu.cms.role.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.role.vo.Role;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "role")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoleImpl extends SeqIdAuditNamesBeanSupporter implements Role
{
	/**
	 * 網站
	 */
	@XmlJavaTypeAdapter(SiteXmlAdapter.class)
	private Site site;

	private static final long serialVersionUID = -2104890043904825918L;

	public RoleImpl(String id)
	{
		setId(id);
	}

	public RoleImpl()
	{
		this(null);
	}

	public RoleImpl(long seq)
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
		RoleImpl copy = null;
		copy = (RoleImpl) super.clone();
		copy.site = clone(site);
		return copy;
	}
}

package org.openyu.cms.friendType.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "friendType")
@XmlAccessorType(XmlAccessType.FIELD)
public class FriendTypeImpl extends SeqIdAuditNamesBeanSupporter implements FriendType
{
	private static final long serialVersionUID = -1882718569449367895L;

	/**
	 * 排列順序
	 */
	private int sort;

	/**
	 * 網站
	 */
	@XmlJavaTypeAdapter(SiteXmlAdapter.class)
	private Site site;

	public FriendTypeImpl(String id)
	{
		setId(id);
	}

	public FriendTypeImpl()
	{
		this(null);
	}

	public FriendTypeImpl(long seq)
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
		builder.append("site", (site != null ? site.getSeq() + ", " + site.getId() : null));
		return builder.toString();
	}

	public Object clone()
	{
		FriendTypeImpl copy = null;
		copy = (FriendTypeImpl) super.clone();
		copy.site = clone(site);
		return copy;
	}

}

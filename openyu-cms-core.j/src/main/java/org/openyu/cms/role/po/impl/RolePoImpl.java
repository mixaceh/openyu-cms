package org.openyu.cms.role.po.impl;

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
import org.hibernate.search.annotations.IndexedEmbedded;

import org.openyu.cms.role.po.RolePo;
import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.po.impl.SitePoImpl;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_role")
@SequenceGenerator(name = "cms_role_g", sequenceName = "cms_role_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.role.po.impl.RolePoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_role", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_role_1", columnNames = { "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class RolePoImpl extends SeqIdAuditNamesEntitySupporter implements RolePo
{

	private static final long serialVersionUID = 2686590537541900593L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 網站
	 */
	private SitePo site;

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_role_g")
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

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("site", (site != null ? site.getSeq() + ", " + site.getId() : null));
		return builder.toString();
	}

	public Object clone()
	{
		RolePoImpl copy = null;
		copy = (RolePoImpl) super.clone();
		//
		copy.site = clone(site);
		return copy;
	}
}

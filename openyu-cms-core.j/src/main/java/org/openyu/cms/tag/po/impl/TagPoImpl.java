package org.openyu.cms.tag.po.impl;

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

import org.openyu.cms.tag.po.TagPo;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_tag")
@SequenceGenerator(name = "cms_tag_g", sequenceName = "cms_tag_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.tag.po.impl.TagPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_tag", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_tag_1", columnNames = { "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class TagPoImpl extends SeqIdAuditNamesEntitySupporter implements TagPo
{

	private static final long serialVersionUID = 701351446922632831L;

	/**
	 * seq
	 */
	private Long seq;

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_tag_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		return builder.toString();
	}

	public Object clone()
	{
		TagPoImpl copy = null;
		copy = (TagPoImpl) super.clone();
		return copy;
	}
}

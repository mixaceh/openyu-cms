package org.openyu.cms.contextType.po.impl;

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
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.openyu.cms.contextType.po.ContextTypePo;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_context_type")
@SequenceGenerator(name = "cms_context_type_g", sequenceName = "cms_context_type_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.contextType.po.impl.ContextTypePoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_context_type", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_context_type_1", columnNames = {
		"valid", "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class ContextTypePoImpl extends SeqIdAuditNamesEntitySupporter implements ContextTypePo
{
	private static final long serialVersionUID = 8067915665437042489L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 是否有效
	 */
	private Boolean valid;

	/**
	 * 圖片寬度
	 */
	private Integer imgWidth;

	/**
	 * 圖片高度
	 */
	private Integer imgHeight;

	/**
	 * 是否有圖片
	 */
	private Boolean image;

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_context_type_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "valid")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getValid()
	{
		return valid;
	}

	public void setValid(Boolean valid)
	{
		this.valid = valid;
	}

	@Column(name = "img_width")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getImgWidth()
	{
		return imgWidth;
	}

	public void setImgWidth(Integer imgWidth)
	{
		this.imgWidth = imgWidth;
	}

	@Column(name = "img_height")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getImgHeight()
	{
		return imgHeight;
	}

	public void setImgHeight(Integer imgHeight)
	{
		this.imgHeight = imgHeight;
	}

	@Column(name = "image")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getImage()
	{
		return image;
	}

	public void setImage(Boolean image)
	{
		this.image = image;
	}


	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("valid", valid);
		builder.append("imgWidth", imgWidth);
		builder.append("imgHeight", imgHeight);
		builder.append("image", image);
		return builder.toString();
	}

	public Object clone()
	{
		ContextTypePoImpl copy = null;
		copy = (ContextTypePoImpl) super.clone();
		return copy;
	}
}

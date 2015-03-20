package org.openyu.cms.group.po.impl;

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

import org.openyu.cms.group.po.GroupPo;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_group")
@SequenceGenerator(name = "cms_group_g", sequenceName = "cms_group_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.group.po.impl.GroupPoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_group", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_group_1", columnNames = {
		"sort", "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class GroupPoImpl extends SeqIdAuditNamesEntitySupporter implements GroupPo
{
	private static final long serialVersionUID = 3374989896892835478L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 排列順序
	 */
	private Integer sort;

	/**
	 * 每日允許上傳,單位(KB)
	 */
	private Integer dayUpload;

	/**
	 * 每個文件最大,單位(KB)
	 */
	private Integer maxUpload;

	/**
	 * 允許上傳的尾碼
	 */
	private String uploadSuffix;

	/**
	 * 是否評論驗證碼
	 */
	private Boolean captcha;

	/**
	 * 是否評論審核
	 */
	private Boolean check;

	/**
	 * 是否預設
	 */
	private Boolean dft;

	public GroupPoImpl()
	{}

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_group_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "sort")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getSort()
	{
		return sort;
	}

	public void setSort(Integer sort)
	{
		this.sort = sort;
	}

	@Column(name = "day_upload")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getDayUpload()
	{
		return dayUpload;
	}

	public void setDayUpload(Integer dayUpload)
	{
		this.dayUpload = dayUpload;
	}

	@Column(name = "max_upload")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getMaxUpload()
	{
		return maxUpload;
	}

	public void setMaxUpload(Integer maxUpload)
	{
		this.maxUpload = maxUpload;
	}

	@Column(name = "upload_suffix", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getUploadSuffix()
	{
		return uploadSuffix;
	}

	public void setUploadSuffix(String uploadSuffix)
	{
		this.uploadSuffix = uploadSuffix;
	}

	@Column(name = "captcha")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getCaptcha()
	{
		return captcha;
	}

	public void setCaptcha(Boolean captcha)
	{
		this.captcha = captcha;
	}

	@Column(name = "checkz")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getCheck()
	{
		return check;
	}

	public void setCheck(Boolean check)
	{
		this.check = check;
	}

	@Column(name = "dft")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getDft()
	{
		return dft;
	}

	public void setDft(Boolean dft)
	{
		this.dft = dft;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("sort", sort);
		builder.append("dayUpload", dayUpload);
		builder.append("maxUpload", maxUpload);
		builder.append("uploadSuffix", uploadSuffix);
		builder.append("captcha", captcha);
		builder.append("check", check);
		builder.append("dft", dft);
		return builder.toString();
	}

	public Object clone()
	{
		GroupPoImpl copy = null;
		copy = (GroupPoImpl) super.clone();
		return copy;
	}

}

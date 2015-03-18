package org.openyu.cms.template.log.impl;

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
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import org.openyu.cms.app.log.supporter.AppLogEntitySupporter;
import org.openyu.cms.archive.po.bridge.ActionTypeBridge;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.template.log.TemplateLog;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_template_log")
@SequenceGenerator(name = "cms_template_log_g", sequenceName = "cms_template_log_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.template.log.impl.TemplateLogImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_template_log", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_template_log_1", columnNames = {
		"site_seq", "client_ip", "user_id", "log_date", "action_type" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class TemplateLogImpl extends AppLogEntitySupporter implements TemplateLog
{

	private static final long serialVersionUID = 6312330128448080240L;

	private Long seq;

	/**
	 * 網站seq
	 */
	private Long siteSeq;

	/**
	 * 操作類別
	 */
	private ActionType actionType;

	/**
	 * 改變前完整檔案名稱
	 * 
	 * /custom/template/s1/default/css/style.css
	 */
	private String beforePath;

	/**
	 * 改變後完整檔案名稱
	 * 
	 * /custom/template/s1/default/css/style.css
	 */
	private String afterPath;

	public TemplateLogImpl()
	{}

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_template_log_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "site_seq")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Long getSiteSeq()
	{
		return siteSeq;
	}

	public void setSiteSeq(Long siteSeq)
	{
		this.siteSeq = siteSeq;
	}

	@Column(name = "action_type", length = 13)
	@Type(type = "org.openyu.cms.archive.po.userType.ActionTypeUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = ActionTypeBridge.class)
	public ActionType getActionType()
	{
		return actionType;
	}

	public void setActionType(ActionType actionType)
	{
		this.actionType = actionType;
	}

	@Column(name = "before_path", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getBeforePath()
	{
		return beforePath;
	}

	public void setBeforePath(String beforePath)
	{
		this.beforePath = beforePath;
	}

	@Column(name = "after_path", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getAfterPath()
	{
		return afterPath;
	}

	public void setAfterPath(String afterPath)
	{
		this.afterPath = afterPath;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("siteSeq", siteSeq);
		builder.append("actionType", actionType);
		builder.append("beforePath", beforePath);
		builder.append("afterPath", afterPath);
		return builder.toString();
	}

	public Object clone()
	{
		TemplateLogImpl copy = null;
		copy = (TemplateLogImpl) super.clone();
		copy.actionType = clone(actionType);
		return copy;
	}
}

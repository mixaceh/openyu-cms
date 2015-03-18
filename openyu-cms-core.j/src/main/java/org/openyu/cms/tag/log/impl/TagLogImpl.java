package org.openyu.cms.tag.log.impl;

import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
import org.openyu.cms.tag.po.bridge.ActionTypeBridge;
import org.openyu.cms.tag.vo.ActionType;
import org.openyu.cms.tag.log.TagLog;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.NamesEntity;
import org.openyu.commons.entity.bridge.NamesEntityBridge;
import org.openyu.commons.entity.supporter.NamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_tag_log")
@SequenceGenerator(name = "cms_tag_log_g", sequenceName = "cms_tag_log_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.tag.log.impl.TagLogImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_tag_log", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_tag_log_1", columnNames = {
		"client_ip", "user_id", "log_date", "action_type" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class TagLogImpl extends AppLogEntitySupporter implements TagLog
{

	private static final long serialVersionUID = 4019884295335150274L;

	private Long seq;

	/**
	 * 標籤seq
	 */
	private Long tagSeq;

	/**
	 * 標籤id
	 */
	private String tagId;

	/**
	 * 操作類別
	 */
	private ActionType actionType;

	/**
	 * 名稱
	 */
	private NamesEntity names = new NamesEntitySupporter();

	public TagLogImpl()
	{}

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_tag_log_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	@Column(name = "tag_seq")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Long getTagSeq()
	{
		return tagSeq;
	}

	public void setTagSeq(Long tagSeq)
	{
		this.tagSeq = tagSeq;
	}

	@Column(name = "tag_id", length = 255)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public String getTagId()
	{
		return tagId;
	}

	public void setTagId(String tagId)
	{
		this.tagId = tagId;
	}

	@Column(name = "action_type", length = 13)
	@Type(type = "org.openyu.cms.tag.po.userType.ActionTypeUserType")
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

	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "names", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getNames()
	{
		return names.getNames();
	}

	public void setNames(Set<LocaleNameEntity> names)
	{
		this.names.setNames(names);
	}

	/**
	 * 
	 * 用locale作為equlas判斷條件,若有相同locale則不加入
	 * 
	 * @param locale
	 * @param name
	 * @return
	 */
	public boolean addName(Locale locale, String name)
	{
		return names.addName(locale, name);
	}

	public LocaleNameEntity getNameEntry(Locale locale)
	{
		return names.getNameEntry(locale);
	}

	public String getName(Locale locale)
	{
		return names.getName(locale);
	}

	public void setName(Locale locale, String name)
	{
		names.setName(locale, name);
	}

	public boolean removeName(Locale locale)
	{
		return names.removeName(locale);
	}

	@Transient
	public String getName()
	{
		return names.getName();
	}

	public void setName(String name)
	{
		names.setName(name);
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("tagSeq", tagSeq);
		builder.append("tagId", tagId);
		builder.append("actionType", actionType);
		append(builder, "names", names);
		return builder.toString();
	}

	public Object clone()
	{
		TagLogImpl copy = null;
		copy = (TagLogImpl) super.clone();
		//
		copy.names = clone(names);
		return copy;
	}
}

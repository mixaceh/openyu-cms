package org.openyu.cms.vote.po.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.site.po.impl.SitePoImpl;
import org.openyu.cms.vote.po.VotePo;
import org.openyu.cms.vote.po.bridge.VoteItemsBridge;
import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.NamesEntity;
import org.openyu.commons.entity.bridge.NamesEntityBridge;
import org.openyu.commons.entity.supporter.NamesEntitySupporter;
import org.openyu.commons.entity.supporter.SeqIdAuditNamesEntitySupporter;

//--------------------------------------------------
//hibernate
//--------------------------------------------------
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "cms_vote")
@SequenceGenerator(name = "cms_vote_g", sequenceName = "cms_vote_s", allocationSize = 1)
//when use ehcache, config in ehcache.xml
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "org.openyu.cms.vote.po.impl.VotePoImpl")
@Proxy(lazy = false)
@org.hibernate.annotations.Table(appliesTo = "cms_vote", indexes = { @org.hibernate.annotations.Index(name = "idx_cms_vote_1", columnNames = {
		"valid", "id" }) })
//--------------------------------------------------
//search
//--------------------------------------------------
//@Indexed
public class VotePoImpl extends SeqIdAuditNamesEntitySupporter implements VotePo
{

	private static final long serialVersionUID = 3176865881702807605L;

	/**
	 * seq
	 */
	private Long seq;

	/**
	 * 標題 用(name)
	 */
	//	private String title;
	/**
	 * 描述
	 */
	private NamesEntity descriptions = new NamesEntitySupporter();

	/**
	 * 開始時間
	 */
	private Date startDate;

	/**
	 * 結束時間
	 */
	private Date endDate;

	/**
	 * 重複投票限制時間，單位小時，為空不允許重複投票
	 */
	private Integer repeateHour;

	/**
	 * 總投票數
	 */
	private Integer totalCount;

	/**
	 * 最多可以選擇幾項
	 */
	private Integer multiSelect;

	/**
	 * 是否限制會員
	 */
	private Boolean restrictMember;

	/**
	 * 是否限制IP
	 */
	private Boolean restrictIp;

	/**
	 * 是否限制COOKIE
	 */
	private Boolean restrictCookie;

	/**
	 * 是否禁用
	 */
	private Boolean valid;

	/**
	 * 是否預設主題
	 */
	private Boolean dft;

	/**
	 * 網站
	 */
	private SitePo site;

	/**
	 * 多個選項項目形成"投票模組"
	 */
	private Map<String, VoteItem> voteItems = new LinkedHashMap<String, VoteItem>();

	public VotePoImpl()
	{}

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cms_vote_g")
	public Long getSeq()
	{
		return seq;
	}

	public void setSeq(Long seq)
	{
		this.seq = seq;
	}

	//------------插入
	@Type(type = "org.openyu.commons.entity.userType.NamesEntityUserType")
	@Column(name = "descriptions", length = 2048)
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = NamesEntityBridge.class)
	public Set<LocaleNameEntity> getDescriptions()
	{
		return descriptions.getNames();
	}

	public void setDescriptions(Set<LocaleNameEntity> descriptions)
	{
		this.descriptions.setNames(descriptions);
	}

	@Transient
	public String getDescription()
	{
		return descriptions.getName();
	}

	public void setDescription(String description)
	{
		descriptions.setName(description);
	}

	@Transient
	public String getDescription(Locale locale)
	{
		return descriptions.getName(locale);
	}

	public void setDescription(Locale locale, String description)
	{
		descriptions.setName(locale, description);
	}

	@Column(name = "start_date")
	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	@Column(name = "end_Date")
	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	@Column(name = "repeate_hour")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getRepeateHour()
	{
		return repeateHour;
	}

	public void setRepeateHour(Integer repeateHour)
	{
		this.repeateHour = repeateHour;
	}

	@Column(name = "total_count")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(Integer totalCount)
	{
		this.totalCount = totalCount;
	}

	@Column(name = "multi_select")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Integer getMultiSelect()
	{
		return multiSelect;
	}

	public void setMultiSelect(Integer multiSelect)
	{
		this.multiSelect = multiSelect;
	}

	@Column(name = "restrict_member")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getRestrictMember()
	{
		return restrictMember;
	}

	public void setRestrictMember(Boolean restrictMember)
	{
		this.restrictMember = restrictMember;
	}

	@Column(name = "restrict_ip")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getRestrictIp()
	{
		return restrictIp;
	}

	public void setRestrictIp(Boolean restrictIp)
	{
		this.restrictIp = restrictIp;
	}

	@Column(name = "restrict_cookie")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	public Boolean getRestrictCookie()
	{
		return restrictCookie;
	}

	public void setRestrictCookie(Boolean restrictCookie)
	{
		this.restrictCookie = restrictCookie;
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

	@Column(name = "vote_items", length = 8192)
	@Type(type = "org.openyu.cms.vote.po.userType.VoteItemsUserType")
	@Field(store = Store.YES, index = Index.YES, analyze = Analyze.NO)
	@FieldBridge(impl = VoteItemsBridge.class)
	public Map<String, VoteItem> getVoteItems()
	{
		return voteItems;
	}

	public void setVoteItems(Map<String, VoteItem> voteItems)
	{
		this.voteItems = voteItems;
	}
	

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("descriptions", descriptions);
		builder.append("startDate", startDate);
		builder.append("endDate", endDate);
		builder.append("repeateHour", repeateHour);
		builder.append("totalCount", totalCount);
		builder.append("multiSelect", multiSelect);
		builder.append("restrictMember", restrictMember);
		builder.append("restrictIp", restrictIp);
		builder.append("restrictCookie", restrictCookie);
		builder.append("valid", valid);
		builder.append("dft", dft);
		builder.append("site", site);
		builder.append("voteItems", voteItems);
		return builder.toString();
	}

	

	public Object clone()
	{
		VotePoImpl copy = null;
		copy = (VotePoImpl) super.clone();
		copy.descriptions = clone(descriptions);
		copy.site = clone(site);
		copy.voteItems = clone(voteItems);
		return copy;
	}
}

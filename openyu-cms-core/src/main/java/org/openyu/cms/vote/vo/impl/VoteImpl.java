package org.openyu.cms.vote.vo.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.adapter.SiteXmlAdapter;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.cms.vote.vo.adapter.StringVoteItemXmlAdapter;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.NamesBean;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.bean.adapter.NamesBeanXmlAdapter;
import org.openyu.commons.bean.impl.WhetherOptionImpl;
import org.openyu.commons.bean.supporter.NamesBeanSupporter;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "vote")
@XmlAccessorType(XmlAccessType.FIELD)
public class VoteImpl extends SeqIdAuditNamesBeanSupporter implements Vote
{
	private static final long serialVersionUID = -1598050492851804695L;

	/**
	 * 描述
	 */
	@XmlJavaTypeAdapter(NamesBeanXmlAdapter.class)
	private NamesBean descriptions = new NamesBeanSupporter();

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
	private int repeateHour;

	/**
	 * 總投票數
	 */
	private int totalCount;

	/**
	 * 最多可以選擇幾項
	 */
	private int multiSelect;

	/**
	 * 是否限制會員
	 */
	private boolean restrictMember;

	/**
	 * 是否限制IP
	 */
	private boolean restrictIp;

	/**
	 * 是否限制COOKIE
	 */
	private boolean restrictCookie;

	/**
	 * 是否禁用
	 */
	private boolean valid;

	/**
	 * 是否預設主題
	 */
	private boolean defaultz;

	/**
	 * 網站
	 */
	@XmlJavaTypeAdapter(SiteXmlAdapter.class)
	private Site site;

	/**
	 * 多個選項項目形成"投票模組"
	 */
	@XmlJavaTypeAdapter(StringVoteItemXmlAdapter.class)
	private Map<String, VoteItem> voteItems = new LinkedHashMap<String, VoteItem>();

	/**
	 * 搜尋用,是否啟用選項
	 */
	@XmlTransient
	private WhetherOption validOption = new WhetherOptionImpl(WhetherType.ALL);

	public VoteImpl(String id)
	{
		setId(id);
	}

	public VoteImpl()
	{
		this(null);
	}

	public String getDescription()
	{
		return descriptions.getName();
	}

	public void setDescription(String description)
	{
		descriptions.setName(description);
	}

	public String getDescription(Locale locale)
	{
		return descriptions.getName(locale);
	}

	public void setDescription(Locale locale, String description)
	{
		descriptions.setName(locale, description);
	}

	public Set<LocaleNameBean> getDescriptions()
	{
		return descriptions.getNames();
	}

	public void setDescriptions(Set<LocaleNameBean> descriptions)
	{
		this.descriptions.setNames(descriptions);
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public int getRepeateHour()
	{
		return repeateHour;
	}

	public void setRepeateHour(int repeateHour)
	{
		this.repeateHour = repeateHour;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}

	public int getMultiSelect()
	{
		return multiSelect;
	}

	public void setMultiSelect(int multiSelect)
	{
		this.multiSelect = multiSelect;
	}

	public boolean getRestrictMember()
	{
		return restrictMember;
	}

	public void setRestrictMember(boolean restrictMember)
	{
		this.restrictMember = restrictMember;
	}

	public boolean getRestrictIp()
	{
		return restrictIp;
	}

	public void setRestrictIp(boolean restrictIp)
	{
		this.restrictIp = restrictIp;
	}

	public boolean getRestrictCookie()
	{
		return restrictCookie;
	}

	public void setRestrictCookie(boolean restrictCookie)
	{
		this.restrictCookie = restrictCookie;
	}

	public boolean getValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public boolean getDefaultz()
	{
		return defaultz;
	}

	public void setDefaultz(boolean defaultz)
	{
		this.defaultz = defaultz;
	}

	public Site getSite()
	{
		return site;
	}

	public void setSite(Site site)
	{
		this.site = site;
	}

	public Map<String, VoteItem> getVoteItems()
	{
		return voteItems;
	}

	public void setVoteItems(Map<String, VoteItem> voteItems)
	{
		this.voteItems = voteItems;
	}

	public WhetherOption getValidOption()
	{
		return validOption;
	}

	public void setValidOption(WhetherOption validOption)
	{
		this.validOption = validOption;
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
		builder.append("defaultz", defaultz);
		builder.append("site", site);
		builder.append("voteItems", voteItems);
		return builder.toString();
	}

	public Object clone()
	{
		VoteImpl copy = null;
		copy = (VoteImpl) super.clone();
		copy.descriptions = clone(descriptions);
		copy.site = clone(site);
		copy.voteItems = clone(voteItems);
		//
		copy.validOption = clone(validOption);
		return copy;
	}

}

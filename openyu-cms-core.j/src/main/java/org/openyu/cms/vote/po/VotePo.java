package org.openyu.cms.vote.po;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.openyu.cms.site.po.SitePo;
import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.commons.entity.LocaleNameEntity;
import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 投票
 */
public interface VotePo extends SeqIdAuditNamesEntity
{
	String KEY = VotePo.class.getName();

	/**
	 * 描述
	 * @return
	 */
	Set<LocaleNameEntity> getDescriptions();

	void setDescriptions(Set<LocaleNameEntity> descriptions);

	String getDescription();

	void setDescription(String description);

	String getDescription(Locale locale);

	void setDescription(Locale locale, String description);

	/**
	 * 開始時間
	 * @return
	 */
	Date getStartDate();

	void setStartDate(Date startDate);

	/**
	 * 結束時間
	 * @return
	 */
	Date getEndDate();

	void setEndDate(Date endDate);

	/**
	 *  重複投票限制時間，單位小時，為空不允許重複投票
	 * @return
	 */
	Integer getRepeateHour();

	void setRepeateHour(Integer repeateHour);

	/**
	 * 總投票數
	 * @return
	 */
	Integer getTotalCount();

	void setTotalCount(Integer totalCount);

	/**
	 * 最多可以選擇幾項
	 * @return
	 */
	Integer getMultiSelect();

	void setMultiSelect(Integer multiSelect);

	/**
	 * 是否限制會員
	 * @return
	 */
	Boolean getRestrictMember();

	void setRestrictMember(Boolean restrictMember);

	/**
	 *  是否限制IP
	 * @return
	 */
	Boolean getRestrictIp();

	void setRestrictIp(Boolean restrictIp);

	/**
	 * 是否限制COOKIE
	 * @return
	 */
	Boolean getRestrictCookie();

	void setRestrictCookie(Boolean restrictCookie);

	/**
	 *  是否禁用
	 * 
	 * @return
	 */
	Boolean getValid();

	void setValid(Boolean valid);
	
	/**
	 * 是否預設主題
	 * @return
	 */
	Boolean getDft();

	void setDft(Boolean dft);

	/**
	 * 網站
	 * @return
	 */
	SitePo getSite();

	void setSite(SitePo site);

	/**
	 * 多個選項項目形成"投票模組"
	 * @return
	 */
	Map<String, VoteItem> getVoteItems();

	void setVoteItems(Map<String, VoteItem> voteItems);

}
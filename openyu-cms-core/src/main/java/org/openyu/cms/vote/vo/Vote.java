package org.openyu.cms.vote.vo;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.LocaleNameBean;
import org.openyu.commons.bean.SeqIdAuditNamesBean;
import org.openyu.commons.bean.WhetherOption;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 投票
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Vote extends SeqIdAuditNamesBean
{
	String KEY = Vote.class.getName();

	/**
	 * 描述
	 * 
	 * @return
	 */
	String getDescription();

	void setDescription(String description);

	String getDescription(Locale locale);

	void setDescription(Locale locale, String description);

	Set<LocaleNameBean> getDescriptions();

	void setDescriptions(Set<LocaleNameBean> descriptions);
	

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
	int getRepeateHour();

	void setRepeateHour(int repeateHour);

	/**
	 * 總投票數
	 * @return
	 */
	int getTotalCount();

	void setTotalCount(int totalCount);

	/**
	 * 最多可以選擇幾項
	 * @return
	 */
	int getMultiSelect();

	void setMultiSelect(int multiSelect);

	/**
	 * 是否限制會員
	 * @return
	 */
	boolean getRestrictMember();

	void setRestrictMember(boolean restrictMember);

	/**
	 *  是否限制IP
	 * @return
	 */
	boolean getRestrictIp();

	void setRestrictIp(boolean restrictIp);

	/**
	 * 是否限制COOKIE
	 * @return
	 */
	boolean getRestrictCookie();

	void setRestrictCookie(boolean restrictCookie);

	/**
	 *  是否禁用
	 * 
	 * @return
	 */
	boolean getValid();

	void setValid(boolean valid);
	
	/**
	 * 是否預設主題
	 * @return
	 */
	boolean getDefaultz();

	void setDefaultz(boolean defaultz);

	/**
	 * 網站
	 * @return
	 */
	Site getSite();

	void setSite(Site site);

	/**
	 * 多個選項項目形成"投票模組"
	 * @return
	 */
	Map<String, VoteItem> getVoteItems();

	void setVoteItems(Map<String, VoteItem> voteItems);
	
	/**
	 * 搜尋用,是否啟用選項
	 * 
	 * @return
	 */
	WhetherOption getValidOption();

	void setValidOption(WhetherOption validOption);
}
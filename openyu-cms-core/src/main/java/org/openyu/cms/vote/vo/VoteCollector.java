package org.openyu.cms.vote.vo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.vote.vo.ActionOption;
import org.openyu.cms.vote.vo.impl.ActionOptionImpl;
import org.openyu.cms.vote.vo.adapter.StringVoteXmlAdapter;
import org.openyu.cms.vote.vo.impl.VoteImpl;
import org.openyu.commons.bean.supporter.BaseCollectorSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.io.IoHelper;
import org.openyu.commons.lang.StringHelper;

/**
 * 1.處理靜態資料,放的是vo的資料,並非po資料
 */
// --------------------------------------------------
// jaxb
// --------------------------------------------------
@XmlRootElement(name = "voteCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class VoteCollector extends BaseCollectorSupporter {

	private static final long serialVersionUID = -4194210395796268482L;

	String KEY = VoteCollector.class.getName();

	private static transient final Logger log = LogManager
			.getLogger(VoteCollector.class);

	private static VoteCollector voteCollector;

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設投票
	 */
	private String defaultVote = "DEFAULT";

	/**
	 * 所有投票
	 * 
	 * <id,vote>
	 */
	@XmlJavaTypeAdapter(StringVoteXmlAdapter.class)
	private Map<String, Vote> votes = new LinkedHashMap<String, Vote>();

	/**
	 * 連結目標選項
	 */
	private List<String> targetOptions = new LinkedList<String>();

	/**
	 * 操作(-1/1/2/3)選項
	 */
	@XmlElement(type = ActionOptionImpl.class)
	private List<ActionOption> actionOptions = new LinkedList<ActionOption>();

	/**
	 * log查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry logInquiry;

	public VoteCollector() {
		setId(VoteCollector.class.getName());
	}

	public synchronized static VoteCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static VoteCollector getInstance(boolean initial) {
		if (voteCollector == null) {
			voteCollector = new VoteCollector();
			if (initial) {
				voteCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
		}
		return voteCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!voteCollector.isInitialized()) {
			voteCollector = readFromSer(VoteCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (voteCollector == null) {
				voteCollector = new VoteCollector();
			}
			//
			voteCollector.setInitialized(true);
		}
	}

	public void clear() {
		votes.clear();
		targetOptions.clear();
		actionOptions.clear();
		// 設為可初始化
		setInitialized(false);
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public String getDefaultVote() {
		return defaultVote;
	}

	public void setDefaultVote(String defaultVote) {
		this.defaultVote = defaultVote;
	}

	public Map<String, Vote> getVotes() {
		if (votes == null) {
			votes = new LinkedHashMap<String, Vote>();
		}
		return votes;
	}

	public void setVotes(Map<String, Vote> votes) {
		this.votes = votes;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------

	/**
	 * 連結目標選項
	 * 
	 * @return
	 */
	public List<String> getTargetOptions() {
		if (targetOptions == null) {
			targetOptions = new LinkedList<String>();
		}
		return targetOptions;
	}

	public void setTargetOptions(List<String> targetOptions) {
		this.targetOptions = targetOptions;
	}

	/**
	 * 操作(-1/1/2/3)選項
	 * 
	 * @return
	 */
	public List<ActionOption> getActionOptions() {
		if (actionOptions == null) {
			actionOptions = new LinkedList<ActionOption>();
		}
		return actionOptions;
	}

	public void setActionOptions(List<ActionOption> actionOptions) {
		this.actionOptions = actionOptions;
	}

	/**
	 * 取得,操作(-1/1/2/3)選項名稱
	 * 
	 * @param value
	 *            , Action.getId().getValue()
	 * @param locale
	 * @return
	 */
	public String getActionName(int value, Locale locale) {
		String result = null;
		for (ActionOption entry : actionOptions) {
			if (entry == null) {
				continue;
			}
			if (entry.getId().getValue() == value) {
				result = entry.getName(locale);
				break;
			}
		}
		return result;
	}

	public Inquiry getLogInquiry() {
		return logInquiry;
	}

	public void setLogInquiry(Inquiry logInquiry) {
		this.logInquiry = logInquiry;
	}

	// --------------------------------------------------
	/**
	 * 建構查詢條件
	 * 
	 * @return
	 */
	public Inquiry createInquiry() {
		return (inquiry != null ? (Inquiry) inquiry.clone() : null);
	}

	/**
	 * 取得clone投票
	 * 
	 * @param id
	 * @return
	 */
	public Vote getVote(String id) {
		Vote result = null;
		if (id != null) {
			result = votes.get(id);
		}
		return (result != null ? (Vote) result.clone() : null);
	}

	public Vote createVote() {
		return createVote(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Vote createVote(String id) {
		Vote result = null;
		// 來自靜態資料的clone副本
		result = getVote(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new VoteImpl(StringHelper.randomUnique());// 1361579JmbDESVea
		} else {
			// xmlId_randomUnique
			result.setId(id + "_" + StringHelper.randomUnique());// DEFAULT_1361579JmbDESVea
		}
		return result;
	}

	/**
	 * 建構log查詢條件
	 * 
	 * @return
	 */
	public Inquiry createLogInquiry() {
		return (logInquiry != null ? (Inquiry) logInquiry.clone() : null);
	}
}
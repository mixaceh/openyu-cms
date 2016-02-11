package org.openyu.cms.topic.vo;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.topic.vo.ActionOption;
import org.openyu.cms.topic.vo.ActionType;
import org.openyu.cms.topic.vo.adapter.ActionTypeXmlAdapter;
import org.openyu.cms.topic.vo.impl.ActionOptionImpl;
import org.openyu.cms.topic.vo.adapter.StringTopicXmlAdapter;
import org.openyu.cms.topic.vo.impl.TopicImpl;
import org.openyu.commons.bean.supporter.BaseCollectorSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.enumz.EnumHelper;
import org.openyu.commons.lang.StringHelper;

/**
 * 1.處理靜態資料,放的是vo的資料,並非po資料
 */
// --------------------------------------------------
// jaxb
// --------------------------------------------------
@XmlRootElement(name = "topicCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class TopicCollector extends BaseCollectorSupporter {
	private static final long serialVersionUID = 1795612055199656968L;

	private static TopicCollector topicCollector;

	// --------------------------------------------------
	// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
	// --------------------------------------------------
	/**
	 * 操作類別
	 */
	@XmlJavaTypeAdapter(ActionTypeXmlAdapter.class)
	private Set<ActionType> actionTypes = new LinkedHashSet<ActionType>();

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設專題
	 */
	private String defaultTopic = "DEFAULT";

	/**
	 * 所有專題
	 * 
	 * <id,topic>
	 */
	@XmlJavaTypeAdapter(StringTopicXmlAdapter.class)
	private Map<String, Topic> topics = new LinkedHashMap<String, Topic>();

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

	public TopicCollector() {
		setId(TopicCollector.class.getName());
	}

	public synchronized static TopicCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static TopicCollector getInstance(boolean initial) {
		if (topicCollector == null) {
			topicCollector = new TopicCollector();
			if (initial) {
				topicCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
			topicCollector.actionTypes = EnumHelper.valuesSet(ActionType.class);
		}
		return topicCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!topicCollector.isInitialized()) {
			topicCollector = readFromSer(TopicCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (topicCollector == null) {
				topicCollector = new TopicCollector();
			}
			//
			topicCollector.setInitialized(true);
		}
	}

	public void clear() {
		topics.clear();
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

	public String getDefaultTopic() {
		return defaultTopic;
	}

	public void setDefaultTopic(String defaultTopic) {
		this.defaultTopic = defaultTopic;
	}

	public Map<String, Topic> getTopics() {
		if (topics == null) {
			topics = new LinkedHashMap<String, Topic>();
		}
		return topics;
	}

	public void setTopics(Map<String, Topic> topics) {
		this.topics = topics;
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
	 * 取得clone專題
	 * 
	 * @param id
	 * @return
	 */
	public Topic getTopic(String id) {
		Topic result = null;
		if (id != null) {
			result = topics.get(id);
		}
		return (result != null ? (Topic) result.clone() : null);
	}

	public Topic createTopic() {
		return createTopic(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Topic createTopic(String id) {
		Topic result = null;
		// 來自靜態資料的clone副本
		result = getTopic(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new TopicImpl(StringHelper.randomUnique());// 1361579JmbDESVea
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
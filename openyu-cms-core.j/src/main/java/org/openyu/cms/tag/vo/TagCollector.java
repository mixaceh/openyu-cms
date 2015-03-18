package org.openyu.cms.tag.vo;

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

import org.openyu.commons.bean.supporter.BaseCollectorSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.enumz.EnumHelper;
import org.openyu.commons.lang.StringHelper;
import org.openyu.cms.tag.vo.adapter.StringTagXmlAdapter;
import org.openyu.cms.tag.vo.impl.TagImpl;
import org.openyu.cms.tag.vo.ActionOption;
import org.openyu.cms.tag.vo.ActionType;
import org.openyu.cms.tag.vo.adapter.ActionTypeXmlAdapter;
import org.openyu.cms.tag.vo.impl.ActionOptionImpl;

/**
 * 1.處理靜態資料,放的是vo的資料,並非po資料
 */
// --------------------------------------------------
// jaxb
// --------------------------------------------------
@XmlRootElement(name = "tagCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class TagCollector extends BaseCollectorSupporter {

	private static final long serialVersionUID = 1227520488672823467L;

	private static TagCollector tagCollector;

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
	 * 預設標籤
	 */
	private String defaultTag = "DEFAULT";

	/**
	 * 所有標籤
	 * 
	 * <id,tag>
	 */
	@XmlJavaTypeAdapter(StringTagXmlAdapter.class)
	private Map<String, Tag> tags = new LinkedHashMap<String, Tag>();

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

	public TagCollector() {
		setId(TagCollector.class.getName());
	}

	public synchronized static TagCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static TagCollector getInstance(boolean initial) {
		if (tagCollector == null) {
			tagCollector = new TagCollector();
			if (initial) {
				tagCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
			tagCollector.actionTypes = EnumHelper.valuesSet(ActionType.class);
		}
		return tagCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!tagCollector.isInitialized()) {
			tagCollector = readFromSer(TagCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (tagCollector == null) {
				tagCollector = new TagCollector();
			}
			//
			tagCollector.setInitialized(true);
		}
	}

	public void clear() {
		tags.clear();
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

	public String getDefaultTag() {
		return defaultTag;
	}

	public void setDefaultTag(String defaultTag) {
		this.defaultTag = defaultTag;
	}

	public Map<String, Tag> getTags() {
		if (tags == null) {
			tags = new LinkedHashMap<String, Tag>();
		}
		return tags;
	}

	public void setTags(Map<String, Tag> tags) {
		this.tags = tags;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------

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
	 * 取得clone標籤
	 * 
	 * @param id
	 * @return
	 */
	public Tag getTag(String id) {
		Tag result = null;
		if (id != null) {
			result = tags.get(id);
		}
		return (result != null ? (Tag) result.clone() : null);
	}

	public Tag createTag() {
		return createTag(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Tag createTag(String id) {
		Tag result = null;
		// 來自靜態資料的clone副本
		result = getTag(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new TagImpl(StringHelper.randomUnique());// 1361579JmbDESVea
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
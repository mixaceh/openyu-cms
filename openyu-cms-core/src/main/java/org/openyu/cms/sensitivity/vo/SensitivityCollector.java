package org.openyu.cms.sensitivity.vo;

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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.adapter.StringDictionaryXmlAdapter;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.sensitivity.vo.adapter.ActionTypeXmlAdapter;
import org.openyu.cms.sensitivity.vo.adapter.StringSensitivityXmlAdapter;
import org.openyu.cms.sensitivity.vo.impl.ActionOptionImpl;
import org.openyu.cms.sensitivity.vo.impl.SensitivityImpl;
import org.openyu.commons.bean.supporter.BaseCollectorSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.lang.StringHelper;

/**
 * 1.處理靜態資料,放的是vo的資料,並非po資料
 */
// --------------------------------------------------
// jaxb
// --------------------------------------------------
@XmlRootElement(name = "sensitivityCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class SensitivityCollector extends BaseCollectorSupporter {

	private static final long serialVersionUID = -1054507299747435843L;

	private static SensitivityCollector sensitivityCollector;

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
	 * 預設敏感詞
	 */
	private String defaultSensitivity = "DEFAULT";

	/**
	 * 所有敏感詞
	 * 
	 * <id,sensitivity>
	 */
	@XmlJavaTypeAdapter(StringSensitivityXmlAdapter.class)
	private Map<String, Sensitivity> sensitivitys = new LinkedHashMap<String, Sensitivity>();

	/**
	 * 預設字典
	 */
	private String defaultDictionary = "DEFAULT";

	/**
	 * 所有字典
	 * 
	 * <key,dictionary>
	 */
	@XmlJavaTypeAdapter(StringDictionaryXmlAdapter.class)
	private Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();

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

	public SensitivityCollector() {
		setId(SensitivityCollector.class.getName());
	}

	public synchronized static SensitivityCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static SensitivityCollector getInstance(boolean initial) {
		if (sensitivityCollector == null) {
			sensitivityCollector = new SensitivityCollector();
			if (initial) {
				sensitivityCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
		}
		return sensitivityCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!sensitivityCollector.isInitialized()) {
			sensitivityCollector = readFromSer(SensitivityCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (sensitivityCollector == null) {
				sensitivityCollector = new SensitivityCollector();
			}
			//
			sensitivityCollector.setInitialized(true);
		}
	}

	public void clear() {
		sensitivitys.clear();
		dictionarys.clear();
		actionOptions.clear();
		// 設為可初始化
		setInitialized(false);
	}

	public Set<ActionType> getActionTypes() {
		if (actionTypes == null) {
			actionTypes = new LinkedHashSet<ActionType>();
		}
		return actionTypes;
	}

	public void setActionTypes(Set<ActionType> actionTypes) {
		this.actionTypes = actionTypes;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public String getDefaultSensitivity() {
		return defaultSensitivity;
	}

	public void setDefaultSensitivity(String defaultSensitivity) {
		this.defaultSensitivity = defaultSensitivity;
	}

	public Map<String, Sensitivity> getSensitivitys() {
		if (sensitivitys == null) {
			sensitivitys = new LinkedHashMap<String, Sensitivity>();
		}
		return sensitivitys;
	}

	public void setSensitivitys(Map<String, Sensitivity> sensitivitys) {
		this.sensitivitys = sensitivitys;
	}

	public String getDefaultDictionary() {
		return defaultDictionary;
	}

	public void setDefaultDictionary(String defaultDictionary) {
		this.defaultDictionary = defaultDictionary;
	}

	public Map<String, Dictionary> getDictionarys() {
		if (dictionarys == null) {
			dictionarys = new LinkedHashMap<String, Dictionary>();
		}
		return dictionarys;
	}

	public void setDictionarys(Map<String, Dictionary> dictionarys) {
		this.dictionarys = dictionarys;
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
	 * 取得clone敏感詞
	 * 
	 * @param id
	 * @return
	 */
	public Sensitivity getSensitivity(String id) {
		Sensitivity result = null;
		if (id != null) {
			result = sensitivitys.get(id);
		}
		return (result != null ? (Sensitivity) result.clone() : null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Sensitivity createSensitivity(String id, Locale locale) {
		Sensitivity result = null;
		// 來自靜態資料的clone副本
		result = getSensitivity(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new SensitivityImpl(StringHelper.randomUnique());// 1361579JmbDESVea
		} else {
			// xmlId_randomUnique
			result.setId(id + "_" + StringHelper.randomUnique());// DEFAULT_1361579JmbDESVea
		}
		// 區域
		result.setLocale(locale);
		//
		return result;
	}

	/**
	 * 取得clone字典
	 * 
	 * @param id
	 * @return
	 */
	public Dictionary getDictionary(String id) {
		Dictionary result = null;
		if (id != null) {
			result = dictionarys.get(id);
		}
		return (result != null ? (Dictionary) result.clone() : null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Dictionary createDictionary(String id) {
		Dictionary result = null;
		// 來自靜態資料的clone副本
		result = getDictionary(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new DictionaryImpl();
		} else {
			result.setKey(null);
		}
		//
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
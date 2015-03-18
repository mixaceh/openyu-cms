package org.openyu.cms.adSpace.vo;

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

import org.openyu.cms.adSpace.vo.adapter.StringAdSpaceXmlAdapter;
import org.openyu.cms.adSpace.vo.impl.AdSpaceImpl;
import org.openyu.cms.adSpace.vo.ActionOption;
import org.openyu.cms.adSpace.vo.impl.ActionOptionImpl;
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
@XmlRootElement(name = "adSpaceCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdSpaceCollector extends BaseCollectorSupporter {

	private static final long serialVersionUID = 2542208907224174711L;

	private static AdSpaceCollector adSpaceCollector;

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設廣告版位
	 */
	private String defaultAdSpace = "DEFAULT";

	/**
	 * 所有廣告版位
	 * 
	 * <id,adSpace>
	 */
	@XmlJavaTypeAdapter(StringAdSpaceXmlAdapter.class)
	private Map<String, AdSpace> adSpaces = new LinkedHashMap<String, AdSpace>();

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

	public AdSpaceCollector() {
		setId(AdSpaceCollector.class.getName());
	}

	public synchronized static AdSpaceCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static AdSpaceCollector getInstance(boolean initial) {
		if (adSpaceCollector == null) {
			adSpaceCollector = new AdSpaceCollector();
			if (initial) {
				adSpaceCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
		}
		return adSpaceCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!adSpaceCollector.isInitialized()) {
			adSpaceCollector = readFromSer(AdSpaceCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (adSpaceCollector == null) {
				adSpaceCollector = new AdSpaceCollector();
			}
			//
			adSpaceCollector.setInitialized(true);
		}
	}

	public void clear() {
		adSpaces.clear();
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

	public String getDefaultAdSpace() {
		return defaultAdSpace;
	}

	public void setDefaultAdSpace(String defaultAdSpace) {
		this.defaultAdSpace = defaultAdSpace;
	}

	public Map<String, AdSpace> getAdSpaces() {
		if (adSpaces == null) {
			adSpaces = new LinkedHashMap<String, AdSpace>();
		}
		return adSpaces;
	}

	public void setAdSpaces(Map<String, AdSpace> adSpaces) {
		this.adSpaces = adSpaces;
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
	 * 取得clone廣告版位
	 * 
	 * @param id
	 * @return
	 */
	public AdSpace getAdSpace(String id) {
		AdSpace result = null;
		if (id != null) {
			result = adSpaces.get(id);
		}
		return (result != null ? (AdSpace) result.clone() : null);
	}

	public AdSpace createAdSpace() {
		return createAdSpace(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public AdSpace createAdSpace(String id) {
		AdSpace result = null;
		// 來自靜態資料的clone副本
		result = getAdSpace(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new AdSpaceImpl(StringHelper.randomUnique());// 1361579JmbDESVea
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
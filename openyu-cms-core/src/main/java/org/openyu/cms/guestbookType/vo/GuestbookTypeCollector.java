package org.openyu.cms.guestbookType.vo;

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

import org.openyu.cms.guestbookType.vo.adapter.StringGuestbookTypeXmlAdapter;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.cms.guestbookType.vo.ActionOption;
import org.openyu.cms.guestbookType.vo.impl.ActionOptionImpl;
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
@XmlRootElement(name = "guestbookTypeCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class GuestbookTypeCollector extends BaseCollectorSupporter {
	private static final long serialVersionUID = -2150655377967052866L;

	private static GuestbookTypeCollector guestbookTypeCollector;

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設留言類別
	 */
	private String defaultGuestbookType = "DEFAULT";

	/**
	 * 所有留言類別
	 * 
	 * <id,guestbookType>
	 */
	@XmlJavaTypeAdapter(StringGuestbookTypeXmlAdapter.class)
	private Map<String, GuestbookType> guestbookTypes = new LinkedHashMap<String, GuestbookType>();

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

	public GuestbookTypeCollector() {
		setId(GuestbookTypeCollector.class.getName());
	}

	public synchronized static GuestbookTypeCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static GuestbookTypeCollector getInstance(
			boolean initial) {
		if (guestbookTypeCollector == null) {
			guestbookTypeCollector = new GuestbookTypeCollector();
			if (initial) {
				guestbookTypeCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
		}
		return guestbookTypeCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!guestbookTypeCollector.isInitialized()) {
			guestbookTypeCollector = readFromSer(GuestbookTypeCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (guestbookTypeCollector == null) {
				guestbookTypeCollector = new GuestbookTypeCollector();
			}
			//
			guestbookTypeCollector.setInitialized(true);
		}
	}

	public void clear() {
		guestbookTypes.clear();
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

	public String getDefaultGuestbookType() {
		return defaultGuestbookType;
	}

	public void setDefaultGuestbookType(String defaultGuestbookType) {
		this.defaultGuestbookType = defaultGuestbookType;
	}

	public Map<String, GuestbookType> getGuestbookTypes() {
		if (guestbookTypes == null) {
			guestbookTypes = new LinkedHashMap<String, GuestbookType>();
		}
		return guestbookTypes;
	}

	public void setGuestbookTypes(Map<String, GuestbookType> guestbookTypes) {
		this.guestbookTypes = guestbookTypes;
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
	 * 取得clone留言類別
	 * 
	 * @param id
	 * @return
	 */
	public GuestbookType getGuestbookType(String id) {
		GuestbookType result = null;
		if (id != null) {
			result = guestbookTypes.get(id);
		}
		return (result != null ? (GuestbookType) result.clone() : null);
	}

	public GuestbookType createGuestbookType() {
		return createGuestbookType(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public GuestbookType createGuestbookType(String id) {
		GuestbookType result = null;
		// 來自靜態資料的clone副本
		result = getGuestbookType(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new GuestbookTypeImpl(StringHelper.randomUnique());// 1361579JmbDESVea
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
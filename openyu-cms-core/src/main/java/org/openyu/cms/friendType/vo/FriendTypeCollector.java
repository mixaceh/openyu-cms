package org.openyu.cms.friendType.vo;

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

import org.openyu.commons.bean.supporter.BaseCollectorSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.lang.StringHelper;
import org.openyu.cms.friendType.vo.adapter.StringFriendTypeXmlAdapter;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.cms.friendType.vo.ActionOption;
import org.openyu.cms.friendType.vo.impl.ActionOptionImpl;

/**
 * 1.處理靜態資料,放的是vo的資料,並非po資料
 */
// --------------------------------------------------
// jaxb
// --------------------------------------------------
@XmlRootElement(name = "friendTypeCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class FriendTypeCollector extends BaseCollectorSupporter {
	private static final long serialVersionUID = 2542208907224174711L;

	private static FriendTypeCollector friendTypeCollector;

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設友情類型
	 */
	private String defaultFriendType = "DEFAULT";

	/**
	 * 所有友情類型
	 * 
	 * <id,friendType>
	 */
	@XmlJavaTypeAdapter(StringFriendTypeXmlAdapter.class)
	private Map<String, FriendType> friendTypes = new LinkedHashMap<String, FriendType>();

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

	public FriendTypeCollector() {
		setId(FriendTypeCollector.class.getName());
	}

	public synchronized static FriendTypeCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static FriendTypeCollector getInstance(boolean initial) {
		if (friendTypeCollector == null) {
			friendTypeCollector = new FriendTypeCollector();
			if (initial) {
				friendTypeCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
		}
		return friendTypeCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!friendTypeCollector.isInitialized()) {
			friendTypeCollector = readFromSer(FriendTypeCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (friendTypeCollector == null) {
				friendTypeCollector = new FriendTypeCollector();
			}
			//
			friendTypeCollector.setInitialized(true);
		}
	}

	public void clear() {
		friendTypes.clear();
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

	public String getDefaultFriendType() {
		return defaultFriendType;
	}

	public void setDefaultFriendType(String defaultFriendType) {
		this.defaultFriendType = defaultFriendType;
	}

	public Map<String, FriendType> getFriendTypes() {
		if (friendTypes == null) {
			friendTypes = new LinkedHashMap<String, FriendType>();
		}
		return friendTypes;
	}

	public void setFriendTypes(Map<String, FriendType> friendTypes) {
		this.friendTypes = friendTypes;
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
	 * 取得clone友情類型
	 * 
	 * @param id
	 * @return
	 */
	public FriendType getFriendType(String id) {
		FriendType result = null;
		if (id != null) {
			result = friendTypes.get(id);
		}
		return (result != null ? (FriendType) result.clone() : null);
	}

	public FriendType createFriendType() {
		return createFriendType(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public FriendType createFriendType(String id) {
		FriendType result = null;
		// 來自靜態資料的clone副本
		result = getFriendType(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new FriendTypeImpl(StringHelper.randomUnique());// 1361579JmbDESVea
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
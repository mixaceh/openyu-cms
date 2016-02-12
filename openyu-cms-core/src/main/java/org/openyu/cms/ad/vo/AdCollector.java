package org.openyu.cms.ad.vo;

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

import org.openyu.cms.ad.vo.Ad.AdType;
import org.openyu.cms.ad.vo.adapter.AdTypeXmlAdapter;
import org.openyu.cms.ad.vo.adapter.StringAdXmlAdapter;
import org.openyu.cms.ad.vo.impl.AdImpl;
import org.openyu.cms.ad.vo.impl.AdTypeOptionImpl;
import org.openyu.cms.ad.vo.ActionOption;
import org.openyu.cms.ad.vo.impl.ActionOptionImpl;
import org.openyu.commons.collector.CollectorHelper;
import org.openyu.commons.collector.supporter.BaseCollectorSupporter;
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
@XmlRootElement(name = "adCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdCollector extends BaseCollectorSupporter {

	private static final long serialVersionUID = 2542208907224174711L;

	private static AdCollector instance;

	// --------------------------------------------------
	// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
	// --------------------------------------------------
	/**
	 * 廣告類型
	 */
	@XmlJavaTypeAdapter(AdTypeXmlAdapter.class)
	private Set<AdType> adTypes = new LinkedHashSet<AdType>();

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設廣告
	 */
	private String defaultAd = "DEFAULT";

	/**
	 * 所有廣告
	 * 
	 * <id,ad>
	 */
	@XmlJavaTypeAdapter(StringAdXmlAdapter.class)
	private Map<String, Ad> ads = new LinkedHashMap<String, Ad>();

	/**
	 * 廣告類型選項
	 */
	@XmlElement(type = AdTypeOptionImpl.class)
	private List<AdTypeOption> adTypeOptions = new LinkedList<AdTypeOption>();

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

	public AdCollector() {
		setId(AdCollector.class.getName());
	}

	public synchronized static AdCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static AdCollector getInstance(boolean start) {
		if (instance == null) {
			instance = CollectorHelper.readFromSer(AdCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (instance == null) {
				instance = new AdCollector();
			}
			//
			if (start) {
				// 啟動
				instance.start();
			}
			// 此有系統值,只是為了轉出xml,並非給企劃編輯用
			instance.adTypes = EnumHelper.valuesSet(AdType.class);
		}
		return instance;
	}

	/**
	 * 單例關閉
	 * 
	 * @return
	 */
	public synchronized static AdCollector shutdownInstance() {
		if (instance != null) {
			AdCollector oldInstance = instance;
			instance = null;
			//
			if (oldInstance != null) {
				oldInstance.shutdown();
			}
		}
		return instance;
	}

	/**
	 * 單例重啟
	 * 
	 * @return
	 */
	public synchronized static AdCollector restartInstance() {
		if (instance != null) {
			instance.restart();
		}
		return instance;
	}

	/**
	 * 內部啟動
	 */
	@Override
	protected void doStart() throws Exception {

	}

	/**
	 * 內部關閉
	 */
	@Override
	protected void doShutdown() throws Exception {
		instance.ads.clear();
	}

	public Set<AdType> getAdTypes() {
		if (adTypes == null) {
			adTypes = new LinkedHashSet<AdType>();
		}
		return adTypes;
	}

	public void setAdTypes(Set<AdType> adTypes) {
		this.adTypes = adTypes;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public String getDefaultAd() {
		return defaultAd;
	}

	public void setDefaultAd(String defaultAd) {
		this.defaultAd = defaultAd;
	}

	public Map<String, Ad> getAds() {
		if (ads == null) {
			ads = new LinkedHashMap<String, Ad>();
		}
		return ads;
	}

	public void setAds(Map<String, Ad> ads) {
		this.ads = ads;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------

	/**
	 * 廣告類型選項
	 * 
	 * @return
	 */
	public List<AdTypeOption> getAdTypeOptions() {
		if (adTypeOptions == null) {
			adTypeOptions = new LinkedList<AdTypeOption>();
		}
		return adTypeOptions;
	}

	public void setAdTypeOptions(List<AdTypeOption> adTypeOptions) {
		this.adTypeOptions = adTypeOptions;
	}

	/**
	 * 廣告類型(1/2/3/4)選項名稱
	 * 
	 * @param adType
	 * @param locale
	 * @return
	 */
	public String getAdTypeName(AdType adType, Locale locale) {
		String result = null;
		for (AdTypeOption entry : adTypeOptions) {
			if (entry == null) {
				continue;
			}
			//
			if (entry.getId() == adType) {
				result = entry.getName(locale);
				break;
			}
		}
		return result;
	}

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
	 * @param value,
	 *            Action.getId().getValue()
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
	 * 取得clone廣告
	 * 
	 * @param id
	 * @return
	 */
	public Ad getAd(String id) {
		Ad result = null;
		if (id != null) {
			result = ads.get(id);
		}
		return (result != null ? (Ad) result.clone() : null);
	}

	public Ad createAd() {
		return createAd(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Ad createAd(String id) {
		Ad result = null;
		// 來自靜態資料的clone副本
		result = getAd(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new AdImpl(StringHelper.randomUnique());// 1361579JmbDESVea
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
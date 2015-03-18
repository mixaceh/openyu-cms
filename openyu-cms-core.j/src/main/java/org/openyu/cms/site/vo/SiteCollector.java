package org.openyu.cms.site.vo;

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

import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.cms.site.vo.adapter.ModifyTypeXmlAdapter;
import org.openyu.cms.site.vo.adapter.StringSiteXmlAdapter;
import org.openyu.cms.site.vo.adapter.VerifyTypeXmlAdapter;
import org.openyu.cms.site.vo.impl.ModifyTypeOptionImpl;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.cms.site.vo.impl.VerifyTypeOptionImpl;
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
@XmlRootElement(name = "siteCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class SiteCollector extends BaseCollectorSupporter {
	private static final long serialVersionUID = 780776222044042528L;

	private static SiteCollector siteCollector;

	// --------------------------------------------------
	// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
	// --------------------------------------------------
	/**
	 * 終審類型
	 */
	@XmlJavaTypeAdapter(VerifyTypeXmlAdapter.class)
	private Set<VerifyType> verifyTypes = new LinkedHashSet<VerifyType>();

	/**
	 * 審核後修改類型
	 */
	@XmlJavaTypeAdapter(ModifyTypeXmlAdapter.class)
	private Set<ModifyType> modifyTypes = new LinkedHashSet<ModifyType>();

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設網站
	 */
	private String defaultSite = "DEFAULT";

	/**
	 * 所有網站
	 * 
	 * <id,site>
	 */
	@XmlJavaTypeAdapter(StringSiteXmlAdapter.class)
	private Map<String, Site> sites = new LinkedHashMap<String, Site>();

	/**
	 * 訪問協定選項
	 */
	private List<String> protocolOptions = new LinkedList<String>();

	/**
	 * 動態頁尾碼選項,建議使用.yhtml為尾碼，以避免衝突
	 */
	private List<String> dynamicSuffixOptions = new LinkedList<String>();

	/**
	 * 靜態頁尾碼選項
	 */
	private List<String> staticSuffixOptions = new LinkedList<String>();

	/**
	 * 終審類型選項
	 */
	@XmlElement(type = VerifyTypeOptionImpl.class)
	private List<VerifyTypeOption> verifyTypeOptions = new LinkedList<VerifyTypeOption>();

	/**
	 * 審核後修改類型選項
	 */
	@XmlElement(type = ModifyTypeOptionImpl.class)
	private List<ModifyTypeOption> modifyTypeOptions = new LinkedList<ModifyTypeOption>();

	public SiteCollector() {
		setId(SiteCollector.class.getName());
	}

	public synchronized static SiteCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static SiteCollector getInstance(boolean initial) {
		if (siteCollector == null) {
			siteCollector = new SiteCollector();
			if (initial) {
				siteCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
			siteCollector.verifyTypes = EnumHelper.valuesSet(VerifyType.class);
			siteCollector.modifyTypes = EnumHelper.valuesSet(ModifyType.class);
		}
		return siteCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!siteCollector.isInitialized()) {
			siteCollector = readFromSer(SiteCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (siteCollector == null) {
				siteCollector = new SiteCollector();
			}
			//
			siteCollector.setInitialized(true);
		}
	}

	public void clear() {
		sites.clear();
		// 設為可初始化
		setInitialized(false);
	}

	public Set<VerifyType> getVerifyTypes() {
		if (verifyTypes == null) {
			verifyTypes = new LinkedHashSet<VerifyType>();
		}
		return verifyTypes;
	}

	public void setVerifyTypes(Set<VerifyType> verifyTypes) {
		this.verifyTypes = verifyTypes;
	}

	public Set<ModifyType> getModifyTypes() {
		if (modifyTypes == null) {
			modifyTypes = new LinkedHashSet<ModifyType>();
		}
		return modifyTypes;
	}

	public void setModifyTypes(Set<ModifyType> modifyTypes) {
		this.modifyTypes = modifyTypes;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public String getDefaultSite() {
		return defaultSite;
	}

	public void setDefaultSite(String defaultSite) {
		this.defaultSite = defaultSite;
	}

	public Map<String, Site> getSites() {
		if (sites == null) {
			sites = new LinkedHashMap<String, Site>();
		}
		return sites;
	}

	public void setSites(Map<String, Site> sites) {
		this.sites = sites;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------

	public List<String> getProtocolOptions() {
		if (protocolOptions == null) {
			protocolOptions = new LinkedList<String>();
		}
		return protocolOptions;
	}

	public void setProtocolOptions(List<String> protocolOptions) {
		this.protocolOptions = protocolOptions;
	}

	public List<String> getDynamicSuffixOptions() {
		if (dynamicSuffixOptions == null) {
			dynamicSuffixOptions = new LinkedList<String>();
		}
		return dynamicSuffixOptions;
	}

	public void setDynamicSuffixOptions(List<String> dynamicSuffixOptions) {
		this.dynamicSuffixOptions = dynamicSuffixOptions;
	}

	public List<String> getStaticSuffixOptions() {
		if (staticSuffixOptions == null) {
			staticSuffixOptions = new LinkedList<String>();
		}
		return staticSuffixOptions;
	}

	public void setStaticSuffixOptions(List<String> staticSuffixOptions) {
		this.staticSuffixOptions = staticSuffixOptions;
	}

	/**
	 * 終審類型選項
	 * 
	 * @return
	 */
	public List<VerifyTypeOption> getVerifyTypeOptions() {
		if (verifyTypeOptions == null) {
			verifyTypeOptions = new LinkedList<VerifyTypeOption>();
		}
		return verifyTypeOptions;
	}

	public void setVerifyTypeOptions(List<VerifyTypeOption> verifyTypeOptions) {
		this.verifyTypeOptions = verifyTypeOptions;
	}

	/**
	 * 終審類型(0/1/2)選項名稱
	 * 
	 * @param verifyType
	 * @param locale
	 * @return
	 */
	public String getVerifyTypeName(VerifyType verifyType, Locale locale) {
		String result = null;
		for (VerifyTypeOption entry : verifyTypeOptions) {
			if (entry == null) {
				continue;
			}
			//
			if (entry.getId() == verifyType) {
				result = entry.getName(locale);
				break;
			}
		}
		return result;
	}

	/**
	 * 審核後修改類型選項
	 * 
	 * @return
	 */
	public List<ModifyTypeOption> getModifyTypeOptions() {
		if (modifyTypeOptions == null) {
			modifyTypeOptions = new LinkedList<ModifyTypeOption>();
		}
		return modifyTypeOptions;
	}

	public void setModifyTypeOptions(List<ModifyTypeOption> modifyTypeOptions) {
		this.modifyTypeOptions = modifyTypeOptions;
	}

	/**
	 * 審核後修改類型(1/2/3)選項名稱
	 * 
	 * @param modifyType
	 * @param locale
	 * @return
	 */
	public String getModifyTypeName(ModifyType modifyType, Locale locale) {
		String result = null;
		for (ModifyTypeOption entry : modifyTypeOptions) {
			if (entry == null) {
				continue;
			}
			//
			if (entry.getId() == modifyType) {
				result = entry.getName(locale);
				break;
			}
		}
		return result;
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
	 * 取得clone網站
	 * 
	 * @param id
	 * @return
	 */
	public Site getSite(String id) {
		Site result = null;
		if (id != null) {
			result = sites.get(id);
		}
		return (result != null ? (Site) result.clone() : null);
	}

	public Site createSite() {
		return createSite(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Site createSite(String id) {
		Site result = null;
		// 來自靜態資料的clone副本
		result = getSite(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new SiteImpl(StringHelper.randomUnique());// 1361579JmbDESVea
		} else {
			// xmlId_randomUnique
			result.setId(id + "_" + StringHelper.randomUnique());// DEFAULT_1361579JmbDESVea
		}
		return result;
	}

}
package org.openyu.cms.contextType.vo;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.commons.bean.supporter.BaseCollectorSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.io.IoHelper;
import org.openyu.commons.lang.StringHelper;
import org.openyu.cms.contextType.vo.adapter.StringContextTypeXmlAdapter;
import org.openyu.cms.contextType.vo.impl.ContextTypeImpl;

/**
 * 1.處理靜態資料,放的是vo的資料,並非po資料
 */
// --------------------------------------------------
// jaxb
// --------------------------------------------------
@XmlRootElement(name = "contextTypeCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContextTypeCollector extends BaseCollectorSupporter {

	private static final long serialVersionUID = 2542208907224174711L;

	private static ContextTypeCollector contextTypeCollector;

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設本文類型
	 */
	private String defaultContextType = "DEFAULT";

	/**
	 * 所有本文類型
	 * 
	 * <id,contextType>
	 */
	@XmlJavaTypeAdapter(StringContextTypeXmlAdapter.class)
	private Map<String, ContextType> contextTypes = new LinkedHashMap<String, ContextType>();

	public ContextTypeCollector() {
		setId(ContextTypeCollector.class.getName());
	}

	public synchronized static ContextTypeCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static ContextTypeCollector getInstance(boolean initial) {
		if (contextTypeCollector == null) {
			contextTypeCollector = new ContextTypeCollector();
			if (initial) {
				contextTypeCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
		}
		return contextTypeCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!contextTypeCollector.isInitialized()) {
			contextTypeCollector = readFromSer(ContextTypeCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (contextTypeCollector == null) {
				contextTypeCollector = new ContextTypeCollector();
			}
			//
			contextTypeCollector.setInitialized(true);
		}
	}

	public void clear() {
		contextTypes.clear();
		// 設為可初始化
		setInitialized(false);
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public String getDefaultContextType() {
		return defaultContextType;
	}

	public void setDefaultContextType(String defaultContextType) {
		this.defaultContextType = defaultContextType;
	}

	public Map<String, ContextType> getContextTypes() {
		if (contextTypes == null) {
			contextTypes = new LinkedHashMap<String, ContextType>();
		}
		return contextTypes;
	}

	public void setContextTypes(Map<String, ContextType> contextTypes) {
		this.contextTypes = contextTypes;
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
	 * 取得clone本文類型
	 * 
	 * @param id
	 * @return
	 */
	public ContextType getContextType(String id) {
		ContextType result = null;
		if (id != null) {
			result = contextTypes.get(id);
		}
		return (result != null ? (ContextType) result.clone() : null);
	}

	public ContextType createContextType() {
		return createContextType(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public ContextType createContextType(String id) {
		ContextType result = null;
		// 來自靜態資料的clone副本
		result = getContextType(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new ContextTypeImpl(StringHelper.randomUnique());// 1361579JmbDESVea
		} else {
			// xmlId_randomUnique
			result.setId(id + "_" + StringHelper.randomUnique());// DEFAULT_1361579JmbDESVea
		}
		return result;
	}

}
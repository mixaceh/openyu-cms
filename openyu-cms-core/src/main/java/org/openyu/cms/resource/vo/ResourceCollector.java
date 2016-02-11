package org.openyu.cms.resource.vo;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.archive.vo.ActionOption;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.archive.vo.impl.ActionOptionImpl;
import org.openyu.cms.archive.vo.adapter.ActionTypeXmlAdapter;
import org.openyu.commons.bean.supporter.BaseCollectorSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.enumz.EnumHelper;

/**
 * 1.處理靜態資料,放的是vo的資料,並非po資料
 */
// --------------------------------------------------
// jaxb
// --------------------------------------------------
@XmlRootElement(name = "resourceCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResourceCollector extends BaseCollectorSupporter {

	private static final long serialVersionUID = -1059982996489996588L;

	private static ResourceCollector resourceCollector;

	// --------------------------------------------------
	// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
	// --------------------------------------------------
	/**
	 * 操作類別
	 */
	@XmlJavaTypeAdapter(ActionTypeXmlAdapter.class)
	private Set<ActionType> actionTypes = new LinkedHashSet<ActionType>();

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

	public ResourceCollector() {
		setId(ResourceCollector.class.getName());
	}

	public synchronized static ResourceCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static ResourceCollector getInstance(boolean initial) {
		if (resourceCollector == null) {
			resourceCollector = new ResourceCollector();
			if (initial) {
				resourceCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
			resourceCollector.actionTypes = EnumHelper
					.valuesSet(ActionType.class);
		}
		return resourceCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!resourceCollector.isInitialized()) {
			resourceCollector = readFromSer(ResourceCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (resourceCollector == null) {
				resourceCollector = new ResourceCollector();
			}
			//
			resourceCollector.setInitialized(true);
		}
	}

	public void clear() {
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
	 * 建構log查詢條件
	 * 
	 * @return
	 */
	public Inquiry createLogInquiry() {
		return (logInquiry != null ? (Inquiry) logInquiry.clone() : null);
	}

}
package org.openyu.cms.group.vo;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.group.vo.adapter.StringGroupXmlAdapter;
import org.openyu.cms.group.vo.impl.GroupImpl;
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
@XmlRootElement(name = "groupCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupCollector extends BaseCollectorSupporter {

	private static final long serialVersionUID = -7413953169380048813L;

	private static GroupCollector groupCollector;

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設群組
	 */
	private String defaultGroup = "DEFAULT";

	/**
	 * 所有群組
	 * 
	 * <id,group>
	 */
	@XmlJavaTypeAdapter(StringGroupXmlAdapter.class)
	private Map<String, Group> groups = new LinkedHashMap<String, Group>();

	public GroupCollector() {
		setId(GroupCollector.class.getName());
	}

	public synchronized static GroupCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static GroupCollector getInstance(boolean initial) {
		if (groupCollector == null) {
			groupCollector = new GroupCollector();
			if (initial) {
				groupCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
		}
		return groupCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!groupCollector.isInitialized()) {
			groupCollector = readFromSer(GroupCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (groupCollector == null) {
				groupCollector = new GroupCollector();
			}
			//
			groupCollector.setInitialized(true);
		}
	}

	public void clear() {
		groups.clear();
		// 設為可初始化
		setInitialized(false);
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}

	public String getDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(String defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	public Map<String, Group> getGroups() {
		if (groups == null) {
			groups = new LinkedHashMap<String, Group>();
		}
		return groups;
	}

	public void setGroups(Map<String, Group> groups) {
		this.groups = groups;
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
	 * 取得clone群組
	 * 
	 * @param id
	 * @return
	 */
	public Group getGroup(String id) {
		Group result = null;
		if (id != null) {
			result = groups.get(id);
		}
		return (result != null ? (Group) result.clone() : null);
	}

	public Group createGroup() {
		return createGroup(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Group createGroup(String id) {
		Group result = null;
		// 來自靜態資料的clone副本
		result = getGroup(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new GroupImpl(StringHelper.randomUnique());// 1361579JmbDESVea
		} else {
			// xmlId_randomUnique
			result.setId(id + "_" + StringHelper.randomUnique());// DEFAULT_1361579JmbDESVea
		}
		return result;
	}

}
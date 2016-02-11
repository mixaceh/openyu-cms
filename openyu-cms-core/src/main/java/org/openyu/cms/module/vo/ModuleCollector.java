package org.openyu.cms.module.vo;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.cms.module.vo.adapter.StringModuleXmlAdapter;
import org.openyu.cms.module.vo.impl.ModuleImpl;
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
@XmlRootElement(name = "moduleCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class ModuleCollector extends BaseCollectorSupporter
{
	private static final long serialVersionUID = 2542208907224174711L;

	private static ModuleCollector moduleCollector;

	// --------------------------------------------------
	// 企劃編輯用
	// --------------------------------------------------
	/**
	 * 查詢條件
	 */
	@XmlElement(type = InquiryImpl.class)
	private Inquiry inquiry;

	/**
	 * 預設模組
	 */
	private String defaultModule = "DEFAULT";

	/**
	 * 所有模組
	 * 
	 * <id,module>
	 */
	@XmlJavaTypeAdapter(StringModuleXmlAdapter.class)
	private Map<String, Module> modules = new LinkedHashMap<String, Module>();

	public ModuleCollector()
	{
		setId(ModuleCollector.class.getName());
	}

	public synchronized static ModuleCollector getInstance()
	{
		return getInstance(true);
	}

	public synchronized static ModuleCollector getInstance(boolean initial)
	{
		if (moduleCollector == null)
		{
			moduleCollector = new ModuleCollector();
			if (initial)
			{
				moduleCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
		}
		return moduleCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize()
	{
		if (!moduleCollector.isInitialized())
		{
			moduleCollector = readFromSer(ModuleCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (moduleCollector == null)
			{
				moduleCollector = new ModuleCollector();
			}
			//
			moduleCollector.setInitialized(true);
		}
	}

	public void clear()
	{
		modules.clear();
		// 設為可初始化
		setInitialized(false);
	}

	public Inquiry getInquiry()
	{
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry)
	{
		this.inquiry = inquiry;
	}

	public String getDefaultModule()
	{
		return defaultModule;
	}

	public void setDefaultModule(String defaultModule)
	{
		this.defaultModule = defaultModule;
	}

	public Map<String, Module> getModules()
	{
		if (modules == null)
		{
			modules = new LinkedHashMap<String, Module>();
		}
		return modules;
	}

	public void setModules(Map<String, Module> modules)
	{
		this.modules = modules;
	}

	// --------------------------------------------------
	/**
	 * 建構查詢條件
	 * 
	 * @return
	 */
	public Inquiry createInquiry()
	{
		return (inquiry != null ? (Inquiry) inquiry.clone() : null);
	}

	/**
	 * 取得clone模組
	 * 
	 * @param id
	 * @return
	 */
	public Module getModule(String id)
	{
		Module result = null;
		if (id != null)
		{
			result = modules.get(id);
		}
		return (result != null ? (Module) result.clone() : null);
	}

	public Module createModule()
	{
		return createModule(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Module createModule(String id)
	{
		Module result = null;
		//來自靜態資料的clone副本
		result = getModule(id);
		//若無靜態資料,則直接建構
		if (result == null)
		{
			result = new ModuleImpl(StringHelper.randomUnique());//1361579JmbDESVea
		}
		else
		{
			//xmlId_randomUnique
			result.setId(id + "_" + StringHelper.randomUnique());//DEFAULT_1361579JmbDESVea
		}
		return result;
	}

}
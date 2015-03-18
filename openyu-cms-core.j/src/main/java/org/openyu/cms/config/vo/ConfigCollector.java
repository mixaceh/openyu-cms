package org.openyu.cms.config.vo;

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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.config.vo.MarkPosOption.MarkPosType;
import org.openyu.cms.config.vo.adapter.StringConfigXmlAdapter;
import org.openyu.cms.config.vo.impl.ConfigImpl;
import org.openyu.cms.config.vo.impl.MarkPosOptionImpl;
import org.openyu.commons.bean.supporter.BaseCollectorSupporter;
import org.openyu.commons.io.IoHelper;
import org.openyu.commons.lang.StringHelper;

/**
 * 1.處理靜態資料,放的是vo的資料,並非po資料
 */
// --------------------------------------------------
// jaxb
// --------------------------------------------------
@XmlRootElement(name = "configCollector")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigCollector extends BaseCollectorSupporter {

	private static final long serialVersionUID = -2384430164677425401L;

	private static ConfigCollector configCollector;

	/**
	 * 預設模組
	 */
	private String defaultConfig = "DEFAULT";

	/**
	 * 所有模組
	 * 
	 * <id,config>
	 */
	@XmlJavaTypeAdapter(StringConfigXmlAdapter.class)
	private Map<String, Config> configs = new LinkedHashMap<String, Config>();

	/**
	 * 浮水印位置選項
	 */
	@XmlElement(type = MarkPosOptionImpl.class)
	private List<MarkPosOption> markPosOptions = new LinkedList<MarkPosOption>();

	public ConfigCollector() {
		setId(ConfigCollector.class.getName());
	}

	public synchronized static ConfigCollector getInstance() {
		return getInstance(true);
	}

	public synchronized static ConfigCollector getInstance(boolean initial) {
		if (configCollector == null) {
			configCollector = new ConfigCollector();
			if (initial) {
				configCollector.initialize();
			}
			// 此有系統預設值,只是為了轉出xml,並非給企劃編輯用
		}
		return configCollector;
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		if (!configCollector.isInitialized()) {
			configCollector = readFromSer(ConfigCollector.class);
			// 此時有可能會沒有ser檔案,或舊的結構造成ex,只要再轉出一次ser,覆蓋原本ser即可
			if (configCollector == null) {
				configCollector = new ConfigCollector();
			}
			//
			configCollector.setInitialized(true);
		}
	}

	public void clear() {
		configs.clear();
		// 設為可初始化
		setInitialized(false);
	}

	public String getDefaultConfig() {
		return defaultConfig;
	}

	public void setDefaultConfig(String defaultConfig) {
		this.defaultConfig = defaultConfig;
	}

	public Map<String, Config> getConfigs() {
		if (configs == null) {
			configs = new LinkedHashMap<String, Config>();
		}
		return configs;
	}

	public void setConfigs(Map<String, Config> configs) {
		this.configs = configs;
	}

	// --------------------------------------------------

	/**
	 * 浮水印位置選項
	 * 
	 * @return
	 */
	public List<MarkPosOption> getMarkPosOptions() {
		if (markPosOptions == null) {
			markPosOptions = new LinkedList<MarkPosOption>();
		}
		return markPosOptions;
	}

	public void setMarkPosOptions(List<MarkPosOption> markPosOptions) {
		this.markPosOptions = markPosOptions;
	}

	/**
	 * 浮水印位置選項名稱
	 * 
	 * @param modifyType
	 * @param locale
	 * @return
	 */
	public String getMarkPosName(MarkPosType markPosType, Locale locale) {
		String result = null;
		for (MarkPosOption entry : markPosOptions) {
			if (entry == null) {
				continue;
			}
			//
			if (entry.getId() == markPosType) {
				result = entry.getName(locale);
				break;
			}
		}
		return result;
	}

	/**
	 * 取得clone模組
	 * 
	 * @param id
	 * @return
	 */
	public Config getConfig(String id) {
		Config result = null;
		if (id != null) {
			result = configs.get(id);
		}
		return (result != null ? (Config) result.clone() : null);
	}

	public Config createConfig() {
		return createConfig(null);
	}

	/**
	 * 
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param id
	 * @return
	 */
	public Config createConfig(String id) {
		Config result = null;
		// 來自靜態資料的clone副本
		result = getConfig(id);
		// 若無靜態資料,則直接建構
		if (result == null) {
			result = new ConfigImpl(StringHelper.randomUnique());// 1361579JmbDESVea
		} else {
			// xmlId_randomUnique
			result.setId(id + "_" + StringHelper.randomUnique());// DEFAULT_1361579JmbDESVea
		}
		return result;
	}

}
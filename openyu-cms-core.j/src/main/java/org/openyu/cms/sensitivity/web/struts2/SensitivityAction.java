package org.openyu.cms.sensitivity.web.struts2;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.sensitivity.vo.SensitivityCollector;
import org.openyu.cms.sensitivity.service.SensitivityService;
import org.openyu.cms.sensitivity.vo.Sensitivity;
import org.openyu.cms.sensitivity.vo.impl.SensitivityImpl;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 敏感詞控制器
 */
@ParentPackage("default")
@Namespace("/back/service/sensitivity")
@Results({ @Result(name = "success", type = "freemarker", location = "sensitivityList.ftl") })
public class SensitivityAction extends AppListActionSupporter {

	private static final long serialVersionUID = 7360654791348658869L;

	private static transient final Logger log = LogManager
			.getLogger(SensitivityAction.class);

	/**
	 * 敏感詞服務
	 */
	@Autowired
	@Qualifier("sensitivityService")
	protected transient SensitivityService sensitivityService;

	/**
	 * 靜態收集器
	 */
	protected transient SensitivityCollector sensitivityCollector = SensitivityCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private Dictionary searcher = new DictionaryImpl();

	/**
	 * 單筆資料
	 */
	private Sensitivity sensitivity = new SensitivityImpl();

	private Dictionary dictionary = new DictionaryImpl();

	private Dictionary origDictionary = new DictionaryImpl();

	/**
	 * 選擇多筆敏感詞
	 */
	private List<String> keys = new LinkedList<String>();

	public SensitivityAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();
		// 初始化查詢條件
		initializeInquiry(sensitivityCollector.createInquiry());
	}

	public Dictionary getSearcher() {
		return searcher;
	}

	public void setSearcher(Dictionary searcher) {
		this.searcher = searcher;
	}

	public Sensitivity getSensitivity() {
		return sensitivity;
	}

	public void setSensitivity(Sensitivity sensitivity) {
		this.sensitivity = sensitivity;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public Dictionary getOrigDictionary() {
		return origDictionary;
	}

	public void setOrigDictionary(Dictionary origDictionary) {
		this.origDictionary = origDictionary;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	@Action(value = "index")
	public String execute() {
		// 返回列表
		return list();
	}

	/**
	 * 列表
	 * 
	 * @return
	 */
	@Action(value = "list")
	public String list() {
		try {
			// 讀取列表
			retrieveList();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 讀取列表
	 * 
	 * @return
	 */
	protected void retrieveList() {
		List<Sensitivity> list = sensitivityService.findSensitivity(inquiry,
				getLocale(), searcher);
		if (list.size() > 0) {
			sensitivity = list.get(0);
		}
	}

	/**
	 * 查詢, sql
	 * 
	 * @return
	 */
	@Action(value = "find")
	public String find() {
		try {
			// 讀取列表
			retrieveList();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 搜尋, lql
	 * 
	 * @return
	 */
	@Action(value = "search")
	public String search() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 顯示單筆
	 * 
	 * @return
	 */
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "sensitivityView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 讀取單筆
	 */
	protected void retriveSingle() {
		sensitivity = sensitivityService.find(SensitivityImpl.class,
				sensitivity.getSeq());
		if (sensitivity != null) {
			dictionary.setValue(sensitivity.getDictionarys()
					.get(dictionary.getKey()).getValue());
			dictionary.setValid(sensitivity.getDictionarys()
					.get(dictionary.getKey()).getValid());
			origDictionary = (Dictionary) dictionary.clone();
		}
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", type = "freemarker", location = "sensitivityAdd.ftl") })
	public String add() {
		try {
			if (sensitivity.getSeq() > 0) {
				sensitivity = sensitivityService.find(SensitivityImpl.class,
						sensitivity.getSeq());
				if (sensitivity == null) {
					// 預設敏感詞
					sensitivity = sensitivityService.createSensitivity(
							sensitivityCollector.getDefaultSensitivity(),
							getLocale());
				}
			} else {
				// 預設敏感詞
				sensitivity = sensitivityService.createSensitivity(
						sensitivityCollector.getDefaultSensitivity(),
						getLocale());
			}
			// 預設字典
			dictionary = sensitivityService
					.createDictionary(sensitivityCollector
							.getDefaultDictionary());
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新增存檔
	 * 
	 * @return
	 */
	@Action(value = "addSave", results = {
			@Result(name = "input", type = "freemarker", location = "sensitivityAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "sensitivityList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "dictionary.key", key = "sensitivity.dictionarys.key.required"),//
	})
	public String addSave() {
		Serializable result = null;
		try {
			// 檢查id
			if (StringHelper.isEmpty(sensitivity.getId())) {
				addActionError(getText("sensitivity.id.required"));
				return INPUT;
			}

			// 存在的敏感詞
			Sensitivity existSensitivity = sensitivityService.find(
					SensitivityImpl.class, sensitivity.getSeq());

			if (existSensitivity != null) {
				// dictionarys
				if (existSensitivity.getDictionarys().containsKey(
						dictionary.getKey())) {
					// 敏感詞存在
					String[] msgArgs = new String[] { dictionary.getKey(),
							dictionary.getValue() };
					addActionError(getText("global.data.exist", msgArgs));
					return INPUT;
				}
			}
			//
			result = sensitivityService.insertDictionary(userSession.getUser(),
					sensitivity, dictionary);
			if (result != null) {
				String[] msgArgs = new String[] { dictionary.getKey(),
						dictionary.getValue() };
				addActionMessage(getText("global.add.success", msgArgs));
				return list();
			}

		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result == null) {
			String[] msgArgs = new String[] { dictionary.getKey(),
					dictionary.getValue() };
			addActionError(getText("global.add.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit", results = { @Result(name = "success", type = "freemarker", location = "sensitivityEdit.ftl") })
	public String edit() {
		try {
			// 讀取單筆
			retriveSingle();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 編輯存檔
	 * 
	 * @return
	 */
	@Action(value = "editSave", results = {
			@Result(name = "input", type = "freemarker", location = "sensitivityEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "sensitivityList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "dictionary.key", key = "sensitivity.dictionarys.key.required"),//
	})
	public String editSave() {
		int result = 0;
		try {
			// 檢查id
			if (StringHelper.isEmpty(sensitivity.getId())) {
				addActionError(getText("sensitivity.id.required"));
				return INPUT;
			}

			// 存在的敏感詞
			Sensitivity existSensitivity = sensitivityService.find(
					SensitivityImpl.class, sensitivity.getSeq());
			if (existSensitivity == null) {
				String[] msgArgs = new String[] {
						String.valueOf(sensitivity.getSeq()), "" };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			result = sensitivityService.updateDictionary(userSession.getUser(),
					existSensitivity, origDictionary, dictionary);

			if (result > 0) {
				String[] msgArgs = new String[] { dictionary.getKey(),
						dictionary.getValue() };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}

		if (result < 1) {
			String[] msgArgs = new String[] { dictionary.getKey(),
					dictionary.getValue() };
			addActionError(getText("global.save.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 刪除, 多筆刪除,by keys
	 * 
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "input", type = "freemarker", location = "sensitivityList.ftl") })
	public String delete() {
		List<Dictionary> result = null;
		int size = 0;
		try {
			// 檢查id
			if (StringHelper.isEmpty(sensitivity.getId())) {
				addActionError(getText("sensitivity.id.required"));
				return INPUT;
			}

			// 存在的敏感詞
			Sensitivity existSensitivity = sensitivityService.find(
					SensitivityImpl.class, sensitivity.getSeq());
			if (existSensitivity == null) {
				String[] msgArgs = new String[] {
						String.valueOf(sensitivity.getSeq()), "" };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			result = sensitivityService.deleteDictionary(userSession.getUser(),
					existSensitivity, keys);
			size = result.size();
			if (size > 0) {
				addActionMessage(getText("global.delete.success",
						new String[] { String.valueOf(size) }));
				// 返回列表
				return list();
			}

		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		//
		if (!keys.isEmpty() && size < 1) {
			addActionMessage(getText("global.delete.no.data"));
		}
		return SUCCESS;
	}

	/**
	 * 存檔
	 * 
	 * @return
	 */
	@Action(value = "save")
	public String save() {
		try {
			// TODO 修改啟用欄位
			// 讀取列表
			retrieveList();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}
}

package org.openyu.cms.module.web.struts2;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;
import org.openyu.cms.module.service.ModuleService;
import org.openyu.cms.module.vo.Module;
import org.openyu.cms.module.vo.ModuleCollector;
import org.openyu.cms.module.vo.impl.ModuleImpl;
import org.openyu.commons.lang.StringHelper;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

/**
 * 模組控制器
 */
@ParentPackage("default")
@Namespace("/back/service/module")
@Results({ @Result(name = "success", type = "freemarker", location = "moduleList.ftl") })
public class ModuleAction extends AppListActionSupporter {

	private static final long serialVersionUID = -8184746975973063374L;

	private static transient final Logger log = LogManager
			.getLogger(ModuleAction.class);

	/**
	 * 模組服務
	 */
	@Autowired
	@Qualifier("moduleService")
	protected transient ModuleService moduleService;

	/**
	 * 靜態收集器
	 */
	protected transient ModuleCollector moduleCollector = ModuleCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private Module searcher = new ModuleImpl();

	/**
	 * 單筆資料
	 */
	private Module module = new ModuleImpl();

	/**
	 * 多筆列表資料
	 */
	private List<Module> modules = new LinkedList<Module>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String moduleName;

	public ModuleAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(moduleCollector.createInquiry());
	}

	public Module getSearcher() {
		return searcher;
	}

	public void setSearcher(Module searcher) {
		this.searcher = searcher;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleName() {
		return moduleName;
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
		modules = moduleService.findModule(inquiry, getLocale(), searcher);
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
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "moduleView.ftl") })
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
		module = moduleService.find(ModuleImpl.class, module.getSeq());
		// 暫存的模組名稱,因應i8n處理
		if (module != null) {
			moduleName = module.getName(getLocale());
		} else {
			addActionMessage(getText("global.data.not.exist"));
		}
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", type = "freemarker", location = "moduleAdd.ftl") })
	public String add() {
		try {
			// 預設模組
			module = moduleService.createModule(moduleCollector
					.getDefaultModule());
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
			@Result(name = "input", type = "freemarker", location = "moduleAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "moduleList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "moduleName", key = "module.names.required"),//
			@RequiredStringValidator(fieldName = "module.path", key = "module.path.required"),//
			@RequiredStringValidator(fieldName = "module.catalogPrefix", key = "module.catalogPrefix.required"),//
	})
	public String addSave() {
		Serializable result = null;
		try {
			// 檢查id
			if (StringHelper.isBlank(module.getId())) {
				addActionError(getText("module.id.required"));
				return INPUT;
			}

			// 預設模組
			Module defaultModule = moduleService.createModule(moduleCollector
					.getDefaultModule());
			// 目錄模組
			module.setCatalogItems(defaultModule.getCatalogItems());
			// 本文模組
			module.setContextItems(defaultModule.getContextItems());

			// 名稱
			module.setName(getLocale(), moduleName);
			//
			result = moduleService.insert(module, userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] {
						String.valueOf(module.getSeq()),
						module.getName(getLocale()) };
				addActionMessage(getText("global.add.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result == null) {
			String[] msgArgs = new String[] { String.valueOf(-1),
					module.getName(getLocale()) };
			addActionError(getText("global.add.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit", results = { @Result(name = "success", type = "freemarker", location = "moduleEdit.ftl") })
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
			@Result(name = "input", type = "freemarker", location = "moduleEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "moduleList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "moduleName", key = "module.names.required"),//
			@RequiredStringValidator(fieldName = "module.path", key = "module.path.required"),//
			@RequiredStringValidator(fieldName = "module.catalogPrefix", key = "module.catalogPrefix.required"),//
	})
	public String editSave() {
		int result = 0;
		try {
			// 檢查id
			if (StringHelper.isBlank(module.getId())) {
				addActionError(getText("module.id.required"));
				return INPUT;
			}

			// 存在的模組
			Module existModule = moduleService.find(ModuleImpl.class,
					module.getSeq());
			if (existModule == null) {
				String[] msgArgs = new String[] {
						String.valueOf(module.getSeq()),
						module.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			module.setNames(existModule.getNames());
			module.setName(getLocale(), moduleName);

			// 目錄模組
			module.setCatalogItems(existModule.getCatalogItems());
			// 本文模組
			module.setContextItems(existModule.getContextItems());
			//
			result = moduleService.update(module);
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(module.getSeq()),
						module.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] { String.valueOf(module.getSeq()),
					module.getName(getLocale()) };
			addActionError(getText("global.save.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 刪除, 多筆刪除,by seqs
	 * 
	 * @return
	 */
	@Action(value = "delete")
	public String delete() {
		List<Module> result = new LinkedList<Module>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = moduleService.delete(ModuleImpl.class, buffs);
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
		if (!seqs.isEmpty() && size < 1) {
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
		return SUCCESS;
	}

}

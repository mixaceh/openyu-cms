package org.openyu.cms.contextType.web.struts2;

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
import org.openyu.cms.contextType.service.ContextTypeService;
import org.openyu.cms.contextType.vo.ContextType;
import org.openyu.cms.contextType.vo.ContextTypeCollector;
import org.openyu.cms.contextType.vo.impl.ContextTypeImpl;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 本文類型控制器
 */
@ParentPackage("default")
@Namespace("/back/service/contextType")
@Results({ @Result(name = "success", type = "freemarker", location = "contextTypeList.ftl") })
public class ContextTypeAction extends AppListActionSupporter {

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger log = LogManager
			.getLogger(ContextTypeAction.class);

	/**
	 * 本文類型服務
	 */
	@Autowired
	@Qualifier("contextTypeService")
	protected transient ContextTypeService contextTypeService;

	/**
	 * 靜態收集器
	 */
	protected transient ContextTypeCollector contextTypeCollector = ContextTypeCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private ContextType searcher = new ContextTypeImpl();

	/**
	 * 單筆資料
	 */
	private ContextType contextType = new ContextTypeImpl();

	/**
	 * 多筆列表資料
	 */
	private List<ContextType> contextTypes = new LinkedList<ContextType>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String contextTypeName;

	public ContextTypeAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(contextTypeCollector.createInquiry());
	}

	public ContextType getSearcher() {
		return searcher;
	}

	public void setSearcher(ContextType searcher) {
		this.searcher = searcher;
	}

	public ContextType getContextType() {
		return contextType;
	}

	public void setContextType(ContextType contextType) {
		this.contextType = contextType;
	}

	public List<ContextType> getContextTypes() {
		return contextTypes;
	}

	public void setContextTypes(List<ContextType> contextTypes) {
		this.contextTypes = contextTypes;
	}

	public String getContextTypeName() {
		return contextTypeName;
	}

	public void setContextTypeName(String contextTypeName) {
		this.contextTypeName = contextTypeName;
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
		contextTypes = contextTypeService.findContextType(inquiry, getLocale(),
				searcher);
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
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "contextTypeView.ftl") })
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
		contextType = contextTypeService.find(ContextTypeImpl.class,
				contextType.getSeq());
		// 暫存的本文類型名稱,因應i8n處理
		if (contextType != null) {
			contextTypeName = contextType.getName(getLocale());
		} else {
			addActionMessage(getText("global.data.not.exist"));
		}
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", type = "freemarker", location = "contextTypeAdd.ftl") })
	public String add() {
		try {
			// 預設本文類型
			contextType = contextTypeService
					.createContextType(contextTypeCollector
							.getDefaultContextType());
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
			@Result(name = "input", type = "freemarker", location = "contextTypeAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "contextTypeList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "contextTypeName", key = "contextType.names.required"),//
	})
	public String addSave() {
		Serializable result = null;
		try {
			// 檢查id
			if (StringHelper.isEmpty(contextType.getId())) {
				addActionError(getText("contextType.id.required"));
				return INPUT;
			}
			// 預設本文類型
			ContextType defalutContextType = contextTypeCollector
					.createContextType(contextTypeCollector
							.getDefaultContextType());

			// 名稱
			contextType.setName(getLocale(), contextTypeName);
			//
			result = contextTypeService.insert(contextType,
					userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] {
						String.valueOf(contextType.getSeq()),
						contextType.getName(getLocale()) };
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
					contextType.getName(getLocale()) };
			addActionError(getText("global.add.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit", results = { @Result(name = "success", type = "freemarker", location = "contextTypeEdit.ftl") })
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
			@Result(name = "input", type = "freemarker", location = "contextTypeEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "contextTypeList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "contextTypeName", key = "contextType.names.required"),//
	})
	public String editSave() {
		int result = 0;
		try {
			// 檢查id
			if (StringHelper.isEmpty(contextType.getId())) {
				addActionError(getText("contextType.id.required"));
				return INPUT;
			}

			// 存在的本文類型
			ContextType existContextType = contextTypeService.find(
					ContextTypeImpl.class, contextType.getSeq());
			if (existContextType == null) {
				String[] msgArgs = new String[] {
						String.valueOf(contextType.getSeq()),
						contextType.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			contextType.setNames(existContextType.getNames());
			contextType.setName(getLocale(), contextTypeName);
			//
			result = contextTypeService.update(contextType,
					userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(contextType.getSeq()),
						contextType.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] {
					String.valueOf(contextType.getSeq()),
					contextType.getName(getLocale()) };
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
		List<ContextType> result = new LinkedList<ContextType>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = contextTypeService.delete(ContextTypeImpl.class, buffs,
					userSession.getUserId());
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

	/**
	 * 重置
	 * 
	 * @return
	 */
	@Action(value = "reset")
	public String reset() {
		try {
			contextTypeService.reset();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		// 返回列表
		return list();
	}
}

package org.openyu.cms.group.web.struts2;

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
import org.openyu.cms.group.service.GroupService;
import org.openyu.cms.group.vo.Group;
import org.openyu.cms.group.vo.GroupCollector;
import org.openyu.cms.group.vo.impl.GroupImpl;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 群組控制器
 */
@ParentPackage("default")
@Namespace("/back/service/group")
@Results({ @Result(name = "success", type = "freemarker", location = "groupList.ftl") })
public class GroupAction extends AppListActionSupporter {

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger log = LogManager
			.getLogger(GroupAction.class);

	/**
	 * 群組服務
	 */
	@Autowired
	@Qualifier("groupService")
	protected transient GroupService groupService;

	/**
	 * 靜態收集器
	 */
	protected transient GroupCollector groupCollector = GroupCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private Group searcher = new GroupImpl();

	/**
	 * 單筆資料
	 */
	private Group group = new GroupImpl();

	/**
	 * 多筆列表資料
	 */
	private List<Group> groups = new LinkedList<Group>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String a;

	public GroupAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(groupCollector.createInquiry());
	}

	public Group getSearcher() {
		return searcher;
	}

	public void setSearcher(Group searcher) {
		this.searcher = searcher;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String getGroupName() {
		return a;
	}

	public void setGroupName(String groupName) {
		this.a = groupName;
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
		groups = groupService.findGroup(inquiry, getLocale(), searcher);
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
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "groupView.ftl") })
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
		group = groupService.find(GroupImpl.class, group.getSeq());
		if (group != null) {
			// 名稱
			a = group.getName(getLocale());
		} else {
			addActionMessage(getText("global.data.not.exist"));
		}
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", type = "freemarker", location = "groupAdd.ftl") })
	public String add() {
		try {
			// 預設群組
			group = groupService.createGroup(groupCollector.getDefaultGroup());
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
			@Result(name = "input", type = "freemarker", location = "groupAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "groupList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "groupName", key = "group.names.required"),//
	})
	public String addSave() {
		Serializable result = null;
		try {
			// 檢查id
			if (StringHelper.isEmpty(group.getId())) {
				addActionError(getText("group.id.required"));
				return INPUT;
			}
			// 預設群組
			Group defalutGropup = groupCollector.createGroup(groupCollector
					.getDefaultGroup());

			// 名稱
			group.setName(getLocale(), a);
			//
			result = groupService.insert(group, userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] {
						String.valueOf(group.getSeq()),
						group.getName(getLocale()) };
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
					group.getName(getLocale()) };
			addActionError(getText("global.add.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit", results = { @Result(name = "success", type = "freemarker", location = "groupEdit.ftl") })
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
			@Result(name = "input", type = "freemarker", location = "groupEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "groupList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "groupName", key = "group.names.required"),//
	})
	public String editSave() {
		int result = 0;
		try {
			// 檢查id
			if (StringHelper.isEmpty(group.getId())) {
				addActionError(getText("group.id.required"));
				return INPUT;
			}

			// 存在的群組
			Group existGroup = groupService.find(GroupImpl.class,
					group.getSeq());
			if (existGroup == null) {
				String[] msgArgs = new String[] {
						String.valueOf(group.getSeq()),
						group.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			group.setNames(existGroup.getNames());
			group.setName(getLocale(), a);
			//
			result = groupService.update(group, userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(group.getSeq()),
						group.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] { String.valueOf(group.getSeq()),
					group.getName(getLocale()) };
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
		List<Group> result = new LinkedList<Group>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = groupService.delete(GroupImpl.class, buffs,
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
			groupService.reset();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		// 返回列表
		return list();
	}
}

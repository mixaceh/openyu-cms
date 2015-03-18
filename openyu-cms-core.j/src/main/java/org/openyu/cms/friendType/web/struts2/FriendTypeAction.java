package org.openyu.cms.friendType.web.struts2;

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
import org.openyu.cms.friendType.service.FriendTypeService;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.FriendTypeCollector;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 友情類型控制器
 */
@ParentPackage("default")
@Namespace("/back/service/friendType")
@Results({ @Result(name = "success", type = "freemarker", location = "friendTypeList.ftl") })
public class FriendTypeAction extends AppListActionSupporter {

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger log = LogManager
			.getLogger(FriendTypeAction.class);

	/**
	 * 友情類型服務
	 */
	@Autowired
	@Qualifier("friendTypeService")
	protected transient FriendTypeService friendTypeService;

	/**
	 * 靜態收集器
	 */
	protected transient FriendTypeCollector friendTypeCollector = FriendTypeCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private FriendType searcher = new FriendTypeImpl();

	/**
	 * 單筆資料
	 */
	private FriendType friendType = new FriendTypeImpl();

	/**
	 * 多筆列表資料
	 */
	private List<FriendType> friendTypes = new LinkedList<FriendType>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String friendTypeName;

	/**
	 * 選擇的網站 seq
	 */
	private long siteSeq;

	public FriendTypeAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(friendTypeCollector.getInquiry());
	}

	public FriendType getSearcher() {
		return searcher;
	}

	public void setSearcher(FriendType searcher) {
		this.searcher = searcher;
	}

	public FriendType getFriendType() {
		return friendType;
	}

	public void setFriendType(FriendType friendType) {
		this.friendType = friendType;
	}

	public List<FriendType> getFriendTypes() {
		return friendTypes;
	}

	public void setFriendTypes(List<FriendType> friendTypes) {
		this.friendTypes = friendTypes;
	}

	public String getFriendTypeName() {
		return friendTypeName;
	}

	public void setFriendTypeName(String friendTypeName) {
		this.friendTypeName = friendTypeName;
	}

	public long getSiteSeq() {
		return siteSeq;
	}

	public void setSiteSeq(long siteSeq) {
		this.siteSeq = siteSeq;
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
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return SUCCESS;
			}

			// 讀取列表
			retrieveList(siteSeq);
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
	protected void retrieveList(long siteSeq) {
		friendTypes = friendTypeService.findFriendType(inquiry, getLocale(),
				siteSeq, searcher);
	}

	/**
	 * 查詢, sql
	 * 
	 * @return
	 */
	@Action(value = "find")
	public String find() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return SUCCESS;
			}

			// 讀取列表
			retrieveList(siteSeq);
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
	 * 顯示單筆,網站別
	 * 
	 * @return
	 */
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "friendTypeView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle(friendType.getSeq());
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 讀取單筆
	 */
	protected void retriveSingle(long seq) {
		friendType = friendTypeService.find(FriendTypeImpl.class, seq);
		// 暫存的友情類型名稱,因應i8n處理
		if (friendType != null) {
			friendTypeName = friendType.getName(getLocale());
		} else {
			addActionMessage(getText("global.data.not.exist"));
		}
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "add", results = {
			@Result(name = "success", type = "freemarker", location = "friendTypeAdd.ftl"),
			@Result(name = "list", type = "freemarker", location = "friendTypeList.ftl") })
	public String add() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			// 預設友情類型
			friendType = friendTypeService.createFriendType(friendTypeCollector
					.getDefaultFriendType());
			// session網站傳給網頁
			this.siteSeq = siteSeq;
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新增存檔,網站別
	 * 
	 * @return
	 */
	@Action(value = "addSave", results = {
			@Result(name = "input", type = "freemarker", location = "friendTypeAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "friendTypeList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "friendTypeName", key = "friendType.names.required"),//
	})
	public String addSave() {
		Serializable result = null;
		try {
			Site site = siteService.find(SiteImpl.class, siteSeq);
			if (site == null) {
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return INPUT;
			}

			// 網站
			friendType.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(friendType.getId())) {
				addActionError(getText("friendType.id.required"));
				return INPUT;
			}
			// 預設友情類型
			FriendType defalutFriendType = friendTypeCollector
					.createFriendType(friendTypeCollector
							.getDefaultFriendType());

			// 名稱
			friendType.setName(getLocale(), friendTypeName);
			//
			result = friendTypeService.insert(friendType,
					userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] {
						String.valueOf(friendType.getSeq()),
						friendType.getName(getLocale()) };
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
					friendType.getName(getLocale()) };
			addActionError(getText("global.add.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit", results = {
			@Result(name = "success", type = "freemarker", location = "friendTypeEdit.ftl"),
			@Result(name = "list", type = "freemarker", location = "friendTypeList.ftl") })
	public String edit() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			// 讀取單筆
			retriveSingle(friendType.getSeq());
			// session網站傳給網頁
			this.siteSeq = siteSeq;
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
			@Result(name = "input", type = "freemarker", location = "friendTypeEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "friendTypeList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "friendTypeName", key = "friendType.names.required"),//
	})
	public String editSave() {
		int result = 0;
		try {
			Site site = siteService.find(SiteImpl.class, siteSeq);
			if (site == null) {
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));
				return INPUT;
			}

			// 網站
			friendType.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(friendType.getId())) {
				addActionError(getText("friendType.id.required"));
				return INPUT;
			}

			// 存在的友情類型
			FriendType existFriendType = friendTypeService.find(
					FriendTypeImpl.class, friendType.getSeq());
			if (existFriendType == null) {
				String[] msgArgs = new String[] {
						String.valueOf(friendType.getSeq()),
						friendType.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			friendType.setNames(existFriendType.getNames());
			friendType.setName(getLocale(), friendTypeName);
			//
			result = friendTypeService.update(friendType,
					userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(friendType.getSeq()),
						friendType.getName(getLocale()) };
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
					String.valueOf(friendType.getSeq()),
					friendType.getName(getLocale()) };
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
		List<FriendType> result = new LinkedList<FriendType>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = friendTypeService.delete(FriendTypeImpl.class, buffs,
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
			friendTypeService.reset();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		// 返回列表
		return list();
	}
}

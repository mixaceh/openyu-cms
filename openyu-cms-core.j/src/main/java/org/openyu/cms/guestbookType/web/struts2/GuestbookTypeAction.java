package org.openyu.cms.guestbookType.web.struts2;

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
import org.openyu.cms.guestbookType.service.GuestbookTypeService;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.GuestbookTypeCollector;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 留言類型控制器
 */
@ParentPackage("default")
@Namespace("/back/service/guestbookType")
@Results({ @Result(name = "success", type = "freemarker", location = "guestbookTypeList.ftl") })
public class GuestbookTypeAction extends AppListActionSupporter {

	private static final long serialVersionUID = -7486035503164720592L;

	private static transient final Logger log = LogManager
			.getLogger(GuestbookTypeAction.class);

	/**
	 * 留言類型服務
	 */
	@Autowired
	@Qualifier("guestbookTypeService")
	protected transient GuestbookTypeService guestbookTypeService;

	/**
	 * 靜態收集器
	 */
	protected transient GuestbookTypeCollector guestbookTypeCollector = GuestbookTypeCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private GuestbookType searcher = new GuestbookTypeImpl();

	/**
	 * 單筆資料
	 */
	private GuestbookType guestbookType = new GuestbookTypeImpl();

	/**
	 * 多筆列表資料
	 */
	private List<GuestbookType> guestbookTypes = new LinkedList<GuestbookType>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String guestbookTypeName;

	/**
	 * 暫存的描述,因應i8n處理
	 */
	private String guestbookTypeDescription;

	/**
	 * 選擇的網站 seq
	 */
	private long siteSeq;

	public GuestbookTypeAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(guestbookTypeCollector.getInquiry());
	}

	public GuestbookType getSearcher() {
		return searcher;
	}

	public void setSearcher(GuestbookType searcher) {
		this.searcher = searcher;
	}

	public GuestbookType getGuestbookType() {
		return guestbookType;
	}

	public void setGuestbookType(GuestbookType guestbookType) {
		this.guestbookType = guestbookType;
	}

	public List<GuestbookType> getGuestbookTypes() {
		return guestbookTypes;
	}

	public void setGuestbookTypes(List<GuestbookType> guestbookTypes) {
		this.guestbookTypes = guestbookTypes;
	}

	public String getGuestbookTypeName() {
		return guestbookTypeName;
	}

	public void setGuestbookTypeName(String guestbookTypeName) {
		this.guestbookTypeName = guestbookTypeName;
	}

	public String getGuestbookTypeDescription() {
		return guestbookTypeDescription;
	}

	public void setGuestbookTypeDescription(String guestbookTypeDescription) {
		this.guestbookTypeDescription = guestbookTypeDescription;
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
		guestbookTypes = guestbookTypeService.findGuestbookType(inquiry,
				getLocale(), siteSeq, searcher);
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
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "guestbookTypeView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle(guestbookType.getSeq());
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
		guestbookType = guestbookTypeService.find(GuestbookTypeImpl.class, seq);
		if (guestbookType != null) {
			// 暫存的名稱,因應i8n處理
			guestbookTypeName = guestbookType.getName(getLocale());
			// 暫存的描述,因應i8n處理
			guestbookTypeDescription = guestbookType
					.getDescription(getLocale());
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
			@Result(name = "success", type = "freemarker", location = "guestbookTypeAdd.ftl"),
			@Result(name = "list", type = "freemarker", location = "guestbookTypeList.ftl") })
	public String add() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			// 預設留言類型
			guestbookType = guestbookTypeService
					.createGuestbookType(guestbookTypeCollector
							.getDefaultGuestbookType());
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
			@Result(name = "input", type = "freemarker", location = "guestbookTypeAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "guestbookTypeList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "guestbookTypeName", key = "guestbookType.names.required"),//
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
			guestbookType.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(guestbookType.getId())) {
				addActionError(getText("guestbookType.id.required"));
				return INPUT;
			}
			// 預設留言類型
			GuestbookType defalutGuestbookType = guestbookTypeCollector
					.createGuestbookType(guestbookTypeCollector
							.getDefaultGuestbookType());

			// 名稱
			guestbookType.setName(getLocale(), guestbookTypeName);
			// 描述
			guestbookType.setDescription(getLocale(), guestbookTypeDescription);
			//
			result = guestbookTypeService.insert(guestbookType,
					userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] {
						String.valueOf(guestbookType.getSeq()),
						guestbookType.getName(getLocale()) };
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
					guestbookType.getName(getLocale()) };
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
			@Result(name = "success", type = "freemarker", location = "guestbookTypeEdit.ftl"),
			@Result(name = "list", type = "freemarker", location = "guestbookTypeList.ftl") })
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
			retriveSingle(guestbookType.getSeq());
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
			@Result(name = "input", type = "freemarker", location = "guestbookTypeEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "guestbookTypeList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "guestbookTypeName", key = "guestbookType.names.required"),//
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
			guestbookType.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(guestbookType.getId())) {
				addActionError(getText("guestbookType.id.required"));
				return INPUT;
			}

			// 存在的留言類型
			GuestbookType existGuestbookType = guestbookTypeService.find(
					GuestbookTypeImpl.class, guestbookType.getSeq());
			if (existGuestbookType == null) {
				String[] msgArgs = new String[] {
						String.valueOf(guestbookType.getSeq()),
						guestbookType.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			guestbookType.setNames(existGuestbookType.getNames());
			guestbookType.setName(getLocale(), guestbookTypeName);
			// 描述
			guestbookType.setDescriptions(existGuestbookType.getDescriptions());
			guestbookType.setDescription(getLocale(), guestbookTypeDescription);
			//
			result = guestbookTypeService.update(guestbookType,
					userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(guestbookType.getSeq()),
						guestbookType.getName(getLocale()) };
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
					String.valueOf(guestbookType.getSeq()),
					guestbookType.getName(getLocale()) };
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
		List<GuestbookType> result = new LinkedList<GuestbookType>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = guestbookTypeService.delete(GuestbookTypeImpl.class,
					buffs, userSession.getUserId());
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
			guestbookTypeService.reset();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		// 返回列表
		return list();
	}
}

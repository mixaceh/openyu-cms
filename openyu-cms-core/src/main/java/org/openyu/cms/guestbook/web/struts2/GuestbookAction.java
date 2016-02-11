package org.openyu.cms.guestbook.web.struts2;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import org.openyu.cms.guestbook.service.GuestbookService;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbook.vo.GuestbookCollector;
import org.openyu.cms.guestbook.vo.impl.GuestbookImpl;
import org.openyu.cms.guestbookType.service.GuestbookTypeService;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.util.CollectionHelper;
import org.openyu.commons.util.DateHelper;

/**
 * 留言控制器
 */
@ParentPackage("default")
@Namespace("/back/service/guestbook")
@Results({ @Result(name = "success", type = "freemarker", location = "guestbookList.ftl") })
public class GuestbookAction extends AppListActionSupporter {

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger log = LogManager
			.getLogger(GuestbookAction.class);

	/**
	 * 留言服務
	 */
	@Autowired
	@Qualifier("guestbookService")
	protected transient GuestbookService guestbookService;

	/**
	 * 留言類型服務
	 */
	@Autowired
	@Qualifier("guestbookTypeService")
	protected transient GuestbookTypeService guestbookTypeService;

	/**
	 * 靜態收集器
	 */
	protected transient GuestbookCollector guestbookCollector = GuestbookCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private Guestbook searcher = new GuestbookImpl();

	/**
	 * 留言類型搜尋者
	 */
	private GuestbookType guestbookTypeSearcher = new GuestbookTypeImpl();

	/**
	 * 單筆資料
	 */
	private Guestbook guestbook = new GuestbookImpl();

	/**
	 * 多筆列表資料
	 */
	private List<Guestbook> guestbooks = new LinkedList<Guestbook>();

	/**
	 * 暫存的標題,因應i8n處理
	 */
	private String guestbookTitle;

	/**
	 * 暫存的留言內容,因應i8n處理
	 */
	private String guestbookContent;

	/**
	 * 暫存的回復內容,因應i8n處理
	 */
	private String guestbookReply;

	/**
	 * 留言類型選項 seq
	 */
	private long guestbookTypeOptionSeq;

	/**
	 * 選擇的網站 seq
	 */
	private long siteSeq;

	public GuestbookAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(guestbookCollector.getInquiry());
	}

	public Guestbook getSearcher() {
		return searcher;
	}

	public void setSearcher(Guestbook searcher) {
		this.searcher = searcher;
	}

	public GuestbookType getGuestbookTypeSearcher() {
		return guestbookTypeSearcher;
	}

	public void setGuestbookTypeSearcher(GuestbookType guestbookTypeSearcher) {
		this.guestbookTypeSearcher = guestbookTypeSearcher;
	}

	public Guestbook getGuestbook() {
		return guestbook;
	}

	public void setGuestbook(Guestbook guestbook) {
		this.guestbook = guestbook;
	}

	public List<Guestbook> getGuestbooks() {
		return guestbooks;
	}

	public void setGuestbooks(List<Guestbook> guestbooks) {
		this.guestbooks = guestbooks;
	}

	public String getGuestbookTitle() {
		return guestbookTitle;
	}

	public void setGuestbookTitle(String guestbookTitle) {
		this.guestbookTitle = guestbookTitle;
	}

	public String getGuestbookContent() {
		return guestbookContent;
	}

	public void setGuestbookContent(String guestbookContent) {
		this.guestbookContent = guestbookContent;
	}

	public String getGuestbookReply() {
		return guestbookReply;
	}

	public void setGuestbookReply(String guestbookReply) {
		this.guestbookReply = guestbookReply;
	}

	public long getSiteSeq() {
		return siteSeq;
	}

	public void setSiteSeq(long siteSeq) {
		this.siteSeq = siteSeq;
	}

	/**
	 * 留言類型選項 seq
	 * 
	 * @return
	 */
	public long getGuestbookTypeOptionSeq() {
		return guestbookTypeOptionSeq;
	}

	public void setGuestbookTypeOptionSeq(long guestbookTypeOptionSeq) {
		this.guestbookTypeOptionSeq = guestbookTypeOptionSeq;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------
	/**
	 * 留言類型選項
	 * 
	 * @return
	 */
	public List<GuestbookType> getGuestbookTypeOptions() {
		return guestbookTypeService.getGuestbookTypes(userSession.getSiteId());
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
		guestbooks = guestbookService.findGuestbook(inquiry, getLocale(),
				siteSeq, guestbookTypeSearcher, searcher);
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
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "guestbookView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle(guestbook.getSeq());
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
		guestbook = guestbookService.find(GuestbookImpl.class, seq);

		if (guestbook != null) {
			// 暫存的標題,因應i8n處理
			guestbookTitle = guestbook.getTitle(getLocale());
			// 暫存的留言內容,因應i8n處理
			guestbookContent = guestbook.getContent(getLocale());
			// 暫存的回復內容,因應i8n處理
			guestbookReply = guestbook.getReply(getLocale());
			// 留言類型
			GuestbookType guestbookType = guestbook.getGuestbookType();
			if (guestbookType != null) {
				guestbookTypeOptionSeq = guestbookType.getSeq();
			}
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
			@Result(name = "success", type = "freemarker", location = "guestbookAdd.ftl"),
			@Result(name = "list", type = "freemarker", location = "guestbookList.ftl") })
	public String add() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				// 列表
				list();
				return LIST;
			}

			// 預設留言
			guestbook = guestbookService.createGuestbook(guestbookCollector
					.getDefaultGuestbook());
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
			@Result(name = "input", type = "freemarker", location = "guestbookAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "guestbookList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	// @Validations(requiredStrings = {
	// @RequiredStringValidator(fieldName = "guestbookTypeOptionSeq", key =
	// "guestbook.guestbookType.required"),//
	// })
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
			guestbook.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(guestbook.getId())) {
				addActionError(getText("guestbook.id.required"));
				return INPUT;
			}

			// 預設留言
			Guestbook defalutGuestbook = guestbookCollector
					.createGuestbook(guestbookCollector.getDefaultGuestbook());

			// 標題
			guestbook.setTitle(getLocale(), guestbookTitle);
			// 留言內容
			guestbook.setContent(getLocale(), guestbookContent);
			// 回復內容
			guestbook.setReply(getLocale(), guestbookReply);
			// 留言類型
			if (guestbookTypeOptionSeq > 0) {
				GuestbookType guestbookType = guestbookTypeService.find(
						GuestbookTypeImpl.class, guestbookTypeOptionSeq);
				if (guestbookType != null) {
					guestbook.setGuestbookType(guestbookType);
				}
			}
			//
			String ip = this.getIpAddr(request);
			guestbook.setIp(ip);
			//
			guestbook.setGuestbookDate(DateHelper.today());
			//
			result = guestbookService
					.insert(guestbook, userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] {
						String.valueOf(guestbook.getSeq()),
						guestbook.getTitle(getLocale()) };
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
					guestbook.getTitle(getLocale()) };
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
			@Result(name = "success", type = "freemarker", location = "guestbookEdit.ftl"),
			@Result(name = "list", type = "freemarker", location = "guestbookList.ftl") })
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
			retriveSingle(guestbook.getSeq());
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
			@Result(name = "input", type = "freemarker", location = "guestbookEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "guestbookList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	// @Validations(requiredStrings = {
	// @RequiredStringValidator(fieldName = "guestbookTypeOptionSeq", key =
	// "guestbook.guestbookType.required"),//
	// })
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
			guestbook.setSite(site);

			// 檢查id
			if (StringHelper.isEmpty(guestbook.getId())) {
				addActionError(getText("guestbook.id.required"));
				return INPUT;
			}

			// 存在的留言
			Guestbook existGuestbook = guestbookService.find(
					GuestbookImpl.class, guestbook.getSeq());
			if (existGuestbook == null) {
				String[] msgArgs = new String[] {
						String.valueOf(guestbook.getSeq()),
						guestbook.getTitle(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 標題
			guestbook.setTitles(existGuestbook.getTitles());
			guestbook.setTitle(getLocale(), guestbookTitle);
			// 留言內容
			guestbook.setContents(existGuestbook.getContents());
			guestbook.setContent(getLocale(), guestbookContent);
			// 回覆內容
			guestbook.setReplys(existGuestbook.getReplys());
			guestbook.setReply(getLocale(), guestbookReply);
			// 留言類型
			if (guestbookTypeOptionSeq > 0) {
				GuestbookType guestbookType = guestbookTypeService.find(
						GuestbookTypeImpl.class, guestbookTypeOptionSeq);
				if (guestbookType != null) {
					guestbook.setGuestbookType(guestbookType);
				}
			}
			//
			String ip = this.getIpAddr(request);
			guestbook.setIp(ip);
			//
			result = guestbookService
					.update(guestbook, userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(guestbook.getSeq()),
						guestbook.getTitle(getLocale()) };
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
					String.valueOf(guestbook.getSeq()),
					guestbook.getTitle(getLocale()) };
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
		List<Guestbook> result = new LinkedList<Guestbook>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = guestbookService.delete(GuestbookImpl.class, buffs,
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
	 * 獲取訪問者IP
	 * 
	 * 在一般情況下使用Request.getRemoteAddr()即可，但是經過nginx等反向代理軟體後，這個方法會失效。
	 * 
	 * 本方法先從Header中獲取X-Real-IP，如果不存在再從X-Forwarded-For獲得第一個IP(用,分割)，
	 * 如果還不存在則調用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理後會有多個IP值，第一個為真實IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
}

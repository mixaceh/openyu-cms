package org.openyu.cms.site.web.struts2;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
import org.openyu.cms.ftp.service.FtpService;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.cms.ftp.vo.impl.FtpImpl;
import org.openyu.cms.site.service.SiteService;
import org.openyu.cms.site.vo.ModifyTypeOption;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.cms.site.vo.SiteCollector;
import org.openyu.cms.site.vo.VerifyTypeOption;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.lang.StringHelper;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

/**
 * 網站控制器
 */
@ParentPackage("default")
@Namespace("/back/service/site")
@Results({ @Result(name = "success", type = "freemarker", location = "siteList.ftl") })
public class SiteAction extends AppListActionSupporter {
	private static final long serialVersionUID = -8184746975973063374L;

	private static transient final Logger log = LogManager
			.getLogger(SiteAction.class);

	/**
	 * 網站服務
	 */
	@Autowired
	@Qualifier("siteService")
	protected transient SiteService siteService;

	/**
	 * 附件FTP服務
	 */
	@Autowired
	@Qualifier("ftpService")
	protected transient FtpService ftpService;

	/**
	 * 靜態收集器
	 */
	protected transient SiteCollector siteCollector = SiteCollector
			.getInstance();

	/**
	 * 搜尋者
	 */
	private Site searcher = new SiteImpl();

	/**
	 * 單筆資料
	 */
	private Site site = new SiteImpl();

	/**
	 * 多筆列表資料
	 */
	private List<Site> sites = new LinkedList<Site>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String siteName;

	/**
	 * 暫存的簡稱,因應i8n處理
	 */
	private String siteShortName;

	/**
	 * 附件FTP選項 seq
	 */
	private long ftpOptionSeq;

	public SiteAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(siteCollector.createInquiry());
	}

	public Site getSearcher() {
		return searcher;
	}

	public void setSearcher(Site searcher) {
		this.searcher = searcher;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteShortName() {
		return siteShortName;
	}

	public void setSiteShortName(String siteShortName) {
		this.siteShortName = siteShortName;
	}

	/**
	 * 附件FTP選項
	 * 
	 * @return
	 */
	public List<Ftp> getFtpOptions() {
		return ftpService.getFtps();
	}

	/**
	 * 附件FTP選項 seq
	 * 
	 * @return
	 */
	public long getFtpOptionSeq() {
		return ftpOptionSeq;
	}

	public void setFtpOptionSeq(long ftpOptionSeq) {
		this.ftpOptionSeq = ftpOptionSeq;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------

	/**
	 * 訪問協定選項
	 * 
	 * @return
	 */
	public List<String> getProtocolOptions() {
		return siteCollector.getProtocolOptions();
	}

	/**
	 * 動態頁尾碼選項
	 * 
	 * @return
	 */
	public List<String> getDynamicSuffixOptions() {
		return siteCollector.getDynamicSuffixOptions();
	}

	/**
	 * 靜態頁尾碼選項
	 * 
	 * @return
	 */
	public List<String> getStaticSuffixOptions() {
		return siteCollector.getStaticSuffixOptions();
	}

	/**
	 * 終審類別選項
	 * 
	 * @return
	 */
	public List<VerifyTypeOption> getVerifyTypeOptions() {
		return siteCollector.getVerifyTypeOptions();
	}

	/**
	 * 終審類別(0/1/2)選項名稱
	 * 
	 * @param verifyType
	 * @return
	 */
	public String getVerifyTypeName(VerifyType verifyType) {
		return getVerifyTypeName(verifyType, getLocale());
	}

	/**
	 * 終審類別(0/1/2)選項名稱
	 * 
	 * @param verifyType
	 * @param locale
	 * @return
	 */
	public String getVerifyTypeName(VerifyType verifyType, Locale locale) {
		return siteCollector.getVerifyTypeName(verifyType, locale);
	}

	/**
	 * 審核後修改類別選項
	 * 
	 * @return
	 */
	public List<ModifyTypeOption> getModifyTypeOptions() {
		return siteCollector.getModifyTypeOptions();
	}

	/**
	 * 審核後修改類別(1/2/3)選項名稱
	 * 
	 * @param modifyType
	 * @return
	 */
	public String getModifyTypeName(ModifyType modifyType) {
		return getModifyTypeName(modifyType, getLocale());
	}

	/**
	 * 審核後修改類別(1/2/3)選項名稱
	 * 
	 * @param modifyType
	 * @param locale
	 * @return
	 */
	public String getModifyTypeName(ModifyType modifyType, Locale locale) {
		return siteCollector.getModifyTypeName(modifyType, locale);
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
		sites = siteService.findSite(inquiry, getLocale(), searcher);
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
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "siteView.ftl") })
	public String view() {
		try {
			// 讀取單筆
			retriveSingle(site.getSeq());
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
		site = siteService.find(SiteImpl.class, seq);
		if (site != null) {
			// 名稱
			siteName = site.getName(getLocale());
			// 簡稱
			siteShortName = site.getShortName(getLocale());
			// 附件FTP
			Ftp ftp = site.getFtp();
			if (ftp != null) {
				ftpOptionSeq = ftp.getSeq();
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
	@Action(value = "add", results = { @Result(name = "success", type = "freemarker", location = "siteAdd.ftl") })
	public String add() {
		try {
			// 預設網站
			site = siteService.createSite(siteCollector.getDefaultSite());
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
			@Result(name = "input", type = "freemarker", location = "siteAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "siteList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "siteName", key = "site.names.required"),//
			@RequiredStringValidator(fieldName = "siteShortName", key = "site.shortNames.required"),//
			@RequiredStringValidator(fieldName = "site.resourcePath", key = "site.resourcePath.required"),//
			@RequiredStringValidator(fieldName = "site.templatePath", key = "site.templatePath.required"),//
	})
	public String addSave() {
		Serializable result = null;
		try {
			// 檢查id
			if (StringHelper.isBlank(site.getId())) {
				addActionError(getText("site.id.required"));
				return INPUT;
			}

			// 預設網站
			Site defaultSite = siteService.createSite(siteCollector
					.getDefaultSite());

			// 名稱
			site.setName(getLocale(), siteName);
			// 簡稱
			site.setShortName(getLocale(), siteShortName);
			// 附件FTP
			if (ftpOptionSeq > 0) {
				Ftp ftp = ftpService.find(FtpImpl.class, ftpOptionSeq);
				if (ftp != null) {
					site.setFtp(ftp);
				}
			}
			//
			result = siteService.insert(site, userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] {
						String.valueOf(site.getSeq()),
						site.getName(getLocale()) };
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
					site.getName(getLocale()) };
			addActionError(getText("global.add.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit", results = { @Result(name = "success", type = "freemarker", location = "siteEdit.ftl") })
	public String edit() {
		try {
			// 讀取單筆
			retriveSingle(site.getSeq());
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
			@Result(name = "input", type = "freemarker", location = "siteEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "siteList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "siteName", key = "site.names.required"),//
			@RequiredStringValidator(fieldName = "siteShortName", key = "site.shortNames.required"),//
			@RequiredStringValidator(fieldName = "site.resourcePath", key = "site.resourcePath.required"),//
			@RequiredStringValidator(fieldName = "site.templatePath", key = "site.templatePath.required"),//
	})
	public String editSave() {
		int result = 0;
		try {
			// 檢查id
			if (StringHelper.isBlank(site.getId())) {
				addActionError(getText("site.id.required"));
				return INPUT;
			}

			// 存在的網站
			Site existSite = siteService.find(SiteImpl.class, site.getSeq());
			if (existSite == null) {
				String[] msgArgs = new String[] {
						String.valueOf(site.getSeq()),
						site.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			site.setNames(existSite.getNames());
			site.setName(getLocale(), siteName);

			// 簡稱
			site.setShortNames(existSite.getShortNames());
			site.setShortName(getLocale(), siteShortName);
			// 附件FTP
			if (ftpOptionSeq > 0) {
				Ftp ftp = ftpService.find(FtpImpl.class, ftpOptionSeq);
				if (ftp != null) {
					site.setFtp(ftp);
				}
			}
			// 屬性
			site.setAttributes(existSite.getAttributes());
			// 文字
			site.setTexts(existSite.getTexts());
			// 設定
			site.setConfigs(existSite.getConfigs());
			//
			result = siteService.update(site);
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(site.getSeq()),
						site.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] { String.valueOf(site.getSeq()),
					site.getName(getLocale()) };
			addActionError(getText("global.save.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯,網站別
	 * 
	 * @return
	 */
	@Action(value = "editSelf", results = {
			@Result(name = "success", type = "freemarker", location = "siteEditSelf.ftl"),
			@Result(name = "list", type = "freemarker", location = "siteList.ftl") })
	public String editSelf() {
		try {
			// 取session網站
			long siteSeq = userSession.getSiteSeq();
			if (siteSeq < 1) {
				String[] msgArgs = new String[] { String.valueOf(siteSeq) };
				addActionError(getText("global.site.not.exist", msgArgs));

				// 列表
				list();
				return LIST;
			}

			// 讀取單筆
			retriveSingle(siteSeq);

		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 編輯存檔,網站別
	 * 
	 * @return
	 */
	@Action(value = "editSelfSave", results = {
			@Result(name = "success", type = "freemarker", location = "siteEditSelf.ftl"),
			@Result(name = "input", type = "freemarker", location = "siteEditSelf.ftl"),
			@Result(name = "list", type = "freemarker", location = "siteList.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "siteList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "siteName", key = "site.names.required"),//
			@RequiredStringValidator(fieldName = "siteShortName", key = "site.shortNames.required"),//
			@RequiredStringValidator(fieldName = "site.resourcePath", key = "site.resourcePath.required"),//
			@RequiredStringValidator(fieldName = "site.templatePath", key = "site.templatePath.required"),//
	})
	public String editSelfSave() {
		int result = 0;
		try {
			// 檢查id
			if (StringHelper.isBlank(site.getId())) {
				addActionError(getText("site.id.required"));
				return INPUT;
			}

			// 存在的網站
			Site existSite = siteService.find(SiteImpl.class, site.getSeq());
			if (existSite == null) {
				String[] msgArgs = new String[] {
						String.valueOf(site.getSeq()),
						site.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			site.setNames(existSite.getNames());
			site.setName(getLocale(), siteName);

			// 簡稱
			site.setShortNames(existSite.getShortNames());
			site.setShortName(getLocale(), siteShortName);
			// 附件FTP
			if (ftpOptionSeq > 0) {
				Ftp ftp = ftpService.find(FtpImpl.class, ftpOptionSeq);
				if (ftp != null) {
					site.setFtp(ftp);
				}
			}
			// 屬性
			site.setAttributes(existSite.getAttributes());
			// 文字
			site.setTexts(existSite.getTexts());
			// 設定
			site.setConfigs(existSite.getConfigs());
			//
			result = siteService.update(site);
			if (result > 0) {
				String[] msgArgs = new String[] {
						String.valueOf(site.getSeq()),
						site.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回編輯
				return editSelf();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] { String.valueOf(site.getSeq()),
					site.getName(getLocale()) };
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
		List<Site> result = new LinkedList<Site>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = siteService.delete(SiteImpl.class, buffs);
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
			siteService.reset();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		// 返回列表
		return list();
	}
}

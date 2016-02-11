package org.openyu.cms.ftp.web.struts2;

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
import org.openyu.cms.ftp.service.FtpService;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.cms.ftp.vo.FtpCollector;
import org.openyu.cms.ftp.vo.impl.FtpImpl;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 群組控制器
 */
@ParentPackage("default")
@Namespace("/back/service/ftp")
@Results({ @Result(name = "success", type = "freemarker", location = "ftpList.ftl") })
public class FtpAction extends AppListActionSupporter {

	private static final long serialVersionUID = 8199262629477758758L;

	private static transient final Logger log = LogManager
			.getLogger(FtpAction.class);

	/**
	 * ftp服務
	 */
	@Autowired
	@Qualifier("ftpService")
	protected transient FtpService ftpService;

	/**
	 * 靜態收集器
	 */
	protected transient FtpCollector ftpCollector = FtpCollector.getInstance();

	/**
	 * 搜尋者
	 */
	private Ftp searcher = new FtpImpl();

	/**
	 * 單筆資料
	 */
	private Ftp ftp = new FtpImpl();

	/**
	 * 多筆列表資料
	 */
	private List<Ftp> ftps = new LinkedList<Ftp>();

	/**
	 * 暫存的名稱,因應i8n處理
	 */
	private String ftpName;

	/**
	 * 是否改變密碼
	 */
	private boolean changePassword;

	public FtpAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 初始化查詢條件
		initializeInquiry(ftpCollector.createInquiry());
	}

	public Ftp getSearcher() {
		return searcher;
	}

	public void setSearcher(Ftp searcher) {
		this.searcher = searcher;
	}

	public Ftp getFtp() {
		return ftp;
	}

	public void setFtp(Ftp ftp) {
		this.ftp = ftp;
	}

	public List<Ftp> getFtps() {
		return ftps;
	}

	public void setFtps(List<Ftp> ftps) {
		this.ftps = ftps;
	}

	public String getFtpName() {
		return ftpName;
	}

	public void setFtpName(String ftpName) {
		this.ftpName = ftpName;
	}

	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	// --------------------------------------------------
	// 選項
	// --------------------------------------------------

	/**
	 * 編碼選項
	 * 
	 * @return
	 */
	public List<String> getEncodingOptions() {
		return ftpCollector.getEncodingOptions();
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
		ftps = ftpService.findFtp(inquiry, getLocale(), searcher);
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
	@Action(value = "view", results = { @Result(name = "success", type = "freemarker", location = "ftpView.ftl") })
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
		ftp = ftpService.find(FtpImpl.class, ftp.getSeq());
		// 暫存的ftp名稱,因應i8n處理
		if (ftp != null) {
			ftpName = ftp.getName(getLocale());
		}
		//
		else {
			addActionMessage(getText("global.data.not.exist"));
		}
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", type = "freemarker", location = "ftpAdd.ftl") })
	public String add() {
		try {
			// 預設ftp
			ftp = ftpService.createFtp(ftpCollector.getDefaultFtp());
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
			@Result(name = "input", type = "freemarker", location = "ftpAdd.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "ftpList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "ftpName", key = "ftp.names.required"),//
			@RequiredStringValidator(fieldName = "ftp.ip", key = "ftp.ip.required"),//
			@RequiredStringValidator(fieldName = "ftp.account", key = "ftp.account.required"),//
			@RequiredStringValidator(fieldName = "ftp.encoding", key = "ftp.encoding.required"),//
			@RequiredStringValidator(fieldName = "ftp.url", key = "ftp.url.required"),//
	})
	public String addSave() {
		Serializable result = null;
		try {
			// 檢查id
			if (StringHelper.isEmpty(ftp.getId())) {
				addActionError(getText("ftp.id.required"));
				return INPUT;
			}

			// 預設ftp
			Ftp defaultFtp = ftpCollector.createFtp(ftpCollector
					.getDefaultFtp());

			// 名稱
			ftp.setName(getLocale(), ftpName);

			// 密碼加密
			String buff = ftpService.encrypt(ftp.getPassword());
			// 加密後長度不可超過32
			if (buff.length() > 32) {
				addActionError(getText("global.password.too.long"));
				return INPUT;
			}
			//
			ftp.setPassword(buff);
			//
			result = ftpService.insert(ftp, userSession.getUserId());
			if (result != null) {
				String[] msgArgs = new String[] { String.valueOf(ftp.getSeq()),
						ftp.getName(getLocale()) };
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
					ftp.getName(getLocale()) };
			addActionError(getText("global.add.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯
	 * 
	 * @return
	 */
	@Action(value = "edit", results = { @Result(name = "success", type = "freemarker", location = "ftpEdit.ftl") })
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
			@Result(name = "input", type = "freemarker", location = "ftpEdit.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "ftpList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "ftpName", key = "ftp.names.required"),//
			@RequiredStringValidator(fieldName = "ftp.ip", key = "ftp.ip.required"),//
			@RequiredStringValidator(fieldName = "ftp.account", key = "ftp.account.required"),//
			@RequiredStringValidator(fieldName = "ftp.encoding", key = "ftp.encoding.required"),//
			@RequiredStringValidator(fieldName = "ftp.url", key = "ftp.url.required"),//
	})
	public String editSave() {
		int result = 0;
		try {
			// 檢查id
			if (StringHelper.isEmpty(ftp.getId())) {
				addActionError(getText("ftp.id.required"));
				return INPUT;
			}

			// 存在的ftp
			Ftp existFtp = ftpService.find(FtpImpl.class, ftp.getSeq());
			if (existFtp == null) {
				String[] msgArgs = new String[] { String.valueOf(ftp.getSeq()),
						ftp.getName(getLocale()) };
				addActionError(getText("global.data.not.exist", msgArgs));
				return INPUT;
			}

			// 名稱
			ftp.setNames(existFtp.getNames());
			ftp.setName(getLocale(), ftpName);

			// 改變密碼加密
			if (changePassword) {
				String buff = ftpService.encrypt(ftp.getPassword());
				// 加密後長度不可超過32
				if (buff.length() > 32) {
					addActionError(getText("global.password.too.long"));
					return INPUT;
				}
				//
				ftp.setPassword(buff);
				changePassword = false;
			}
			//
			result = ftpService.update(ftp, userSession.getUserId());
			if (result > 0) {
				String[] msgArgs = new String[] { String.valueOf(ftp.getSeq()),
						ftp.getName(getLocale()) };
				addActionMessage(getText("global.save.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (result < 1) {
			String[] msgArgs = new String[] { String.valueOf(ftp.getSeq()),
					ftp.getName(getLocale()) };
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
		List<Ftp> result = new LinkedList<Ftp>();
		int size = 0;
		try {
			// String轉成Long
			List<Serializable> buffs = CollectionHelper.toLongs(seqs);
			result = ftpService.delete(FtpImpl.class, buffs,
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
			ftpService.reset();
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		// 返回列表
		return list();
	}
}

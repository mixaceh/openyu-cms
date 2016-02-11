package org.openyu.cms.resource.web.struts2;

import java.io.File;
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

import org.openyu.cms.app.util.AppConfigHelper;
import org.openyu.cms.app.web.struts2.supporter.AppListActionSupporter;
import org.openyu.cms.archive.vo.Archive;
import org.openyu.cms.archive.vo.Breadcrumb;
import org.openyu.cms.resource.service.ResourceService;
import org.openyu.commons.lang.ArrayHelper;
import org.openyu.commons.lang.StringHelper;
import org.openyu.commons.util.CollectionHelper;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * 資源控制器
 */
@ParentPackage("default")
@Namespace("/back/service/resource")
@Results({ @Result(name = "success", type = "freemarker", location = "resourceList.ftl") })
public class ResourceAction extends AppListActionSupporter {
	private static final long serialVersionUID = -8964759933069005036L;

	private static transient final Logger log = LogManager
			.getLogger(ResourceAction.class);

	/**
	 * 資源服務
	 */
	@Autowired
	@Qualifier("resourceService")
	protected transient ResourceService resourceService;

	/**
	 * 資源相對路徑
	 * 
	 * /custom/resource
	 */
	private String resourcePath;

	/**
	 * 資源絕對路徑
	 * 
	 * C:\java\apps\jyu6\ide\Eclipse
	 * 4.2\.metadata\.plugins\org.eclipse.wst.server
	 * .core\tmp3\wtpwebapps\csm\custom\resource
	 */
	private String resourceAbsolutePath;

	/**
	 * 選擇的相對路徑
	 * 
	 * /custom/resource/s1/default
	 */
	private String selectPath;

	/**
	 * 選擇的絕對路徑
	 * 
	 * C:\java\apps\jyu6\ide\Eclipse
	 * 4.2\.metadata\.plugins\org.eclipse.wst.server
	 * .core\tmp3\wtpwebapps\csm\custom\resource\s1\default
	 */
	private String selectAbsolutePath;

	/**
	 * 多筆列表資料
	 */
	private List<Archive> archives = new LinkedList<Archive>();

	/**
	 * 多筆完整檔案名
	 * 
	 * /custom/resource/s1/default/css/style.css
	 */
	private List<String> paths = new LinkedList<String>();

	/**
	 * 原檔案/目錄名稱
	 */
	private String origName;

	/**
	 * 新檔案/目錄名稱
	 */
	private String newName;

	/**
	 * 檔案內容
	 */
	private String content;

	/**
	 * 麵包屑路徑
	 */
	private List<Breadcrumb> breadcrumbs = new LinkedList<Breadcrumb>();

	public ResourceAction() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		super.initialize();

		// 資源相對路徑
		// /custom/resource/s1
		resourcePath = AppConfigHelper.getResourcePath() + "/"
				+ userSession.getSite().getResourcePath();

		// 資源絕對路徑
		// C:\java\apps\jyu6\ide\Eclipse
		// 4.2\.metadata\.plugins\org.eclipse.wst.server.core\tmp3\wtpwebapps\csm\custom\resource\s1
		resourceAbsolutePath = AppConfigHelper.getResourceAbsolutePath()
				+ File.separator + userSession.getSite().getResourcePath();
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public String getResourceAbsolutePath() {
		return resourceAbsolutePath;
	}

	public void setResourceAbsolutePath(String resourceAbsolutePath) {
		this.resourceAbsolutePath = resourceAbsolutePath;
	}

	public String getSelectPath() {
		return selectPath;
	}

	/**
	 * 取得選擇的相對路徑,轉成檔案系統
	 * 
	 * /custom/resource/s1/default -> \custom\resource\s1\default
	 * 
	 * @return
	 */
	public String getFileSelectPath() {
		if (selectPath != null) {
			return selectPath.replace("/", File.separator);
		}
		return null;
	}

	public void setSelectPath(String selectPath) {
		this.selectPath = selectPath;
	}

	public List<Archive> getArchives() {
		return archives;
	}

	public void setArchives(List<Archive> archives) {
		this.archives = archives;
	}

	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
	}

	public String getOrigName() {
		return origName;
	}

	public void setOrigName(String origName) {
		this.origName = origName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Breadcrumb> getBreadcrumbs() {
		return breadcrumbs;
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
			if (StringHelper.isEmpty(selectPath)) {
				selectPath = resourcePath;
				selectAbsolutePath = resourceAbsolutePath;
			} else {
				// 檢查只能讀取session網站的路徑
				if (!selectPath.startsWith(resourcePath)) {
					String[] msgArgs = new String[] { selectPath };
					addActionError(getText("global.path.not.allowed", msgArgs));
					return SUCCESS;
				} else {
					// C:\java\apps\jyu6\ide\Eclipse
					// 4.2\.metadata\.plugins\org.eclipse.wst.server.core\tmp3\wtpwebapps\csm\
					// /custom/resource/s1/default ->
					// \custom\resource\s1\default
					selectAbsolutePath = getRealPath() + getFileSelectPath();
				}
			}
			// System.out.println(selectAbsolutePath);
			//
			archives = resourceService.listFile(getRealPath(),
					selectAbsolutePath, false);
			// 麵包屑路徑
			breadcrumbs = resourceService.listBreadcrumb(resourcePath,
					selectPath);
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新建目錄
	 * 
	 * @return
	 */
	@Action(value = "addDir", results = { @Result(name = "success", type = "freemarker", location = "resourceAddDir.ftl") })
	public String addDir() {
		try {
			// 麵包屑路徑
			breadcrumbs = resourceService.listBreadcrumb(resourcePath,
					selectPath);
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新建目錄存檔
	 * 
	 * @return
	 */
	@Action(value = "addDirSave", results = {
			@Result(name = "input", type = "freemarker", location = "resourceAddDir.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "resourceList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "newName", key = "resource.dir.required"),//
	})
	public String addDirSave() {
		boolean result = false;
		//
		StringBuilder dirName = new StringBuilder();
		dirName.append(getFileSelectPath() + File.separator + newName);
		try {
			// 檢查路徑
			if (StringHelper.isEmpty(selectPath)) {
				addActionError(getText("resource.path.empty"));
				return INPUT;
			}
			// 新建目錄
			result = resourceService.createDir(userSession.getUser(),
					getRealPath(), dirName.toString());
			if (result) {
				String[] msgArgs = new String[] { dirName.toString() };
				addActionMessage(getText("global.add.file.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (!result) {
			String[] msgArgs = new String[] { dirName.toString() };
			addActionError(getText("global.add.file.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 新建檔案
	 * 
	 * @return
	 */
	@Action(value = "addFile", results = { @Result(name = "success", type = "freemarker", location = "resourceAddFile.ftl") })
	public String addFile() {
		try {
			// 麵包屑路徑
			breadcrumbs = resourceService.listBreadcrumb(resourcePath,
					selectPath);
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新建檔案存檔
	 * 
	 * @return
	 */
	@Action(value = "addFileSave", results = {
			@Result(name = "input", type = "freemarker", location = "resourceAddFile.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "resourceList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "newName", key = "resource.fileName.required"),//
	})
	public String addFileSave() {
		boolean result = false;
		//
		StringBuilder fileName = new StringBuilder();
		fileName.append(getFileSelectPath() + File.separator + newName);
		try {
			// 檢查路徑
			if (StringHelper.isEmpty(selectPath)) {
				addActionError(getText("resource.path.empty"));
				return INPUT;
			}

			// 新建檔案
			result = resourceService.createFile(userSession.getUser(),
					getRealPath(), fileName.toString(), content);
			if (result) {
				String[] msgArgs = new String[] { fileName.toString() };
				addActionMessage(getText("global.add.file.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (!result) {
			String[] msgArgs = new String[] { fileName.toString() };
			addActionError(getText("global.add.file.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 編輯檔案
	 * 
	 * @return
	 */
	@Action(value = "editFile", results = { @Result(name = "success", type = "freemarker", location = "resourceEditFile.ftl") })
	public String editFile() {
		try {
			content = resourceService.readFile(getRealPath(),
					getFileSelectPath() + File.separator + origName);
			// 麵包屑路徑
			breadcrumbs = resourceService.listBreadcrumb(resourcePath,
					selectPath);

		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 編輯檔案存檔
	 * 
	 * @return
	 */
	@Action(value = "editFileSave", results = {
			@Result(name = "input", type = "freemarker", location = "resourceEditFile.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "resourceList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	public String editFileSave() {
		boolean result = false;
		//
		StringBuilder fileName = new StringBuilder();
		fileName.append(getFileSelectPath() + File.separator + origName);
		try {
			// 檢查路徑
			if (StringHelper.isEmpty(selectPath)) {
				addActionError(getText("resource.path.empty"));
				return INPUT;
			}

			// 儲存檔案
			result = resourceService.saveFile(userSession.getUser(),
					getRealPath(), fileName.toString(), content);
			if (result) {
				String[] msgArgs = new String[] { fileName.toString() };
				addActionMessage(getText("global.save.file.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (!result) {
			String[] msgArgs = new String[] { fileName.toString() };
			addActionError(getText("global.save.file.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 檔案/目錄更名
	 * 
	 * @return
	 */
	@Action(value = "rename", results = { @Result(name = "success", type = "freemarker", location = "resourceRename.ftl") })
	public String rename() {
		try {
			newName = origName;
			// 麵包屑路徑
			breadcrumbs = resourceService.listBreadcrumb(resourcePath,
					selectPath);
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 檔案/目錄更名存檔
	 * 
	 * @return
	 */
	@Action(value = "renameSave", results = {
			@Result(name = "input", type = "freemarker", location = "resourceRename.ftl"),
			@Result(name = "invalid.token", type = "freemarker", location = "resourceList.ftl") }, interceptorRefs = {
			@InterceptorRef(value = "token"), @InterceptorRef("defaultStack") })
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "newName", key = "resource.newName.required"),//
	})
	public String renameSave() {
		boolean result = false;
		try {
			// 檢查路徑
			if (StringHelper.isEmpty(selectPath)) {
				addActionError(getText("resource.path.empty"));
				return INPUT;
			}
			// 新名稱與原名稱相同
			if (StringHelper.notEmpty(newName)) {
				if (newName.equals(origName)) {
					addActionError(getText("resource.name.same"));
					return INPUT;
				}
			}

			// 檔案/目錄更名
			result = resourceService.renameFile(userSession.getUser(),
					getRealPath(), getFileSelectPath() + File.separator
							+ origName, getFileSelectPath() + File.separator
							+ newName);
			if (result) {
				String[] msgArgs = new String[] { origName, newName };
				addActionMessage(getText("global.rename.file.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		if (!result) {
			String[] msgArgs = new String[] { origName, newName };
			addActionError(getText("global.rename.file.fail", msgArgs));
		}
		return INPUT;
	}

	/**
	 * 刪除, 多筆刪除,by names
	 * 
	 * @return
	 */
	@Action(value = "delete")
	public String delete() {
		List<String> result = new LinkedList<String>();
		int size = 0;
		try {
			// C:\java\apps\jyu6\ide\Eclipse
			// 4.2\.metadata\.plugins\org.eclipse.wst.server.core\tmp3\wtpwebapps\csm\
			// /custom/resource/s1/default/watermark.png
			result = resourceService.deleteFile(userSession.getUser(),
					getRealPath(), paths);
			size = result.size();
			if (size > 0) {
				String[] msgArgs = new String[] { CollectionHelper
						.toString(result) };
				addActionMessage(getText("global.delete.file.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		//
		if (!paths.isEmpty() && size < 1) {
			addActionMessage(getText("global.delete.no.file"));
		}
		return SUCCESS;
	}

	@Action(value = "upload", interceptorRefs = {
			@InterceptorRef(value = "fileUpload"),
			@InterceptorRef("defaultStack") })
	public String upload() {
		List<String> result = new LinkedList<String>();
		int size = 0;
		// 轉成list
		List<String> fileNames = CollectionHelper.toList(uploadFileName);
		// 有路徑的list
		List<String> pathNames = new LinkedList<String>();
		//
		try {
			// this.savePath = getRealPath() + getFileSelectPath();
			this.savePath = getRealPath();

			// 檢查路徑
			if (StringHelper.isEmpty(savePath)) {
				addActionError(getText("global.path.empty"));
				// 返回列表
				return list();
			}

			// 檢查上傳檔案
			if (ArrayHelper.isEmpty(upload)) {
				addActionError(getText("global.upload.empty"));
				// 返回列表
				return list();
			}
			// 加上路徑
			for (String fileName : fileNames) {
				pathNames.add(getFileSelectPath() + File.separator + fileName);
			}

			// 上傳檔案
			result = resourceService.uploadFile(userSession.getUser(),
					savePath, CollectionHelper.toList(upload), pathNames);
			size = result.size();
			//
			if (size > 0) {
				String[] msgArgs = new String[] { CollectionHelper
						.toString(result) };
				addActionMessage(getText("global.upload.file.success", msgArgs));
				// 返回列表
				return list();
			}
		} catch (Exception ex) {
			log.warn(ex);
			ex.printStackTrace();
		}
		//
		if (upload != null && upload.length > 0 && size < 1) {
			addActionError(getText("global.upload.no.file"));
			// 返回列表
			return list();
		}
		//
		return list();
	}

}

package org.openyu.cms.archive.service;

import java.io.File;
import java.util.List;
import org.openyu.cms.app.service.AppService;
import org.openyu.cms.archive.vo.Archive;
import org.openyu.cms.archive.vo.Breadcrumb;
import org.openyu.cms.user.vo.User;

/**
 * 檔案服務
 */
public interface ArchiveService extends AppService
{

	/**
	 * 檔案列表
	 * 
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param absolutePath 目錄絕對路徑
	 * @param dirAndEditable 是否只取得目錄,和可編輯的尾碼名類別,的目錄檔案
	 * @return
	 */
	List<Archive> listFile(String parentAbsolutePath, String absolutePath, boolean dirAndEditType);

	/**
	 * 新建目錄
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param dirName 目錄
	 * @return
	 */
	boolean createDir(User user, String parentAbsolutePath, String dirName);

	/**
	 * 新建檔案
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param newName 檔案名稱
	 * @param content 內容
	 */
	boolean createFile(User user, String parentAbsolutePath, String newName, String content);

	/**
	 * 讀取檔案
	 * 
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param origName 檔案名稱
	 */
	String readFile(String parentAbsolutePath, String origName);

	/**
	 * 儲存檔案
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param origName 檔案名稱
	 * @param content 內容
	 */
	boolean saveFile(User user, String parentAbsolutePath, String origName, String content);

	/**
	 * 更名檔案/目錄
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param origName 原檔案名
	 * @param newName 新檔案名
	 */
	boolean renameFile(User user, String parentAbsolutePath, String origName, String newName);

	/**
	 * 刪除檔案
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param names 檔案/目錄名稱
	 * @return
	 */
	List<String> deleteFile(User user, String parentAbsolutePath, List<String> names);

	/**
	 * 上傳檔案
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param files
	 * @param fileName
	 * @return
	 */
	List<String> uploadFile(User user, String parentAbsolutePath, List<File> files,
							List<String> fileNames);

	/**
	 * 取得麵包屑路徑
	 * 
	 * @param path
	 * @return
	 */
	List<Breadcrumb> listBreadcrumb(String parentPath, String path);

}
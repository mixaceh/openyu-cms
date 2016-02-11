package org.openyu.cms.archive.service.supporter;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.archive.service.ArchiveService;
import org.openyu.cms.archive.vo.Archive;
import org.openyu.cms.archive.vo.Breadcrumb;
import org.openyu.cms.archive.vo.impl.ArchiveImpl;
import org.openyu.cms.archive.vo.impl.BreadcrumbImpl;
import org.openyu.cms.archive.vo.impl.DirAndEditTypeFilter;
import org.openyu.cms.archive.vo.impl.DirComparator;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.io.FileHelper;
import org.openyu.commons.io.IoHelper;
import org.openyu.commons.lang.StringHelper;

public class ArchiveServiceSupporter extends AppServiceSupporter implements ArchiveService
{
	private static transient final Logger log = LogManager.getLogger(ArchiveServiceSupporter.class);

	public ArchiveServiceSupporter()
	{}

	/**
	 * 檔案列表
	 * 
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param absolutePath 目錄絕對路徑
	 * @param dirAndEditable 是否只取得目錄,和可編輯的尾碼名類別,的目錄檔案
	 * @return
	 */
	public List<Archive> listFile(String parentAbsolutePath, String absolutePath,
									boolean dirAndEditType)
	{
		List<Archive> result = new ArrayList<Archive>();
		//
		File parentPath = new File(absolutePath);
		if (FileHelper.isExist(parentPath))
		{
			File[] files = null;
			if (dirAndEditType)
			{
				//檔案過濾器
				DirAndEditTypeFilter archiveFilter = new DirAndEditTypeFilter();
				files = parentPath.listFiles(archiveFilter);
			}
			else
			{
				files = parentPath.listFiles();
			}
			//排序,目錄比較器
			Arrays.sort(files, new DirComparator());
			//file -> archive
			for (File file : files)
			{
				result.add(new ArchiveImpl(file, parentAbsolutePath));
			}
		}
		//
		return result;
	}

	/**
	 * 新建目錄
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param dirName 目錄
	 * @return
	 */
	public boolean createDir(User user, String parentAbsolutePath, String dirName)
	{
		boolean result = false;
		try
		{
			//先檢查是否有此層目錄
			File parentPath = new File(parentAbsolutePath);
			if (!FileHelper.isExist(parentPath))
			{
				//若無則新建
				FileHelper.md(parentPath);
			}
			//
			File dirPath = new File(parentPath, dirName);
			if (!FileHelper.isExist(dirPath))
			{
				result = FileHelper.md(dirPath);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		//
		return result;
	}

	/**
	 * 新建檔案
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param newName 檔案名稱
	 * @param content 內容
	 */
	public boolean createFile(User user, String parentAbsolutePath, String newName, String content)
	{
		boolean result = false;
		try
		{
			//先檢查是否有此層目錄
			File parentPath = new File(parentAbsolutePath);
			if (!FileHelper.isExist(parentPath))
			{
				//若無則新建
				FileHelper.md(parentPath);
			}
			//
			File file = new File(parentPath, newName);
			//若無檔案存在,才新建
			if (!FileHelper.isExist(file))
			{
				//把字串寫出成檔案
				result = IoHelper.writeToString(file, content);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		//
		return result;
	}

	/**
	 * 讀取檔案
	 * 
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param origName 檔案名稱
	 */
	public String readFile(String parentAbsolutePath, String origName)
	{
		String result = null;
		try
		{
			//先檢查是否有此層目錄
			File parentPath = new File(parentAbsolutePath);
			if (FileHelper.isExist(parentPath))
			{
				File file = new File(parentPath, origName);
				//檔案存在才讀取
				if (FileHelper.isExist(file))
				{
					//讀取檔案為字串
					result = IoHelper.readString(file);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		//
		return result.toString();
	}

	/**
	 * 儲存檔案
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param origName 檔案名稱
	 * @param content 內容
	 */
	public boolean saveFile(User user, String parentAbsolutePath, String origName, String content)
	{
		boolean result = false;
		try
		{
			//先檢查是否有此層目錄
			File parentPath = new File(parentAbsolutePath);
			if (!FileHelper.isExist(parentPath))
			{
				//若無則新建
				FileHelper.md(parentPath);
			}
			//
			File file = new File(parentPath, origName);
			//把字串寫出成檔案
			result = IoHelper.writeToString(file, content);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		//
		return result;
	}

	/**
	 * 更名檔案/目錄
	 * 
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param origName 原檔案名
	 * @param newName 新檔案名
	 */
	public boolean renameFile(User user, String parentAbsolutePath, String origName, String newName)
	{
		boolean result = false;
		try
		{
			//先檢查是否有此層目錄
			File parentPath = new File(parentAbsolutePath);
			if (FileHelper.isExist(parentPath))
			{
				//原檔案
				File origFile = new File(parentPath, origName);
				if (FileHelper.isExist(origFile))
				{
					//新檔案
					File newFile = new File(parentPath, newName);
					result = origFile.renameTo(newFile);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		//
		return result;
	}

	/**
	 * 刪除檔案
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param names 檔案/目錄名稱
	 * @return
	 */
	public List<String> deleteFile(User user, String parentAbsolutePath, List<String> names)
	{
		List<String> result = new LinkedList<String>();
		for (String name : names)
		{
			try
			{
				//先檢查是否有此層目錄
				File parentPath = new File(parentAbsolutePath);
				if (FileHelper.isExist(parentPath))
				{
					//轉成檔案系統
					StringBuilder buff = new StringBuilder(name.replace("/", File.separator));
					File file = new File(parentPath, buff.toString());
					boolean delete = FileHelper.delete(file);
					if (delete)
					{
						// \custom\resource\s1\default
						result.add(buff.toString());
					}
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 上傳檔案
	 * 
	 * @param user 使用者
	 * @param parentAbsolutePath 父層絕對路徑
	 * @param files
	 * @param fileName
	 * @return
	 */
	public List<String> uploadFile(User user, String parentAbsolutePath, List<File> files,
									List<String> fileNames)
	{
		List<String> result = new LinkedList<String>();
		//
		//先檢查是否有此層目錄
		File parentPath = new File(parentAbsolutePath);
		if (!FileHelper.isExist(parentPath))
		{
			//若無則新建
			FileHelper.md(parentPath);
		}
		//
		int i = 0;
		for (File file : files)
		{
			InputStream in = null;
			OutputStream out = null;
			try
			{
				//讀入檔案成byte[]
				in = IoHelper.createInputStream(file);
				byte[] buffs = IoHelper.read(in);

				//輸出檔名
				String outName = fileNames.get(i);
				//輸出檔案
				File outFile = new File(parentPath, outName);
				//輸出串流
				out = IoHelper.createOutputStream(outFile);
				//byte[]寫出到串流
				boolean write = IoHelper.write(out, buffs);
				//
				if (write)
				{
					result.add(outName);
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				//關串流
				IoHelper.close(in);
				IoHelper.close(out);
			}
			//
			i++;
		}
		//
		return result;
	}

	/**
	 * 取得麵包屑路徑
	 * 
	 * @param path
	 * @return
	 */
	public List<Breadcrumb> listBreadcrumb(String parentPath, String path)
	{
		List<Breadcrumb> result = new ArrayList<Breadcrumb>();
		try
		{
			// /s1/default -> "", s1, default
			String[] buffs = path.replace(parentPath, "").split("/");
			//SystemHelper.println(buffs);
			//
			if (buffs != null)
			{
				StringBuilder buff = new StringBuilder();
				for (String entry : buffs)
				{
					if (StringHelper.isBlank(entry))
					{
						continue;
					}
					// /s1,/s1/default
					buff.append("/");
					buff.append(entry);
					//
					Breadcrumb breadcrumb = new BreadcrumbImpl();
					breadcrumb.setParent(parentPath); // /custom/resource
					breadcrumb.setPath(parentPath + buff);// /custom/resource/s1, /custom/resource/s1/default
					breadcrumb.setName("/" + entry);// /s1, /default
					result.add(breadcrumb);
				}
			}
			//System.out.println(result);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

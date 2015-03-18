package org.openyu.cms.resource.service.impl;

import java.io.File;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.archive.service.supporter.ArchiveServiceSupporter;
import org.openyu.cms.resource.service.ResourceService;
import org.openyu.cms.user.vo.User;

public class ResourceServiceImpl extends ArchiveServiceSupporter implements ResourceService
{
	private static transient final Logger log = LogManager.getLogger(ResourceServiceImpl.class);

	public ResourceServiceImpl()
	{}

	public boolean createDir(User user, String parentAbsolutePath, String dirName)
	{
		return super.createDir(user, parentAbsolutePath, dirName);
	}

	public boolean createFile(User user, String parentAbsolutePath, String newName, String content)
	{
		return super.createFile(user, parentAbsolutePath, newName, content);
	}

	public boolean saveFile(User user, String parentAbsolutePath, String origName, String content)
	{
		return super.saveFile(user, parentAbsolutePath, origName, content);
	}

	public boolean renameFile(User user, String parentAbsolutePath, String origName, String newName)
	{
		return super.renameFile(user, parentAbsolutePath, origName, newName);
	}

	public List<String> deleteFile(User user, String parentAbsolutePath, List<String> names)
	{
		return super.deleteFile(user, parentAbsolutePath, names);
	}

	public List<String> uploadFile(User user, String parentAbsolutePath, List<File> files,
									List<String> fileNames)
	{
		return super.uploadFile(user, parentAbsolutePath, files, fileNames);
	}

}

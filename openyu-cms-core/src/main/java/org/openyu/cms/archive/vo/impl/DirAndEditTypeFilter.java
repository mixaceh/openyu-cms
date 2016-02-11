package org.openyu.cms.archive.vo.impl;

import java.io.File;
import java.io.FileFilter;

import org.openyu.cms.archive.vo.Archive;

/**
 * 檔案過濾器
 * 
 * 1.目錄
 * 
 * 2.可編輯的尾碼名類別
 * 
 */
public class DirAndEditTypeFilter implements FileFilter
{

	public DirAndEditTypeFilter()
	{}

	public boolean accept(File file)
	{
		Archive archive = new ArchiveImpl(file);
		return file.isDirectory() || archive.isEditType();
	}
}

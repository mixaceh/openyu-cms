package org.openyu.cms.archive.vo.impl;

import java.io.File;
import java.util.Comparator;

/**
 * 目錄比較器,目錄排在前面
 */
public class DirComparator implements Comparator<File>
{
	public DirComparator()
	{}

	public int compare(File o1, File o2)
	{
		if (o1.isDirectory() && !o2.isDirectory())
		{
			return -1;
		}
		else if (!o1.isDirectory() && o2.isDirectory())
		{
			return 1;
		}
		else
		{
			return o1.compareTo(o2);
		}
	}
}

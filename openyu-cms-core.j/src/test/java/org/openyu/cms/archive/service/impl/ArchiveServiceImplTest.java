package org.openyu.cms.archive.service.impl;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.openyu.cms.app.util.AppConfigHelper;
import org.openyu.cms.archive.ArchiveTestSupporter;
import org.openyu.cms.archive.vo.Archive;

public class ArchiveServiceImplTest extends ArchiveTestSupporter {

	@Test
	public void listFile() {
		// webapp\custom\resource\s1
		String resourcePath = AppConfigHelper.getResourcePath() + "/s1";
		System.out.println("resourcePath: " + resourcePath);
		//
		// D:/dev/openyu/trunk/wd/cms.j/src/main/webapp/custom/resource/s1
		String resourceAbsolutePath = AppConfigHelper.getResourceAbsolutePath()
				+ File.separator + "s1";
		System.out.println("resourceAbsolutePath: " + resourceAbsolutePath);
		//
		List<Archive> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = archiveService.listFile(
					"D:/dev/openyu/trunk/wd/cms.j/src/main/webapp",
					resourceAbsolutePath, true);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result.size());
		System.out.println(result);
		assertNotNull(result);
	}
}

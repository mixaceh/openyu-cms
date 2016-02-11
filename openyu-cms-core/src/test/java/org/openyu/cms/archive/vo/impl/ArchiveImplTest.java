package org.openyu.cms.archive.vo.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.openyu.cms.app.AppBeanTestSupporter;
import org.openyu.cms.app.util.AppConfigHelper;
import org.openyu.cms.archive.vo.Archive;

public class ArchiveImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		// D:/dev/openyu/trunk/wd/cms.j/src/main/webapp/custom/resource/s1
		String resourceAbsolutePath = AppConfigHelper.getResourceAbsolutePath()
				+ File.separator + "s1";
		System.out.println("resourceAbsolutePath: " + resourceAbsolutePath);

		// gif
		String fileName = resourceAbsolutePath + "/default/no_picture.gif";
		File file = new File(fileName);
		Archive value = new ArchiveImpl(file, "");
		System.out.println("result: " + value);
		assertTrue(value.isImageType());
		assertFalse(value.isEditType());
		// css
		fileName = resourceAbsolutePath + "/default/css/style.css";
		file = new File(fileName);
		value = new ArchiveImpl(file,
				"D:/dev/openyu/trunk/wd/cms.j/src/main/webapp");
		System.out.println("result: " + value);
		assertFalse(value.isImageType());
		assertTrue(value.isEditType());
		//
		System.out.println(value.getPath());// /custom/resource/s1/default/css/style.css
		System.out.println(value.getParent());// /custom/resource/s1/default/css
		System.out.println(value.getName());// style.css

		String result = beanCollector.writeToXml(ArchiveImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Archive result = beanCollector.readFromXml(ArchiveImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}
}

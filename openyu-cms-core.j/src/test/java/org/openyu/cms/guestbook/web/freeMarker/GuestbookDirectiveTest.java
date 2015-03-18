package org.openyu.cms.guestbook.web.freeMarker;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import org.openyu.cms.guestbook.GuestbookTestSupporter;
import freemarker.template.Template;

public class GuestbookDirectiveTest extends GuestbookTestSupporter
{
	@Test
	public void directive() throws Exception
	{
		//為了測試改為class system
		freeMarkerConfiguration.setClassForTemplateLoading(getClass(), "/");
		Template template = freeMarkerConfiguration
				.getTemplate("org/openyu/cms/guestbook/web/freeMarker/cms_guestbook.ftl");
		//
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("cms_guestbook", guestbookDirective);
		//
		Writer out = new OutputStreamWriter(System.out);
		//
		template.process(rootMap, out);
		//
		out.flush();
	}

}

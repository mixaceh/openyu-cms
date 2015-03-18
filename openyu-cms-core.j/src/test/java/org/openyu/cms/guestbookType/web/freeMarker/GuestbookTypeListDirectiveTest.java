package org.openyu.cms.guestbookType.web.freeMarker;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.openyu.cms.guestbookType.GuestbookTypeTestSupporter;
import freemarker.template.Template;

public class GuestbookTypeListDirectiveTest extends GuestbookTypeTestSupporter
{
	@Test
	public void directive() throws Exception
	{
		//為了測試改為class system
		freeMarkerConfiguration.setClassForTemplateLoading(getClass(), "/");
		Template template = freeMarkerConfiguration
				.getTemplate("org/openyu/cms/guestbookType/web/freeMarker/cms_guestbook_type_list.ftl");
		//
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("cms_guestbook_type_list", guestbookTypeListDirective);
		//
		Writer out = new OutputStreamWriter(System.out);
		//
		template.process(rootMap, out);
		//
		out.flush();
	}

}

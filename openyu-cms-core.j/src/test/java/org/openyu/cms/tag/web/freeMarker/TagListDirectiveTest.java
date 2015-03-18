package org.openyu.cms.tag.web.freeMarker;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.openyu.cms.tag.TagTestSupporter;
import freemarker.template.Template;

public class TagListDirectiveTest extends TagTestSupporter
{
	@Test
	public void directive() throws Exception
	{
		//為了測試改為class system
		freeMarkerConfiguration.setClassForTemplateLoading(getClass(), "/");
		Template template = freeMarkerConfiguration
				.getTemplate("org/openyu/cms/tag/web/freeMarker/cms_tag_list.ftl");
		//
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("cms_tag_list", tagListDirective);
		//
		Writer out = new OutputStreamWriter(System.out);
		//
		template.process(rootMap, out);
		//
		out.flush();
	}

}

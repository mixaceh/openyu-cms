package org.openyu.cms.adSpace.web.freeMarker;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.openyu.cms.adSpace.AdSpaceTestSupporter;
import freemarker.template.Template;

public class AdSpaceListDirectiveTest extends AdSpaceTestSupporter
{
	@Test
	public void directive() throws Exception
	{
		//為了測試改為class system
		freeMarkerConfiguration.setClassForTemplateLoading(getClass(), "/");
		Template template = freeMarkerConfiguration
				.getTemplate("org/openyu/cms/adSpace/web/freeMarker/cms_ad_space_list.ftl");
		//
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("cms_ad_space_list", adSpaceListDirective);
		//
		Writer out = new OutputStreamWriter(System.out);
		//
		template.process(rootMap, out);
		//
		out.flush();
	}

}

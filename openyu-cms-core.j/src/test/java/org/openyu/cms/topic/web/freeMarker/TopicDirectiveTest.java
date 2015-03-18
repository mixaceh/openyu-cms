package org.openyu.cms.topic.web.freeMarker;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.openyu.cms.topic.TopicTestSupporter;
import freemarker.template.Template;

public class TopicDirectiveTest extends TopicTestSupporter
{
	@Test
	public void directive() throws Exception
	{
		//為了測試改為class system
		freeMarkerConfiguration.setClassForTemplateLoading(getClass(), "/");
		Template template = freeMarkerConfiguration
				.getTemplate("org/openyu/cms/topic/web/freeMarker/cms_topic.ftl");
		//
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("cms_topic", topicDirective);
		//
		Writer out = new OutputStreamWriter(System.out);
		//
		template.process(rootMap, out);
		//
		out.flush();
	}

}

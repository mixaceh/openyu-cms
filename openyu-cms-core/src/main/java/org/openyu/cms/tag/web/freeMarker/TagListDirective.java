package org.openyu.cms.tag.web.freeMarker;

import org.openyu.cms.app.web.freeMarker.supporter.AppDirectiveSupporter;
import org.openyu.cms.tag.service.TagService;
import org.openyu.cms.tag.vo.Tag;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * tag列表標籤
 * 
 * @param siteSeq
 * @param siteId
 */
public class TagListDirective extends AppDirectiveSupporter
{
	@Autowired
	@Qualifier("tagService")
	protected transient TagService tagService;

	public TagListDirective()
	{}

	public void directive(Environment env, Map<String, TemplateModel> params,
							TemplateModel loopVars[], TemplateDirectiveBody body)
		throws TemplateException, IOException
	{
		//多筆結果
		List<Tag> results = null;

		results = tagService.findTag(getLocale(), null);

		//輸出變數
		Map<String, TemplateModel> variables = new HashMap<String, TemplateModel>();
		variables.put(TAG_RESULTS, ObjectWrapper.DEFAULT_WRAPPER.wrap(results));
		//產出結果
		render(env, body, variables);
	}

}

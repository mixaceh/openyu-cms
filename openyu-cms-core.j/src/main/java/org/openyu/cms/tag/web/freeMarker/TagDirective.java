package org.openyu.cms.tag.web.freeMarker;

import org.openyu.cms.app.web.freeMarker.supporter.AppDirectiveSupporter;
import org.openyu.cms.tag.service.TagService;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.tag.vo.impl.TagImpl;
import org.openyu.commons.util.CollectionHelper;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * tag標籤
 * 
 * @param seq
 * @param id
 */
public class TagDirective extends AppDirectiveSupporter
{

	@Autowired
	@Qualifier("tagService")
	protected transient TagService tagService;

	public TagDirective()
	{}

	public void directive(Environment env, Map<String, TemplateModel> params,
							TemplateModel loopVars[], TemplateDirectiveBody body)
		throws TemplateException, IOException
	{
		//單筆結果
		Tag result = null;

		//傳入參數
		long seq = toLong(PARAM_SEQ, params);
		String id = toString(PARAM_ID, params);

		//搜尋器
		Tag searcher = new TagImpl(seq);
		searcher.setId(id);
		searcher.setOnly(true);//唯一

		List<Tag> tags = tagService.findTag(getLocale(), searcher);

		//輸出變數
		Map<String, TemplateModel> variables = new HashMap<String, TemplateModel>();
		//單筆
		if (CollectionHelper.notEmpty(tags))
		{
			result = tags.get(0);
		}
		variables.put(TAG_RESULT, ObjectWrapper.DEFAULT_WRAPPER.wrap(result));

		//產出結果
		render(env, body, variables);
	}
}

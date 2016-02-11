package org.openyu.cms.topic.web.freeMarker;

import org.openyu.cms.app.web.freeMarker.supporter.AppDirectiveSupporter;
import org.openyu.cms.topic.service.TopicService;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.topic.vo.impl.TopicImpl;
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
 * 專題標籤
 * 
 * @param seq
 * @param id
 */
public class TopicDirective extends AppDirectiveSupporter
{

	@Autowired
	@Qualifier("topicService")
	protected transient TopicService topicService;

	public TopicDirective()
	{}

	public void directive(Environment env, Map<String, TemplateModel> params,
							TemplateModel loopVars[], TemplateDirectiveBody body)
		throws TemplateException, IOException
	{
		//單筆結果
		Topic result = null;

		//傳入參數
		long seq = toLong(PARAM_SEQ, params);
		String id = toString(PARAM_ID, params);

		//搜尋器
		Topic searcher = new TopicImpl(seq);
		searcher.setId(id);
		searcher.setOnly(true);//唯一

		List<Topic> topics = topicService.findTopic(getLocale(), null, searcher);

		//輸出變數
		Map<String, TemplateModel> variables = new HashMap<String, TemplateModel>();
		//單筆
		if (CollectionHelper.notEmpty(topics))
		{
			result = topics.get(0);
		}
		variables.put(TAG_RESULT, ObjectWrapper.DEFAULT_WRAPPER.wrap(result));

		//產出結果
		render(env, body, variables);
	}
}

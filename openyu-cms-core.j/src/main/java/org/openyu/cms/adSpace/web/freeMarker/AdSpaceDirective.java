package org.openyu.cms.adSpace.web.freeMarker;

import org.openyu.cms.app.web.freeMarker.supporter.AppDirectiveSupporter;
import org.openyu.cms.adSpace.service.AdSpaceService;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.vo.impl.AdSpaceImpl;
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
 * 廣告版位標籤
 * 
 * @param seq
 * @param id
 */
public class AdSpaceDirective extends AppDirectiveSupporter
{

	@Autowired
	@Qualifier("adSpaceService")
	protected transient AdSpaceService adSpaceService;

	public AdSpaceDirective()
	{}

	public void directive(Environment env, Map<String, TemplateModel> params,
							TemplateModel loopVars[], TemplateDirectiveBody body)
		throws TemplateException, IOException
	{
		//單筆結果
		AdSpace result = null;

		//傳入參數
		long seq = toLong(PARAM_SEQ, params);
		String id = toString(PARAM_ID, params);

		//搜尋器
		AdSpace searcher = new AdSpaceImpl(seq);
		searcher.setId(id);
		searcher.setOnly(true);//唯一
		//
		List<AdSpace> adSpaces = adSpaceService.findAdSpace(getLocale(), null, searcher);

		//輸出變數
		Map<String, TemplateModel> variables = new HashMap<String, TemplateModel>();
		//單筆
		if (CollectionHelper.notEmpty(adSpaces))
		{
			result = adSpaces.get(0);
		}
		variables.put(TAG_RESULT, ObjectWrapper.DEFAULT_WRAPPER.wrap(result));

		//產出結果
		render(env, body, variables);
	}
}

package org.openyu.cms.ad.web.freeMarker;

import org.openyu.cms.app.web.freeMarker.supporter.AppDirectiveSupporter;
import org.openyu.cms.ad.service.AdService;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.ad.vo.impl.AdImpl;
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
 * 廣告標籤
 * 
 * @param seq
 * @param id
 */
public class AdDirective extends AppDirectiveSupporter
{

	@Autowired
	@Qualifier("adService")
	protected transient AdService adService;

	public AdDirective()
	{}

	public void directive(Environment env, Map<String, TemplateModel> params,
							TemplateModel loopVars[], TemplateDirectiveBody body)
		throws TemplateException, IOException
	{
		//單筆結果
		Ad result = null;

		//傳入參數
		long seq = toLong(PARAM_SEQ, params);
		String id = toString(PARAM_ID, params);

		//搜尋器
		Ad searcher = new AdImpl(seq);
		searcher.setId(id);
		searcher.setOnly(true);//唯一

		List<Ad> ads = adService.findAd(getLocale(), null, searcher);

		//輸出變數
		Map<String, TemplateModel> variables = new HashMap<String, TemplateModel>();
		//單筆
		if (CollectionHelper.notEmpty(ads))
		{
			result = ads.get(0);
		}
		variables.put(TAG_RESULT, ObjectWrapper.DEFAULT_WRAPPER.wrap(result));

		//產出結果
		render(env, body, variables);
	}
}

package org.openyu.cms.guestbookType.web.freeMarker;

import org.openyu.cms.app.web.freeMarker.supporter.AppDirectiveSupporter;
import org.openyu.cms.guestbookType.service.GuestbookTypeService;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 留言類型列表標籤,網站別
 * 
 * @param siteSeq
 * @param siteId
 */
public class GuestbookTypeListDirective extends AppDirectiveSupporter
{
	@Autowired
	@Qualifier("guestbookTypeService")
	protected transient GuestbookTypeService guestbookTypeService;

	public GuestbookTypeListDirective()
	{}

	public void directive(Environment env, Map<String, TemplateModel> params,
							TemplateModel loopVars[], TemplateDirectiveBody body)
		throws TemplateException, IOException
	{
		//多筆結果
		List<GuestbookType> results = null;

		//傳入參數
		long siteSeq = toLong(PARAM_SITE_SEQ, params);
		String siteId = toString(PARAM_SITE_ID, params);

		//搜尋器
		Site siteSearcher = new SiteImpl(siteSeq);
		siteSearcher.setId(siteId);
		results = guestbookTypeService.findGuestbookType(getLocale(), siteSearcher, null);

		//輸出變數
		Map<String, TemplateModel> variables = new HashMap<String, TemplateModel>();
		variables.put(TAG_RESULTS, ObjectWrapper.DEFAULT_WRAPPER.wrap(results));
		//產出結果
		render(env, body, variables);
	}

}

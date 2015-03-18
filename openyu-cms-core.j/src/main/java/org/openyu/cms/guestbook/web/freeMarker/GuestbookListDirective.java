package org.openyu.cms.guestbook.web.freeMarker;

import org.openyu.cms.app.web.freeMarker.supporter.AppDirectiveSupporter;
import org.openyu.cms.guestbook.service.GuestbookService;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
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
 * 留言列表標籤,網站別
 * 
 * @param siteSeq
 * @param siteId
 * @param guestbookTypeSeq
 * @param guestbookTypeId
 */
public class GuestbookListDirective extends AppDirectiveSupporter
{
	/**
	 * 傳入參數: 留言類型seq
	 */
	String PARAM_GUESTBOOK_SEQ = "guestbook_seq";

	/**
	 * 傳入參數: 留言類型id
	 */
	String PARAM_GUESTBOOK_ID = "guestbook_id";

	@Autowired
	@Qualifier("guestbookService")
	protected transient GuestbookService guestbookService;

	public GuestbookListDirective()
	{}

	public void directive(Environment env, Map<String, TemplateModel> params,
							TemplateModel loopVars[], TemplateDirectiveBody body)
		throws TemplateException, IOException
	{
		//多筆結果
		List<Guestbook> results = null;

		//傳入參數
		long siteSeq = toLong(PARAM_SITE_SEQ, params);
		String siteId = toString(PARAM_SITE_ID, params);
		long guestbookTypeSeq = toLong(PARAM_GUESTBOOK_SEQ, params);
		String guestbookTypeId = toString(PARAM_GUESTBOOK_ID, params);

		//搜尋器
		Site siteSearcher = new SiteImpl(siteSeq);
		siteSearcher.setId(siteId);
		//留言類型搜尋器
		GuestbookType guestbookTypeSearcher = new GuestbookTypeImpl(guestbookTypeSeq);
		guestbookTypeSearcher.setId(guestbookTypeId);

		results = guestbookService.findGuestbook(getLocale(), siteSearcher, guestbookTypeSearcher,
			null);

		//輸出變數
		Map<String, TemplateModel> variables = new HashMap<String, TemplateModel>();
		variables.put(TAG_RESULTS, ObjectWrapper.DEFAULT_WRAPPER.wrap(results));
		//產出結果
		render(env, body, variables);
	}

}

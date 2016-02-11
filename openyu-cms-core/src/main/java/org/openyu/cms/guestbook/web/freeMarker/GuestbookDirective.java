package org.openyu.cms.guestbook.web.freeMarker;

import org.openyu.cms.app.web.freeMarker.supporter.AppDirectiveSupporter;
import org.openyu.cms.guestbook.service.GuestbookService;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbook.vo.impl.GuestbookImpl;
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
 * 留言標籤
 * 
 * @param seq
 * @param id
 */
public class GuestbookDirective extends AppDirectiveSupporter
{

	@Autowired
	@Qualifier("guestbookService")
	protected transient GuestbookService guestbookService;

	public GuestbookDirective()
	{}

	public void directive(Environment env, Map<String, TemplateModel> params,
							TemplateModel loopVars[], TemplateDirectiveBody body)
		throws TemplateException, IOException
	{
		//單筆結果
		Guestbook result = null;

		//傳入參數
		long seq = toLong(PARAM_SEQ, params);
		String id = toString(PARAM_ID, params);

		//搜尋器
		Guestbook searcher = new GuestbookImpl(seq);
		searcher.setId(id);
		searcher.setOnly(true);//唯一

		List<Guestbook> guestbooks = guestbookService.findGuestbook(getLocale(), searcher);

		//輸出變數
		Map<String, TemplateModel> variables = new HashMap<String, TemplateModel>();
		//單筆
		if (CollectionHelper.notEmpty(guestbooks))
		{
			result = guestbooks.get(0);
		}
		variables.put(TAG_RESULT, ObjectWrapper.DEFAULT_WRAPPER.wrap(result));

		//產出結果
		render(env, body, variables);
	}
}

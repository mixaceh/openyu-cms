package org.openyu.cms.friendType.web.freeMarker;

import org.openyu.cms.app.web.freeMarker.supporter.AppDirectiveSupporter;
import org.openyu.cms.friendType.service.FriendTypeService;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
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
 * 友情類型標籤
 * 
 * @param seq
 * @param id
 */
public class FriendTypeDirective extends AppDirectiveSupporter
{

	@Autowired
	@Qualifier("friendTypeService")
	protected transient FriendTypeService friendTypeService;

	public FriendTypeDirective()
	{}

	public void directive(Environment env, Map<String, TemplateModel> params,
							TemplateModel loopVars[], TemplateDirectiveBody body)
		throws TemplateException, IOException
	{
		//單筆結果
		FriendType result = null;

		//傳入參數
		long seq = toLong(PARAM_SEQ, params);
		String id = toString(PARAM_ID, params);

		//搜尋器
		FriendType searcher = new FriendTypeImpl(seq);
		searcher.setId(id);
		searcher.setOnly(true);//唯一

		List<FriendType> friendTypes = friendTypeService.findFriendType(getLocale(), searcher);

		//輸出變數
		Map<String, TemplateModel> variables = new HashMap<String, TemplateModel>();
		//單筆
		if (CollectionHelper.notEmpty(friendTypes))
		{
			result = friendTypes.get(0);
		}
		variables.put(TAG_RESULT, ObjectWrapper.DEFAULT_WRAPPER.wrap(result));

		//產出結果
		render(env, body, variables);
	}
}

package org.openyu.cms.friend.web.freeMarker;

import org.openyu.cms.app.web.freeMarker.supporter.AppDirectiveSupporter;
import org.openyu.cms.friend.service.FriendService;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
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
 * 友情列表標籤,網站別
 * 
 * @param siteSeq
 * @param siteId
 * @param friendTypeSeq
 * @param friendTypeId
 */
public class FriendListDirective extends AppDirectiveSupporter
{
	/**
	 * 傳入參數: 友情類型seq
	 */
	String PARAM_FRIEND_TYPE_SEQ = "friend_type_seq";

	/**
	 * 傳入參數: 友情類型id
	 */
	String PARAM_FRIEND_TYPE_ID = "friend_type_id";

	@Autowired
	@Qualifier("friendService")
	protected transient FriendService friendService;

	public FriendListDirective()
	{}

	public void directive(Environment env, Map<String, TemplateModel> params,
							TemplateModel loopVars[], TemplateDirectiveBody body)
		throws TemplateException, IOException
	{
		//多筆結果
		List<Friend> results = null;

		//傳入參數
		long siteSeq = toLong(PARAM_SITE_SEQ, params);
		String siteId = toString(PARAM_SITE_ID, params);
		long friendTypeSeq = toLong(PARAM_FRIEND_TYPE_SEQ, params);
		String friendTypeId = toString(PARAM_FRIEND_TYPE_ID, params);

		//搜尋器
		Site siteSearcher = new SiteImpl(siteSeq);
		siteSearcher.setId(siteId);
		//友情類型搜尋器
		FriendType friendTypeSearcher = new FriendTypeImpl(friendTypeSeq);
		friendTypeSearcher.setId(friendTypeId);

		results = friendService.findFriend(getLocale(), siteSearcher,
			friendTypeSearcher, null);

		//輸出變數
		Map<String, TemplateModel> variables = new HashMap<String, TemplateModel>();
		variables.put(TAG_RESULTS, ObjectWrapper.DEFAULT_WRAPPER.wrap(results));
		//產出結果
		render(env, body, variables);
	}

}

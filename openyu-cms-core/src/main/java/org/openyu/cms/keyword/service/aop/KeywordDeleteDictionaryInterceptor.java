package org.openyu.cms.keyword.service.aop;

import java.lang.reflect.Method;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.app.aop.supporter.AppAroundAdviceSupporter;
import org.openyu.cms.keyword.service.KeywordLogService;
import org.openyu.cms.keyword.vo.ActionType;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除關鍵字攔截器
 */
public class KeywordDeleteDictionaryInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(KeywordDeleteDictionaryInterceptor.class);

	@Autowired
	@Qualifier("keywordLogService")
	protected transient KeywordLogService keywordLogService;

	public KeywordDeleteDictionaryInterceptor()
	{}

	/**
	 * KeywordService
	 * 
	 * List<Dictionary> deleteDictionary(User user, Keyword keyword,
	 * List<String> keys);
	 */
	public Object invoke(MethodInvocation methodInvocation, Method method, Class<?>[] paramTypes,
							Object[] args)
	{
		//傳回值
		Object result = null;
		try
		{
			// --------------------------------------------------
			//proceed前
			// --------------------------------------------------
			//參數
			User user = clone(args[0]);
			Keyword keyword = clone(args[1]);
			List<String> keys = clone(args[2]);

			// --------------------------------------------------
			result = methodInvocation.proceed();
			// --------------------------------------------------

			// --------------------------------------------------
			//proceed後
			// --------------------------------------------------

			//傳回值
			@SuppressWarnings("unchecked")
			List<Dictionary> ret = (List<Dictionary>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (Dictionary dictionary : ret)
				{
					keywordLogService.recordChangeDictionary(user, ActionType.DELETE, keyword,
						dictionary, null);
				}
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

package org.openyu.cms.keyword.service.aop;

import java.lang.reflect.Method;

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

/**
 * 修改關鍵字攔截器
 */
public class KeywordUpdateDictionaryInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(KeywordUpdateDictionaryInterceptor.class);

	@Autowired
	@Qualifier("keywordLogService")
	protected transient KeywordLogService keywordLogService;

	public KeywordUpdateDictionaryInterceptor()
	{}

	/**
	 * KeywordService
	 * 
	 * int updateDictionary(User user, Keyword keyword, Dictionary
	 * origDictionary, Dictionary dictionary);
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
			Dictionary origDictionary = clone(args[2]);
			Dictionary dictionary = clone(args[3]);

			// --------------------------------------------------
			result = methodInvocation.proceed();
			// --------------------------------------------------

			// --------------------------------------------------
			//proceed後
			// --------------------------------------------------

			//傳回值
			int ret = (Integer) result;
			//
			if (ret > 0)
			{
				keywordLogService.recordChangeDictionary(user, ActionType.UPDATE, keyword,
					origDictionary, dictionary);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

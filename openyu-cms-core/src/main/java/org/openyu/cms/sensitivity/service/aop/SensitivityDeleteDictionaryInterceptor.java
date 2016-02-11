package org.openyu.cms.sensitivity.service.aop;

import java.lang.reflect.Method;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.app.aop.supporter.AppAroundAdviceSupporter;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.sensitivity.service.SensitivityLogService;
import org.openyu.cms.sensitivity.vo.ActionType;
import org.openyu.cms.sensitivity.vo.Sensitivity;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除敏感詞攔截器
 */
public class SensitivityDeleteDictionaryInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(SensitivityDeleteDictionaryInterceptor.class);

	@Autowired
	@Qualifier("sensitivityLogService")
	protected transient SensitivityLogService sensitivityLogService;

	public SensitivityDeleteDictionaryInterceptor()
	{}

	/**
	 * SensitivityService
	 * 
	 * List<Dictionary> deleteDictionary(User user, Sensitivity sensitivity,
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
			Sensitivity sensitivity = clone(args[1]);
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
					sensitivityLogService.recordChangeDictionary(user, ActionType.DELETE,
						sensitivity, dictionary, null);
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

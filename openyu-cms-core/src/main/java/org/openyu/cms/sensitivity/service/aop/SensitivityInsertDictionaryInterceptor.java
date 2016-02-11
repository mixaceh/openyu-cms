package org.openyu.cms.sensitivity.service.aop;

import java.lang.reflect.Method;

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

/**
 * 新增敏感詞攔截器
 */
public class SensitivityInsertDictionaryInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(SensitivityInsertDictionaryInterceptor.class);

	@Autowired
	@Qualifier("sensitivityLogService")
	protected transient SensitivityLogService sensitivityLogService;

	public SensitivityInsertDictionaryInterceptor()
	{}

	/**
	 * SensitivityService
	 * 
	 * int insertDictionary(User user, Sensitivity sensitivity, Dictionary
	 * dictionary);
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
			Dictionary dictionary = clone(args[2]);
			
			// --------------------------------------------------
			result = methodInvocation.proceed();
			// --------------------------------------------------

			// --------------------------------------------------
			//proceed後
			// --------------------------------------------------

			//傳回值
			Integer ret = (Integer) result;
			//
			if (ret > 0)
			{
				sensitivityLogService.recordChangeDictionary(user, ActionType.INSERT, sensitivity,
					null, dictionary);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

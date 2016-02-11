package org.openyu.cms.ad.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.ad.service.AdLogService;
import org.openyu.cms.ad.vo.ActionType;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.app.aop.supporter.AppAroundAdviceSupporter;
import org.openyu.cms.user.vo.User;

/**
 * 新增廣告攔截器
 */
public class AdInsertAdInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager.getLogger(AdInsertAdInterceptor.class);

	@Autowired
	@Qualifier("adLogService")
	protected transient AdLogService adLogService;

	public AdInsertAdInterceptor()
	{}

	/**
	 * AdService
	 * 
	 * ActionType insertDictionary(User user, Ad ad, Dictionary
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
			User user = (User) args[0];
			Ad ad = (Ad) args[1];
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
				adLogService.recordChangeAd(user, ActionType.INSERT, ad);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

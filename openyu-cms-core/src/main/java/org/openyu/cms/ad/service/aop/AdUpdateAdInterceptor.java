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
 * 修改廣告攔截器
 */
public class AdUpdateAdInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager.getLogger(AdUpdateAdInterceptor.class);

	@Autowired
	@Qualifier("adLogService")
	protected transient AdLogService adLogService;

	public AdUpdateAdInterceptor()
	{}

	/**
	 * AdService
	 * 
	 * int updateDictionary(User user, Ad ad, Dictionary origDictionary,
	 * Dictionary dictionary);
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
			int ret = (Integer) result;
			//
			if (ret > 0)
			{
				adLogService.recordChangeAd(user, ActionType.UPDATE, ad);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

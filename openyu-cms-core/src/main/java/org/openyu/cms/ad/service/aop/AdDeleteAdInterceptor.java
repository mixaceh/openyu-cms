package org.openyu.cms.ad.service.aop;

import java.lang.reflect.Method;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.aop.AppMethodInterceptorSupporter;
import org.openyu.cms.ad.service.AdLogService;
import org.openyu.cms.ad.vo.ActionType;
import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除廣告攔截器
 */
public class AdDeleteAdInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(AdDeleteAdInterceptor.class);

	@Autowired
	@Qualifier("adLogService")
	protected transient AdLogService adLogService;

	public AdDeleteAdInterceptor()
	{}

	/**
	 * AdService
	 * 
	 * List<Ad> deleteAd(User user, Ad ad,
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
			User user = (User) args[0];
//			Ad ad = (Ad) args[1];
			@SuppressWarnings("unchecked")
			List<String> keys = (List<String>) args[1];

			// --------------------------------------------------
			result = methodInvocation.proceed();
			// --------------------------------------------------

			// --------------------------------------------------
			//proceed後
			// --------------------------------------------------

			//傳回值
			@SuppressWarnings("unchecked")
			List<Ad> ret = (List<Ad>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (Ad theAd : ret)
				{
					adLogService.recordChangeAd(user, ActionType.DELETE, theAd);
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

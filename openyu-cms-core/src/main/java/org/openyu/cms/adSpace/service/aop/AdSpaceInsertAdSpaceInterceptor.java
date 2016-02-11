package org.openyu.cms.adSpace.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.aop.AppMethodInterceptorSupporter;
import org.openyu.cms.adSpace.service.AdSpaceLogService;
import org.openyu.cms.adSpace.vo.ActionType;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.user.vo.User;

/**
 * 新增廣告版位攔截器
 */
public class AdSpaceInsertAdSpaceInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(AdSpaceInsertAdSpaceInterceptor.class);

	@Autowired
	@Qualifier("adSpaceLogService")
	protected transient AdSpaceLogService adSpaceLogService;

	public AdSpaceInsertAdSpaceInterceptor()
	{}

	/**
	 * AdSpaceService
	 * 
	 * int insertAdSpace(User user, AdSpace adSpace);
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
			AdSpace adSpace = clone(args[1]);

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
				adSpaceLogService.recordChangeAdSpace(user, ActionType.INSERT, adSpace);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

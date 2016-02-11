package org.openyu.cms.friendType.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.aop.AppMethodInterceptorSupporter;
import org.openyu.cms.friendType.service.FriendTypeLogService;
import org.openyu.cms.friendType.vo.ActionType;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.user.vo.User;

/**
 * 新增友情類型攔截器
 */
public class FriendTypeInsertFriendTypeInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(FriendTypeInsertFriendTypeInterceptor.class);

	@Autowired
	@Qualifier("friendTypeLogService")
	protected transient FriendTypeLogService friendTypeLogService;

	public FriendTypeInsertFriendTypeInterceptor()
	{}

	/**
	 * FriendTypeService
	 * 
	 * int insertFriendType(User user, FriendType friendType);
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
			FriendType friendType = clone(args[1]);

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
				friendTypeLogService.recordChangeFriendType(user, ActionType.INSERT, friendType);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

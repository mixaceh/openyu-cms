package org.openyu.cms.friendType.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.app.aop.supporter.AppAroundAdviceSupporter;
import org.openyu.cms.friendType.service.FriendTypeLogService;
import org.openyu.cms.friendType.vo.ActionType;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.user.vo.User;

/**
 * 修改友情類型攔截器
 */
public class FriendTypeUpdateFriendTypeInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(FriendTypeUpdateFriendTypeInterceptor.class);

	@Autowired
	@Qualifier("friendTypeLogService")
	protected transient FriendTypeLogService friendTypeLogService;

	public FriendTypeUpdateFriendTypeInterceptor()
	{}

	/**
	 * FriendTypeService
	 * 
	 * int updateFriendType(User user, FriendType friendType);
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
			int ret = (Integer) result;
			//
			if (ret > 0)
			{
				friendTypeLogService.recordChangeFriendType(user, ActionType.UPDATE, friendType);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

package org.openyu.cms.friend.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.aop.AppMethodInterceptorSupporter;
import org.openyu.cms.friend.service.FriendLogService;
import org.openyu.cms.friend.vo.ActionType;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.user.vo.User;

/**
 * 新增友情連結攔截器
 */
public class FriendInsertFriendInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(FriendInsertFriendInterceptor.class);

	@Autowired
	@Qualifier("friendLogService")
	protected transient FriendLogService friendLogService;

	public FriendInsertFriendInterceptor()
	{}

	/**
	 * FriendService
	 * 
	 * int insertFriend(User user, Friend friend);
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
			Friend friend = clone(args[1]);

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
				friendLogService.recordChangeFriend(user, ActionType.INSERT, friend);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

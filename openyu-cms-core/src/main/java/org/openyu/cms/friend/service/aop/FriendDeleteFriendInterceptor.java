package org.openyu.cms.friend.service.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.app.aop.supporter.AppAroundAdviceSupporter;
import org.openyu.cms.friend.service.FriendLogService;
import org.openyu.cms.friend.vo.ActionType;
import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除友情連結攔截器
 */
public class FriendDeleteFriendInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(FriendDeleteFriendInterceptor.class);

	@Autowired
	@Qualifier("friendLogService")
	protected transient FriendLogService friendLogService;

	public FriendDeleteFriendInterceptor()
	{}

	/**
	 * FriendService
	 * 
	 * List<Friend> deleteFriend(User user, Collection<Serializable> seqs);
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
			Collection<Serializable> seqs = clone(args[1]);

			// --------------------------------------------------
			result = methodInvocation.proceed();
			// --------------------------------------------------

			// --------------------------------------------------
			//proceed後
			// --------------------------------------------------

			//傳回值
			@SuppressWarnings("unchecked")
			List<Friend> ret = (List<Friend>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (Friend friend : ret)
				{
					friendLogService.recordChangeFriend(user, ActionType.DELETE, friend);
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

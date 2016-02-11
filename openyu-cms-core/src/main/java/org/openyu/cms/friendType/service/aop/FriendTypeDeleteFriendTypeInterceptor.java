package org.openyu.cms.friendType.service.aop;

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
import org.openyu.cms.friendType.service.FriendTypeLogService;
import org.openyu.cms.friendType.vo.ActionType;
import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除友情類型攔截器
 */
public class FriendTypeDeleteFriendTypeInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(FriendTypeDeleteFriendTypeInterceptor.class);

	@Autowired
	@Qualifier("friendTypeLogService")
	protected transient FriendTypeLogService friendTypeLogService;

	public FriendTypeDeleteFriendTypeInterceptor()
	{}

	/**
	 * FriendTypeService
	 * 
	 * List<FriendType> deleteFriendType(User user, Collection<Serializable> seqs);
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
			List<FriendType> ret = (List<FriendType>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (FriendType friendType : ret)
				{
					friendTypeLogService.recordChangeFriendType(user, ActionType.DELETE, friendType);
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

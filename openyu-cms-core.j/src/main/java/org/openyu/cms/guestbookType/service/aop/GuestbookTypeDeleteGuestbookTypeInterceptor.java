package org.openyu.cms.guestbookType.service.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.aop.AppMethodInterceptorSupporter;
import org.openyu.cms.guestbookType.service.GuestbookTypeLogService;
import org.openyu.cms.guestbookType.vo.ActionType;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除留言類型攔截器
 */
public class GuestbookTypeDeleteGuestbookTypeInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(GuestbookTypeDeleteGuestbookTypeInterceptor.class);

	@Autowired
	@Qualifier("guestbookTypeLogService")
	protected transient GuestbookTypeLogService guestbookTypeLogService;

	public GuestbookTypeDeleteGuestbookTypeInterceptor()
	{}

	/**
	 * GuestbookTypeService
	 * 
	 * List<GuestbookType> deleteGuestbookType(User user, Collection<Serializable> seqs);
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
			List<GuestbookType> ret = (List<GuestbookType>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (GuestbookType guestbookType : ret)
				{
					guestbookTypeLogService.recordChangeGuestbookType(user, ActionType.DELETE, guestbookType);
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

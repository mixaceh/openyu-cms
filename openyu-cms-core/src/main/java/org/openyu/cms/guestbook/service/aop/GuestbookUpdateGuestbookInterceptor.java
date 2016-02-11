package org.openyu.cms.guestbook.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.aop.AppMethodInterceptorSupporter;
import org.openyu.cms.guestbook.service.GuestbookLogService;
import org.openyu.cms.guestbook.vo.ActionType;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.user.vo.User;

/**
 * 修改留言攔截器
 */
public class GuestbookUpdateGuestbookInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(GuestbookUpdateGuestbookInterceptor.class);

	@Autowired
	@Qualifier("guestbookLogService")
	protected transient GuestbookLogService guestbookLogService;

	public GuestbookUpdateGuestbookInterceptor()
	{}

	/**
	 * GuestbookService
	 * 
	 * int updateGuestbook(User user, Guestbook guestbook);
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
			Guestbook guestbook = clone(args[1]);

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
				guestbookLogService.recordChangeGuestbook(user, ActionType.UPDATE, guestbook);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

package org.openyu.cms.guestbookType.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.app.aop.supporter.AppAroundAdviceSupporter;
import org.openyu.cms.guestbookType.service.GuestbookTypeLogService;
import org.openyu.cms.guestbookType.vo.ActionType;
import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.user.vo.User;

/**
 * 新增留言類型攔截器
 */
public class GuestbookTypeInsertGuestbookTypeInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(GuestbookTypeInsertGuestbookTypeInterceptor.class);

	@Autowired
	@Qualifier("guestbookTypeLogService")
	protected transient GuestbookTypeLogService guestbookTypeLogService;

	public GuestbookTypeInsertGuestbookTypeInterceptor()
	{}

	/**
	 * GuestbookTypeService
	 * 
	 * int insertGuestbookType(User user, GuestbookType guestbookType);
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
			GuestbookType guestbookType = clone(args[1]);

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
				guestbookTypeLogService.recordChangeGuestbookType(user, ActionType.INSERT, guestbookType);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

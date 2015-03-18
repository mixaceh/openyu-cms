package org.openyu.cms.guestbook.service.aop;

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
import org.openyu.cms.guestbook.service.GuestbookLogService;
import org.openyu.cms.guestbook.vo.ActionType;
import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除留言攔截器
 */
public class GuestbookDeleteGuestbookInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(GuestbookDeleteGuestbookInterceptor.class);

	@Autowired
	@Qualifier("guestbookLogService")
	protected transient GuestbookLogService guestbookLogService;

	public GuestbookDeleteGuestbookInterceptor()
	{}

	/**
	 * GuestbookService
	 * 
	 * List<Guestbook> deleteGuestbook(User user, Collection<Serializable> seqs);
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
			List<Guestbook> ret = (List<Guestbook>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (Guestbook guestbook : ret)
				{
					guestbookLogService.recordChangeGuestbook(user, ActionType.DELETE, guestbook);
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

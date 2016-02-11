package org.openyu.cms.tag.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.app.aop.supporter.AppAroundAdviceSupporter;
import org.openyu.cms.tag.service.TagLogService;
import org.openyu.cms.tag.vo.ActionType;
import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.user.vo.User;

/**
 * 修改標籤攔截器
 */
public class TagUpdateTagInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager.getLogger(TagUpdateTagInterceptor.class);

	@Autowired
	@Qualifier("tagLogService")
	protected transient TagLogService tagLogService;

	public TagUpdateTagInterceptor()
	{}

	/**
	 * TagService
	 * 
	 * int updateTag(User user, Tag tag);
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
			Tag tag = clone(args[1]);

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
				tagLogService.recordChangeTag(user, ActionType.UPDATE, tag);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

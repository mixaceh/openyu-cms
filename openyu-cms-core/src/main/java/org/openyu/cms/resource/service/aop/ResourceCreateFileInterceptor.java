package org.openyu.cms.resource.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.aop.AppMethodInterceptorSupporter;
import org.openyu.cms.resource.service.ResourceLogService;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.user.vo.User;

/**
 * 新建檔案攔截器
 */
public class ResourceCreateFileInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(ResourceCreateFileInterceptor.class);

	@Autowired
	@Qualifier("resourceLogService")
	protected transient ResourceLogService resourceLogService;

	public ResourceCreateFileInterceptor()
	{}

	/**
	 * ResourceServiceve
	 * 
	 * createFile(User user, String parentAbsolutePath, String newName, String
	 * content);
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
			String parentAbsolutePath = (String) args[1];
			String newName = (String) args[2];
			String content = (String) args[3];

			// --------------------------------------------------
			result = methodInvocation.proceed();
			// --------------------------------------------------

			// --------------------------------------------------
			//proceed後
			// --------------------------------------------------

			//傳回值
			boolean ret = (Boolean) result;
			//
			if (ret)
			{
				resourceLogService
						.recordChangeResource(user, ActionType.CREATE_FILE, null, newName);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

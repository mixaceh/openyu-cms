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
 * 新建目錄攔截器
 */
public class ResourceCreateDirInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(ResourceCreateDirInterceptor.class);

	@Autowired
	@Qualifier("resourceLogService")
	protected transient ResourceLogService resourceLogService;

	public ResourceCreateDirInterceptor()
	{}

	/**
	 * ResourceServiceve
	 * 
	 * boolean createDir(User user, String parentAbsolutePath, String dirName);
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
			String dirName = (String) args[2];

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
				resourceLogService.recordChangeResource(user, ActionType.CREATE_DIR, null, dirName);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

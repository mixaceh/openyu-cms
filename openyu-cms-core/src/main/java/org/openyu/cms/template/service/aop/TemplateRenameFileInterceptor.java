package org.openyu.cms.template.service.aop;

import java.io.File;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.aop.AppMethodInterceptorSupporter;
import org.openyu.cms.template.service.TemplateLogService;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.user.vo.User;

/**
 * 更名檔案/目錄攔截器
 */
public class TemplateRenameFileInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(TemplateRenameFileInterceptor.class);

	@Autowired
	@Qualifier("templateLogService")
	protected transient TemplateLogService templateLogService;

	public TemplateRenameFileInterceptor()
	{}

	/**
	 * TemplateServiceve
	 * 
	 * boolean renameFile(User user, String parentAbsolutePath, String origName,
	 * String newName);
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
			String origName = (String) args[2];
			String newName = (String) args[3];

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
				File parentPath = new File(parentAbsolutePath);
				//新檔案/目錄
				File newFile = new File(parentPath, newName);
				//檔案
				if (newFile.isFile())
				{
					templateLogService.recordChangeTemplate(user, ActionType.RENAME_FILE, origName,
						newName);
				}
				//目錄
				else
				{
					templateLogService.recordChangeTemplate(user, ActionType.RENAME_DIR, origName,
						newName);
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

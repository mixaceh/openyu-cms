package org.openyu.cms.template.service.aop;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.template.service.TemplateLogService;
import org.openyu.cms.app.aop.supporter.AppAroundAdviceSupporter;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 上傳檔案攔截器
 */
public class TemplateUploadFileInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(TemplateUploadFileInterceptor.class);

	@Autowired
	@Qualifier("templateLogService")
	protected transient TemplateLogService templateLogService;

	public TemplateUploadFileInterceptor()
	{}

	/**
	 * TemplateServiceve
	 * 
	 * List<String> uploadFile(User user, String parentAbsolutePath, List<File>
	 * files, List<String> fileNames);
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
			List<File> files = clone(args[2]);
			List<String> fileNames = clone(args[3]);

			// --------------------------------------------------
			result = methodInvocation.proceed();
			// --------------------------------------------------

			// --------------------------------------------------
			//proceed後
			// --------------------------------------------------

			//傳回值
			@SuppressWarnings("unchecked")
			List<String> ret = (List<String>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (String name : ret)
				{
					templateLogService.recordChangeTemplate(user, ActionType.UPLOAD_FILE, null,
						name);
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

package org.openyu.cms.resource.service.aop;

import java.io.File;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.resource.service.ResourceLogService;
import org.openyu.cms.app.aop.supporter.AppAroundAdviceSupporter;
import org.openyu.cms.archive.vo.ActionType;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除檔案攔截器
 */
public class ResourceDeleteFileInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(ResourceDeleteFileInterceptor.class);

	@Autowired
	@Qualifier("resourceLogService")
	protected transient ResourceLogService resourceLogService;

	public ResourceDeleteFileInterceptor()
	{}

	/**
	 * ResourceServiceve
	 * 
	 * List<String> deleteFile(User user, String parentAbsolutePath,
	 * List<String> names);
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
			List<String> names = clone(args[2]);

			//判斷是檔案還是目錄的操作
			Map<String, ActionType> actionTypes = new LinkedHashMap<String, ActionType>();
			for (String name : names)
			{
				File parentPath = new File(parentAbsolutePath);
				//轉成檔案系統
				StringBuilder buff = new StringBuilder(name.replace("/", File.separator));
				File file = new File(parentPath, buff.toString());
				//檔案
				if (file.isFile())
				{
					// \custom\resource\s1\default
					actionTypes.put(buff.toString(), ActionType.DELETE_FILE);
				}
				//目錄
				else
				{
					actionTypes.put(buff.toString(), ActionType.DELETE_DIR);
				}
			}

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
					//DELETE_FILE
					//DELETE_DIR
					ActionType actionType = actionTypes.get(name);
					if (actionType != null)
					{
						resourceLogService.recordChangeResource(user, actionType, name, null);
					}
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

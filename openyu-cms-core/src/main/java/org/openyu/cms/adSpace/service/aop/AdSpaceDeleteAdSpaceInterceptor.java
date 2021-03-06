package org.openyu.cms.adSpace.service.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.adSpace.service.AdSpaceLogService;
import org.openyu.cms.adSpace.vo.ActionType;
import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.app.aop.supporter.AppAroundAdviceSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除廣告版位攔截器
 */
public class AdSpaceDeleteAdSpaceInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(AdSpaceDeleteAdSpaceInterceptor.class);

	@Autowired
	@Qualifier("adSpaceLogService")
	protected transient AdSpaceLogService adSpaceLogService;

	public AdSpaceDeleteAdSpaceInterceptor()
	{}

	/**
	 * AdSapceService
	 * 
	 * List<AdSapce> deleteAdSapce(User user, Collection<Serializable> seqs);
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
			List<AdSpace> ret = (List<AdSpace>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (AdSpace adSpace : ret)
				{
					adSpaceLogService.recordChangeAdSpace(user, ActionType.DELETE, adSpace);
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

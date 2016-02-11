package org.openyu.cms.tag.service.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

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
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除標籤攔截器
 */
public class TagDeleteTagInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager.getLogger(TagDeleteTagInterceptor.class);

	@Autowired
	@Qualifier("tagLogService")
	protected transient TagLogService tagLogService;

	public TagDeleteTagInterceptor()
	{}

	/**
	 * TagService
	 * 
	 * List<Tag> deleteTag(User user, Collection<Serializable> seqs);
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
			List<Tag> ret = (List<Tag>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (Tag tag : ret)
				{
					tagLogService.recordChangeTag(user, ActionType.DELETE, tag);
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

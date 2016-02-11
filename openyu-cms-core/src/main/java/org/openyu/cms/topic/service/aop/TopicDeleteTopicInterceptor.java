package org.openyu.cms.topic.service.aop;

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
import org.openyu.cms.topic.service.TopicLogService;
import org.openyu.cms.topic.vo.ActionType;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除專題攔截器
 */
public class TopicDeleteTopicInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(TopicDeleteTopicInterceptor.class);

	@Autowired
	@Qualifier("topicLogService")
	protected transient TopicLogService topicLogService;

	public TopicDeleteTopicInterceptor()
	{}

	/**
	 * TopicService
	 * 
	 * List<Topic> deleteTopic(User user, Collection<Serializable> seqs);
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
			List<Topic> ret = (List<Topic>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (Topic topic : ret)
				{
					topicLogService.recordChangeTopic(user, ActionType.DELETE, topic);
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

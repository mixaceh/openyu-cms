package org.openyu.cms.topic.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.aop.AppMethodInterceptorSupporter;
import org.openyu.cms.topic.service.TopicLogService;
import org.openyu.cms.topic.vo.ActionType;
import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.user.vo.User;

/**
 * 新增專題攔截器
 */
public class TopicInsertTopicInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(TopicInsertTopicInterceptor.class);

	@Autowired
	@Qualifier("topicLogService")
	protected transient TopicLogService topicLogService;

	public TopicInsertTopicInterceptor()
	{}

	/**
	 * TopicService
	 * 
	 * int insertTopic(User user, Topic topic);
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
			Topic topic = clone(args[1]);

			// --------------------------------------------------
			result = methodInvocation.proceed();
			// --------------------------------------------------

			// --------------------------------------------------
			//proceed後
			// --------------------------------------------------

			//傳回值
			Integer ret = (Integer) result;
			//
			if (ret > 0)
			{
				topicLogService.recordChangeTopic(user, ActionType.INSERT, topic);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

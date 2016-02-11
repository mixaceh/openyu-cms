package org.openyu.cms.topic.service.aop;

import java.lang.reflect.Method;

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

/**
 * 修改專題攔截器
 */
public class TopicUpdateTopicInterceptor extends AppAroundAdviceSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(TopicUpdateTopicInterceptor.class);

	@Autowired
	@Qualifier("topicLogService")
	protected transient TopicLogService topicLogService;

	public TopicUpdateTopicInterceptor()
	{}

	/**
	 * TopicService
	 * 
	 * int updateTopic(User user, Topic topic);
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
			int ret = (Integer) result;
			//
			if (ret > 0)
			{
				topicLogService.recordChangeTopic(user, ActionType.UPDATE, topic);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

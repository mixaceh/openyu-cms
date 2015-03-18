package org.openyu.cms.vote.service.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.aop.AppMethodInterceptorSupporter;
import org.openyu.cms.vote.service.VoteLogService;
import org.openyu.cms.vote.vo.ActionType;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.user.vo.User;

/**
 * 新增投票攔截器
 */
public class VoteInsertVoteInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(VoteInsertVoteInterceptor.class);

	@Autowired
	@Qualifier("voteLogService")
	protected transient VoteLogService voteLogService;

	public VoteInsertVoteInterceptor()
	{}

	/**
	 * VoteService
	 * 
	 * int insertVote(User user, Vote vote);
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
			Vote vote = clone(args[1]);

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
				voteLogService.recordChangeVote(user, ActionType.INSERT, vote);
			}
		}
		catch (Throwable ex)
		{
			ex.printStackTrace();
		}
		return result;
	}
}

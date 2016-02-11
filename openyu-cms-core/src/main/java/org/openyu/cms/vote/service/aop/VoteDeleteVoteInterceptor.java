package org.openyu.cms.vote.service.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

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
import org.openyu.commons.util.CollectionHelper;

/**
 * 刪除投票攔截器
 */
public class VoteDeleteVoteInterceptor extends AppMethodInterceptorSupporter
{

	private static transient final Logger log = LogManager
			.getLogger(VoteDeleteVoteInterceptor.class);

	@Autowired
	@Qualifier("voteLogService")
	protected transient VoteLogService voteLogService;

	public VoteDeleteVoteInterceptor()
	{}

	/**
	 * VoteService
	 * 
	 * List<Vote> deleteVote(User user, Collection<Serializable> seqs);
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
			List<Vote> ret = (List<Vote>) result;
			//
			if (CollectionHelper.notEmpty(ret))
			{
				for (Vote vote : ret)
				{
					voteLogService.recordChangeVote(user, ActionType.DELETE, vote);
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

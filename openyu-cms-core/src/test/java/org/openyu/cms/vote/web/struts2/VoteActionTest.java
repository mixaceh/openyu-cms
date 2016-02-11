package org.openyu.cms.vote.web.struts2;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.openyu.cms.vote.VoteTestSupporter;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.vo.impl.VoteImpl;
import org.openyu.commons.util.DateHelper;

public class VoteActionTest extends VoteTestSupporter
{

	@Test
	public void execute()
	{
		String result = null;
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
//			result = voteAction.execute();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void list()
	{
		String result = null;
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
		{
//			result = voteAction.list();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertNotNull(result);
	}

}

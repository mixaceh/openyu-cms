package org.openyu.cms.keyword.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.keyword.vo.ActionType;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.keyword.vo.impl.KeywordImpl;
import org.openyu.cms.keyword.KeywordTestSupporter;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class KeywordLogServiceImplTest extends KeywordTestSupporter
{

	@After
	public void tearDown() throws Exception
	{
		ThreadHelper.sleep(5 * 1000);
	}

	@Test
	public void recordChangeDictionary()
	{
		final String UNIQUE = randomUnique();
		final String ID = "TEST_KEYWORD" + UNIQUE;
		//
		User user = new UserImpl();
		Keyword keyword = new KeywordImpl(ID);
		//
		Dictionary dictionary = new DictionaryImpl();
		String key = randomAlphabet(5);
		dictionary.setKey(key);
		dictionary.setValue(randomAlphabet(5));
		dictionary.setValid(randomBoolean());

		keywordLogService.recordChangeDictionary(user, ActionType.INSERT, keyword, null, dictionary);
	}

}

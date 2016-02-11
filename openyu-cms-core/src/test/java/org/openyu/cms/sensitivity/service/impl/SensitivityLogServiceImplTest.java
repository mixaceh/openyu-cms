package org.openyu.cms.sensitivity.service.impl;

import org.junit.After;
import org.junit.Test;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.cms.sensitivity.SensitivityTestSupporter;
import org.openyu.cms.sensitivity.vo.ActionType;
import org.openyu.cms.sensitivity.vo.Sensitivity;
import org.openyu.cms.sensitivity.vo.impl.SensitivityImpl;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.thread.ThreadHelper;

public class SensitivityLogServiceImplTest extends SensitivityTestSupporter
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
		final String ID = "TEST_SENSITIVITY" + UNIQUE;
		//
		User user = new UserImpl();
		Sensitivity sensitivity = new SensitivityImpl(ID);
		//
		Dictionary dictionary = new DictionaryImpl();
		String key = randomAlphabet(5);
		dictionary.setKey(key);
		dictionary.setValue(randomAlphabet(5));
		dictionary.setValid(randomBoolean());

		sensitivityLogService.recordChangeDictionary(user, ActionType.INSERT, sensitivity, null,
			dictionary);
	}

}

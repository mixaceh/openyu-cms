package org.openyu.cms.vote.vo.impl;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.cms.app.AppBeanTestSupporter;

public class VoteImplTest extends AppBeanTestSupporter {

	@Test
	public void writeToXml() {
		final String ID = "TEST_VOTE";
		//
		Vote value = new VoteImpl();
		value.setId(ID);
		value.addName(Locale.TRADITIONAL_CHINESE, "測試投票");
		value.addName(Locale.SIMPLIFIED_CHINESE, "测试模组");
		value.addName(Locale.US, "Test Group");
		//
		//
		value.setDescription(Locale.TRADITIONAL_CHINESE, "測試描述");
		value.setDescription(Locale.SIMPLIFIED_CHINESE, "測試描述");
		value.setDescription(Locale.US, "Description");
		//
		value.setStartDate(randomDate());
		value.setEndDate(randomDate());
		value.setRepeateHour(randomInt(24));
		value.setTotalCount(randomInt(100));
		value.setMultiSelect(randomInt(5));
		value.setRestrictMember(randomBoolean());
		value.setRestrictIp(randomBoolean());
		value.setRestrictCookie(randomBoolean());
		value.setValid(randomBoolean());
		value.setDefaultz(randomBoolean());

		// 投票
		Map<String, VoteItem> voteItems = new LinkedHashMap<String, VoteItem>();

		VoteItem voteItem = new VoteItemImpl();
		String key = randomAlphabet(5);
		voteItem.setId("1");
		voteItem.setName(Locale.TRADITIONAL_CHINESE, "測試標題1");
		voteItem.setName(Locale.SIMPLIFIED_CHINESE, "測試标题1");
		voteItem.setName(Locale.US, "Test Title 1");
		voteItem.setVoteCount(randomInt(100));
		voteItem.setSort(randomInt(10));
		voteItems.put(key, voteItem);

		voteItem = new VoteItemImpl();
		key = randomAlphabet(5);
		voteItem.setId("2");
		voteItem.setName(Locale.TRADITIONAL_CHINESE, "測試標題2");
		voteItem.setName(Locale.SIMPLIFIED_CHINESE, "測試标题2");
		voteItem.setName(Locale.US, "Test Title 2");
		voteItem.setVoteCount(randomInt(100));
		voteItem.setSort(randomInt(10));
		voteItems.put(key, voteItem);

		voteItem = new VoteItemImpl();
		key = randomAlphabet(5);
		voteItem.setId("3");
		voteItem.setName(Locale.TRADITIONAL_CHINESE, "測試標題3");
		voteItem.setName(Locale.SIMPLIFIED_CHINESE, "測試标题3");
		voteItem.setName(Locale.US, "Test Title 3");
		voteItem.setVoteCount(randomInt(100));
		voteItem.setSort(randomInt(10));
		voteItems.put(key, voteItem);

		value.setVoteItems(voteItems);
		//
		String result = beanCollector.writeToXml(VoteImpl.class, value);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void readFromXml() {
		Vote result = beanCollector.readFromXml(VoteImpl.class);
		System.out.println(result);
		assertNotNull(result);
	}

}

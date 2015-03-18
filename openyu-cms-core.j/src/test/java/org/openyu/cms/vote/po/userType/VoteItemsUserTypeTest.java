package org.openyu.cms.vote.po.userType;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.Test;

import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.cms.vote.vo.impl.VoteItemImpl;

import org.openyu.commons.junit.supporter.BaseTestSupporter;

public class VoteItemsUserTypeTest extends BaseTestSupporter {

	private static VoteItemsUserType userType = new VoteItemsUserType();

	@Test
	// 1000000 times: 101 mills.
	// 1000000 times: 116 mills.
	// 1000000 times: 100 mills.
	// verified
	public void assemble() {
		// voteItem
		Set<VoteItem> value = new LinkedHashSet<VoteItem>();

		VoteItem voteItem = new VoteItemImpl();
		voteItem.setName(Locale.TRADITIONAL_CHINESE, "測試標題1");
		voteItem.setName(Locale.SIMPLIFIED_CHINESE, "測試标题1");
		voteItem.setName(Locale.US, "Test Title 1");
		voteItem.setVoteCount(2);
		voteItem.setSort(1);
		value.add(voteItem);
		//
		voteItem = new VoteItemImpl();
		voteItem.setName(Locale.TRADITIONAL_CHINESE, "測試標題2");
		voteItem.setName(Locale.SIMPLIFIED_CHINESE, "測試标题2");
		voteItem.setName(Locale.US, "Test Title 2");
		voteItem.setVoteCount(33);
		voteItem.setSort(2);
		value.add(voteItem);
		//
		voteItem = new VoteItemImpl();
		voteItem.setName(Locale.TRADITIONAL_CHINESE, "測試標題3");
		voteItem.setName(Locale.SIMPLIFIED_CHINESE, "測試标题3");
		voteItem.setName(Locale.US, "Test Title 3");
		voteItem.setVoteCount(14);
		voteItem.setSort(3);
		value.add(voteItem);
		//
		String result = null;
		//
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = userType.marshal(value, null);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		// 1
		System.out.println(result);
		//
		assertEquals(
				"♥1♠3ⓓⓒ3♦zh_TW♣測試標題1♦zh_CN♣測試标题1♦en_US♣Test Title 1ⓒ2ⓒ1ⓓⓒ3♦zh_TW♣測試標題2♦zh_CN♣測試标题2♦en_US♣Test Title 2ⓒ33ⓒ2ⓓⓒ3♦zh_TW♣測試標題3♦zh_CN♣測試标题3♦en_US♣Test Title 3ⓒ14ⓒ3",
				result);
	}

	@Test
	// 1000000 times: 1358 mills.
	// 1000000 times: 1307 mills.
	// 1000000 times: 1339 mills.
	// verified
	public void disassemble() {
		String value = "♥1♠3ⓓⓒ3♦zh_TW♣測試標題1♦zh_CN♣測試标题1♦en_US♣Test Title 1ⓒ2ⓒ1ⓓⓒ3♦zh_TW♣測試標題2♦zh_CN♣測試标题2♦en_US♣Test Title 2ⓒ33ⓒ2ⓓⓒ3♦zh_TW♣測試標題3♦zh_CN♣測試标题3♦en_US♣Test Title 3ⓒ14ⓒ3";
		//
		Set<VoteItem> result = null;
		//
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = userType.unmarshal(value, null, null);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertEquals(3, result.size());
	}
}

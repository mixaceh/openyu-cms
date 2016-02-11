package org.openyu.cms.vote.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.openyu.cms.vote.po.VotePo;
import org.openyu.cms.vote.po.impl.VotePoImpl;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.cms.vote.vo.impl.VoteImpl;
import org.openyu.cms.vote.vo.impl.VoteItemImpl;
import org.openyu.cms.vote.VoteTestSupporter;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class VoteDaoImplTest extends VoteTestSupporter {

	/**
	 * 隨機產生投票資料
	 * 
	 * @return
	 */
	public static VotePo randomVotePo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_VOTE" + UNIQUE;
		final String ZH_TW_NAME = "測試投票" + UNIQUE;
		final String ZH_CN_NAME = "测试投票" + UNIQUE;
		final String EN_US_NAME = "Test Vote" + UNIQUE;
		//
		VotePo result = new VotePoImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setDescription(Locale.TRADITIONAL_CHINESE, "測試描述");
		result.setDescription(Locale.SIMPLIFIED_CHINESE, "測試描述");
		result.setDescription(Locale.US, "Description");
		//
		result.setStartDate(randomDate());
		result.setEndDate(randomDate());
		result.setRepeateHour(randomInt(24));
		result.setTotalCount(randomInt(100));
		result.setMultiSelect(randomInt(5));
		result.setRestrictMember(randomBoolean());
		result.setRestrictIp(randomBoolean());
		result.setRestrictCookie(randomBoolean());
		result.setValid(randomBoolean());
		result.setDft(randomBoolean());
		//
		// 投票項目
		Map<String, VoteItem> voteItems = new LinkedHashMap<String, VoteItem>();

		VoteItem voteItem = new VoteItemImpl();
		String key = "1";
		voteItem.setId("1");
		voteItem.setName(Locale.TRADITIONAL_CHINESE, "測試標題1");
		voteItem.setName(Locale.SIMPLIFIED_CHINESE, "測試标题1");
		voteItem.setName(Locale.US, "Test Title 1");
		voteItem.setVoteCount(randomInt(100));
		voteItem.setSort(randomInt(10));
		voteItems.put(key, voteItem);

		voteItem = new VoteItemImpl();
		key = "2";
		voteItem.setId("2");
		voteItem.setName(Locale.TRADITIONAL_CHINESE, "測試標題2");
		voteItem.setName(Locale.SIMPLIFIED_CHINESE, "測試标题2");
		voteItem.setName(Locale.US, "Test Title 2");
		voteItem.setVoteCount(randomInt(100));
		voteItem.setSort(randomInt(10));
		voteItems.put(key, voteItem);

		voteItem = new VoteItemImpl();
		key = "3";
		voteItem.setId("3");
		voteItem.setName(Locale.TRADITIONAL_CHINESE, "測試標題3");
		voteItem.setName(Locale.SIMPLIFIED_CHINESE, "測試标题3");
		voteItem.setName(Locale.US, "Test Title 3");
		voteItem.setVoteCount(randomInt(100));
		voteItem.setSort(randomInt(10));
		voteItems.put(key, voteItem);

		result.setVoteItems(voteItems);

		return result;
	}

	/**
	 * 檢核投票資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertVotePo(VotePo expected, VotePo actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getDescription(), actual.getDescription());
		//
		assertEquals(expected.getStartDate(), actual.getStartDate());
		assertEquals(expected.getEndDate(), actual.getEndDate());
		assertEquals(expected.getRepeateHour(), actual.getRepeateHour());
		assertEquals(expected.getTotalCount(), actual.getTotalCount());
		assertEquals(expected.getMultiSelect(), actual.getMultiSelect());
		assertEquals(expected.getRestrictMember(), actual.getRestrictMember());
		assertEquals(expected.getRestrictIp(), actual.getRestrictIp());
		assertEquals(expected.getRestrictCookie(), actual.getRestrictCookie());
		assertEquals(expected.getValid(), actual.getValid());
		assertEquals(expected.getDft(), actual.getDft());
		//
		// assertEquals(expected.getVoteItems(), actual.getVoteItems());
	}

	@Test
	// insert -> find -> delete
	//
	// 10 times: 7237 mills.
	// 10 times: 6825 mills.
	// 10 times: 6693 mills.
	//
	// verified: ok
	public void crud() {
		int count = 10;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// 隨機
			VotePo votePo = randomVotePo();
			// create
			Serializable pk = voteDao.insert(votePo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			VotePo foundEntity = voteDao
					.find(VotePoImpl.class, votePo.getSeq());
			printFind(i, foundEntity);
			assertVotePo(votePo, foundEntity);

			// update
			votePo.setName("ppp");
			votePo.setValid(true);
			int updated = voteDao.update(votePo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			VotePo deletedEntity = voteDao.delete(VotePoImpl.class,
					votePo.getSeq());
			printDelete(i, deletedEntity);
			assertNotNull(deletedEntity);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// verified: ok
	public void insert() {
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// 隨機
			VotePo votePo = randomVotePo();
			//
			Serializable pk = voteDao.insert(votePo);
			printInsert(i, pk);
			assertNotNull(pk);

			VotePo foundEntity = voteDao
					.find(VotePoImpl.class, votePo.getSeq());
			assertVotePo(votePo, foundEntity);

			System.out.println(votePo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void find() {
		VotePo result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteDao.find(VotePoImpl.class, 197L);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findVote() {

		List<VotePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteDao.findVote(true);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size());
		System.out.println(result);
		assertNotNull(result);
		//
		result = voteDao.findVote(false);
		System.out.println(result.size());
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findDefaultVote() {

		VotePo result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteDao.findDefaultVote();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	/**
	 * 查詢條件
	 */
	public void findVoteByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Vote searcher = new VoteImpl();
		searcher.setValid(true);
		//
		List<VotePo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteDao.findVote(inquiry, Locale.TRADITIONAL_CHINESE,
					searcher);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(inquiry.getPagination());
		System.out.println(result.size() + ", " + result);
		//
		assertNotNull(result);
		//
		searcher = new VoteImpl();
		searcher.setSeq(1);
		result = voteDao
				.findVote(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
		System.out.println(result.size() + ", " + result);
	}
}

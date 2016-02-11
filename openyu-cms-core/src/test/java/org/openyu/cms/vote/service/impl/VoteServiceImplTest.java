package org.openyu.cms.vote.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Id;

import org.junit.Test;

import org.openyu.cms.vote.VoteTestSupporter;
import org.openyu.cms.vote.po.impl.VotePoImpl;
import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.cms.vote.vo.impl.VoteImpl;
import org.openyu.cms.vote.vo.impl.VoteItemImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;
import org.openyu.commons.util.CollectionHelper;
import org.openyu.commons.util.DateHelper;

public class VoteServiceImplTest extends VoteTestSupporter {

	/**
	 * 隨機產生投票資料
	 * 
	 * @return
	 */
	public static Vote randomVote() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_VOTE" + UNIQUE;
		final String ZH_TW_NAME = "測試投票" + UNIQUE;
		final String ZH_CN_NAME = "测试模组" + UNIQUE;
		final String EN_US_NAME = "Test Vote" + UNIQUE;
		//
		Vote result = new VoteImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		result.setValid(randomBoolean());
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
		result.setDefaultz(randomBoolean());
		// 多個選項項目形成"投票投票"
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

		result.setVoteItems(voteItems);

		return result;
	}

	/**
	 * 檢核投票資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertVote(Vote expected, Vote actual) {
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
		assertEquals(expected.getDefaultz(), actual.getDefaultz());
		//
		// assertCollection(expected.getVoteItems(), actual.getVoteItems());
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
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			// 隨機
			Vote vote = randomVote();
			// create
			Serializable pk = voteService.insert(vote);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			Vote foundEntity = voteService.find(VoteImpl.class, vote.getSeq());
			printFind(i, foundEntity);
			assertVote(vote, foundEntity);

			// update
			vote.setName("ppp");
			vote.setValid(true);
			int updated = voteService.update(vote);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			Vote deletedEntity = voteService.delete(VoteImpl.class,
					vote.getSeq());
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
			Vote vote = randomVote();
			//
			Serializable pk = voteService.insert(vote);
			printInsert(i, pk);
			assertNotNull(pk);

			Vote foundEntity = voteService.find(VoteImpl.class, vote.getSeq());
			assertVote(vote, foundEntity);

			System.out.println(vote);
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
		Vote result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteService.find(VoteImpl.class, 197L);
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
		List<Vote> result = null;
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteService.findVote(true);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size() + ", " + result);
		assertNotNull(result);
		//
		result = voteService.findVote(false);
		System.out.println(result.size() + ", " + result);
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findDefaultVote() {
		Vote result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteService.findDefaultVote();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.toString());
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
		List<Vote> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteService.findVote(inquiry, Locale.TRADITIONAL_CHINESE,
					searcher);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(inquiry.getPagination());
		System.out.println(result.size() + ", " + result);
		//
		assertNotNull(result);
	}

	@Test
	// verified: ok
	public void updateSort() {
		List<Vote> result = null;
		//
		List<Long> seqs = new LinkedList<Long>();
		seqs.add(4L);
		seqs.add(5L);
		//
		List<Integer> sorts = new LinkedList<Integer>();
		sorts.add(10);
		sorts.add(20);
		//
		List<Boolean> valids = new LinkedList<Boolean>();
		valids.add(true);
		valids.add(true);
		long defaultSeq = 5L;
		//
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteService.updateSort(seqs, sorts, valids, defaultSeq);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		//
		System.out.println(result);
		assertTrue(result.size() > 0);
	}

	@Test
	public void delete() {
		List<Serializable> seqs = new LinkedList<Serializable>();
		seqs.add(25L);
		seqs.add(26L);
		//
		List<Vote> result = null;
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteService.delete(VoteImpl.class, seqs);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size() + ", " + result);
		assertNotNull(result);
		//
		result = null;
		seqs.clear();
		seqs.add("40");
		seqs.add("41");
		//
		List<Serializable> longs = CollectionHelper.toLongs(seqs);
		result = voteService.delete(VoteImpl.class, longs);
		System.out.println(result.size() + ", " + result);
		assertNotNull(result);
	}

	@Test
	public void deleteBySeq() {
		Vote result = null;
		int count = 1;
		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = voteService.delete(VoteImpl.class, 66L);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void annotation() {
		Annotation[] annotations = VotePoImpl.class.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
			// System.out.println(annotation.annotationType());
		}
		//
		Method pkMethod = null;
		Method[] methods = VotePoImpl.class.getMethods();
		for (Method method : methods) {
			Annotation[] annos = method.getAnnotations();
			for (Annotation anno : annos) {
				// System.out.println(anno);
				Class<?> clazz = anno.annotationType();
				if (clazz == Id.class) {
					System.out.println("pk: " + clazz);
					pkMethod = method;
					break;
				}
			}
		}
		//
		if (pkMethod != null) {
			Annotation[] annos = pkMethod.getAnnotations();
			for (Annotation anno : annos) {
				Class<?> clazz = anno.annotationType();
				if (clazz == Column.class) {
					System.out.println("pk: " + clazz);
				}
			}
		}

	}

	@Test
	public void datetimepicker() {
		Vote vote = voteService.find(VoteImpl.class, 1L);
		// 日期 時間[分開]
		String voteStartDate = DateHelper.toString(vote.getStartDate(),
				"yyyy-MM-dd");
		System.out.println(voteStartDate);
		String voteStartTime = DateHelper
				.toString(vote.getStartDate(), "HH:mm");
		System.out.println(voteStartTime);
		//
		String startDate = "2013-08-01T00:00:00+08:00";
		String startTime = "2013-08-01T09:30:00+08:00";
		System.out.println(startDate.substring(0, 10));
		System.out.println(startTime.substring(11, 18));
		System.out.println(startDate.substring(0, 10) + " "
				+ startTime.substring(11, 18));
		Date sdate0 = DateHelper.toDate(startDate, "yyyy-MM-dd HH:mm:ss");
		Date sdate1 = DateHelper.toDate(startDate.substring(0, 10) + " "
				+ startTime.substring(11, 18), "yyyy-MM-dd HH:mm:ss");
	}
}

package org.openyu.cms.ftp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.ftp.FtpTestSupporter;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.cms.ftp.vo.impl.FtpImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class FtpServiceImplTest extends FtpTestSupporter {
	/**
	 * 隨機產生FTP資料
	 * 
	 * @return
	 */
	public static Ftp randomFtp() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_FTP" + UNIQUE;
		final String ZH_TW_NAME = "測試Ftp" + UNIQUE;
		final String ZH_CN_NAME = "测试Ftp" + UNIQUE;
		final String EN_US_NAME = "Test Ftp" + UNIQUE;
		//
		Ftp result = new FtpImpl();
		result.setId(ID);
		result.addName(Locale.TRADITIONAL_CHINESE, ZH_TW_NAME);
		result.addName(Locale.SIMPLIFIED_CHINESE, ZH_CN_NAME);
		result.addName(Locale.US, EN_US_NAME);
		//
		result.setIp(randomIp("192.168.100"));
		result.setPort(randomInt(65536));
		result.setTimeout(randomInt(60));
		result.setAccount(randomString());
		result.setPassword(randomString());
		result.setPath(randomString());
		result.setEncoding(randomString());
		result.setUrl(randomString());
		//
		return result;
	}

	/**
	 * 檢核FTP資料
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertFtp(Ftp expected, Ftp actual) {
		assertNotNull(expected);
		assertNotNull(actual);

		assertEquals(expected.getSeq(), actual.getSeq());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		//
		assertEquals(expected.getIp(), actual.getIp());
		assertEquals(expected.getPort(), actual.getPort());
		assertEquals(expected.getTimeout(), actual.getTimeout());
		assertEquals(expected.getAccount(), actual.getAccount());
		assertEquals(expected.getPassword(), actual.getPassword());
		assertEquals(expected.getPath(), actual.getPath());
		assertEquals(expected.getEncoding(), actual.getEncoding());
		assertEquals(expected.getUrl(), actual.getUrl());
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findFtp() {

		List<Ftp> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = ftpService.findFtp();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");

		System.out.println(result.size());
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	/**
	 * 查詢條件
	 */
	public void findFtpByInquiry() {
		Inquiry inquiry = new InquiryImpl();
		// 分頁
		Pagination pagination = new PaginationImpl();
		pagination.setPageSize(5);// 每頁筆數
		inquiry.setPagination(pagination);
		//
		Ftp searcher = new FtpImpl();
		//
		List<Ftp> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = ftpService.findFtp(inquiry, Locale.TRADITIONAL_CHINESE,
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
	/**
	 * 取得所有Ftp
	 */
	// 1000000 times: 6219 mills.
	// 1000000 times: 5816 mills.
	// 1000000 times: 5757 mills.
	public void getFtps() {
		List<Object> result = null;
		int count = 1000000;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = ftpService.getBeanCache().getValues();
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
		System.out.println(result.size() + ", " + result);
		//
		assertNotNull(result);
	}

}

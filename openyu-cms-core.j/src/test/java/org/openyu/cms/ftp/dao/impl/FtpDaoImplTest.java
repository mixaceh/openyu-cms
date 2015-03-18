package org.openyu.cms.ftp.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.openyu.cms.ftp.FtpTestSupporter;
import org.openyu.cms.ftp.po.FtpPo;
import org.openyu.cms.ftp.po.impl.FtpPoImpl;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.cms.ftp.vo.impl.FtpImpl;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.dao.inquiry.impl.InquiryImpl;
import org.openyu.commons.dao.inquiry.impl.PaginationImpl;

public class FtpDaoImplTest extends FtpTestSupporter {
	/**
	 * 隨機產生FTP資料
	 * 
	 * @return
	 */
	public static FtpPo randomFtpPo() {
		final String UNIQUE = randomUnique();
		final String ID = "TEST_FTP" + UNIQUE;
		final String ZH_TW_NAME = "測試Ftp" + UNIQUE;
		final String ZH_CN_NAME = "测试Ftp" + UNIQUE;
		final String EN_US_NAME = "Test Ftp" + UNIQUE;
		//
		FtpPo result = new FtpPoImpl();
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
	public static void assertFtpPo(FtpPo expected, FtpPo actual) {
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
			FtpPo ftpPo = randomFtpPo();
			// create
			Serializable pk = ftpDao.insert(ftpPo);
			printInsert(i, pk);
			assertNotNull(pk);

			// retrieve
			FtpPo foundEntity = ftpDao.find(FtpPoImpl.class, ftpPo.getSeq());
			printFind(i, foundEntity);
			assertFtpPo(ftpPo, foundEntity);

			// update
			ftpPo.setName("ooo");
			// ftpPo.setDefault(true);
			int updated = ftpDao.update(ftpPo);
			printUpdate(i, updated);
			assertTrue(updated > 0);

			// delete
			FtpPo deletedEntity = ftpDao
					.delete(FtpPoImpl.class, ftpPo.getSeq());
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
			FtpPo ftpPo = randomFtpPo();
			//
			Serializable pk = ftpDao.insert(ftpPo);
			printInsert(i, pk);
			assertNotNull(pk);

			FtpPo foundEntity = ftpDao.find(FtpPoImpl.class, ftpPo.getSeq());
			assertFtpPo(ftpPo, foundEntity);

			System.out.println(ftpPo);
		}
		long end = System.currentTimeMillis();
		System.out.println(count + " times: " + (end - beg) + " mills. ");
	}

	@Test
	// 1 times: 272 mills.
	// 1 times: 276 mills.
	// 1 times: 276 mills.
	// verified: ok
	public void findFtp() {
		List<FtpPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = ftpDao.findFtp();
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
		List<FtpPo> result = null;
		int count = 1;

		long beg = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			result = ftpDao.findFtp(inquiry, Locale.TRADITIONAL_CHINESE,
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
		searcher = new FtpImpl();
		searcher.setSeq(1);
		result = ftpDao.findFtp(inquiry, Locale.TRADITIONAL_CHINESE, searcher);
		System.out.println(result.size() + ", " + result);
	}

}

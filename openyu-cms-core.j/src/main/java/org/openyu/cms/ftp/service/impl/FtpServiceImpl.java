package org.openyu.cms.ftp.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.ftp.dao.FtpDao;
import org.openyu.cms.ftp.po.FtpPo;
import org.openyu.cms.ftp.service.FtpService;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.cms.ftp.vo.FtpCollector;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ByteHelper;
import org.openyu.commons.lang.ClassHelper;
import org.openyu.commons.lang.EncodingHelper;
import org.openyu.commons.security.SecurityHelper;

public class FtpServiceImpl extends AppServiceSupporter implements FtpService {
	private static transient final Logger log = LogManager
			.getLogger(FtpServiceImpl.class);

	protected transient FtpCollector ftpCollector = FtpCollector.getInstance();

	public FtpServiceImpl() {
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		// 初始化cache,<id,Ftp>
		List<Ftp> ftps = findFtp();
		for (Ftp ftp : ftps) {
			addFtp(ftp);
		}
	}

	public FtpDao getFtpDao() {
		return (FtpDao) getOjDao();
	}

	@Autowired
	@Qualifier("ftpDao")
	public void setFtpDao(FtpDao ftpDao) {
		setOjDao(ftpDao);
	}

	public List<Ftp> findFtp() {
		List<FtpPo> orig = getFtpDao().findFtp();
		return ClassHelper.copyProperties(orig);
	}

	public List<Ftp> findFtp(Locale locale) {
		List<FtpPo> orig = getFtpDao().findFtp(locale);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param ftp
	 * @return
	 */
	public List<Ftp> findFtp(Locale locale, Ftp searcher) {
		List<FtpPo> orig = getFtpDao().findFtp(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param ftp
	 * @return
	 */
	public List<Ftp> findFtp(Inquiry inquiry, Locale locale, Ftp searcher) {
		List<FtpPo> orig = getFtpDao().findFtp(inquiry, locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------
	/**
	 * 加入Ftp
	 * 
	 * @param ftp
	 * @return
	 */
	public Ftp addFtp(Ftp ftp) {
		Ftp result = null;
		if (ftp != null) {
			result = (Ftp) beans.put(ftp.getId(), ftp);
		}
		return result;
	}

	/**
	 * 更新Ftp
	 * 
	 * @param origFtp
	 * @param newFtp
	 * @return
	 */
	public synchronized Ftp updateFtp(Ftp origFtp, Ftp newFtp) {
		Ftp result = null;
		if (origFtp != null) {
			String ftpId = origFtp.getId();
			boolean contains = beans.contains(ftpId);
			if (contains) {
				result = removeFtp(ftpId);
			}
			addFtp(newFtp);
		}
		return result;
	}

	/**
	 * 移除Ftp
	 * 
	 * @param ftpId
	 * @return
	 */
	public Ftp removeFtp(String ftpId) {
		Ftp result = null;
		if (ftpId != null) {
			result = (Ftp) beans.remove(ftpId);
		}
		return result;
	}

	/**
	 * 取得所有Ftp
	 * 
	 * @return
	 */
	public List<Ftp> getFtps() {
		List<Ftp> result = new LinkedList<Ftp>();
		for (Object entry : beans.getValues()) {
			if (entry instanceof Ftp) {
				result.add((Ftp) entry);
			}
		}
		return result;
	}

	// --------------------------------------------------
	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param ftpId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public Ftp createFtp(String ftpId) {
		return ftpCollector.createFtp(ftpId);
	}

	/**
	 * 對稱加密,傳回hex
	 * 
	 * @param value
	 * @return
	 */
	public String encrypt(String value) {
		return encrypt(value, EncodingHelper.UTF_8);
	}

	/**
	 * 對稱加密,傳回hex
	 * 
	 * @param value
	 * @param charsetName
	 * @return
	 */
	public String encrypt(String value, String charsetName) {
		String result = null;
		if (value != null) {
			// ALGORITHM = "DES"
			byte[] buf = SecurityHelper.securityProcessor(ByteHelper
					.toByteArray(value, charsetName));
			result = EncodingHelper.encodeHexString(buf);
		}
		return result;
	}

	/**
	 * 對稱解密,從hex解密
	 * 
	 * @param value
	 * @return
	 */
	public String decrypt(String value) {
		return decrypt(value, EncodingHelper.UTF_8);
	}

	/**
	 * 對稱解密,從hex解密
	 * 
	 * @param value
	 * @param charsetName
	 * @return
	 */
	public String decrypt(String value, String charsetName) {
		String result = null;
		if (value != null) {
			// ALGORITHM = "DES"
			byte[] buf = EncodingHelper.decodeHex(value);
			result = ByteHelper.toString(
					SecurityHelper.desecurityProcessor(buf), charsetName);
		}
		return result;
	}

}

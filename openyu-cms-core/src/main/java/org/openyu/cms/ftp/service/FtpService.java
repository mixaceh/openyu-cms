package org.openyu.cms.ftp.service;

import java.util.List;
import java.util.Locale;
import org.openyu.cms.app.service.AppService;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 附件FTP服務
 */
public interface FtpService extends AppService
{

	/**
	 * 查詢FTP
	 * 
	 * @return
	 */
	List<Ftp> findFtp();

	/**
	 * 查詢FTP
	 * 
	 * @param locale
	 * @return
	 */
	List<Ftp> findFtp(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Ftp> findFtp(Locale locale, Ftp searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Ftp> findFtp(Inquiry inquiry, Locale locale, Ftp searcher);

	// --------------------------------------------------
	/**
	 * 加入Ftp
	 * 
	 * @param ftp
	 * @return
	 */
	Ftp addFtp(Ftp ftp);

	/**
	 * 更新Ftp
	 * 
	 * @param origFtp
	 * @param newFtp
	 * @return
	 */
	Ftp updateFtp(Ftp origFtp, Ftp newFtp);

	/**
	 * 移除Ftp
	 * 
	 * @param ftpId
	 * @return
	 */
	Ftp removeFtp(String ftpId);

	/**
	 * 取得所有Ftp
	 * 
	 * @return
	 */
	List<Ftp> getFtps();

	// --------------------------------------------------
	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param ftpId, DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	Ftp createFtp(String ftpId);

	/**
	 * 對稱加密,傳回hex
	 * 
	 * @param value
	 * @return
	 */
	String encrypt(String value);

	/**
	 * 對稱加密,傳回hex
	 * 
	 * @param value
	 * @param charsetName
	 * @return
	 */
	String encrypt(String value, String charsetName);

	/**
	 * 對稱解密,從hex解密
	 * 
	 * @param value
	 * @return
	 */
	String decrypt(String value);

	/**
	 * 對稱解密,從hex解密
	 * 
	 * @param value
	 * @param charsetName
	 * @return
	 */
	String decrypt(String value, String charsetName);
}
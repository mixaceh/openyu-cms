package org.openyu.cms.ftp.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.ftp.po.FtpPo;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface FtpDao extends AppDao
{
	/**
	 * 查詢FTP
	 * 
	 * @return
	 */
	List<FtpPo> findFtp();

	/**
	 * 查詢FTP
	 * 
	 * @param locale
	 * @return
	 */
	List<FtpPo> findFtp(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<FtpPo> findFtp(Locale locale, Ftp searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<FtpPo> findFtp(Inquiry inquiry, Locale locale, Ftp searcher);

}
package org.openyu.cms.keyword.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.keyword.po.KeywordPo;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.site.vo.Site;
import org.openyu.commons.dao.OjDao;
import org.openyu.commons.dao.inquiry.Inquiry;

public interface KeywordDao extends OjDao
{

	/**
	 * 查詢關鍵字
	 * 
	 * @param cacheable
	 * @return
	 */
	public List<KeywordPo> findKeyword();

	/**
	 * 查詢關鍵字
	 * 
	 * @param locale
	 * @return
	 */
	List<KeywordPo> findKeyword(Locale locale);

	/**
	 * 查詢關鍵字,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<KeywordPo> findKeyword(long siteSeq);

	/**
	 * 查詢關鍵字,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @return
	 */
	public List<KeywordPo> findKeyword(Locale locale, long siteSeq);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<KeywordPo> findKeyword(Locale locale, long siteSeq, Keyword searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<KeywordPo> findKeyword(Locale locale, Site siteSearcher, Keyword searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<KeywordPo> findKeyword(Inquiry inquiry, Locale locale, long siteSeq,
										Keyword searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<KeywordPo> findKeyword(Inquiry inquiry, Locale locale, Site siteSearcher,
										Keyword searcher);
}
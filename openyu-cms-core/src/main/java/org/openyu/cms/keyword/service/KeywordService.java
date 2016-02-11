package org.openyu.cms.keyword.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.service.AppService;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 關鍵字服務
 */
public interface KeywordService extends AppService {
	/**
	 * 置換關鍵字
	 * 
	 * @param sentence
	 * @return
	 */
	String replaceKeyword(String sentence);

	/**
	 * 查詢關鍵字(全部Locale)
	 * 
	 * @return
	 */
	List<Keyword> findKeyword();

	/**
	 * 查詢關鍵字(依Locale)
	 * 
	 * @param locale
	 * @return
	 */
	List<Keyword> findKeyword(Locale locale);

	/**
	 * 查詢關鍵字,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	List<Keyword> findKeyword(long siteSeq);

	/**
	 * 查詢關鍵字(分頁)
	 * 
	 * @param inquiry
	 * @return
	 */
	List<Keyword> findKeyword(Inquiry inquiry);

	/**
	 * 查詢關鍵字(分頁, 依Locale)
	 * 
	 * @param inquiry
	 * @param locale
	 * @return
	 */
	List<Keyword> findKeyword(Inquiry inquiry, Locale locale);

	/**
	 * 查詢關鍵字(分頁, 依Locale, searcher)
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Keyword> findKeyword(Inquiry inquiry, Locale locale,
			Dictionary searcher);

	/**
	 * 查詢關鍵字(分頁, 依Locale, siteSeq, searcher)
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<Keyword> findKeyword(Inquiry inquiry, Locale locale, long siteSeq,
			Dictionary searcher);

	/**
	 * 查詢關鍵字,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @return
	 */
	List<Keyword> findKeyword(Locale locale, long siteSeq);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<Keyword> findKeyword(Locale locale, long siteSeq, Keyword searcher);

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	List<Keyword> findKeyword(Inquiry inquiry, Locale locale, long siteSeq,
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
	List<Keyword> findKeyword(Inquiry inquiry, Locale locale,
			Site siteSearcher, Keyword searcher);

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param keywordId
	 * @param locale
	 * @return
	 */
	Keyword createKeyword(String keywordId, Locale locale);

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param dictionaryKey
	 * @return
	 */
	Dictionary createDictionary(String dictionaryKey);

	/**
	 * 新增關鍵字
	 * 
	 * @param user
	 * @param keyword
	 * @param dictionary
	 * @return
	 */
	Serializable insertDictionary(User user, Keyword keyword,
			Dictionary dictionary);

	/**
	 * 修改關鍵字
	 * 
	 * @param user
	 * @param keyword
	 * @param origDictionary
	 * @param dictionary
	 * @return
	 */
	int updateDictionary(User user, Keyword keyword, Dictionary origDictionary,
			Dictionary dictionary);

	/**
	 * 刪除關鍵字
	 * 
	 * @param user
	 * @param keyword
	 * @param keys
	 * @return
	 */
	List<Dictionary> deleteDictionary(User user, Keyword keyword,
			List<String> keys);

}
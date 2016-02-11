package org.openyu.cms.keyword.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.keyword.dao.KeywordDao;
import org.openyu.cms.keyword.po.KeywordPo;
import org.openyu.cms.keyword.service.KeywordService;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.keyword.vo.KeywordCollector;
import org.openyu.cms.keyword.vo.impl.KeywordImpl;
import org.openyu.cms.site.vo.Site;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.lang.ClassHelper;
import org.openyu.commons.lang.StringHelper;
import org.openyu.commons.util.CollectionHelper;
import org.openyu.commons.util.LocaleHelper;

public class KeywordServiceImpl extends AppServiceSupporter implements
		KeywordService {
	private static transient final Logger log = LogManager
			.getLogger(KeywordServiceImpl.class);

	protected transient KeywordCollector keywordCollector = KeywordCollector
			.getInstance();

	public KeywordServiceImpl() {

	}

	public KeywordDao getKeywordDao() {
		return (KeywordDao) getOjDao();
	}

	@Autowired
	@Qualifier("keywordDao")
	public void setKeywordDao(KeywordDao keywordDao) {
		setOjDao(keywordDao);
	}

	public List<Keyword> findKeyword() {
		List<KeywordPo> orig = getKeywordDao().findKeyword();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢關鍵字
	 */
	public List<Keyword> findKeyword(Locale locale) {
		List<KeywordPo> orig = getKeywordDao().findKeyword(locale);
		return ClassHelper.copyProperties(orig);
	}

	public List<Keyword> findKeyword(Inquiry inquiry) {
		return findKeyword(inquiry, null);
	}

	/**
	 * 查詢關鍵字
	 */
	public List<Keyword> findKeyword(Inquiry inquiry, Locale locale) {
		return findKeyword(inquiry, locale, null);
	}

	/**
	 * 置換關鍵字
	 */
	public String replaceKeyword(String sentence) {
		String result = sentence;
		if (StringUtils.isBlank(result)) {
			return result;
		}
		// LocaleHelper.getLocale(); 取得xml(appConfig-op.xml)定義的Locale
		List<KeywordPo> list = getKeywordDao().findKeyword();
		Map<String, Dictionary> dictionarys = null;
		for (KeywordPo keywordPo : list) {
			dictionarys = keywordPo.getDictionarys();
			for (String key : dictionarys.keySet()) {
				if (dictionarys.get(key).getValid()) {
					result = result.replace(key, dictionarys.get(key)
							.getValue());
				}
			}
		}
		return result;
	}

	/**
	 * 查詢關鍵字(分頁, 依Locale, searcher)
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<Keyword> findKeyword(Inquiry inquiry, Locale locale,
			Dictionary searcher) {
		return findKeyword(inquiry, locale, 0, searcher);
	}

	/**
	 * 查詢關鍵字(分頁, 依Locale, searcher)
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<Keyword> findKeyword(Inquiry inquiry, Locale locale,
			long siteSeq, Dictionary searcher) {
		List<Keyword> result = null;
		if (locale == null) {
			if (siteSeq > 0) {
				result = findKeyword(LocaleHelper.getLocale(), siteSeq);
			} else {
				result = findKeyword(LocaleHelper.getLocale());
			}
		} else {
			if (siteSeq > 0) {
				result = findKeyword(locale, siteSeq);
			} else {
				result = findKeyword(locale);
			}
		}

		if (CollectionHelper.notEmpty(result)) {
			Keyword keyword = result.get(0);
			//
			Map<String, Dictionary> dictionarys = keyword.getDictionarys();
			// 暫時存放
			Map<String, Dictionary> buffs = new LinkedHashMap<String, Dictionary>();
			// find
			// 排序 order
			if (searcher != null) {
				// 搜尋 search result into buffs
				if (StringHelper.notBlank(searcher.getKey())) {
					for (String key : dictionarys.keySet()) {
						if (key.toLowerCase().indexOf(
								searcher.getKey().toLowerCase()) > -1) {
							buffs.put(key, dictionarys.get(key));
						}
					}
				} else {
					buffs = dictionarys;
				}
				// key 排序 buffs
				if (StringHelper.notBlank(inquiry.getSort().getId())
						&& buffs.size() > 1) {
					if (inquiry.getSort().getId().equals("key")) {
						buffs = getOrderByKey(buffs, inquiry.getOrder().getId()
								.getValue());
					}
					// value 排序
					else if (inquiry.getSort().getId().equals("value")) {
						buffs = getOrderByValue(buffs, inquiry.getOrder()
								.getId().getValue());
					}
				}
				// 結果丟回 dictionarys
				dictionarys = buffs;
			}
			// 分頁
			Pagination pagination = inquiry.getPagination();
			// 10w
			if (CollectionHelper.notEmpty(dictionarys)) {
				pagination.setRowCount(dictionarys.size());// 1w

			}

			buffs = new LinkedHashMap<String, Dictionary>();
			int index = 0;
			int rowCount = (pagination.getFirstResult()
					+ pagination.getMaxResults() > pagination.getRowCount()) ? pagination
					.getRowCount() : pagination.getFirstResult()
					+ pagination.getMaxResults();

			for (Map.Entry<String, Dictionary> entry : dictionarys.entrySet()) {
				if (index >= pagination.getFirstResult() && index < rowCount) {
					buffs.put(entry.getKey(), entry.getValue());
					if (buffs.size() == pagination.getMaxResults()) {
						break;
					}
				}
				//
				index++;
			}

			//
			keyword.setDictionarys(buffs);
		}
		return result;
	}

	/**
	 * 查詢關鍵字,網站別
	 * 
	 * @param siteSeq
	 * @return
	 */
	public List<Keyword> findKeyword(long siteSeq) {
		List<KeywordPo> orig = getKeywordDao().findKeyword(siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢關鍵字,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @return
	 */
	public List<Keyword> findKeyword(Locale locale, long siteSeq) {
		List<KeywordPo> orig = getKeywordDao().findKeyword(locale, siteSeq);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<Keyword> findKeyword(Locale locale, long siteSeq,
			Keyword searcher) {
		List<KeywordPo> orig = getKeywordDao().findKeyword(locale, siteSeq,
				searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSeq
	 * @param searcher
	 * @return
	 */
	public List<Keyword> findKeyword(Inquiry inquiry, Locale locale,
			long siteSeq, Keyword searcher) {
		List<KeywordPo> orig = getKeywordDao().findKeyword(inquiry, locale,
				siteSeq, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件,網站別
	 * 
	 * @param inquiry
	 * @param locale
	 * @param siteSearcher
	 * @param searcher
	 * @return
	 */
	public List<Keyword> findKeyword(Inquiry inquiry, Locale locale,
			Site siteSearcher, Keyword searcher) {
		List<KeywordPo> orig = getKeywordDao().findKeyword(inquiry, locale,
				siteSearcher, searcher);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	// default order asc
	/**
	 * 以key做排序
	 * 
	 * @param map
	 * @param order
	 *            DESC | ASC
	 * @return
	 */
	public LinkedHashMap<String, Dictionary> getOrderByKey(
			Map<String, Dictionary> map, final String order) {
		List<Map.Entry<String, Dictionary>> infoIds = new ArrayList<Map.Entry<String, Dictionary>>(
				map.entrySet());

		// 排序
		Collections.sort(infoIds,
				new Comparator<Map.Entry<String, Dictionary>>() {
					public int compare(Map.Entry<String, Dictionary> o1,
							Map.Entry<String, Dictionary> o2) {
						String p1 = (String) o1.getKey();
						String p2 = (String) o2.getKey();

						if (order.equals("desc"))
							return p2.compareTo(p1);
						else
							return p1.compareTo(p2);
					}
				});

		/* 轉換成新map輸出 */
		LinkedHashMap<String, Dictionary> newMap = new LinkedHashMap<String, Dictionary>();

		for (Map.Entry<String, Dictionary> entity : infoIds) {
			newMap.put(entity.getKey(), entity.getValue());
		}

		return newMap;
	}

	/**
	 * 以value做排序
	 * 
	 * @param map
	 * @param order
	 *            DESC | ASC
	 * @return
	 */
	public LinkedHashMap<String, Dictionary> getOrderByValue(
			Map<String, Dictionary> map, final String order) {
		List<Map.Entry<String, Dictionary>> infoIds = new ArrayList<Map.Entry<String, Dictionary>>(
				map.entrySet());

		// 排序
		Collections.sort(infoIds,
				new Comparator<Map.Entry<String, Dictionary>>() {
					public int compare(Map.Entry<String, Dictionary> o1,
							Map.Entry<String, Dictionary> o2) {
						String p1 = (String) o1.getValue().getValue();
						String p2 = (String) o2.getValue().getValue();

						if (order.equals("desc"))
							return p2.compareTo(p1);
						else
							return p1.compareTo(p2);
					}
				});

		/* 轉換成新map輸出 */
		LinkedHashMap<String, Dictionary> newMap = new LinkedHashMap<String, Dictionary>();

		for (Map.Entry<String, Dictionary> entity : infoIds) {
			newMap.put(entity.getKey(), entity.getValue());
		}

		return newMap;
	}

	// --------------------------------------------------------------------------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param keywordId
	 * @param locale
	 * @return
	 */
	public Keyword createKeyword(String keywordId, Locale locale) {
		return keywordCollector.createKeyword(keywordId, locale);
	}

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param dictionaryKey
	 * @return
	 */
	public Dictionary createDictionary(String dictionaryKey) {
		return keywordCollector.createDictionary(dictionaryKey);
	}

	// -----------------------------------------
	/**
	 * 新增關鍵字
	 * 
	 * @param user
	 * @param keyword
	 * @param dictionary
	 * @return
	 */
	public Serializable insertDictionary(User user, Keyword keyword,
			Dictionary dictionary) {
		Serializable result = null;

		// 存在的關鍵字
		Keyword existKeyword = find(KeywordImpl.class, keyword.getSeq());

		if (existKeyword == null) {
			keyword.getDictionarys().put(dictionary.getKey(), dictionary);

			// locale
			keyword.setLocale(user.getSessionLocale());
			result = insert(keyword, user.getId());
		}
		// exist
		// 但也是表示dictionary新增成功
		else {
			// dictionarys
			keyword.setDictionarys(existKeyword.getDictionarys());
			// MAP操作 add
			boolean contains = keyword.getDictionarys().containsKey(
					dictionary.getKey());
			if (!contains) {
				keyword.getDictionarys().put(dictionary.getKey(), dictionary);
				// audit
				keyword.setAudit(existKeyword.getAudit());
				// locale
				keyword.setLocale(existKeyword.getLocale());
				//
				result = update(keyword, user.getId());
			}
		}
		return result;
	}

	/**
	 * 修改關鍵字
	 * 
	 * @param user
	 * @param keyword
	 * @param origDictionary
	 * @param dictionary
	 * @param locale
	 * @return
	 */
	public int updateDictionary(User user, Keyword keyword,
			Dictionary origDictionary, Dictionary dictionary) {
		int result = 0;
		// MAP操作 update
		Map<String, Dictionary> dictionarys = keyword.getDictionarys();

		// key change
		if (!origDictionary.getKey().equals(dictionary.getKey())) {
			dictionarys.remove(origDictionary.getKey());
			dictionarys.put(dictionary.getKey(), dictionary);
		}
		// value & valid change
		else if (!origDictionary.getValue().equals(dictionary.getValue())
				|| (origDictionary.getValid() != dictionary.getValid())) {
			dictionarys.put(dictionary.getKey(), dictionary);
		}
		//
		result = update(keyword, user.getId());
		return result;
	}

	/**
	 * 刪除關鍵字
	 * 
	 * @param user
	 * @param keyword
	 * @param keys
	 * @return
	 */
	public List<Dictionary> deleteDictionary(User user, Keyword keyword,
			List<String> keys) {
		List<Dictionary> result = new LinkedList<Dictionary>();
		// MAP操作 delete
		// keyword = (Keyword) keyword.clone();
		Map<String, Dictionary> dictionarys = keyword.getDictionarys();
		for (String key : keys) {
			boolean contains = dictionarys.containsKey(key);
			if (contains) {
				Dictionary orig = dictionarys.remove(key);
				result.add(orig);
			}
		}

		//
		int update = update(keyword, user.getId());
		if (update < 1) {
			result = new LinkedList<Dictionary>();
		}
		return result;
	}

}

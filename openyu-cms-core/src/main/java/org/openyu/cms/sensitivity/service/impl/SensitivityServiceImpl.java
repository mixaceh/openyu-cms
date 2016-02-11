package org.openyu.cms.sensitivity.service.impl;

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
import org.openyu.cms.sensitivity.service.impl.SensitivityServiceImpl;
import org.openyu.cms.sensitivity.vo.SensitivityCollector;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.sensitivity.dao.SensitivityDao;
import org.openyu.cms.sensitivity.po.SensitivityPo;
import org.openyu.cms.sensitivity.service.SensitivityService;
import org.openyu.cms.sensitivity.vo.Sensitivity;
import org.openyu.cms.sensitivity.vo.impl.SensitivityImpl;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.dao.inquiry.Pagination;
import org.openyu.commons.lang.ClassHelper;
import org.openyu.commons.lang.StringHelper;
import org.openyu.commons.util.CollectionHelper;
import org.openyu.commons.util.LocaleHelper;

public class SensitivityServiceImpl extends AppServiceSupporter implements
		SensitivityService {
	private static transient final Logger log = LogManager
			.getLogger(SensitivityServiceImpl.class);

	protected transient SensitivityCollector sensitivityCollector = SensitivityCollector
			.getInstance();

	public SensitivityServiceImpl() {

	}

	public SensitivityDao getSensitivityDao() {
		return (SensitivityDao) getOjDao();
	}

	@Autowired
	@Qualifier("sensitivityDao")
	public void setSensitivityDao(SensitivityDao sensitivityDao) {
		setOjDao(sensitivityDao);
	}

	public List<Sensitivity> findSensitivity() {
		List<SensitivityPo> orig = getSensitivityDao().findSensitivity();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢敏感詞
	 */
	public List<Sensitivity> findSensitivity(Locale locale) {
		List<SensitivityPo> orig = getSensitivityDao().findSensitivity(locale);
		return ClassHelper.copyProperties(orig);
	}

	public List<Sensitivity> findSensitivity(Inquiry inquiry) {
		return findSensitivity(inquiry, null);
	}

	/**
	 * 查詢敏感詞
	 */
	public List<Sensitivity> findSensitivity(Inquiry inquiry, Locale locale) {
		List<Sensitivity> result = null;
		if (locale == null) {
			result = findSensitivity(LocaleHelper.getLocale());
		} else {
			result = findSensitivity(locale);
		}

		if (CollectionHelper.notEmpty(result)) {
			// 排序 order
			// 搜尋 sort
			// inquiry.getSort();
			// 分頁
			Pagination pagination = inquiry.getPagination();
			//
			Sensitivity sensitivity = result.get(0);
			Map<String, Dictionary> dictionarys = sensitivity.getDictionarys();
			// 10w
			if (CollectionHelper.notEmpty(dictionarys)) {
				pagination.setRowCount(dictionarys.size());// 1w

			}
			//
			Map<String, Dictionary> buffs = new LinkedHashMap<String, Dictionary>();

			int index = 0;
			int rowCount = (pagination.getFirstResult()
					+ pagination.getMaxResults() > pagination.getRowCount()) ? pagination
					.getRowCount() : pagination.getFirstResult()
					+ pagination.getMaxResults();
			// System.out.println("rowCount>> " + rowCount);

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
			sensitivity.setDictionarys(buffs);
		}
		return result;
	}

	/**
	 * 置換敏感詞
	 */
	public String replaceSensitivity(String sentence) {
		String result = sentence;
		if (StringUtils.isBlank(result)) {
			return result;
		}
		// LocaleHelper.getLocale(); 取得xml(appConfig-op.xml)定義的Locale
		List<SensitivityPo> list = getSensitivityDao().findSensitivity();
		Map<String, Dictionary> dictionarys = null;
		for (SensitivityPo sensitivityPo : list) {
			dictionarys = sensitivityPo.getDictionarys();
			for (String key : dictionarys.keySet()) {
				result = result.replace(key, dictionarys.get(key).getValue());
			}
		}
		return result;
	}

	public List<Sensitivity> findSensitivity(Inquiry inquiry, Locale locale,
			Dictionary searcher) {
		List<Sensitivity> result = null;
		if (locale == null) {
			result = findSensitivity(LocaleHelper.getLocale());
		} else {
			result = findSensitivity(locale);
		}

		if (CollectionHelper.notEmpty(result)) {
			Sensitivity sensitivity = result.get(0);
			//
			Map<String, Dictionary> dictionarys = sensitivity.getDictionarys();
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
				// buffs = getOrderByKey(sensitivity.getDictionarys(),"DESC" );
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
					// 結果丟回 dictionarys
					dictionarys = buffs;
				}
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
			// System.out.println("rowCount>> " + rowCount);

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
			sensitivity.setDictionarys(buffs);
		}
		return result;
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

	// --------------------------------------------------
	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param sensitivityId
	 * @param locale
	 * @return
	 */
	public Sensitivity createSensitivity(String sensitivityId, Locale locale) {
		return sensitivityCollector.createSensitivity(sensitivityId, locale);
	}

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param dictionaryKey
	 * @return
	 */
	public Dictionary createDictionary(String dictionaryKey) {
		return sensitivityCollector.createDictionary(dictionaryKey);
	}

	// -----------------------------------------
	/**
	 * 新增敏感詞
	 * 
	 * @param user
	 * @param sensitivity
	 * @param dictionary
	 * @return
	 */
	public Serializable insertDictionary(User user, Sensitivity sensitivity,
			Dictionary dictionary) {
		Serializable result = null;

		// 存在的敏感詞
		Sensitivity existSensitivity = find(SensitivityImpl.class,
				sensitivity.getSeq());

		if (existSensitivity == null) {
			sensitivity.getDictionarys().put(dictionary.getKey(), dictionary);

			// locale
			sensitivity.setLocale(user.getSessionLocale());
			result = insert(sensitivity, user.getId());
		}
		// exist
		// 但也是表示dictionary新增成功
		else {
			// dictionarys
			sensitivity.setDictionarys(existSensitivity.getDictionarys());
			// MAP操作 add
			boolean contains = sensitivity.getDictionarys().containsKey(
					dictionary.getKey());
			if (!contains) {
				sensitivity.getDictionarys().put(dictionary.getKey(),
						dictionary);
				// audit
				sensitivity.setAudit(existSensitivity.getAudit());
				// locale
				sensitivity.setLocale(existSensitivity.getLocale());
				//
				result = update(sensitivity, user.getId());
			}
		}
		return result;
	}

	/**
	 * 修改敏感詞
	 * 
	 * @param user
	 * @param sensitivity
	 * @param origDictionary
	 * @param dictionary
	 * @return
	 */
	public int updateDictionary(User user, Sensitivity sensitivity,
			Dictionary origDictionary, Dictionary dictionary) {
		int result = 0;
		// MAP操作 update
		Map<String, Dictionary> dictionarys = sensitivity.getDictionarys();

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
		result = update(sensitivity, user.getId());
		return result;
	}

	/**
	 * 刪除敏感詞
	 * 
	 * @param user
	 * @param sensitivity
	 * @param keys
	 * @return
	 */
	public List<Dictionary> deleteDictionary(User user,
			Sensitivity sensitivity, List<String> keys) {
		List<Dictionary> result = new LinkedList<Dictionary>();
		// MAP操作 delete
		// sensitivity = (Sensitivity) sensitivity.clone();
		Map<String, Dictionary> dictionarys = sensitivity.getDictionarys();
		for (String key : keys) {
			boolean contains = dictionarys.containsKey(key);
			if (contains) {
				Dictionary orig = dictionarys.remove(key);
				result.add(orig);
			}
		}

		//
		int update = update(sensitivity, user.getId());
		if (update < 1) {
			result = new LinkedList<Dictionary>();
		}
		return result;
	}

}

package org.openyu.cms.sensitivity.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.openyu.cms.app.service.AppService;
import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.sensitivity.vo.Sensitivity;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 敏感詞服務
 */
public interface SensitivityService extends AppService {
	/**
	 * 置換敏感詞
	 * 
	 * @param sentence
	 * @return
	 */
	String replaceSensitivity(String sentence);

	/**
	 * 查詢敏感詞(全部Locale)
	 * 
	 * @return
	 */
	List<Sensitivity> findSensitivity();

	/**
	 * 查詢敏感詞(依Locale)
	 * 
	 * @param cacheable
	 * @return
	 */
	List<Sensitivity> findSensitivity(Locale locale);

	/**
	 * 查詢敏感詞(分頁)
	 * 
	 * @param inquiry
	 * @return
	 */
	List<Sensitivity> findSensitivity(Inquiry inquiry);

	/**
	 * 查詢敏感詞(分頁, 依Locale)
	 * 
	 * @param inquiry
	 * @param locale
	 * @return
	 */
	List<Sensitivity> findSensitivity(Inquiry inquiry, Locale locale);

	/**
	 * 查詢敏感詞(分頁, 依Locale, searcher)
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Sensitivity> findSensitivity(Inquiry inquiry, Locale locale,
			Dictionary searcher);

	// --------------------------------------------------
	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param sensitivityId
	 * @param locale
	 * @return
	 */
	Sensitivity createSensitivity(String sensitivityId, Locale locale);

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param dictionaryKey
	 * @return
	 */
	Dictionary createDictionary(String dictionaryKey);

	/**
	 * 新增敏感詞
	 * 
	 * @param user
	 * @param sensitivity
	 * @param dictionary
	 * @return
	 */
	Serializable insertDictionary(User user, Sensitivity sensitivity,
			Dictionary dictionary);

	/**
	 * 修改敏感詞
	 * 
	 * @param user
	 * @param sensitivity
	 * @param origDictionary
	 * @param dictionary
	 * @return
	 */
	int updateDictionary(User user, Sensitivity sensitivity,
			Dictionary origDictionary, Dictionary dictionary);

	/**
	 * 刪除敏感詞
	 * 
	 * @param user
	 * @param sensitivity
	 * @param keys
	 * @return
	 */
	List<Dictionary> deleteDictionary(User user, Sensitivity sensitivity,
			List<String> keys);

}
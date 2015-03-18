package org.openyu.cms.tag.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.user.vo.User;
import org.openyu.cms.app.service.AppService;
import org.openyu.commons.dao.inquiry.Inquiry;

/**
 * 標籤服務
 */
public interface TagService extends AppService {

	/**
	 * 查詢標籤
	 * 
	 * @param valid
	 * @return
	 */
	List<Tag> findTag();

	/**
	 * 查詢標籤
	 * 
	 * @param locale
	 * @param valid
	 * @return
	 */
	List<Tag> findTag(Locale locale);

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Tag> findTag(Locale locale, Tag searcher);

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	List<Tag> findTag(Inquiry inquiry, Locale locale, Tag searcher);

	// --------------------------------------------------

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param tagId
	 *            , DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	Tag createTag(String tagId);

}
package org.openyu.cms.tag.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.tag.dao.TagDao;
import org.openyu.cms.tag.po.TagPo;
import org.openyu.cms.tag.service.TagService;
import org.openyu.cms.tag.vo.TagCollector;
import org.openyu.cms.tag.vo.impl.TagImpl;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.ClassHelper;

public class TagServiceImpl extends AppServiceSupporter implements TagService {
	private static transient final Logger log = LogManager
			.getLogger(TagServiceImpl.class);

	protected transient TagCollector tagCollector = TagCollector.getInstance();

	public TagServiceImpl() {
	}

	public TagDao getTagDao() {
		return (TagDao) getOjDao();
	}

	@Autowired
	@Qualifier("tagDao")
	public void setTagDao(TagDao tagDao) {
		setOjDao(tagDao);
	}

	/**
	 * 查詢標籤
	 * 
	 * @return
	 */
	public List<Tag> findTag() {
		List<TagPo> orig = getTagDao().findTag();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢標籤
	 * 
	 * @param locale
	 * @return
	 */
	public List<Tag> findTag(Locale locale) {
		List<TagPo> orig = getTagDao().findTag(locale);
		return ClassHelper.copyProperties(orig);
	}

	// --------------------------------------------------

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param tag
	 * @return
	 */
	public List<Tag> findTag(Locale locale, Tag searcher) {
		List<TagPo> orig = getTagDao().findTag(locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param tag
	 * @return
	 */
	public List<Tag> findTag(Inquiry inquiry, Locale locale, Tag searcher) {
		List<TagPo> orig = getTagDao().findTag(inquiry, locale, searcher);
		return ClassHelper.copyProperties(orig);
	}

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
	public Tag createTag(String tagId) {
		return tagCollector.createTag(tagId);
	}

}

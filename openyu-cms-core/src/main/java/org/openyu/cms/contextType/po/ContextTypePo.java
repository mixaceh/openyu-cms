package org.openyu.cms.contextType.po;

import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 本文類型
 */
public interface ContextTypePo extends SeqIdAuditNamesEntity
{
	String KEY = ContextTypePo.class.getName();

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	Boolean getValid();

	void setValid(Boolean valid);

	/**
	 * 圖片寬
	 */
	Integer getImgWidth();

	void setImgWidth(Integer imgWidth);

	/**
	 * 圖片高
	 */
	Integer getImgHeight();

	void setImgHeight(Integer imgHeight);

	/**
	 * 是否有圖片
	 */
	Boolean getImage();

	void setImage(Boolean image);

}
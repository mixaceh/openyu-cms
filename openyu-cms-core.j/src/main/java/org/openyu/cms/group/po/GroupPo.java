package org.openyu.cms.group.po;

import org.openyu.commons.entity.SeqIdAuditNamesEntity;

/**
 * 群組
 */
public interface GroupPo extends SeqIdAuditNamesEntity
{
	String KEY = GroupPo.class.getName();

	/**
	 * 排列順序
	 */
	Integer getSort();

	void setSort(Integer sort);

	/**
	 * 每日允許上傳,單位(KB)
	 */
	Integer getDayUpload();

	void setDayUpload(Integer dayUpload);

	/**
	 * 每個文件最大,單位(KB)
	 */
	Integer getMaxUpload();

	void setMaxUpload(Integer maxUpload);

	/**
	 * 允許上傳的尾碼
	 */
	String getUploadSuffix();

	void setUploadSuffix(String uploadSuffix);

	/**
	 * 是否評論驗證碼
	 */
	Boolean getCaptcha();

	void setCaptcha(Boolean captcha);

	/**
	 * 是否評論審核
	 */
	Boolean getCheck();

	void setCheck(Boolean check);

	/**
	 * 是否預設
	 */
	Boolean getDft();

	void setDft(Boolean dft);

}
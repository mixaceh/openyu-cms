package org.openyu.cms.group.vo;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openyu.commons.bean.SeqIdAuditNamesBean;
import com.sun.xml.bind.AnyTypeAdapter;

/**
 * 群組
 */
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Group extends SeqIdAuditNamesBean
{
	String KEY = Group.class.getName();

	/**
	 * 排序
	 */
	int getSort();

	void setSort(int sort);

	/**
	 * 每日允許上傳,單位(KB)
	 */
	int getDayUpload();

	void setDayUpload(int dayUpload);

	/**
	 * 每個文件最大,單位(KB)
	 */
	int getMaxUpload();

	void setMaxUpload(int maxUpload);

	/**
	 * 允許上傳的尾碼
	 */
	String getUploadSuffix();

	void setUploadSuffix(String uploadSuffix);

	/**
	 * 是否評論驗證碼
	 */
	boolean getCaptcha();

	void setCaptcha(boolean captcha);

	/**
	 * 是否評論審核
	 */
	boolean getCheck();

	void setCheck(boolean check);

	/**
	 * 是否預設
	 */
	boolean getDft();

	void setDft(boolean dft);

}
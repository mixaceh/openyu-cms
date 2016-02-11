package org.openyu.cms.group.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.group.vo.Group;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupImpl extends SeqIdAuditNamesBeanSupporter implements Group
{
	private static final long serialVersionUID = -6288922327735854968L;

	/**
	 * 排列順序
	 */
	private int sort;

	/**
	 * 每日允許上傳,單位(KB)
	 */
	private int dayUpload;

	/**
	 * 每個文件最大,單位(KB)
	 */
	private int maxUpload;

	/**
	 * 允許上傳的尾碼
	 */
	private String uploadSuffix;

	/**
	 * 是否評論驗證碼
	 */
	private boolean captcha;

	/**
	 * 是否評論審核
	 */
	private boolean check;

	/**
	 * 是否預設
	 */
	private boolean dft;

	public GroupImpl(String id)
	{
		setId(id);
	}

	public GroupImpl()
	{
		this(null);
	}

	public int getSort()
	{
		return sort;
	}

	public void setSort(int sort)
	{
		this.sort = sort;
	}

	public int getDayUpload()
	{
		return dayUpload;
	}

	public void setDayUpload(int dayUpload)
	{
		this.dayUpload = dayUpload;
	}

	public int getMaxUpload()
	{
		return maxUpload;
	}

	public void setMaxUpload(int maxUpload)
	{
		this.maxUpload = maxUpload;
	}

	public String getUploadSuffix()
	{
		return uploadSuffix;
	}

	public void setUploadSuffix(String uploadSuffix)
	{
		this.uploadSuffix = uploadSuffix;
	}

	public boolean getCaptcha()
	{
		return captcha;
	}

	public void setCaptcha(boolean captcha)
	{
		this.captcha = captcha;
	}

	public boolean getCheck()
	{
		return check;
	}

	public void setCheck(boolean check)
	{
		this.check = check;
	}

	public boolean getDft()
	{
		return dft;
	}

	public void setDft(boolean dft)
	{
		this.dft = dft;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("sort", sort);
		builder.append("dayUpload", dayUpload);
		builder.append("maxUpload", maxUpload);
		builder.append("uploadSuffix", uploadSuffix);
		builder.append("captcha", captcha);
		builder.append("check", check);
		builder.append("dft", dft);
		return builder.toString();
	}

	public Object clone()
	{
		GroupImpl copy = null;
		copy = (GroupImpl) super.clone();
		return copy;
	}

}

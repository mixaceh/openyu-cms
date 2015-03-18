package org.openyu.cms.contextType.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.contextType.vo.ContextType;
import org.openyu.commons.bean.WhetherOption;
import org.openyu.commons.bean.WhetherOption.WhetherType;
import org.openyu.commons.bean.impl.WhetherOptionImpl;
import org.openyu.commons.bean.supporter.SeqIdAuditNamesBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "contextType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContextTypeImpl extends SeqIdAuditNamesBeanSupporter implements ContextType
{
	private static final long serialVersionUID = -1882718569449367895L;

	/**
	 * 是否有效
	 */
	private boolean valid;

	/**
	 * 圖片寬度
	 */
	private int imgWidth;

	/**
	 * 圖片高度
	 */
	private int imgHeight;

	/**
	 * 是否有圖片
	 */
	private boolean image;

	/**
	 * 搜尋用,是否啟用選項
	 */
	@XmlTransient
	private WhetherOption validOption = new WhetherOptionImpl(WhetherType.ALL);

	public ContextTypeImpl(String id)
	{
		setId(id);
	}

	public ContextTypeImpl()
	{
		this(null);
	}

	public boolean getValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public int getImgWidth()
	{
		return imgWidth;
	}

	public void setImgWidth(int imgWidth)
	{
		this.imgWidth = imgWidth;
	}

	public int getImgHeight()
	{
		return imgHeight;
	}

	public void setImgHeight(int imgHeight)
	{
		this.imgHeight = imgHeight;
	}

	public boolean getImage()
	{
		return image;
	}

	public void setImage(boolean image)
	{
		this.image = image;
	}

	public WhetherOption getValidOption()
	{
		return validOption;
	}

	public void setValidOption(WhetherOption validOption)
	{
		this.validOption = validOption;
	}


	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("valid", valid);
		builder.append("imgWidth", imgWidth);
		builder.append("imgHeight", imgHeight);
		builder.append("image", image);
		builder.append("validOption", validOption);
		return builder.toString();
	}

	public Object clone()
	{
		ContextTypeImpl copy = null;
		copy = (ContextTypeImpl) super.clone();
		//
		copy.validOption = clone(validOption);
		return copy;
	}

}

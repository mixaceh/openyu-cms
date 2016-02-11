package org.openyu.cms.keyword.vo.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.commons.bean.supporter.BaseBeanSupporter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "dictionary")
@XmlAccessorType(XmlAccessType.FIELD)
public class DictionaryImpl extends BaseBeanSupporter implements Dictionary
{

	private static final long serialVersionUID = -882845349380987311L;

	/**
	 * 關鍵字
	 */
	private String key;

	/**
	 * 替換字
	 */
	private String value;

	/**
	 * 啟用
	 */
	private boolean valid;

	public DictionaryImpl()
	{}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public boolean getValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public boolean equals(Object object)
	{
		if (!(object instanceof DictionaryImpl))
		{
			return false;
		}
		if (this == object)
		{
			return true;
		}
		DictionaryImpl other = (DictionaryImpl) object;
		return new EqualsBuilder().append(key, other.key).isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(key).toHashCode();
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("key", key);
		builder.append("value", value);
		builder.append("valid", valid);
		return builder.toString();
	}

	public Object clone()
	{
		DictionaryImpl copy = null;
		copy = (DictionaryImpl) super.clone();
		return copy;
	}
}

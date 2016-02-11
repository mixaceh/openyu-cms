package org.openyu.cms.sensitivity.vo.impl;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.adapter.StringDictionaryXmlAdapter;
import org.openyu.cms.sensitivity.vo.Sensitivity;
import org.openyu.commons.bean.supporter.SeqIdAuditBeanSupporter;
import org.openyu.commons.jaxb.adapter.LocaleXmlAdapter;

//--------------------------------------------------
//jaxb
//--------------------------------------------------
@XmlRootElement(name = "sensitivity")
@XmlAccessorType(XmlAccessType.FIELD)
public class SensitivityImpl extends SeqIdAuditBeanSupporter implements Sensitivity
{

	private static final long serialVersionUID = 3738143226405975330L;

	@XmlJavaTypeAdapter(StringDictionaryXmlAdapter.class)
	private Map<String, Dictionary> dictionarys = new LinkedHashMap<String, Dictionary>();

	@XmlJavaTypeAdapter(LocaleXmlAdapter.class)
	private Locale locale;

	public SensitivityImpl(String id)
	{
		setId(id);
	}

	public SensitivityImpl()
	{
		this(null);
	}

	public Map<String, Dictionary> getDictionarys()
	{
		return dictionarys;
	}

	public void setDictionarys(Map<String, Dictionary> dictionarys)
	{
		this.dictionarys = dictionarys;
	}

	public Locale getLocale()
	{
		return locale;
	}

	public void setLocale(Locale locale)
	{
		this.locale = locale;
	}

	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("dictionarys", dictionarys);
		builder.append("locale", locale);
		return builder.toString();
	}

	public Object clone()
	{
		SensitivityImpl copy = null;
		copy = (SensitivityImpl) super.clone();
		return copy;
	}
}

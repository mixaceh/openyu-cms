package org.openyu.cms.site.vo.adapter;

import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlValue;

import org.openyu.cms.site.vo.Site.VerifyType;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<verifyTypes key="_0">0</verifyTypes>
//<verifyTypes key="_1">1</verifyTypes>
//<verifyTypes key="_2">2</verifyTypes>
//--------------------------------------------------
public class VerifyTypeXmlAdapter extends
		BaseXmlAdapterSupporter<VerifyTypeXmlAdapter.VerifyTypeEntry, VerifyType>
{

	public VerifyTypeXmlAdapter()
	{}

	// --------------------------------------------------
	public static class VerifyTypeEntry
	{
		@XmlAttribute
		public String key;

		@XmlValue
		public int value;

		public VerifyTypeEntry(String key, int value)
		{
			this.key = key;
			this.value = value;
		}

		public VerifyTypeEntry()
		{}
	}

	// --------------------------------------------------
	public VerifyType unmarshal(VerifyTypeEntry value) throws Exception
	{
		VerifyType result = null;
		//
		if (value != null)
		{
			result = VerifyType.valueOf(value.key);
		}
		return result;
	}

	public VerifyTypeEntry marshal(VerifyType value) throws Exception
	{
		VerifyTypeEntry result = null;
		//
		if (value != null)
		{
			result = new VerifyTypeEntry(value.name(), value.getValue());
		}
		return result;
	}
}

package org.openyu.cms.ad.vo.adapter;

import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlValue;

import org.openyu.cms.ad.vo.Ad.AdType;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<adTypes key="PICTURE">1</adTypes>
//<adTypes key="FLASH">2</adTypes>
//<adTypes key="TEXT">3</adTypes>
//<adTypes key="CODE">4</adTypes>
//--------------------------------------------------
public class AdTypeXmlAdapter extends
		BaseXmlAdapterSupporter<AdTypeXmlAdapter.AdTypeEntry, AdType>
{

	public AdTypeXmlAdapter()
	{}

	// --------------------------------------------------
	public static class AdTypeEntry
	{
		@XmlAttribute
		public String key;

		@XmlValue
		public int value;

		public AdTypeEntry(String key, int value)
		{
			this.key = key;
			this.value = value;
		}

		public AdTypeEntry()
		{}
	}

	// --------------------------------------------------
	public AdType unmarshal(AdTypeEntry value) throws Exception
	{
		AdType result = null;
		//
		if (value != null)
		{
			result = AdType.valueOf(value.key);
		}
		return result;
	}

	public AdTypeEntry marshal(AdType value) throws Exception
	{
		AdTypeEntry result = null;
		//
		if (value != null)
		{
			result = new AdTypeEntry(value.name(), value.getValue());
		}
		return result;
	}
}

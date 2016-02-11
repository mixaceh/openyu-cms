package org.openyu.cms.site.vo.adapter;

import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlValue;

import org.openyu.cms.site.vo.Site.ModifyType;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<modifyTypes key="CAN_NOT_MODIFY">1</modifyTypes>
//<modifyTypes key="WITHDRAW">2</modifyTypes>
//<modifyTypes key="UNCHANGING">3</modifyTypes>
//--------------------------------------------------
public class ModifyTypeXmlAdapter extends
		BaseXmlAdapterSupporter<ModifyTypeXmlAdapter.ModifyTypeEntry, ModifyType>
{

	public ModifyTypeXmlAdapter()
	{}

	// --------------------------------------------------
	public static class ModifyTypeEntry
	{
		@XmlAttribute
		public String key;

		@XmlValue
		public int value;

		public ModifyTypeEntry(String key, int value)
		{
			this.key = key;
			this.value = value;
		}

		public ModifyTypeEntry()
		{}
	}

	// --------------------------------------------------
	public ModifyType unmarshal(ModifyTypeEntry value) throws Exception
	{
		ModifyType result = null;
		//
		if (value != null)
		{
			result = ModifyType.valueOf(value.key);
		}
		return result;
	}

	public ModifyTypeEntry marshal(ModifyType value) throws Exception
	{
		ModifyTypeEntry result = null;
		//
		if (value != null)
		{
			result = new ModifyTypeEntry(value.name(), value.getValue());
		}
		return result;
	}
}

package org.openyu.cms.archive.vo.adapter;

import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlValue;

import org.openyu.cms.archive.vo.ActionType;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------

//--------------------------------------------------
public class ActionTypeXmlAdapter extends
		BaseXmlAdapterSupporter<ActionTypeXmlAdapter.ActionTypeEntry, ActionType>
{

	public ActionTypeXmlAdapter()
	{}

	// --------------------------------------------------
	public static class ActionTypeEntry
	{
		@XmlAttribute
		public String key;

		@XmlValue
		public int value;

		public ActionTypeEntry(String key, int value)
		{
			this.key = key;
			this.value = value;
		}

		public ActionTypeEntry()
		{}
	}

	// --------------------------------------------------
	public ActionType unmarshal(ActionTypeEntry value) throws Exception
	{
		ActionType result = null;
		//
		if (value != null)
		{
			result = ActionType.valueOf(value.key);
		}
		return result;
	}

	public ActionTypeEntry marshal(ActionType value) throws Exception
	{
		ActionTypeEntry result = null;
		//
		if (value != null)
		{
			result = new ActionTypeEntry(value.name(), value.getValue());
		}
		return result;
	}
}

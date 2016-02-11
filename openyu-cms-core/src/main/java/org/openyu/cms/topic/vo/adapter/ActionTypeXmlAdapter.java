package org.openyu.cms.topic.vo.adapter;

import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlValue;

import org.openyu.cms.topic.vo.ActionType;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<actionTypes key="UNKNOWN">-1</actionTypes>
//<actionTypes key="INSERT">1</actionTypes>
//<actionTypes key="UPDATE">2</actionTypes>
//<actionTypes key="DELETE">3</actionTypes>
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

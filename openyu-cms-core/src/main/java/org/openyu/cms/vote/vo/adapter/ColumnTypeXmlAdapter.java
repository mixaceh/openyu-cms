package org.openyu.cms.vote.vo.adapter;

import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlValue;

import org.openyu.cms.vote.vo.VoteItem.ColumnType;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<columnTypes key="STRING">1</columnTypes>
//--------------------------------------------------
public class ColumnTypeXmlAdapter extends
		BaseXmlAdapterSupporter<ColumnTypeXmlAdapter.ColumnTypeEntry, ColumnType>
{

	public ColumnTypeXmlAdapter()
	{}

	// --------------------------------------------------
	public static class ColumnTypeEntry
	{
		@XmlAttribute
		public String key;

		@XmlValue
		public int value;

		public ColumnTypeEntry(String key, int value)
		{
			this.key = key;
			this.value = value;
		}

		public ColumnTypeEntry()
		{}
	}

	// --------------------------------------------------
	public ColumnType unmarshal(ColumnTypeEntry value) throws Exception
	{
		ColumnType result = null;
		//
		if (value != null)
		{
			result = ColumnType.valueOf(value.key);
		}
		return result;
	}

	public ColumnTypeEntry marshal(ColumnType value) throws Exception
	{
		ColumnTypeEntry result = null;
		//
		if (value != null)
		{
			result = new ColumnTypeEntry(value.name(), value.getValue());
		}
		return result;
	}
}

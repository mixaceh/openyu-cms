package org.openyu.cms.guestbookType.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<guestbookTypes>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <names/>
//        <valid>true</valid>
//        <url>http://</url>
//        <descriptions/>
//        <click>0</click>
//        <sort>10</sort>
//    </value>
//</entry>
//</guestbookTypes>
public class StringGuestbookTypeXmlAdapter extends
		BaseXmlAdapterSupporter<StringGuestbookTypeXmlAdapter.StringGuestbookTypeList, Map<String, GuestbookType>>
{

	public StringGuestbookTypeXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringGuestbookTypeList
	{
		public List<StringGuestbookTypeEntry> entry = new LinkedList<StringGuestbookTypeEntry>();
	}

	public static class StringGuestbookTypeEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = GuestbookTypeImpl.class)
		public GuestbookType value;

		public StringGuestbookTypeEntry(String key, GuestbookType value)
		{
			this.key = key;
			this.value = value;
		}

		public StringGuestbookTypeEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, GuestbookType> unmarshal(StringGuestbookTypeList value) throws Exception
	{
		Map<String, GuestbookType> result = new LinkedHashMap<String, GuestbookType>();
		if (value != null)
		{
			for (StringGuestbookTypeEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringGuestbookTypeList marshal(Map<String, GuestbookType> value) throws Exception
	{
		StringGuestbookTypeList result = new StringGuestbookTypeList();
		//
		if (value != null)
		{
			for (Map.Entry<String, GuestbookType> entry : value.entrySet())
			{
				result.entry.add(new StringGuestbookTypeEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

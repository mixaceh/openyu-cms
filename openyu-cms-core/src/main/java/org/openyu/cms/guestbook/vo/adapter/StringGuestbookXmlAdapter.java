package org.openyu.cms.guestbook.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbook.vo.impl.GuestbookImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<guestbooks>
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
//</guestbooks>
public class StringGuestbookXmlAdapter extends
		BaseXmlAdapterSupporter<StringGuestbookXmlAdapter.StringGuestbookList, Map<String, Guestbook>>
{

	public StringGuestbookXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringGuestbookList
	{
		public List<StringGuestbookEntry> entry = new LinkedList<StringGuestbookEntry>();
	}

	public static class StringGuestbookEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = GuestbookImpl.class)
		public Guestbook value;

		public StringGuestbookEntry(String key, Guestbook value)
		{
			this.key = key;
			this.value = value;
		}

		public StringGuestbookEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Guestbook> unmarshal(StringGuestbookList value) throws Exception
	{
		Map<String, Guestbook> result = new LinkedHashMap<String, Guestbook>();
		if (value != null)
		{
			for (StringGuestbookEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringGuestbookList marshal(Map<String, Guestbook> value) throws Exception
	{
		StringGuestbookList result = new StringGuestbookList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Guestbook> entry : value.entrySet())
			{
				result.entry.add(new StringGuestbookEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

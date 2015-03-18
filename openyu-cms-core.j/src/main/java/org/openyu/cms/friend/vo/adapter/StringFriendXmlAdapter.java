package org.openyu.cms.friend.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friend.vo.impl.FriendImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<friends>
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
//</friends>
public class StringFriendXmlAdapter extends
		BaseXmlAdapterSupporter<StringFriendXmlAdapter.StringFriendList, Map<String, Friend>>
{

	public StringFriendXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringFriendList
	{
		public List<StringFriendEntry> entry = new LinkedList<StringFriendEntry>();
	}

	public static class StringFriendEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = FriendImpl.class)
		public Friend value;

		public StringFriendEntry(String key, Friend value)
		{
			this.key = key;
			this.value = value;
		}

		public StringFriendEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Friend> unmarshal(StringFriendList value) throws Exception
	{
		Map<String, Friend> result = new LinkedHashMap<String, Friend>();
		if (value != null)
		{
			for (StringFriendEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringFriendList marshal(Map<String, Friend> value) throws Exception
	{
		StringFriendList result = new StringFriendList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Friend> entry : value.entrySet())
			{
				result.entry.add(new StringFriendEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

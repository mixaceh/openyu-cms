package org.openyu.cms.friendType.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<friendTypes>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <names/>
//        <sort>10</sort>
//    </value>
//</entry>
//</friendTypes>
public class StringFriendTypeXmlAdapter
		extends
		BaseXmlAdapterSupporter<StringFriendTypeXmlAdapter.StringFriendTypeList, Map<String, FriendType>>
{

	public StringFriendTypeXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringFriendTypeList
	{
		public List<StringFriendTypeEntry> entry = new LinkedList<StringFriendTypeEntry>();
	}

	public static class StringFriendTypeEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = FriendTypeImpl.class)
		public FriendType value;

		public StringFriendTypeEntry(String key, FriendType value)
		{
			this.key = key;
			this.value = value;
		}

		public StringFriendTypeEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, FriendType> unmarshal(StringFriendTypeList value) throws Exception
	{
		Map<String, FriendType> result = new LinkedHashMap<String, FriendType>();
		if (value != null)
		{
			for (StringFriendTypeEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringFriendTypeList marshal(Map<String, FriendType> value) throws Exception
	{
		StringFriendTypeList result = new StringFriendTypeList();
		//
		if (value != null)
		{
			for (Map.Entry<String, FriendType> entry : value.entrySet())
			{
				result.entry.add(new StringFriendTypeEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

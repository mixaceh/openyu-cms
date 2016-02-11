package org.openyu.cms.tag.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.tag.vo.impl.TagImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<tags>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <names/>
//        <sort>10</sort>
//    </value>
//</entry>
//</tags>
public class StringTagXmlAdapter
		extends
		BaseXmlAdapterSupporter<StringTagXmlAdapter.StringTagList, Map<String, Tag>>
{

	public StringTagXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringTagList
	{
		public List<StringTagEntry> entry = new LinkedList<StringTagEntry>();
	}

	public static class StringTagEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = TagImpl.class)
		public Tag value;

		public StringTagEntry(String key, Tag value)
		{
			this.key = key;
			this.value = value;
		}

		public StringTagEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Tag> unmarshal(StringTagList value) throws Exception
	{
		Map<String, Tag> result = new LinkedHashMap<String, Tag>();
		if (value != null)
		{
			for (StringTagEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringTagList marshal(Map<String, Tag> value) throws Exception
	{
		StringTagList result = new StringTagList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Tag> entry : value.entrySet())
			{
				result.entry.add(new StringTagEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

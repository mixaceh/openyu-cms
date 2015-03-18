package org.openyu.cms.keyword.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.keyword.vo.Dictionary;
import org.openyu.cms.keyword.vo.impl.DictionaryImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<dictionarys>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <valid>true</valid>
//    </value>
//</entry>
//</dictionarys>
public class StringDictionaryXmlAdapter
		extends
		BaseXmlAdapterSupporter<StringDictionaryXmlAdapter.StringDictionaryList, Map<String, Dictionary>>
{

	public StringDictionaryXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringDictionaryList
	{
		public List<StringDictionaryEntry> entry = new LinkedList<StringDictionaryEntry>();
	}

	public static class StringDictionaryEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = DictionaryImpl.class)
		public Dictionary value;

		public StringDictionaryEntry(String key, Dictionary value)
		{
			this.key = key;
			this.value = value;
		}

		public StringDictionaryEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Dictionary> unmarshal(StringDictionaryList value) throws Exception
	{
		Map<String, Dictionary> result = new LinkedHashMap<String, Dictionary>();
		if (value != null)
		{
			for (StringDictionaryEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringDictionaryList marshal(Map<String, Dictionary> value) throws Exception
	{
		StringDictionaryList result = new StringDictionaryList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Dictionary> entry : value.entrySet())
			{
				result.entry.add(new StringDictionaryEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

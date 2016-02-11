package org.openyu.cms.keyword.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.keyword.vo.Keyword;
import org.openyu.cms.keyword.vo.impl.KeywordImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<keywords>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//    </value>
//</entry>
//</keywords>
public class StringKeywordXmlAdapter extends
		BaseXmlAdapterSupporter<StringKeywordXmlAdapter.StringKeywordList, Map<String, Keyword>>
{

	public StringKeywordXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringKeywordList
	{
		public List<StringKeywordEntry> entry = new LinkedList<StringKeywordEntry>();
	}

	public static class StringKeywordEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = KeywordImpl.class)
		public Keyword value;

		public StringKeywordEntry(String key, Keyword value)
		{
			this.key = key;
			this.value = value;
		}

		public StringKeywordEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Keyword> unmarshal(StringKeywordList value) throws Exception
	{
		Map<String, Keyword> result = new LinkedHashMap<String, Keyword>();
		if (value != null)
		{
			for (StringKeywordEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringKeywordList marshal(Map<String, Keyword> value) throws Exception
	{
		StringKeywordList result = new StringKeywordList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Keyword> entry : value.entrySet())
			{
				result.entry.add(new StringKeywordEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

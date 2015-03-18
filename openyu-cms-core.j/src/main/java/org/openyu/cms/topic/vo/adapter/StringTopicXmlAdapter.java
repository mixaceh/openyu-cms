package org.openyu.cms.topic.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.topic.vo.Topic;
import org.openyu.cms.topic.vo.impl.TopicImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<topics>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <names/>
//        <valid>true</valid>
//        <url>http://</url>
//        <target>_blank</target>
//        <click>0</click>
//        <display>0</display>
//        <weight>1</weight>
//    </value>
//</entry>
//</topics>
public class StringTopicXmlAdapter extends
		BaseXmlAdapterSupporter<StringTopicXmlAdapter.StringTopicList, Map<String, Topic>>
{

	public StringTopicXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringTopicList
	{
		public List<StringTopicEntry> entry = new LinkedList<StringTopicEntry>();
	}

	public static class StringTopicEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = TopicImpl.class)
		public Topic value;

		public StringTopicEntry(String key, Topic value)
		{
			this.key = key;
			this.value = value;
		}

		public StringTopicEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Topic> unmarshal(StringTopicList value) throws Exception
	{
		Map<String, Topic> result = new LinkedHashMap<String, Topic>();
		if (value != null)
		{
			for (StringTopicEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringTopicList marshal(Map<String, Topic> value) throws Exception
	{
		StringTopicList result = new StringTopicList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Topic> entry : value.entrySet())
			{
				result.entry.add(new StringTopicEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

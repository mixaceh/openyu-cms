package org.openyu.cms.group.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.group.vo.Group;
import org.openyu.cms.group.vo.impl.GroupImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<groups>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <names/>
//        <sort>10</sort>
//        <dayUpload>0</dayUpload>
//        <maxUpload>0</maxUpload>
//        <captcha>true</captcha>
//        <check>true</check>
//        <dft>false</dft>
//    </value>
//</entry>
//</groups>
public class StringGroupXmlAdapter extends
		BaseXmlAdapterSupporter<StringGroupXmlAdapter.StringGroupList, Map<String, Group>>
{

	public StringGroupXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringGroupList
	{
		public List<StringGroupEntry> entry = new LinkedList<StringGroupEntry>();
	}

	public static class StringGroupEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = GroupImpl.class)
		public Group value;

		public StringGroupEntry(String key, Group value)
		{
			this.key = key;
			this.value = value;
		}

		public StringGroupEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Group> unmarshal(StringGroupList value) throws Exception
	{
		Map<String, Group> result = new LinkedHashMap<String, Group>();
		if (value != null)
		{
			for (StringGroupEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringGroupList marshal(Map<String, Group> value) throws Exception
	{
		StringGroupList result = new StringGroupList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Group> entry : value.entrySet())
			{
				result.entry.add(new StringGroupEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

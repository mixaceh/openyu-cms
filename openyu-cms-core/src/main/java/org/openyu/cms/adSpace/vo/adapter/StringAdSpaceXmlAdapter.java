package org.openyu.cms.adSpace.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.vo.impl.AdSpaceImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<adSpaces>
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
//</adSpaces>
public class StringAdSpaceXmlAdapter extends
		BaseXmlAdapterSupporter<StringAdSpaceXmlAdapter.StringAdSpaceList, Map<String, AdSpace>>
{

	public StringAdSpaceXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringAdSpaceList
	{
		public List<StringAdSpaceEntry> entry = new LinkedList<StringAdSpaceEntry>();
	}

	public static class StringAdSpaceEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = AdSpaceImpl.class)
		public AdSpace value;

		public StringAdSpaceEntry(String key, AdSpace value)
		{
			this.key = key;
			this.value = value;
		}

		public StringAdSpaceEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, AdSpace> unmarshal(StringAdSpaceList value) throws Exception
	{
		Map<String, AdSpace> result = new LinkedHashMap<String, AdSpace>();
		if (value != null)
		{
			for (StringAdSpaceEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringAdSpaceList marshal(Map<String, AdSpace> value) throws Exception
	{
		StringAdSpaceList result = new StringAdSpaceList();
		//
		if (value != null)
		{
			for (Map.Entry<String, AdSpace> entry : value.entrySet())
			{
				result.entry.add(new StringAdSpaceEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

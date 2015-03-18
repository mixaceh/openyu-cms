package org.openyu.cms.ad.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.ad.vo.impl.AdImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<ads>
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
//</ads>
public class StringAdXmlAdapter extends
		BaseXmlAdapterSupporter<StringAdXmlAdapter.StringAdList, Map<String, Ad>>
{

	public StringAdXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringAdList
	{
		public List<StringAdEntry> entry = new LinkedList<StringAdEntry>();
	}

	public static class StringAdEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = AdImpl.class)
		public Ad value;

		public StringAdEntry(String key, Ad value)
		{
			this.key = key;
			this.value = value;
		}

		public StringAdEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Ad> unmarshal(StringAdList value) throws Exception
	{
		Map<String, Ad> result = new LinkedHashMap<String, Ad>();
		if (value != null)
		{
			for (StringAdEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringAdList marshal(Map<String, Ad> value) throws Exception
	{
		StringAdList result = new StringAdList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Ad> entry : value.entrySet())
			{
				result.entry.add(new StringAdEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

package org.openyu.cms.contextType.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.contextType.vo.ContextType;
import org.openyu.cms.contextType.vo.impl.ContextTypeImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<contextTypes>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <names/>
//        <valid>true</valid>
//        <imgWidth>140</imgWidth>
//        <imgHeight>140</imgHeight>
//        <image>false</image>
//    </value>
//</entry>
//</contextTypes>
public class StringContextTypeXmlAdapter
		extends
		BaseXmlAdapterSupporter<StringContextTypeXmlAdapter.StringContextTypeList, Map<String, ContextType>>
{

	public StringContextTypeXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringContextTypeList
	{
		public List<StringContextTypeEntry> entry = new LinkedList<StringContextTypeEntry>();
	}

	public static class StringContextTypeEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = ContextTypeImpl.class)
		public ContextType value;

		public StringContextTypeEntry(String key, ContextType value)
		{
			this.key = key;
			this.value = value;
		}

		public StringContextTypeEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, ContextType> unmarshal(StringContextTypeList value) throws Exception
	{
		Map<String, ContextType> result = new LinkedHashMap<String, ContextType>();
		if (value != null)
		{
			for (StringContextTypeEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringContextTypeList marshal(Map<String, ContextType> value) throws Exception
	{
		StringContextTypeList result = new StringContextTypeList();
		//
		if (value != null)
		{
			for (Map.Entry<String, ContextType> entry : value.entrySet())
			{
				result.entry.add(new StringContextTypeEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

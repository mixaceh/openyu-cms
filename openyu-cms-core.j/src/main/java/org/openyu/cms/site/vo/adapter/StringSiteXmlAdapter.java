package org.openyu.cms.site.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<sites>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <names/>
//        <shortNames/>
//        <protocol>http://</protocol>
//        <dynamicSuffix>.yhtml</dynamicSuffix>
//        <staticSuffix>.html</staticSuffix>
//        <indexRoot>false</indexRoot>
//        <staticIndex>false</staticIndex>
//        <verifyType>_2</verifyType>
//        <modifyType>UNCHANGING</modifyType>
//        <relativePath>false</relativePath>
//        <recover>true</recover>
//        <attributes/>
//        <texts/>
//        <configs/>
//    </value>
//</entry>
//</sites>
public class StringSiteXmlAdapter extends
		BaseXmlAdapterSupporter<StringSiteXmlAdapter.StringSiteList, Map<String, Site>>
{

	public StringSiteXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringSiteList
	{
		public List<StringSiteEntry> entry = new LinkedList<StringSiteEntry>();
	}

	public static class StringSiteEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = SiteImpl.class)
		public Site value;

		public StringSiteEntry(String key, Site value)
		{
			this.key = key;
			this.value = value;
		}

		public StringSiteEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Site> unmarshal(StringSiteList value) throws Exception
	{
		Map<String, Site> result = new LinkedHashMap<String, Site>();
		if (value != null)
		{
			for (StringSiteEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringSiteList marshal(Map<String, Site> value) throws Exception
	{
		StringSiteList result = new StringSiteList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Site> entry : value.entrySet())
			{
				result.entry.add(new StringSiteEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

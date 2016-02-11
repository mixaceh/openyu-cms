package org.openyu.cms.config.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.config.vo.Config;
import org.openyu.cms.config.vo.impl.ConfigImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<configs>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <sysDeployeePath></sysDeployeePath>
//        <sysPort>80</sysPort>
//        <sysDefaultImg>/csm/back/theme/no_picture.gif</sysDefaultImg>
//        <sysEmailValidate>false</sysEmailValidate>
//        <sysDbFileUri>/dbfile.svl?n=</sysDbFileUri>
//        <sysUploadToDb>false</sysUploadToDb>
//        <downloadTime>0</downloadTime>
//</entry>
//</configs>        
public class StringConfigXmlAdapter extends
		BaseXmlAdapterSupporter<StringConfigXmlAdapter.StringConfigList, Map<String, Config>>
{

	public StringConfigXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringConfigList
	{
		public List<StringConfigEntry> entry = new LinkedList<StringConfigEntry>();
	}

	public static class StringConfigEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = ConfigImpl.class)
		public Config value;

		public StringConfigEntry(String key, Config value)
		{
			this.key = key;
			this.value = value;
		}

		public StringConfigEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Config> unmarshal(StringConfigList value) throws Exception
	{
		Map<String, Config> result = new LinkedHashMap<String, Config>();
		if (value != null)
		{
			for (StringConfigEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringConfigList marshal(Map<String, Config> value) throws Exception
	{
		StringConfigList result = new StringConfigList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Config> entry : value.entrySet())
			{
				result.entry.add(new StringConfigEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

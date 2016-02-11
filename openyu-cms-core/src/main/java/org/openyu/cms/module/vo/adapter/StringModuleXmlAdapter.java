package org.openyu.cms.module.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.module.vo.Module;
import org.openyu.cms.module.vo.impl.ModuleImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<modules>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <names/>
//        <valid>true</valid>
//        <catalogImgWidth>150</catalogImgWidth>
//        <catalogImgHeight>150</catalogImgHeight>
//        <contextImgWidth>300</contextImgWidth>
//        <contextImgHeight>300</contextImgHeight>
//        <sort>10</sort>
//        <context>true</context>
//        <dft>false</dft>
//        <catalogItems/>
//        </contextItems>
//    </value>
//</entry>
//</modules>        
public class StringModuleXmlAdapter extends
		BaseXmlAdapterSupporter<StringModuleXmlAdapter.StringModuleList, Map<String, Module>>
{

	public StringModuleXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringModuleList
	{
		public List<StringModuleEntry> entry = new LinkedList<StringModuleEntry>();
	}

	public static class StringModuleEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = ModuleImpl.class)
		public Module value;

		public StringModuleEntry(String key, Module value)
		{
			this.key = key;
			this.value = value;
		}

		public StringModuleEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Module> unmarshal(StringModuleList value) throws Exception
	{
		Map<String, Module> result = new LinkedHashMap<String, Module>();
		if (value != null)
		{
			for (StringModuleEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringModuleList marshal(Map<String, Module> value) throws Exception
	{
		StringModuleList result = new StringModuleList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Module> entry : value.entrySet())
			{
				result.entry.add(new StringModuleEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

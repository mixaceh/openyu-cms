package org.openyu.cms.sensitivity.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.sensitivity.vo.Sensitivity;
import org.openyu.cms.sensitivity.vo.impl.SensitivityImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<sensitivitys>
//	<entry key="DEFAULT">
//	    <value>
//	        <id>DEFAULT</id>
//	        <dictionarys/>
//	        <locale>zh_TW</locale>
//	    </value>
//	</entry>
//</sensitivitys>
public class StringSensitivityXmlAdapter
		extends
		BaseXmlAdapterSupporter<StringSensitivityXmlAdapter.StringSensitivityList, Map<String, Sensitivity>>
{

	public StringSensitivityXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringSensitivityList
	{
		public List<StringSensitivityEntry> entry = new LinkedList<StringSensitivityEntry>();
	}

	public static class StringSensitivityEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = SensitivityImpl.class)
		public Sensitivity value;

		public StringSensitivityEntry(String key, Sensitivity value)
		{
			this.key = key;
			this.value = value;
		}

		public StringSensitivityEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Sensitivity> unmarshal(StringSensitivityList value) throws Exception
	{
		Map<String, Sensitivity> result = new LinkedHashMap<String, Sensitivity>();
		if (value != null)
		{
			for (StringSensitivityEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringSensitivityList marshal(Map<String, Sensitivity> value) throws Exception
	{
		StringSensitivityList result = new StringSensitivityList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Sensitivity> entry : value.entrySet())
			{
				result.entry.add(new StringSensitivityEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

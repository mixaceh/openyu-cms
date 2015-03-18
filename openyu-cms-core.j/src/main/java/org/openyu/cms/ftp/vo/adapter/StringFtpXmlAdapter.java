package org.openyu.cms.ftp.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.cms.ftp.vo.impl.FtpImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<ftps>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <names/>
//        <port>21</port>
//        <timeout>0</timeout>
//    </value>
//</entry>
//</ftps>
public class StringFtpXmlAdapter extends
		BaseXmlAdapterSupporter<StringFtpXmlAdapter.StringFtpList, Map<String, Ftp>>
{

	public StringFtpXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringFtpList
	{
		public List<StringFtpEntry> entry = new LinkedList<StringFtpEntry>();
	}

	public static class StringFtpEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = FtpImpl.class)
		public Ftp value;

		public StringFtpEntry(String key, Ftp value)
		{
			this.key = key;
			this.value = value;
		}

		public StringFtpEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Ftp> unmarshal(StringFtpList value) throws Exception
	{
		Map<String, Ftp> result = new LinkedHashMap<String, Ftp>();
		if (value != null)
		{
			for (StringFtpEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringFtpList marshal(Map<String, Ftp> value) throws Exception
	{
		StringFtpList result = new StringFtpList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Ftp> entry : value.entrySet())
			{
				result.entry.add(new StringFtpEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

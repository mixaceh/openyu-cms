package org.openyu.cms.vote.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.vote.vo.Vote;
import org.openyu.cms.vote.vo.impl.VoteImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<votes>
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
//        <defaultz>false</defaultz>
//        <catalogItems/>
//        </contextItems>
//    </value>
//</entry>
//</votes>        
public class StringVoteXmlAdapter extends
		BaseXmlAdapterSupporter<StringVoteXmlAdapter.StringVoteList, Map<String, Vote>>
{

	public StringVoteXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringVoteList
	{
		public List<StringVoteEntry> entry = new LinkedList<StringVoteEntry>();
	}

	public static class StringVoteEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = VoteImpl.class)
		public Vote value;

		public StringVoteEntry(String key, Vote value)
		{
			this.key = key;
			this.value = value;
		}

		public StringVoteEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, Vote> unmarshal(StringVoteList value) throws Exception
	{
		Map<String, Vote> result = new LinkedHashMap<String, Vote>();
		if (value != null)
		{
			for (StringVoteEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringVoteList marshal(Map<String, Vote> value) throws Exception
	{
		StringVoteList result = new StringVoteList();
		//
		if (value != null)
		{
			for (Map.Entry<String, Vote> entry : value.entrySet())
			{
				result.entry.add(new StringVoteEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

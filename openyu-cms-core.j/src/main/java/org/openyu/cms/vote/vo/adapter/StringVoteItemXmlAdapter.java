package org.openyu.cms.vote.vo.adapter;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.vote.vo.VoteItem;
import org.openyu.cms.vote.vo.impl.VoteItemImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<voteItems>
//<entry key="DEFAULT">
//    <value>
//        <id>DEFAULT</id>
//        <valid>true</valid>
//    </value>
//</entry>
//</voteItems>
public class StringVoteItemXmlAdapter
		extends
		BaseXmlAdapterSupporter<StringVoteItemXmlAdapter.StringVoteItemList, Map<String, VoteItem>>
{

	public StringVoteItemXmlAdapter()
	{}

	// --------------------------------------------------
	public static class StringVoteItemList
	{
		public List<StringVoteItemEntry> entry = new LinkedList<StringVoteItemEntry>();
	}

	public static class StringVoteItemEntry
	{
		@XmlAttribute
		public String key;

		@XmlElement(type = VoteItemImpl.class)
		public VoteItem value;

		public StringVoteItemEntry(String key, VoteItem value)
		{
			this.key = key;
			this.value = value;
		}

		public StringVoteItemEntry()
		{}

	}

	// --------------------------------------------------
	public Map<String, VoteItem> unmarshal(StringVoteItemList value) throws Exception
	{
		Map<String, VoteItem> result = new LinkedHashMap<String, VoteItem>();
		if (value != null)
		{
			for (StringVoteItemEntry entry : value.entry)
			{
				result.put(entry.key, entry.value);
			}
		}
		return result;
	}

	public StringVoteItemList marshal(Map<String, VoteItem> value) throws Exception
	{
		StringVoteItemList result = new StringVoteItemList();
		//
		if (value != null)
		{
			for (Map.Entry<String, VoteItem> entry : value.entrySet())
			{
				result.entry.add(new StringVoteItemEntry(entry.getKey(), entry.getValue()));
			}
		}
		return result;
	}
}

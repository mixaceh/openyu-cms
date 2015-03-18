package org.openyu.cms.friend.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.friend.vo.Friend;
import org.openyu.cms.friend.vo.impl.FriendImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<friend>
//	<seq>1</seq>
//	<id>c</id>
//</friend>
//--------------------------------------------------
public class FriendXmlAdapter extends BaseXmlAdapterSupporter<FriendXmlAdapter.FriendEntry, Friend>
{

	public FriendXmlAdapter()
	{}

	// --------------------------------------------------
	public static class FriendEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public FriendEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public FriendEntry()
		{}
	}

	// --------------------------------------------------
	public Friend unmarshal(FriendEntry value) throws Exception
	{
		Friend result = null;
		//
		if (value != null)
		{
			result = new FriendImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public FriendEntry marshal(Friend value) throws Exception
	{
		FriendEntry result = null;
		//
		if (value != null)
		{
			result = new FriendEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

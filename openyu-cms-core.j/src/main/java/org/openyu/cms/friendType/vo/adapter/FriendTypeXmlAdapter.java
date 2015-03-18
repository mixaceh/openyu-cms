package org.openyu.cms.friendType.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.friendType.vo.FriendType;
import org.openyu.cms.friendType.vo.impl.FriendTypeImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// -------------------------------------------------s-
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<friendType>
//	<seq>1</seq>
//	<id>TEST_FTP</id>
//</friendType>
//--------------------------------------------------
public class FriendTypeXmlAdapter extends
		BaseXmlAdapterSupporter<FriendTypeXmlAdapter.FriendTypeEntry, FriendType>
{

	public FriendTypeXmlAdapter()
	{}

	// --------------------------------------------------
	public static class FriendTypeEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public FriendTypeEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public FriendTypeEntry()
		{}
	}

	// --------------------------------------------------
	public FriendType unmarshal(FriendTypeEntry value) throws Exception
	{
		FriendType result = null;
		//
		if (value != null)
		{
			result = new FriendTypeImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public FriendTypeEntry marshal(FriendType value) throws Exception
	{
		FriendTypeEntry result = null;
		//
		if (value != null)
		{
			result = new FriendTypeEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

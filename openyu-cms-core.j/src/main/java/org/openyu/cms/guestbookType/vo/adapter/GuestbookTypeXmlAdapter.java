package org.openyu.cms.guestbookType.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.guestbookType.vo.GuestbookType;
import org.openyu.cms.guestbookType.vo.impl.GuestbookTypeImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<guestbookType>
//	<seq>1</seq>
//	<id>c</id>
//</guestbookType>
//--------------------------------------------------
public class GuestbookTypeXmlAdapter extends
		BaseXmlAdapterSupporter<GuestbookTypeXmlAdapter.GuestbookTypeEntry, GuestbookType>
{

	public GuestbookTypeXmlAdapter()
	{}

	// --------------------------------------------------
	public static class GuestbookTypeEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public GuestbookTypeEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public GuestbookTypeEntry()
		{}
	}

	// --------------------------------------------------
	public GuestbookType unmarshal(GuestbookTypeEntry value) throws Exception
	{
		GuestbookType result = null;
		//
		if (value != null)
		{
			result = new GuestbookTypeImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public GuestbookTypeEntry marshal(GuestbookType value) throws Exception
	{
		GuestbookTypeEntry result = null;
		//
		if (value != null)
		{
			result = new GuestbookTypeEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

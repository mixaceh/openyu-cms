package org.openyu.cms.guestbook.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.guestbook.vo.Guestbook;
import org.openyu.cms.guestbook.vo.impl.GuestbookImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<guestbook>
//	<seq>1</seq>
//	<id>c</id>
//</guestbook>
//--------------------------------------------------
public class GuestbookXmlAdapter extends BaseXmlAdapterSupporter<GuestbookXmlAdapter.GuestbookEntry, Guestbook>
{

	public GuestbookXmlAdapter()
	{}

	// --------------------------------------------------
	public static class GuestbookEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public GuestbookEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public GuestbookEntry()
		{}
	}

	// --------------------------------------------------
	public Guestbook unmarshal(GuestbookEntry value) throws Exception
	{
		Guestbook result = null;
		//
		if (value != null)
		{
			result = new GuestbookImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public GuestbookEntry marshal(Guestbook value) throws Exception
	{
		GuestbookEntry result = null;
		//
		if (value != null)
		{
			result = new GuestbookEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

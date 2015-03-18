package org.openyu.cms.ad.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.ad.vo.Ad;
import org.openyu.cms.ad.vo.impl.AdImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<ad>
//	<seq>1</seq>
//	<id>c</id>
//</ad>
//--------------------------------------------------
public class AdXmlAdapter extends BaseXmlAdapterSupporter<AdXmlAdapter.AdEntry, Ad>
{

	public AdXmlAdapter()
	{}

	// --------------------------------------------------
	public static class AdEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public AdEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public AdEntry()
		{}
	}

	// --------------------------------------------------
	public Ad unmarshal(AdEntry value) throws Exception
	{
		Ad result = null;
		//
		if (value != null)
		{
			result = new AdImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public AdEntry marshal(Ad value) throws Exception
	{
		AdEntry result = null;
		//
		if (value != null)
		{
			result = new AdEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

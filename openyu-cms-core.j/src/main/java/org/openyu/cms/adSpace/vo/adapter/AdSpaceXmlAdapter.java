package org.openyu.cms.adSpace.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.adSpace.vo.AdSpace;
import org.openyu.cms.adSpace.vo.impl.AdSpaceImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<adSpace>
//	<seq>1</seq>
//	<id>c</id>
//</adSpace>
//--------------------------------------------------
public class AdSpaceXmlAdapter extends
		BaseXmlAdapterSupporter<AdSpaceXmlAdapter.AdSpaceEntry, AdSpace>
{

	public AdSpaceXmlAdapter()
	{}

	// --------------------------------------------------
	public static class AdSpaceEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public AdSpaceEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public AdSpaceEntry()
		{}
	}

	// --------------------------------------------------
	public AdSpace unmarshal(AdSpaceEntry value) throws Exception
	{
		AdSpace result = null;
		//
		if (value != null)
		{
			result = new AdSpaceImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public AdSpaceEntry marshal(AdSpace value) throws Exception
	{
		AdSpaceEntry result = null;
		//
		if (value != null)
		{
			result = new AdSpaceEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

package org.openyu.cms.site.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.site.vo.Site;
import org.openyu.cms.site.vo.impl.SiteImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<site>
//	<seq>1</seq>
//	<id>TEST_SITE</id>
//</site>
//--------------------------------------------------
public class SiteXmlAdapter extends BaseXmlAdapterSupporter<SiteXmlAdapter.SiteEntry, Site>
{

	public SiteXmlAdapter()
	{}

	// --------------------------------------------------
	public static class SiteEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public SiteEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public SiteEntry()
		{}
	}

	// --------------------------------------------------
	public Site unmarshal(SiteEntry value) throws Exception
	{
		Site result = null;
		//
		if (value != null)
		{
			result = new SiteImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public SiteEntry marshal(Site value) throws Exception
	{
		SiteEntry result = null;
		//
		if (value != null)
		{
			result = new SiteEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

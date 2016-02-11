package org.openyu.cms.catalog.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.catalog.vo.Catalog;
import org.openyu.cms.catalog.vo.impl.CatalogImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<catalog>
//	<seq>1</seq>
//	<id>TEST_SITE</id>
//</catalog>
//--------------------------------------------------
public class CatalogXmlAdapter extends BaseXmlAdapterSupporter<CatalogXmlAdapter.CatalogEntry, Catalog>
{

	public CatalogXmlAdapter()
	{}

	// --------------------------------------------------
	public static class CatalogEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public CatalogEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public CatalogEntry()
		{}
	}

	// --------------------------------------------------
	public Catalog unmarshal(CatalogEntry value) throws Exception
	{
		Catalog result = null;
		//
		if (value != null)
		{
			result = new CatalogImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public CatalogEntry marshal(Catalog value) throws Exception
	{
		CatalogEntry result = null;
		//
		if (value != null)
		{
			result = new CatalogEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

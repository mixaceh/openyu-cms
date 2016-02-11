package org.openyu.cms.module.vo.adapter;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import org.openyu.cms.module.vo.CatalogItem;
import org.openyu.cms.module.vo.impl.CatalogItemImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

//--------------------------------------------------
//reslove: JAXB can’t handle interfaces
//--------------------------------------------------
//<catalogItems>
//	<entry key="path">
//	    <value>
//	        <id>path</id>
//	        <names/>
//	        <sort>0</sort>
//	        <single>false</single>
//	        <custom>false</custom>
//	        <display>false</display>
//	    </value>
//	</entry>
//</catalogItems>
//--------------------------------------------------
public class CatalogItemSetXmlAdapter extends
		BaseXmlAdapterSupporter<CatalogItemSetXmlAdapter.CatalogItemList, Set<CatalogItem>>
{

	public CatalogItemSetXmlAdapter()
	{}

	// --------------------------------------------------
	public static class CatalogItemList
	{
		public List<CatalogItemEntry> entry = new LinkedList<CatalogItemEntry>();
	}

	public static class CatalogItemEntry
	{
		@XmlAttribute
		public String key;//id

		@XmlElement(type = CatalogItemImpl.class)
		public CatalogItem value;

		public CatalogItemEntry(String key, CatalogItem value)
		{
			this.key = key;
			this.value = value;
		}

		public CatalogItemEntry()
		{}
	}

	// --------------------------------------------------
	public Set<CatalogItem> unmarshal(CatalogItemList value) throws Exception
	{
		Set<CatalogItem> result = new LinkedHashSet<CatalogItem>();
		//
		if (value != null)
		{
			for (CatalogItemEntry entry : value.entry)
			{
				CatalogItem catalogItem = entry.value;
				result.add(catalogItem);
			}
		}
		return result;
	}

	public CatalogItemList marshal(Set<CatalogItem> value) throws Exception
	{
		CatalogItemList result = new CatalogItemList();
		//
		if (value != null)
		{
			for (CatalogItem entry : value)
			{
				result.entry.add(new CatalogItemEntry(entry.getId(), entry));
			}
		}
		return result;
	}
}

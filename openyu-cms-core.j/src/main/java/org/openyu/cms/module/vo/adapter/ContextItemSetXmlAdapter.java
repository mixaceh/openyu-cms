package org.openyu.cms.module.vo.adapter;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import org.openyu.cms.module.vo.ContextItem;
import org.openyu.cms.module.vo.impl.ContextItemImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

//--------------------------------------------------
//reslove: JAXB can’t handle interfaces
//--------------------------------------------------
//<contextItems>
//<entry key="path">
//    <value>
//        <id>path</id>
//        <names/>
//        <sort>0</sort>
//        <single>false</single>
//        <custom>false</custom>
//        <display>false</display>
//    </value>
//</entry>
//</contextItems>
//--------------------------------------------------
public class ContextItemSetXmlAdapter extends
		BaseXmlAdapterSupporter<ContextItemSetXmlAdapter.ContextItemList, Set<ContextItem>>
{

	public ContextItemSetXmlAdapter()
	{}

	// --------------------------------------------------
	public static class ContextItemList
	{
		public List<ContextItemEntry> entry = new LinkedList<ContextItemEntry>();
	}

	public static class ContextItemEntry
	{
		@XmlAttribute
		public String key;//id

		@XmlElement(type = ContextItemImpl.class)
		public ContextItem value;

		public ContextItemEntry(String key, ContextItem value)
		{
			this.key = key;
			this.value = value;
		}

		public ContextItemEntry()
		{}
	}

	// --------------------------------------------------
	public Set<ContextItem> unmarshal(ContextItemList value) throws Exception
	{
		Set<ContextItem> result = new LinkedHashSet<ContextItem>();
		//
		if (value != null)
		{
			for (ContextItemEntry entry : value.entry)
			{
				ContextItem contextItem = entry.value;
				result.add(contextItem);
			}
		}
		return result;
	}

	public ContextItemList marshal(Set<ContextItem> value) throws Exception
	{
		ContextItemList result = new ContextItemList();
		//
		if (value != null)
		{
			for (ContextItem entry : value)
			{
				result.entry.add(new ContextItemEntry(entry.getId(), entry));
			}
		}
		return result;
	}
}

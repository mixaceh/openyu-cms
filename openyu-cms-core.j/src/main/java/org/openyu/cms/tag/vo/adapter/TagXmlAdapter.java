package org.openyu.cms.tag.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.tag.vo.Tag;
import org.openyu.cms.tag.vo.impl.TagImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// -------------------------------------------------s-
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<tag>
//	<seq>1</seq>
//	<id>TEST_TAG</id>
//</tag>
//--------------------------------------------------
public class TagXmlAdapter extends
		BaseXmlAdapterSupporter<TagXmlAdapter.TagEntry, Tag>
{

	public TagXmlAdapter()
	{}

	// --------------------------------------------------
	public static class TagEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public TagEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public TagEntry()
		{}
	}

	// --------------------------------------------------
	public Tag unmarshal(TagEntry value) throws Exception
	{
		Tag result = null;
		//
		if (value != null)
		{
			result = new TagImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public TagEntry marshal(Tag value) throws Exception
	{
		TagEntry result = null;
		//
		if (value != null)
		{
			result = new TagEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

package org.openyu.cms.role.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.role.vo.Role;
import org.openyu.cms.role.vo.impl.RoleImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<role>
//	<seq>1</seq>
//	<id>TEST_SITE</id>
//</role>
//--------------------------------------------------
public class RoleXmlAdapter extends BaseXmlAdapterSupporter<RoleXmlAdapter.RoleEntry, Role>
{

	public RoleXmlAdapter()
	{}

	// --------------------------------------------------
	public static class RoleEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public RoleEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public RoleEntry()
		{}
	}

	// --------------------------------------------------
	public Role unmarshal(RoleEntry value) throws Exception
	{
		Role result = null;
		//
		if (value != null)
		{
			result = new RoleImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public RoleEntry marshal(Role value) throws Exception
	{
		RoleEntry result = null;
		//
		if (value != null)
		{
			result = new RoleEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

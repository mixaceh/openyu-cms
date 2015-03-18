package org.openyu.cms.user.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.user.vo.User;
import org.openyu.cms.user.vo.impl.UserImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<site>
//	<seq>1</seq>
//	<id>TEST_SITE</id>
//</site>
//--------------------------------------------------
public class UserXmlAdapter extends BaseXmlAdapterSupporter<UserXmlAdapter.UserEntry, User>
{

	public UserXmlAdapter()
	{}

	// --------------------------------------------------
	public static class UserEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public UserEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public UserEntry()
		{}
	}

	// --------------------------------------------------
	public User unmarshal(UserEntry value) throws Exception
	{
		User result = null;
		//
		if (value != null)
		{
			result = new UserImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public UserEntry marshal(User value) throws Exception
	{
		UserEntry result = null;
		//
		if (value != null)
		{
			result = new UserEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

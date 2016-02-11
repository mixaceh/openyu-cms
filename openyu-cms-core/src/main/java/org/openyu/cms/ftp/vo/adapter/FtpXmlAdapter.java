package org.openyu.cms.ftp.vo.adapter;

import javax.xml.bind.annotation.XmlElement;

import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.cms.ftp.vo.impl.FtpImpl;
import org.openyu.commons.jaxb.adapter.supporter.BaseXmlAdapterSupporter;

// --------------------------------------------------
// reslove: JAXB can’t handle interfaces
// --------------------------------------------------
//<ftp>
//	<seq>1</seq>
//	<id>TEST_FTP</id>
//</ftp>
//--------------------------------------------------
public class FtpXmlAdapter extends BaseXmlAdapterSupporter<FtpXmlAdapter.FtpEntry, Ftp>
{

	public FtpXmlAdapter()
	{}

	// --------------------------------------------------
	public static class FtpEntry
	{
		@XmlElement
		public long seq;

		@XmlElement
		public String id;

		public FtpEntry(long seq, String id)
		{
			this.seq = seq;
			this.id = id;
		}

		public FtpEntry()
		{}
	}

	// --------------------------------------------------
	public Ftp unmarshal(FtpEntry value) throws Exception
	{
		Ftp result = null;
		//
		if (value != null)
		{
			result = new FtpImpl();
			result.setSeq(value.seq);
			result.setId(value.id);
		}
		return result;
	}

	public FtpEntry marshal(Ftp value) throws Exception
	{
		FtpEntry result = null;
		//
		if (value != null)
		{
			result = new FtpEntry(value.getSeq(), value.getId());
		}
		return result;
	}
}

package org.openyu.cms.app.ws.supporter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openyu.cms.app.ws.AppWs;
import org.openyu.commons.ws.supporter.BaseWsSupporter;

public class AppWsSupporter extends BaseWsSupporter implements AppWs
{
	private static transient final Logger log = LogManager.getLogger(AppWsSupporter.class);

	public AppWsSupporter()
	{

	}
}

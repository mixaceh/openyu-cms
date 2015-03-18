package org.openyu.cms.ad.po.bridge;

import org.openyu.cms.ad.po.userType.AdTypeUserType;
import org.openyu.commons.hibernate.search.bridge.supporter.BaseStringBridgeSupporter;

//--------------------------------------------------
//reslove: Hibernate search
//--------------------------------------------------
public class AdTypeBridge extends BaseStringBridgeSupporter
{

	private AdTypeUserType userType = new AdTypeUserType();

	public AdTypeBridge()
	{

	}

	public String objectToString(Object value)
	{
		return userType.marshal(value, null);
	}
}

package org.openyu.cms.module.po.bridge;

import org.openyu.cms.module.po.userType.ContextItemsUserType;
import org.openyu.commons.hibernate.search.bridge.supporter.BaseStringBridgeSupporter;

//--------------------------------------------------
//reslove: Hibernate search
//--------------------------------------------------
public class ContextItemsBridge extends BaseStringBridgeSupporter
{

	private ContextItemsUserType userType = new ContextItemsUserType();

	public ContextItemsBridge()
	{

	}

	public String objectToString(Object value)
	{
		return userType.marshal(value, null);
	}
}

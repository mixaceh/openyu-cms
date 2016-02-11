package org.openyu.cms.module.po.bridge;

import org.openyu.cms.module.po.userType.CatalogItemsUserType;
import org.openyu.commons.hibernate.search.bridge.supporter.BaseStringBridgeSupporter;

//--------------------------------------------------
//reslove: Hibernate search
//--------------------------------------------------
public class CatalogItemsBridge extends BaseStringBridgeSupporter
{

	private CatalogItemsUserType userType = new CatalogItemsUserType();

	public CatalogItemsBridge()
	{

	}

	public String objectToString(Object value)
	{
		return userType.marshal(value, null);
	}
}

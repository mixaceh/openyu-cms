package org.openyu.cms.keyword.po.bridge;

import org.openyu.cms.keyword.po.userType.DictionaryUserType;
import org.openyu.commons.hibernate.search.bridge.supporter.BaseStringBridgeSupporter;

//--------------------------------------------------
//reslove: Hibernate search
//--------------------------------------------------
public class DictionaryBridge extends BaseStringBridgeSupporter
{

	private DictionaryUserType userType = new DictionaryUserType();

	public DictionaryBridge()
	{

	}

	public String objectToString(Object value)
	{
		return userType.marshal(value, null);
	}
}

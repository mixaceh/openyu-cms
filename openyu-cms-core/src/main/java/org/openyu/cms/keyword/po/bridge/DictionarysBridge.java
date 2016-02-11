package org.openyu.cms.keyword.po.bridge;

import org.openyu.cms.keyword.po.userType.StringDictionaryUserType;
import org.openyu.commons.hibernate.search.bridge.supporter.BaseStringBridgeSupporter;

//--------------------------------------------------
//reslove: Hibernate search
//--------------------------------------------------
public class DictionarysBridge extends BaseStringBridgeSupporter {

	private StringDictionaryUserType userType = new StringDictionaryUserType();

	public DictionarysBridge() {

	}

	public String objectToString(Object value) {
		return userType.marshal(value, null);
	}
}

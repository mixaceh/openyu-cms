package org.openyu.cms.site.po.bridge;

import org.openyu.cms.site.po.userType.ModifyTypeUserType;
import org.openyu.commons.hibernate.search.bridge.supporter.BaseStringBridgeSupporter;

//--------------------------------------------------
//reslove: Hibernate search
//--------------------------------------------------
public class ModifyTypeBridge extends BaseStringBridgeSupporter {

	private ModifyTypeUserType userType = new ModifyTypeUserType();

	public ModifyTypeBridge() {

	}

	public String objectToString(Object value) {
		return userType.marshal(value, null);
	}
}

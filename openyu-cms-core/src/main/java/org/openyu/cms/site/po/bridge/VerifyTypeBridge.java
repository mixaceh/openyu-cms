package org.openyu.cms.site.po.bridge;

import org.openyu.cms.site.po.userType.VerifyTypeUserType;
import org.openyu.commons.hibernate.search.bridge.supporter.BaseStringBridgeSupporter;

//--------------------------------------------------
//reslove: Hibernate search
//--------------------------------------------------
public class VerifyTypeBridge extends BaseStringBridgeSupporter {

	private VerifyTypeUserType userType = new VerifyTypeUserType();

	public VerifyTypeBridge() {

	}

	public String objectToString(Object value) {
		return userType.marshal(value, null);
	}
}

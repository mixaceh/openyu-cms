package org.openyu.cms.friendType.po.bridge;

import org.openyu.cms.friendType.po.userType.ActionTypeUserType;
import org.openyu.commons.hibernate.search.bridge.supporter.BaseStringBridgeSupporter;

//--------------------------------------------------
//reslove: Hibernate search
//--------------------------------------------------
/**
 * 友情類型操作類別
 */
public class ActionTypeBridge extends BaseStringBridgeSupporter {

	private ActionTypeUserType userType = new ActionTypeUserType();

	public ActionTypeBridge() {

	}

	public String objectToString(Object value) {
		return userType.marshal(value, null);
	}
}

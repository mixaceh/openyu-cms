package org.openyu.cms.vote.po.bridge;

import org.openyu.cms.vote.po.userType.VoteItemsUserType;
import org.openyu.commons.hibernate.search.bridge.supporter.BaseStringBridgeSupporter;

//--------------------------------------------------
//reslove: Hibernate search
//--------------------------------------------------
public class VoteItemsBridge extends BaseStringBridgeSupporter {

	private VoteItemsUserType userType = new VoteItemsUserType();

	public VoteItemsBridge() {

	}

	public String objectToString(Object value) {
		return userType.marshal(value, null);
	}
}

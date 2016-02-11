package org.openyu.cms.app.aop.supporter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openyu.cms.app.aop.AppAroundAdvice;
import org.openyu.commons.aop.supporter.BaseAroundAdviceSupporter;

public abstract class AppAroundAdviceSupporter extends BaseAroundAdviceSupporter implements AppAroundAdvice {

	private static final long serialVersionUID = -1873112690834439563L;

	private static transient final Logger log = LogManager.getLogger(AppAroundAdviceSupporter.class);

	public AppAroundAdviceSupporter() {
	}
}

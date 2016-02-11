package org.openyu.cms.app.web.freeMarker.supporter;

import org.openyu.cms.app.web.freeMarker.AppDirective;
import org.openyu.commons.freemarker.supporter.BaseWebDirectiveSupporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * app fm標籤
 */
public abstract class AppDirectiveSupporter extends BaseWebDirectiveSupporter
		implements AppDirective {

	private static transient final Logger LOGGER = LoggerFactory
			.getLogger(AppDirectiveSupporter.class);

	public AppDirectiveSupporter() {

	}

}

package org.openyu.cms.app.aop.supporter;

import org.openyu.commons.aop.supporter.BaseAspectSupporter;
import org.openyu.commons.thread.ThreadService;
import org.openyu.commons.thread.anno.DefaultThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppAspectSupporter extends BaseAspectSupporter {

	private static final long serialVersionUID = 6883668407239700707L;

	private static transient final Logger LOGGER = LoggerFactory.getLogger(AppAspectSupporter.class);

	/**
	 * 線程服務
	 */
	@DefaultThreadService
	protected transient ThreadService threadService;

	public AppAspectSupporter() {

	}
}

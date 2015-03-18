package org.openyu.cms.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.core.service.CoreService;
import org.openyu.commons.job.supporter.BaseJobSupporter;

public class CoreServiceImpl extends AppServiceSupporter implements CoreService {

	private static transient final Logger LOGGER = LoggerFactory
			.getLogger(CoreServiceImpl.class);

	public CoreServiceImpl() {
	}

	/**
	 * 在spring 上,利用 init-method="initialize",初始化
	 */
	public void initialize() {

	}

	/**
	 * 排程每日00:00執行
	 */
	public static class Day0000Job extends BaseJobSupporter {
		private transient CoreService coreService;

		public Day0000Job() {
		}

		public CoreService getCoreService() {
			return coreService;
		}

		public void setCoreService(CoreService coreService) {
			this.coreService = coreService;
		}

		public void execute() {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			// --------------------------------------------------
			coreService.day0000();
			// --------------------------------------------------
			stopWatch.stop();
			log(LOGGER, CoreService.class, "day0000", stopWatch);
		}
	}

	/**
	 * 排程每日00:00執行
	 */
	public void day0000() {

	}

	/**
	 * 排程每日00:03執行
	 */
	public static class Day0003Job extends BaseJobSupporter {
		private transient CoreService coreService;

		public Day0003Job() {
		}

		public CoreService getCoreService() {
			return coreService;
		}

		public void setCoreService(CoreService coreService) {
			this.coreService = coreService;
		}

		public void execute() {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			// --------------------------------------------------
			coreService.day0003();
			// --------------------------------------------------
			stopWatch.stop();
			log(LOGGER, CoreService.class, "day0003", stopWatch);
		}
	}

	/**
	 * 排程每日00:03執行
	 */
	public void day0003() {

	}

	/**
	 * 排程每日00:07執行
	 */
	public static class Day0007Job extends BaseJobSupporter {
		private transient CoreService coreService;

		public Day0007Job() {
		}

		public CoreService getCoreService() {
			return coreService;
		}

		public void setCoreService(CoreService coreService) {
			this.coreService = coreService;
		}

		public void execute() {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			// --------------------------------------------------
			coreService.day0007();
			// --------------------------------------------------
			stopWatch.stop();
			log(LOGGER, CoreService.class, "day0007", stopWatch);
		}
	}

	/**
	 * 排程每日00:07執行
	 */
	public void day0007() {

	}
}

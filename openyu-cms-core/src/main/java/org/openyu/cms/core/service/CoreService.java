package org.openyu.cms.core.service;

import org.openyu.cms.app.service.AppService;

/**
 * 核心服務
 * 
 * 1.處理初始化
 * 
 * 2.處理定時儲存
 */
public interface CoreService extends AppService
{

	/**
	 * 排程每日00:00執行
	 */
	void day0000();

	/**
	 * 排程每日00:03執行
	 */

	void day0003();

	/**
	 * 排程每日00:07執行
	 */

	void day0007();

}

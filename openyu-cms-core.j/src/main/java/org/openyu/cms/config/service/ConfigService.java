package org.openyu.cms.config.service;

import java.util.List;

import org.openyu.cms.app.service.AppService;
import org.openyu.cms.config.vo.Config;

public interface ConfigService extends AppService
{

	/**
	 * 查詢設定
	 * 
	 * @return
	 */
	List<Config> findConfig();

	/**
	 * 查詢第一筆設定
	 * 
	 * @return
	 */
	Config findFirstConfig();

	// --------------------------------------------------
	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param configId, DEFAULT
	 * @return
	 */
	Config createConfig(String configId);

}
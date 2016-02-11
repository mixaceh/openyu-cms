package org.openyu.cms.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.service.supporter.AppServiceSupporter;
import org.openyu.cms.config.dao.ConfigDao;
import org.openyu.cms.config.po.ConfigPo;
import org.openyu.cms.config.service.ConfigService;
import org.openyu.cms.config.vo.Config;
import org.openyu.cms.config.vo.ConfigCollector;
import org.openyu.commons.lang.ClassHelper;

public class ConfigServiceImpl extends AppServiceSupporter implements ConfigService
{
	protected transient ConfigCollector configCollector = ConfigCollector.getInstance();

	public ConfigServiceImpl()
	{}

	public ConfigDao getConfigDao()
	{
		return (ConfigDao) getOjDao();
	}

	@Autowired
	@Qualifier("configDao")
	public void setConfigDao(ConfigDao configDao)
	{
		setOjDao(configDao);
	}

	/**
	 * 查詢設定
	 * 
	 * @return
	 */
	public List<Config> findConfig()
	{
		List<ConfigPo> orig = getConfigDao().findConfig();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 查詢第一筆設定
	 * 
	 * @return
	 */
	public Config findFirstConfig()
	{
		ConfigPo orig = getConfigDao().findFirstConfig();
		return ClassHelper.copyProperties(orig);
	}

	/**
	 * 依照靜態資料,建構新的物件,使用clone/或用建構new xxx()
	 * 
	 * @param configId, DEFAULT
	 * @param locale
	 * @param name
	 * @return
	 */
	public Config createConfig(String configId)
	{
		return configCollector.createConfig(configId);
	}

}

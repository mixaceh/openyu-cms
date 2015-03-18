package org.openyu.cms.config.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.config.dao.ConfigDao;
import org.openyu.cms.config.po.ConfigPo;
import org.openyu.cms.config.po.impl.ConfigPoImpl;

public class ConfigDaoImpl extends AppDaoSupporter implements ConfigDao
{
	private static transient final Logger log = LogManager.getLogger(ConfigDaoImpl.class);

	private static final String CONFIG_PO_NAME = ConfigPoImpl.class.getName();

	/**
	 * 查詢設定
	 * 
	 * @return
	 */
	public List<ConfigPo> findConfig()
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(CONFIG_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		return findByHql(null, hql, params);
	}

	/**
	 * 查詢第一筆設定
	 * 
	 * @return
	 */
	public ConfigPo findFirstConfig()
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		//
		hql.append("from ");
		hql.append(CONFIG_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		return findUniqueByHql(hql, params);
	}

}

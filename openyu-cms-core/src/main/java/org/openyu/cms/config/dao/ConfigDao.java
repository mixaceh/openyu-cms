package org.openyu.cms.config.dao;

import java.util.List;

import org.openyu.cms.app.dao.AppDao;
import org.openyu.cms.config.po.ConfigPo;

public interface ConfigDao extends AppDao
{

	/**
	 * 查詢設定
	 * 
	 * @return
	 */
	List<ConfigPo> findConfig();

	/**
	 * 查詢第一筆設定
	 * 
	 * @return
	 */
	ConfigPo findFirstConfig();
}
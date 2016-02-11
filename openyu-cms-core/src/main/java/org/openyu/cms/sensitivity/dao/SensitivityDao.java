package org.openyu.cms.sensitivity.dao;

import java.util.List;
import java.util.Locale;

import org.openyu.cms.sensitivity.po.SensitivityPo;
import org.openyu.commons.dao.OjDao;

public interface SensitivityDao extends OjDao
{

	/**
	 * 查詢敏感詞
	 * @param cacheable
	 * @return
	 */
	public List<SensitivityPo> findSensitivity();

	/**
	 * 查詢群組
	 * 
	 * @param locale
	 * @return
	 */
	List<SensitivityPo> findSensitivity(Locale locale);
	
}
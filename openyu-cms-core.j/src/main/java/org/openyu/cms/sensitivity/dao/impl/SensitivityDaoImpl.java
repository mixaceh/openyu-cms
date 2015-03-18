package org.openyu.cms.sensitivity.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.sensitivity.dao.SensitivityDao;
import org.openyu.cms.sensitivity.po.SensitivityPo;
import org.openyu.cms.sensitivity.po.impl.SensitivityPoImpl;

public class SensitivityDaoImpl extends AppDaoSupporter implements
		SensitivityDao {

	private static transient final Logger log = LogManager
			.getLogger(SensitivityDaoImpl.class);

	private static final String SENSITIVITY_PO_NAME = SensitivityPoImpl.class
			.getName();

	public List<SensitivityPo> findSensitivity() {
		return findSensitivity(null);
	}

	public List<SensitivityPo> findSensitivity(Locale locale) {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		//
		hql.append("from ");
		hql.append(SENSITIVITY_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		if (locale != null) {
			hql.append("and locale=:locale ");
			params.put("locale", locale);
		}
		//
		return findByHql(null, hql.toString(), params);
	}

}

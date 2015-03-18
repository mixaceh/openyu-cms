package org.openyu.cms.ftp.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openyu.cms.app.dao.supporter.AppDaoSupporter;
import org.openyu.cms.ftp.dao.FtpDao;
import org.openyu.cms.ftp.po.FtpPo;
import org.openyu.cms.ftp.po.impl.FtpPoImpl;
import org.openyu.cms.ftp.vo.Ftp;
import org.openyu.commons.dao.inquiry.Inquiry;
import org.openyu.commons.lang.StringHelper;

public class FtpDaoImpl extends AppDaoSupporter implements FtpDao
{
	private static transient final Logger log = LogManager.getLogger(FtpPoImpl.class);

	private static final String FTP_PO_NAME = FtpPoImpl.class.getName();

	public List<FtpPo> findFtp()
	{
		return findFtp(null);
	}

	public List<FtpPo> findFtp(Locale locale)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		//
		hql.append("from ");
		hql.append(FTP_PO_NAME + " ");
		hql.append("where 1=1 ");
		//
		return findByHql(null, locale, hql, params);
	}

	/**
	 * 查詢條件
	 * 
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<FtpPo> findFtp(Locale locale, Ftp searcher)
	{
		return findFtp(null, locale, searcher);
	}

	/**
	 * 查詢條件
	 * 
	 * @param inquiry
	 * @param locale
	 * @param searcher
	 * @return
	 */
	public List<FtpPo> findFtp(Inquiry inquiry, Locale locale, Ftp searcher)
	{
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		//
		StringBuilder hql = new StringBuilder();
		hql.append("from ");
		hql.append(FTP_PO_NAME + " ");
		hql.append("where 1=1 ");
		
		if (searcher != null)
		{
			//seq
			if (searcher.getSeq() > 0)
			{
				hql.append("and seq=:seq ");
				params.put("seq", searcher.getSeq());
			}
			
			//id
			if (StringHelper.notBlank(searcher.getId()))
			{
				hql.append("and lower(id) like lower(:id) ");
				params.put("id", "%" + searcher.getId() + "%");
			}

			//name
			if (StringHelper.notBlank(searcher.getName()))
			{
				hql.append("and lower(names) like lower(:name)");
				params.put("name", "%" + searcher.getName() + "%");
			}
		}
		//
		return findByHql(inquiry, locale, hql, params);
	}

}

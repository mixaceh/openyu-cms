package org.openyu.cms.user.vo;

import org.openyu.cms.site.vo.Site;
import org.openyu.commons.bean.BaseBean;

/**
 * 使用者session,web用
 */
public interface UserSession extends BaseBean
{
	String KEY = UserSession.class.getName();

	User getUser();

	void setUser(User user);

	/**
	 * 取得目前使用者seq
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	long getUserSeq();

	/**
	 * 取得目前使用者id
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	String getUserId();

	/**
	 * 取得目前使用者名稱
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	String getUserName();

	FrontUser getFrontUser();

	void setFrontUser(FrontUser frontUser);

	/**
	 * 取得目前前台使用者seq
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	long getFrontUserSeq();

	/**
	 * 取得目前前台使用者id
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	String getFrontUserId();

	/**
	 * 取得目前前台使用者名稱
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	String getFrontUserName();

	Site getSite();

	void setSite(Site site);

	/**
	 * 取得目前網站seq
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	long getSiteSeq();

	/**
	 * 取得目前網站id
	 * 
	 * 只是為了簡化寫法
	 * 
	 * @return
	 */
	String getSiteId();

}

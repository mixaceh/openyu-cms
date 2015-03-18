package org.openyu.cms.app.web.freeMarker;

import org.openyu.commons.freemarker.BaseWebDirective;

/**
 * app fm標籤
 */
public interface AppDirective extends BaseWebDirective
{
	/**
	 * 傳入參數: 網站seq
	 */
	String PARAM_SITE_SEQ = "site_seq";

	/**
	 * 傳入參數: 網站id
	 */
	String PARAM_SITE_ID = "site_id";

}

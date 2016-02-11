package org.openyu.cms.app.service.supporter;

import java.util.Locale;

import org.openyu.cms.app.log.AppLogEntity;
import org.openyu.cms.app.service.AppLogService;
import org.openyu.cms.user.vo.User;
import org.openyu.commons.service.supporter.LogServiceSupporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogServiceSupporter extends LogServiceSupporter implements AppLogService {

	private static final long serialVersionUID = 4647698815973054546L;

	private static transient final Logger LOGGER = LoggerFactory.getLogger(AppLogServiceSupporter.class);

	public AppLogServiceSupporter() {
	}

	// --------------------------------------------------

	/**
	 * 紀錄使用者相關資訊
	 * 
	 * @param user
	 * @param entity
	 */
	protected void recordUser(User user, AppLogEntity entity) {
		if (user != null) {
			entity.setUserSeq(user.getSeq());
			entity.setUserId(user.getId());
			// 來自於action.getLocale()
			Locale sessionLocale = user.getSessionLocale();
			entity.setUserName(user.getName(sessionLocale));

			// 來自於action.request
			entity.setClientIp(user.getRequestClientIp());
			entity.setClientPort(user.getRequestClientPort());
			entity.setServerIp(user.getRequestServerIp());
			entity.setServerPort(user.getRequestServerPort());
			entity.setUri(user.getRequestUri());
		}
	}
}

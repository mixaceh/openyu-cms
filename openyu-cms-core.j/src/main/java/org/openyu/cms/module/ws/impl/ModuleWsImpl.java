package org.openyu.cms.module.ws.impl;

import java.util.List;

import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.openyu.cms.app.ws.supporter.AppWsSupporter;
import org.openyu.cms.module.service.ModuleService;
import org.openyu.cms.module.vo.Module;
import org.openyu.cms.module.ws.ModuleWs;

@WebService(endpointInterface = "org.openyu.cms.module.ws.ModuleWs")
public class ModuleWsImpl extends AppWsSupporter implements ModuleWs
{

	@Autowired
	@Qualifier("moduleService")
	private ModuleService moduleService;

	public ModuleWsImpl()
	{}

	/**
	 * 查詢是否有效模組
	 * 
	 * @param valid
	 * @return
	 */

	public List<Module> findModule(boolean valid)
	{
		return moduleService.findModule(valid);
	}

	/**
	 * 查詢預設模組
	 */
	public Module findDefaultModule()
	{
		return moduleService.findDefaultModule();
	}
}

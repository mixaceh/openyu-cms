package org.openyu.cms.module.ws;

import java.util.LinkedHashSet;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.openyu.cms.app.ws.AppWs;
import org.openyu.cms.module.vo.Module;
import org.openyu.cms.module.vo.impl.ModuleImpl;

@WebService
@XmlSeeAlso({ ModuleImpl.class, LinkedHashSet.class })
public interface ModuleWs extends AppWs
{

	/**
	 * 查詢是否有效模組
	 * 
	 * @param valid
	 * @return
	 */
	//http://localhost:8080/cms/web/service/module/findModule?valid=true
	List<Module> findModule(@WebParam(name = "valid")
	boolean valid);

	/**
	 * 查詢預設模組
	 * 
	 * @return
	 */
	Module findDefaultModule();

}

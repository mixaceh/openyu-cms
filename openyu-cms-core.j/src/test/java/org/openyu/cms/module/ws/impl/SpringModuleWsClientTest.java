package org.openyu.cms.module.ws.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.openyu.cms.module.vo.Module;
import org.openyu.cms.module.ws.ModuleWs;

public class SpringModuleWsClientTest
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{}

	@Before
	public void setUp() throws Exception
	{}

	@After
	public void tearDown() throws Exception
	{}

	@Test
	public void server() throws InterruptedException
	{
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		//
		String address = "http://localhost:8080/cms/web/service/module";
		factory.setAddress(address);
		//
		factory.setServiceClass(ModuleWs.class);
		factory.setServiceBean(new ModuleWsImpl());
		//
		factory.create();
		//
		Thread.sleep(30 * 1000);
	}

	@Test
	public void findList()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			new String[] { "org/openyu/cms/module/applicationContext-module-client.xml",//
			});
		//
		ModuleWs service = (ModuleWs) applicationContext.getBean("moduleWs");
		//
		System.out.println(service);
		assertNotNull(service);
		//
		List<Module> result = service.findModule(true);
		System.out.println(result);
	}
}

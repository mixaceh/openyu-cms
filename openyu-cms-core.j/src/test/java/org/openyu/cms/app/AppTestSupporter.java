package org.openyu.cms.app;

import static org.junit.Assert.assertNotNull;

import org.apache.velocity.app.VelocityEngine;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.openyu.commons.junit.supporter.BaseTestSupporter;

import freemarker.template.Configuration;

public class AppTestSupporter extends BaseTestSupporter {
	// --------------------------------------------------
	// 本系統共用的service
	// --------------------------------------------------
	/**
	 * db
	 */
	protected static HibernateTransactionManager txManager;

	protected static SessionFactory sessionFactory;

	/**
	 * log db
	 */
	protected static HibernateTransactionManager logTxManager;

	protected static SessionFactory logSessionFactory;

	protected static VelocityEngine velocityEngine;

	protected static Configuration freeMarkerConfiguration;

	// ---------------------------------------------------
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
				"applicationContext-init.xml",//
				"META-INF/applicationContext-commons-core.xml",//
				"applicationContext-message.xml",//
				"applicationContext-database.xml",//
				"applicationContext-database-log.xml",//
				"applicationContext-cxf.xml",//
				"applicationContext-schedule.xml",//
				"org/openyu/cms/app/applicationContext-app.xml",//
		});
		// ---------------------------------------------------
		initialize();
		// ---------------------------------------------------
	}

	protected static void initialize() {
		txManager = (HibernateTransactionManager) applicationContext
				.getBean("txManager");
		sessionFactory = (SessionFactory) applicationContext
				.getBean("sessionFactory");
		logTxManager = (HibernateTransactionManager) applicationContext
				.getBean("logTxManager");
		logSessionFactory = (SessionFactory) applicationContext
				.getBean("logSessionFactory");
		//
		velocityEngine = (VelocityEngine) applicationContext
				.getBean("velocityEngine");
		freeMarkerConfiguration = (Configuration) applicationContext
				.getBean("freeMarkerConfiguration");
	}

	// --------------------------------------------------

	public static class BeanTest extends AppTestSupporter {
		@Test
		public void txManager() {
			System.out.println(txManager);
			assertNotNull(txManager);
		}

		@Test
		public void sessionFactory() {
			System.out.println(sessionFactory);
			assertNotNull(sessionFactory);
		}

		@Test
		public void logTxManager() {
			System.out.println(logTxManager);
			assertNotNull(logTxManager);
		}

		@Test
		public void logSessionFactory() {
			System.out.println(logSessionFactory);
			assertNotNull(logSessionFactory);
		}
	}
}

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.openyu.cms.app.dao.AppLogDao;
import org.openyu.cms.app.service.AppLogService;
import org.openyu.commons.dao.supporter.OjDaoSupporter;
import org.openyu.commons.junit.supporter.BaseTestSupporter;
import org.openyu.commons.service.BaseLogService;
import org.openyu.commons.service.QueueService;
import org.openyu.commons.thread.ThreadHelper;

public class ApplicationContextDatabaseLogTest extends BaseTestSupporter {

	private static ApplicationContext applicationContext;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
				"applicationContext-init.xml",//
				"META-INF/applicationContext-commons-core.xml",//
				"applicationContext-database-log.xml",//
		});
	}

	@Test
	public void logDataSource() throws Exception {
		DataSource bean = (DataSource) applicationContext
				.getBean("logDataSource");
		System.out.println(bean);
		assertNotNull(bean);
		//
		System.out.println("connection: " + bean.getConnection());
		System.out.println("autoCommit: "
				+ bean.getConnection().getAutoCommit());
		System.out.println("transactionIsolation: "
				+ bean.getConnection().getTransactionIsolation());
	}

	@Test
	public void logSessionFactory() throws Exception {
		SessionFactory bean = (SessionFactory) applicationContext
				.getBean("logSessionFactory");
		System.out.println(bean);
		assertNotNull(bean);
		//
		Session session = bean.openSession();
		session.doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection: " + connection);
				System.out.println("autoCommit: " + connection.getAutoCommit());
				System.out.println("transactionIsolation: "
						+ connection.getTransactionIsolation());
			}
		});
	}

	@Test
	public void logTxAdvice() {
		TransactionInterceptor bean = (TransactionInterceptor) applicationContext
				.getBean("logTxAdvice");
		System.out.println(bean);
		assertNotNull(bean);
	}

	@Test
	public void logTxManager() {
		HibernateTransactionManager bean = (HibernateTransactionManager) applicationContext
				.getBean("logTxManager");
		System.out.println(bean);
		assertNotNull(bean);
	}

	@Test
	public void logOjDaoSupporter() {
		OjDaoSupporter bean = (OjDaoSupporter) applicationContext
				.getBean("logOjDaoSupporter");
		System.out.println(bean);
		assertNotNull(bean);
	}

	@Test
	public void appLogDaoSupporter() {
		AppLogDao bean = (AppLogDao) applicationContext
				.getBean("appLogDaoSupporter");
		System.out.println(bean);
		assertNotNull(bean);
	}

	@Test
	public void baseLogServiceSupporter() {
		BaseLogService bean = (BaseLogService) applicationContext
				.getBean("baseLogServiceSupporter");
		System.out.println(bean);
		assertNotNull(bean);
	}

	@Test
	public void appLogServiceSupporter() {
		AppLogService bean = (AppLogService) applicationContext
				.getBean("appLogServiceSupporter");
		System.out.println(bean);
		assertNotNull(bean);
	}

	@Test
	public void logQueueService() {
		QueueService bean = (QueueService) applicationContext
				.getBean("logQueueService");
		System.out.println(bean);
		assertNotNull(bean);
		//
		ThreadHelper.sleep(3 * 1000);
		BeanDefinitionRegistry factory = (BeanDefinitionRegistry) applicationContext
				.getAutowireCapableBeanFactory();
		factory.removeBeanDefinition("logQueueService");
		ThreadHelper.sleep(3 * 1000);
	}
}

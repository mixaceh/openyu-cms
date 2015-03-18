import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.openyu.commons.junit.supporter.BaseTestSupporter;

public class ApplicationContextTest extends BaseTestSupporter {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		beg = System.currentTimeMillis();
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		end = System.currentTimeMillis();
	}

	@Test
	public void applicationContext() {
		System.out.println(applicationContext);
	}

	@Test
	public void getResource() {
		Resource resource = applicationContext
				.getResource("applicationContext.xml");
		System.out.println(resource + " exist: " + resource.exists());
		//
		resource = applicationContext.getResource("WEB-INF/web.xml");
		System.out.println(resource + " exist: " + resource.exists());
	}

	@Test
	public void beans() {
		printBeans();
	}
}

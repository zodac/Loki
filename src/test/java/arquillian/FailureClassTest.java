package arquillian;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import daos.FailureClassDAO;
import entities.FailureClass;

@RunWith(Arquillian.class)
public class FailureClassTest {
	private static final int FAILURE_CLASS_TEST_ID = -1;
	private static final String FAILURE_CLASS_TEST_DESC = "TEST FAILURE";
	
	@EJB
	private static FailureClassDAO fcDAO;
	private static FailureClass failureClass;	
	
	@Deployment(name="myJBOSS")
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, "daos", "jpas", "services", "webservices")
				.addAsResource("META-INF/persistence.xml","META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}	
	
	@BeforeClass
	public static void setUp(){
		failureClass = new FailureClass();
		failureClass.setFailureClass(FAILURE_CLASS_TEST_ID);
		failureClass.setDescription(FAILURE_CLASS_TEST_DESC);
		fcDAO.addFailureClass(failureClass);
	}
	
	@AfterClass
	public static void cleanUp(){
		fcDAO.removeFailureClass(failureClass);
	}
	
	@Test
	public void testFailureClass(){
		FailureClass fc = fcDAO.getFailureClass(FAILURE_CLASS_TEST_ID);
		assertEquals(FAILURE_CLASS_TEST_DESC, fc.getDescription());
	}	
}
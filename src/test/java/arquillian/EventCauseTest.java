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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import daos.EventCauseDAO;
import entities.EventCause;
import entities.EventCausePK;

@Ignore
@RunWith(Arquillian.class)
public class EventCauseTest {
	private static final int EVENT_CAUSE_TEST_EVENT_ID = -1;
	private static final int EVENT_CAUSE_TEST_CAUSE_CODE = -2;
	private static final String EVENT_CAUSE_TEST_DESC = "TEST EVENT CAUSE";
	
	@EJB
	private static EventCauseDAO ecDAO;
	private static EventCause eventCause;
	private static EventCausePK epk;
	
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
		epk = new EventCausePK();
		epk.setA_Event_ID(EVENT_CAUSE_TEST_EVENT_ID);
		epk.setB_Cause_Code(EVENT_CAUSE_TEST_CAUSE_CODE);
		
		eventCause = new EventCause();
		eventCause.setId(epk);
		eventCause.setDescription(EVENT_CAUSE_TEST_DESC);
		ecDAO.addEventCause(eventCause);
	}
	
	@AfterClass
	public static void cleanUp(){
		ecDAO.removeEventCause(epk);
	}
	
	@Test
	public void testFailureClass(){
		EventCause ec = ecDAO.getEventCause(epk);
		assertEquals(EVENT_CAUSE_TEST_DESC, ec.getDescription());
	}	
}
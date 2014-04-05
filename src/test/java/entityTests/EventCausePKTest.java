package entityTests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import entities.EventCausePK;

public class EventCausePKTest {
	private static int EVENT_CAUSE_TEST_EVENT_ID = -1;
	private static int EVENT_CAUSE_TEST_CAUSE_CODE = -2;
	private static EventCausePK epk;
	
	@BeforeClass
	public static void setUp(){
		epk = new EventCausePK();
	}

	@Test
	public void testSetEventCausePK() {
		epk.setA_Event_ID(EVENT_CAUSE_TEST_EVENT_ID);
		epk.setB_Cause_Code(EVENT_CAUSE_TEST_CAUSE_CODE);
		assertEquals(EVENT_CAUSE_TEST_EVENT_ID, epk.getA_Event_ID());
		assertEquals(EVENT_CAUSE_TEST_CAUSE_CODE, epk.getB_Cause_Code());
	}
	
	@Test
	public void testEquals(){
		assertEquals(true, epk.equals(epk));
	}
	
	@Test
	public void testXHashCode(){
		assertEquals(false, epk.hashCode()==new EventCausePK().hashCode());
	}
}

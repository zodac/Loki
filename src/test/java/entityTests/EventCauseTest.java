package entityTests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import entities.EventCause;
import entities.EventCausePK;

public class EventCauseTest {
	private static int EVENT_CAUSE_TEST_EVENT_ID = -1;
	private static int EVENT_CAUSE_TEST_CAUSE_CODE = -2;
	private static String EVENT_CAUSE_TEST_DESCRIPTION = "Test Event/Cause";
	private static EventCause eventCause;
	
	@BeforeClass
	public static void setUp(){
		eventCause = new EventCause();
	}

	@Test
	public void testSetEventCause() {
		EventCausePK epk = new EventCausePK();
		epk.setA_Event_ID(EVENT_CAUSE_TEST_EVENT_ID);
		epk.setB_Cause_Code(EVENT_CAUSE_TEST_CAUSE_CODE);
		eventCause.setId(epk);
		eventCause.setDescription(EVENT_CAUSE_TEST_DESCRIPTION);
		assertEquals(EVENT_CAUSE_TEST_EVENT_ID, eventCause.getId().getA_Event_ID());
		assertEquals(EVENT_CAUSE_TEST_CAUSE_CODE, eventCause.getId().getB_Cause_Code());
		assertEquals(EVENT_CAUSE_TEST_DESCRIPTION, eventCause.getDescription());
	}
}

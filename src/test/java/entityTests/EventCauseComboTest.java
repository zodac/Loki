package entityTests;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.BeforeClass;
import org.junit.Test;

import queryEntities.EventIdCauseCodeCombo;

public class EventCauseComboTest {
	private static BigInteger EVENT_CAUSE_COMBO_TEST_OCCURRENCES = BigInteger.valueOf(-1);
	private static int EVENT_CAUSE_COMBO_TEST_EVENT_ID = -2;
	private static int EVENT_CAUSE_COMBO_TEST_CAUSE_CODE = -3;

	private static EventIdCauseCodeCombo eventCauseCombo;
	
	@BeforeClass
	public static void setUp(){
		eventCauseCombo = new EventIdCauseCodeCombo();
	}

	@Test
	public void testSetUEType() {
		eventCauseCombo.setCause_Code(EVENT_CAUSE_COMBO_TEST_CAUSE_CODE);
		eventCauseCombo.setEvent_ID(EVENT_CAUSE_COMBO_TEST_EVENT_ID);
		eventCauseCombo.setOccurrences(EVENT_CAUSE_COMBO_TEST_OCCURRENCES);
		assertEquals(EVENT_CAUSE_COMBO_TEST_OCCURRENCES, eventCauseCombo.getOccurrences());
		assertEquals(EVENT_CAUSE_COMBO_TEST_CAUSE_CODE, eventCauseCombo.getCause_Code());
		assertEquals(EVENT_CAUSE_COMBO_TEST_EVENT_ID, eventCauseCombo.getEvent_ID());
	}
}

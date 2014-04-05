package entityTests;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import entities.CallFailure;
import entities.EventCause;
import entities.FailureClass;
import entities.MccMnc;
import entities.UEType;

public class CallFailureTest {
	private static int CALL_FAILURE_TEST_CELL_ID = -1;
	private static Date CALL_FAILURE_TEST_DATE = new Date();
	private static int CALL_FAILURE_TEST_DURATION = -2;
	private static EventCause CALL_FAILURE_TEST_EVENT_CAUSE = new EventCause();
	private static FailureClass CALL_FAILURE_TEST_FAILURE_CLASS = new FailureClass();
	private static long CALL_FAILURE_TEST_HIER321_ID = -3;
	private static long CALL_FAILURE_TEST_HIER32_ID = -4;
	private static long CALL_FAILURE_TEST_HIER3_ID = -5;
	private static int CALL_FAILURE_TEST_ID = -6;
	private static BigInteger CALL_FAILURE_TEST_IMSI = BigInteger.valueOf(-7);
	private static MccMnc CALL_FAILURE_TEST_MCC_MNC = new MccMnc();
	private static String CALL_FAILURE_TEST_NE_VERSION = "Test NE Version";
	private static UEType CALL_FAILURE_TEST_UETYPE = new UEType();
	private static CallFailure callFailure;
	
	@BeforeClass
	public static void setUp(){
		callFailure = new CallFailure();
	}

	@Test
	public void testSetCallFailure() {
		callFailure.setCellId(CALL_FAILURE_TEST_CELL_ID);
		callFailure.setDate(CALL_FAILURE_TEST_DATE);
		callFailure.setDuration(CALL_FAILURE_TEST_DURATION);
		callFailure.setEventCause(CALL_FAILURE_TEST_EVENT_CAUSE);
		callFailure.setFailureClass(CALL_FAILURE_TEST_FAILURE_CLASS);
		callFailure.setHier321Id(CALL_FAILURE_TEST_HIER321_ID);
		callFailure.setHier32Id(CALL_FAILURE_TEST_HIER32_ID);
		callFailure.setHier3Id(CALL_FAILURE_TEST_HIER3_ID);
		callFailure.setId(CALL_FAILURE_TEST_ID);
		callFailure.setImsi(CALL_FAILURE_TEST_IMSI);
		callFailure.setMccMnc(CALL_FAILURE_TEST_MCC_MNC);
		callFailure.setNEVersion(CALL_FAILURE_TEST_NE_VERSION);
		callFailure.setUEType(CALL_FAILURE_TEST_UETYPE);
		assertEquals(CALL_FAILURE_TEST_CELL_ID, callFailure.getCellId());
		assertEquals(CALL_FAILURE_TEST_DATE, callFailure.getDate());
		assertEquals(CALL_FAILURE_TEST_DURATION, callFailure.getDuration());
		assertEquals(CALL_FAILURE_TEST_EVENT_CAUSE, callFailure.getEventCause());
		assertEquals(CALL_FAILURE_TEST_FAILURE_CLASS, callFailure.getFailureClass());
		assertEquals(CALL_FAILURE_TEST_HIER321_ID, callFailure.getHier321Id());
		assertEquals(CALL_FAILURE_TEST_HIER32_ID, callFailure.getHier32Id());
		assertEquals(CALL_FAILURE_TEST_HIER3_ID, callFailure.getHier3Id());
		assertEquals(CALL_FAILURE_TEST_ID, callFailure.getId());
		assertEquals(CALL_FAILURE_TEST_IMSI, callFailure.getImsi());
		assertEquals(CALL_FAILURE_TEST_MCC_MNC, callFailure.getMccMnc());
		assertEquals(CALL_FAILURE_TEST_NE_VERSION, callFailure.getNEVersion());
		assertEquals(CALL_FAILURE_TEST_UETYPE, callFailure.getUEtype());
	}
}
